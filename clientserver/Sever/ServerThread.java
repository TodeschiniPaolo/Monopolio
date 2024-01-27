//Importo i package
import java.net.*;
import java.io.*;

   //Creazione di una classe per il Multrithreading
   class ServerThread extends Thread {
     private Socket socket;
      private Socket[] array;
     public String userInput;
     public ServerThread (Socket socket,Socket[] array) {
  
       this.socket = socket;
       System.out.println("  Stato    Tipo Richiesta  Porta Server  Porta Client  Indirizzo Cliernt\n");
     }

     //esecuzione del Thread sul Socket
     public void run() {
      try {
        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        DataInputStream[] isa = new DataInputStream[6];
        DataOutputStream[] osa = new DataOutputStream[6];
        for (int i = 0; i < 6; i++) {
          isa[i] = new DataInputStream(array[i].getInputStream());
          osa[i] = new DataOutputStream(array[i].getOutputStream());
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        userInput = reader.readLine();
        os.close();
        is.close();
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