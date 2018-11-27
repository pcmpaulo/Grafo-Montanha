import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Grafo {
    public List<Vertice> listaVertices = new LinkedList<>();
    public int camadas;
    public int tamanho;
    public int somaCamada;
    public void preencherLista(){
        tamanho = 8;
        somaCamada = tamanho;
        tamanho += 1;
        for (int i = 1; i <= 8; i++) {
            listaVertices.get(0).parentes.add(listaVertices.get(i));
            listaVertices.get(i).parentes.add(listaVertices.get(0));
        }
        listaVertices.get(8).parentes.add(listaVertices.get(1));
        listaVertices.get(1).parentes.add(listaVertices.get(8));
        for (int i = 1; i < camadas; i++) {
            somaCamada += somaCamada;
            tamanho += somaCamada;
        }
        for (int i = 1; i < tamanho; i++) {
            if (i+1 < tamanho){
                listaVertices.get(i).parentes.add(listaVertices.get(i+1));
            }
            if (i-1 < tamanho && i-1 >=0){
                listaVertices.get(i).parentes.add(listaVertices.get(i-1));
            }
        }
        int aux = 8;
        for (int i = 1; i < tamanho; i++) {
            if (i < tamanho && i+aux < tamanho){
                listaVertices.get(i).parentes.add(listaVertices.get(i+aux));
                listaVertices.get(i+aux).parentes.add(listaVertices.get(i));
            }
            if (i < tamanho && i+aux+1 < tamanho){
                listaVertices.get(i).parentes.add(listaVertices.get(i+aux+1));
                listaVertices.get(i+aux+1).parentes.add(listaVertices.get(i));
            }
            if (i < tamanho && i+aux+2 < tamanho){
                listaVertices.get(i).parentes.add(listaVertices.get(i+aux+2));
                listaVertices.get(i+aux+2).parentes.add(listaVertices.get(i));
            }
            aux += 1;
        }
    }
    public void criarVertices(){
        int tamanhoCamadas = 8;
        int auxTamanhoCamadas = tamanhoCamadas;
        int j = 1;
        int media = (camadas*8);
        Random r = new Random();
        r.setSeed(10);
        listaVertices.add(new Vertice(0,media));
        for (int i = camadas; i > 0; i--) {
            media = i*8;
            for (; j <= tamanhoCamadas; j++) {
                listaVertices.add(new Vertice(j,(media - (r.nextInt(8)))));
            }
            auxTamanhoCamadas += auxTamanhoCamadas;
            tamanhoCamadas += auxTamanhoCamadas;
        }
    }
}
