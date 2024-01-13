//Importo i package
import java.net.*;
import java.util.HashMap;
import java.io.*;

   //Classe Server per attivare la Socket
     public class TCPParallelServer {
    HashMap<String, Proprieta> proprieta = new HashMap<String, Proprieta>();

    public TCPParallelServer() {
      proprieta.put("vicolo corto", new Proprieta("vicolo corto", 60, "marrone", 0,50, 0));
      proprieta.put("vicolo stretto", new Proprieta("vicolo stretto", 60, "marrone", 0,50, 0));
      proprieta.put("stazione sud", new Proprieta("stazione sud", 200, "stazione", 0,0, 0));
      proprieta.put("bastioni gran sasso", new Proprieta("bastioni gran sasso", 100, "celeste", 0,50, 0));
      proprieta.put("viale monterosa", new Proprieta("viale monterosa", 100, "celeste", 0,50, 0));
      proprieta.put("viale vesuvio", new Proprieta("viale vesuvio", 120, "celeste", 0,50, 0));
      proprieta.put("via accademia", new Proprieta("via accademia", 140, "rosa", 0,100, 0));
      proprieta.put("societa elettrica", new Proprieta("societa elettrica", 150, "societa", 0,0, 0));
      proprieta.put("corso ateneo", new Proprieta("corso ateneo", 140, "rosa", 0,100, 0));
      proprieta.put("piazza universita", new Proprieta("piazza universita", 160, "rosa", 0,100, 0));
      proprieta.put("stazione ovest", new Proprieta("stazione ovest", 200, "stazione", 0,0, 0));
      proprieta.put("via verdi", new Proprieta("via verdi", 180, "arancione", 0,100, 0));
      proprieta.put("corso raffaello", new Proprieta("corso raffaello", 180, "arancione", 0,100, 0));
      proprieta.put("piazza dante", new Proprieta("piazza dante", 200, "arancione", 0,100, 0));
      proprieta.put("via marco polo", new Proprieta("via marco polo", 220, "rosso", 0,150, 0));
      proprieta.put("corso magellano", new Proprieta("corso magellano", 220, "rosso", 0,150, 0));
      proprieta.put("largo colombo", new Proprieta("largo colombo", 240, "rosso", 0,150, 0));
      proprieta.put("stazione nord", new Proprieta("stazione nord", 200, "stazione", 0,0, 0));
      proprieta.put("viale costantino", new Proprieta("viale costantino", 260, "giallo", 0,150, 0));
      proprieta.put("viale traiano", new Proprieta("viale traiano", 260, "giallo", 0,150, 0));
      proprieta.put("societa acqua potabile", new Proprieta("societa acqua potabile", 150, "societa", 0,0, 0));
      proprieta.put("piazza giulio cesare", new Proprieta("piazza giulio cesare", 280, "giallo", 0,150, 0));
      proprieta.put("via roma", new Proprieta("via roma", 300, "verde", 0,200, 0));
      proprieta.put("corso impero", new Proprieta("corso impero", 300, "verde", 0,200, 0));
      proprieta.put("largo augusto", new Proprieta("largo augusto", 320, "verde", 0,200, 0));
      proprieta.put("stazione est", new Proprieta("stazione est", 200, "stazione", 0,0, 0));
      proprieta.put("viale dei giardini", new Proprieta("viale dei giardini", 350, "blu", 0,200, 0));
      proprieta.put("viale della vittoria", new Proprieta("viale della vittoria", 400, "blu", 0,200, 0));
    }
     public void start() throws Exception {
       ServerSocket serverSocket = new ServerSocket(7777);

       //Ciclo infinito di ascolto dei Client
       while(true)
	{
         System.out.println(" Attesa ");
         Socket socket = serverSocket.accept();
         System.out.println("Ricezione una chiamata di apertura da:\n" + socket);
        //avvia il processo per ogni client 
	ServerThread serverThread = new ServerThread(socket);
        serverThread.start();
       }
     }

     public static void main (String[] args) throws Exception {
       TCPParallelServer tcpServer = new TCPParallelServer();
       tcpServer.start();
     }
   }
