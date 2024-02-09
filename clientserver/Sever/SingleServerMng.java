import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SingleServerMng extends Thread{
    private Socket socket;
	private TCPParallelServer tcpServer;
    public SingleServerMng(Socket socket, TCPParallelServer tcpServer) {
        this.socket = socket;
		this.tcpServer = tcpServer;
        start();
    }
    public int quantecasa(String nomeProprieta){
		return tcpServer.proprieta.get(nomeProprieta).getNumeroCase();
	}
    public void run() {
        
        boolean first = true;
		int azione =0;
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
                out.println("benvenuto: "+str + " cosa vuoi fare ");
                first = false;}
                else{
                 switch(str){
                    case "via":
                        g.via();
                        out.println("hai "+g.soldi +" cosa vuoi fare ");
                      break;
                    case "compra":
						azione =0;
                        out.println("cosa:");
                        String cosa = in.readLine();
                        switch (cosa) {
                          case "proprieta":
                      out.println("nome proprieta da acquistare ");
                            azione =1;
                            break;
                          case "casa":
                      out.println("nome proprieta su cui costruire ");
                            azione =2;
                            break;
                          default:
                      out.println("comando compra non valido");
                            break;
                        }
						if (azione !=0)
						{
                        String nomeProprieta = in.readLine();
							if (tcpServer.proprieta.get(nomeProprieta) != null)
							{
								if (azione == 1)
								{
									if (tcpServer.proprieta.get(nomeProprieta).getProprietario() == null)
									{
										if(g.soldi > tcpServer.proprieta.get(nomeProprieta).getCosto())
										{
											g.soldi = g.soldi - tcpServer.proprieta.get(nomeProprieta).getCosto();
											out.println("Comprato "+ nomeProprieta +" cosa vuoi fare ");
											tcpServer.proprieta.get(nomeProprieta).setProprietario(g.getNome());
										}
										else
										{
											out.println("Non hai abbastanza soldi per comprare "+ nomeProprieta +" cosa vuoi fare ");
										}
									}
									else
									{
										out.println("Posseduta "+ nomeProprieta +" cosa vuoi fare ");
									}
								}
								else
								{	// al momento solo 2
									//if (possiedeproprieta(proprieta,g.getNome()))
                 					 if (tcpServer.proprieta.get(nomeProprieta).getProprietario() == g.getNome())
									{
										if (tcpServer.proprieta.get(nomeProprieta).getNumeroCase() < 5 && tcpServer.proprieta.get(nomeProprieta).getColore() != "stazione" && tcpServer.proprieta.get(nomeProprieta).getColore() != "societa")
										{
											if (g.soldi > tcpServer.proprieta.get(nomeProprieta).getCostoCasa())
											{
												g.soldi = g.soldi - tcpServer.proprieta.get(nomeProprieta).getCostoCasa();
												tcpServer.proprieta.get(nomeProprieta).costruisciCasa();
												out.println("Costruito "+ quantecasa(nomeProprieta) +" cosa vuoi fare ");
											}
											else
											{
												out.println("Mancano soldi Costruzioni "+ nomeProprieta +" cosa vuoi fare ");
											}
										}
										else
										{
											out.println("MAX Costruzioni "+ nomeProprieta +" cosa vuoi fare ");
										}
									}
									else
									{
										out.println("Non posseduta "+ nomeProprieta +" cosa vuoi fare ");
									}
								}
							}
							else
							{
								out.println("Non ESISTE "+ nomeProprieta +" cosa vuoi fare ");
							}
						}
                      break;
                    case "elenco":
						Iterator<Entry<String, Proprieta>> it = tcpServer.proprieta.entrySet().iterator();
						
						while (it.hasNext()) {
							Map.Entry<String, Proprieta> entry = (Map.Entry<String, Proprieta>)it.next();
					 
							if (tcpServer.proprieta.get(entry.getKey()).getProprietario() == g.getNome())
							{	
								out.println("Proprieta posseduta " + entry.getKey()+ " costo "+ tcpServer.proprieta.get(entry.getKey()).getCosto() + " case "+ tcpServer.proprieta.get(entry.getKey()).getNumeroCase());
							}


						}

						it = tcpServer.proprieta.entrySet().iterator();
 
						while (it.hasNext()) {
							Map.Entry<String, Proprieta> entry = (Map.Entry<String, Proprieta>)it.next();
					 
							if (tcpServer.proprieta.get(entry.getKey()).getProprietario() != g.getNome())
							{	
								out.println("Proprieta NON posseduta " + entry.getKey()+ " costo "+ tcpServer.proprieta.get(entry.getKey()).getCosto());
							}
						}
						out.println("ENDELENCO");
                        out.println("hai "+g.soldi +" cosa vuoi fare ");
                      
                    break;
                    case "conto":
                        out.println("hai "+g.soldi +" cosa vuoi fare ");
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
