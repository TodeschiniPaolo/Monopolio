//Importo i package
import java.net.*;
import java.io.*;

   //Creazione di una classe per il Multrithreading
   class ServerThread extends Thread {
     private Socket socket;
     public String userInput;
     public ServerThread (Socket socket,Socket[] array) {
       this.socket = socket;
       System.out.println("  Stato    Tipo Richiesta  Porta Server  Porta Client  Indirizzo Cliernt\n");
     }

     //esecuzione del Thread sul Socket
     public void run() {
       try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        userInput = reader.readLine();

       }
       catch (IOException e) {
         System.out.println("IOException: " + e);
       }
     }
   }

   //Classe Server per attivare la Socket
/*   public class TCPParallelServer {
     public void start() throws Exception {
       ServerSocket serverSocket = new ServerSocket(7777);

       //Ciclo infinito di ascolto dei Client
       while(true) {
         System.out.println("In attesa di chiamate dai Client... ");
         Socket socket = serverSocket.accept();
         System.out.println("Ho ricevuto una chiamata di apertura da:\n" + socket);
         ServerThread serverThread = new ServerThread(socket);
         serverThread.start();
       }
     }

     public static void main (String[] args) throws Exception {
       TCPParallelServer tcpServer = new TCPParallelServer();
       tcpServer.start();
     }
   }
*/