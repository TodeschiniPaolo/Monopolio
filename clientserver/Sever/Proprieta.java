public class Proprieta {
    String nome;
    int costo;
    String colore;
    int numero_case;
    int costo_casa;
    //ipotecato
    int proprietario;
    Proprieta(String nome, int costo, String colore, int numero_case,int costo_casa, int proprietario){
        this.nome = nome;
        this.costo = costo;
        this.colore = colore;
        this.numero_case = numero_case;
        this.costo_casa = costo_casa;
        this.proprietario = proprietario;
    }
}
