public class Proprieta {
    String nome;
    int costo;
    String colore;
    int numero_case;
    int costo_casa;
    //ipotecato
    String proprietario;
    Proprieta(String nome, int costo, String colore, int numero_case,int costo_casa, String proprietario){
        this.nome = nome;
        this.costo = costo;
        this.colore = colore;
        this.numero_case = numero_case;
        this.costo_casa = costo_casa;
        this.proprietario = proprietario;
    }
    public String getNome(){
        return nome;
    }
    public int getCosto(){
        return costo;
    }
    public int getNumeroCase(){
        return numero_case;
    }
    public int getCostoCasa(){
        return costo_casa;
    }
    public void setProprietario(String nome){
        proprietario = nome;
    }
    public String getProprietario(){
        return proprietario;
    }
    public void costruisciCasa(){
        numero_case++;
    }
    
}
