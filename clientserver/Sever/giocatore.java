public class giocatore {
    public String nome;
    public int soldi;
    public giocatore(String nome){
        this.nome = nome;
        this.soldi = 1500;
    }
    public giocatore(){
        this.soldi = 1500;
    }
    public void via()
    {
        this.soldi += 200;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
}
