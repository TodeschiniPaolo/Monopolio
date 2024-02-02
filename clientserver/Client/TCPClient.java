//Importo i package necessari 
import java.net.*; 
import java.io.*; 
  
   public class TCPClient { 
    int soldi = 1500;
    boolean first = true;
    String nome;
     public void start()throws IOException { 
       //Connessione della Socket con il Server 
       Socket socket = new Socket("localhost", 7777); 
  
       //Stream di byte da passare al Socket 
       DataOutputStream os = new DataOutputStream(socket.getOutputStream()); 
       DataInputStream is = new DataInputStream(socket.getInputStream()); 
       BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
       BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
       System.out.print("Per disconnettersi dal Server scrivere: QUIT\n"); 
       
       //Ciclo infinito per inserimento testo del Client 
       while (true) 
	{ 
    if(first){
      String receivedMessage = in.readLine();
      System.out.print(receivedMessage);
      nome = stdIn.readLine();
      os.writeBytes(nome + "\n");
     // System.out.print("");
      //System.out.println("Benvenuto "+nome);
      first = false;
    }
    String receivedMessage = in.readLine();
        
        // System.out.print(nome+" hai "+soldi+" \n Cosa vuoi fare: "); 
         String userInput = stdIn.readLine(); 
         if (userInput.equals("QUIT")) 
		break; 
os.writeBytes(userInput + "\n");  
      switch(userInput){
        case "via":
        System.out.println(receivedMessage);
          break;
        case "compra":
          System.out.println(receivedMessage);
          break;
        case "elenco":
        
          
        break;
        default :
          break; 
      }
      System.out.println(receivedMessage);
       } 
  
       //Chiusura dello Stream e del Socket 
       os.close(); 
       is.close(); 
       socket.close(); 
     } 
     public static void main (String[] args) throws Exception { 
       TCPClient tcpClient = new TCPClient(); 
       tcpClient.start(); 
     } 
   } 
