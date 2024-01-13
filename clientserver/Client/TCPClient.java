//Importo i package necessari 
import java.net.*; 
import java.io.*; 
  
   public class TCPClient { 
    int soldi = 1500;
     public void start()throws IOException { 
       //Connessione della Socket con il Server 
       Socket socket = new Socket("localhost", 7777); 
  
       //Stream di byte da passare al Socket 
       DataOutputStream os = new DataOutputStream(socket.getOutputStream()); 
       DataInputStream is = new DataInputStream(socket.getInputStream()); 
       BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); 
       System.out.print("Per disconnettersi dal Server scrivere: QUIT\n"); 
  
       //Ciclo infinito per inserimento testo del Client 
       while (true) 
	{ 
         System.out.print("hai "+soldi+" /nCosa vuoi fare: "); 
         String userInput = stdIn.readLine(); 
         if (userInput.equals("QUIT")) 
		break; 
	os.writeBytes(userInput + '\n');  
      switch(userInput){
        case "via":
          soldi = soldi + 200;
          break;
        case "compra":
        System.out.print("cosa:");
        String cosa = stdIn.readLine();
        
          break;
        case "elenco":
          
        break;
      }
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
