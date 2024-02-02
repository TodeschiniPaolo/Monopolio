import java.io.*;
import java.net.Socket;
public class SingleServerMng extends Thread{
    private Socket socket;
    public SingleServerMng(Socket socket) {
        this.socket = socket;
        start();
    }
    public void run() {
        
        boolean first = true;
        giocatore g = new giocatore();
        try {
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            
            out.println("inserisci il tuo nome: ");
            while(true){
                //os.writeBytes("server dice: "+"\n");
              String str = in.readLine();
              if(str.equals("QUIT")){
                System.out.println("Client disconnesso");
                break;
              }
              //.println("Hai detto: " + str);
              System.out.println("Echoing: " + str);
              if(first){
                g.setNome(str);
                out.println("benvenuto: "+str);
                first = false;}
                else{
                 switch(str){
                    case "via":
                        g.via();
                        out.println("hai "+g.soldi+" \n Cosa vuoi fare: ");
                      break;
                    case "compra":
                        out.println("cosa:");
                        String cosa = in.readLine();
                        switch (cosa) {
                          case "proprieta":
                            
                            break;
                          case "casa":
                            
                            break;
                          default:
                            break;
                        }
                      break;
                    case "elenco":
                    
                      
                    break;
                    case "conto":
                        out.println("hai "+g.soldi);
                        break;
                    default :
                      out.println("comando non valido");
                      break; 
                  }   }

            }
            //out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
