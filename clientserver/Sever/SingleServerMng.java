import java.io.*;
import java.net.Socket;
public class SingleServerMng extends Thread{
    private Socket socket;
    public SingleServerMng(Socket socket) {
        this.socket = socket;
        start();
    }
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            while(true){
              String str = in.readLine();
              if(str.equals("QUIT")){
                System.out.println("Client disconnesso");
                break;
              }
              out.println("Hai detto: " + str);
              System.out.println("Echoing: " + str);
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
