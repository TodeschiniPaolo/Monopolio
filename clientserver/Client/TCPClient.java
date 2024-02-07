//Importo i package necessari 
import java.net.*; 
import java.io.*; 
  
   public class TCPClient { 
    int soldi = 1500;
    boolean first = true;
    String nome;
String receivedMessage;

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
      receivedMessage = in.readLine();
      System.out.print(receivedMessage);
      nome = stdIn.readLine();
      os.writeBytes(nome + "\n");
     // System.out.print("");
      //System.out.println("Benvenuto "+nome);
      first = false;
    }
        
      receivedMessage = in.readLine();
      System.out.print(receivedMessage);
         String userInput = stdIn.readLine(); 
	os.writeBytes(userInput + "\n");  
         if (userInput.equals("QUIT")) 
		break; 
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
