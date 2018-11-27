import java.util.LinkedList;

public class Vertice {
    private int numero;
    private int altura;
    public LinkedList<Vertice> parentes = new LinkedList();
    public Vertice(int numero,int altura){
        this.numero = numero;
        this.altura = altura;
    }
    public int getAltura() {
        return altura;
    }
    public int getNumero() {
        return numero;
    }
}
