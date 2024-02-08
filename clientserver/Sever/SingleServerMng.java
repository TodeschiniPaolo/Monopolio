import java.io.*;
import java.net.Socket;
public class SingleServerMng extends Thread{
    private Socket socket;
    public SingleServerMng(Socket socket) {
        this.socket = socket;
        start();
    }
	private boolean esiste =false;
	private boolean posseduta =false;
	private boolean possiede =false;
	private int n_case=0;

private boolean esisteproprieta(String proprieta)
{
	if (esiste)
		return true;
	else{
		esiste =true;
		return false;
		}
}

private boolean possedutaproprieta(String proprieta)
{
	if (posseduta)
		return true;
	else{
		posseduta =true;
		return false;
		}
}

private boolean possiedeproprieta(String proprieta)
{
	if (possiede)
		return true;
	else{
		possiede =true;
		return false;
		}
}

private void setproprieta(String proprieta, String nome)
{
}

private boolean costruiscicasa(String proprieta)
{
	if (n_case < 5)
	{
		n_case=n_case+1;
		return true;
	}
	else
	{
		return false;
	}
}

private int quantecasa(String proprieta)
{
return n_case;
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
                            
                            break;
                          default:
                      out.println("comando compra non valido");
                            break;
                        }
						if (azione ==0)
						{
                        String proprieta = in.readLine();
							if (esisteproprieta(proprieta))
							{
								if (azione == 1)
								{
									if (possedutaproprieta(proprieta))
									{
										out.println("Comprato "+ proprieta +" cosa vuoi fare ");
										setproprieta(proprieta,g.getNome());
									}
									else
									{
										out.println("Posseduta "+ proprieta +" cosa vuoi fare ");
									}
								}
								else
								{	// al momento solo 2
									//if (possiedeproprieta(proprieta,g.getNome()))
                  if (possiedeproprieta(proprieta))
									{
										if (costruiscicasa(proprieta))
										{
											out.println("Costruito "+ quantecasa(proprieta) +" cosa vuoi fare ");
										}
										else
										{
											out.println("MAX Costruzioni "+ proprieta +" cosa vuoi fare ");
										}
									}
									else
									{
										out.println("Non posseduta "+ proprieta +" cosa vuoi fare ");
									}
								}
							}
							else
							{
								out.println("Non ESISTE "+ proprieta +" cosa vuoi fare ");
							}
						}
                      break;
                    case "elenco":
                    
                      
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
