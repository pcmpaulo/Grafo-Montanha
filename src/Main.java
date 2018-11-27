import javax.swing.*;
import java.util.*;
import static sun.java2d.pipe.hw.AccelSurface.UNDEFINED;

public class Main {
    public static Grafo grafo = new Grafo();
    public static int getCusto(Vertice vertice1, Vertice vertice2) {
        int custo = vertice1.getAltura() -vertice2.getAltura();
        if (custo < 0){
            custo = custo * -1;
        }
        if (custo == 0){
            return 9;
        }
        if (custo == 1 || custo == 2 || custo == 3){
            return custo*10;
        }
        if (custo == 4 || custo == 5 || custo == 6){
            return custo*9;
        }
        if (custo == 7 || custo == 8 || custo == 9){
            return custo*11;
        }
        return custo*20;
    }
    public static List<Integer> getCaminho(Vertice inicio,int destino) {
        int custoDestino[] = new int[grafo.tamanho];
        int anterior[] = new int[grafo.tamanho];
        Set<Integer> naoVisitados = new HashSet<>();
        custoDestino[0] = 0;
        for (int v = 0; v < grafo.tamanho; v++) {
            if (v != inicio.getNumero()) {
                custoDestino[v] = Integer.MAX_VALUE;
            }
            anterior[v] = UNDEFINED;
            naoVisitados.add(v);
        }
        while (!naoVisitados.isEmpty()) {
            int atual = proximo(custoDestino, naoVisitados);
            naoVisitados.remove(atual);
            for (Vertice vizinhoT : grafo.listaVertices.get(atual).parentes) {
                int vizinho = vizinhoT.getNumero();
                int pesoDestino = custoDestino[atual] + getCusto(grafo.listaVertices.get(atual), vizinhoT);
                if (pesoDestino < custoDestino[vizinho]) {
                    custoDestino[vizinho] = pesoDestino;
                    anterior[vizinho] = atual;
                }
            }
            if (atual > destino) {
                return listaDeCaminho(anterior, atual);
            }
        }
        return Collections.emptyList();
    }
    private static int proximo(int[] distancia, Set<Integer> naoVisitado) {
        double distanciaMinima = Integer.MAX_VALUE;
        int indice = 0;
        for (Integer i : naoVisitado) {
            if (distancia[i] < distanciaMinima) {
                distanciaMinima = distancia[i];
                indice = i;
            }
        }
        return indice;
    }
    private static List<Integer> listaDeCaminho(int[] anterior, int u) {
        List<Integer> caminho = new ArrayList<>();
        caminho.add(u);
        while (anterior[u] != UNDEFINED) {
            caminho.add(anterior[u]);
            u = anterior[u];
        }
        Collections.reverse(caminho);
        return caminho;
    }
    public static String matrizAdjacencia(){
        String mensagem = "Matriz Adjacencia: \n";
        int matriz[][] = new int [grafo.tamanho][grafo.tamanho];
        for (int i = 0; i < grafo.tamanho; i++) {
            for (Vertice v: grafo.listaVertices.get(i).parentes) {
                matriz[i][v.getNumero()] = 1;
            }
        }
        for (int i = 0; i < matriz.length; i++) {
            mensagem += "\n";
            for (int j = 0; j < matriz.length; j++) {
                mensagem += "| "+ matriz[i][j];
            }
        }
        return mensagem;
    }
    public static String matrizIncidencia(){
        String mensagem = "Matriz Incidencia: \n";
        int largura = 0;
        for (int i = 0; i < grafo.tamanho; i++) {
            largura += grafo.listaVertices.get(i).parentes.size();
        }
        int incidencia[][] = new int[grafo.tamanho][largura];
        int aux = 0;
        Grafo temp = grafo;
        for (int i = 0; i < temp.tamanho; i++) {
            for (Vertice v: temp.listaVertices.get(i).parentes) {
                incidencia[i][aux]= 1;
                incidencia[v.getNumero()][aux] = 1;
                v.parentes.remove(grafo.listaVertices.get(i));
                aux +=1;
            }
        }
        for (int i = 0; i < incidencia.length; i++) {
            mensagem += "\n";
            for (int j = 0; j < incidencia.length; j++) {
                mensagem += "| "+ incidencia[i][j];
            }
        }
        return mensagem;
    }
    public static void main(String[]args){
        grafo.camadas = Integer.parseInt(JOptionPane.showInputDialog("qual o numero de camadas da montanha?"));
        grafo.criarVertices();
        grafo.preencherLista();
        for (int i = 0; i < grafo.tamanho; i++) {
            System.out.print(grafo.listaVertices.get(i).getNumero() + " - " + grafo.listaVertices.get(i).getAltura());
            for (Vertice v:grafo.listaVertices.get(i).parentes) {
                System.out.print(" - " + v.getNumero() + " - ");
            }
            System.out.print("\n");
        }
        List caminho = getCaminho(grafo.listaVertices.get(0),grafo.somaCamada);
        while(!caminho.isEmpty()){
            System.out.print(caminho.get(0) + " - ");
            caminho.remove(0);
        }
        JOptionPane.showMessageDialog(null,matrizAdjacencia());
        JOptionPane.showMessageDialog(null,matrizIncidencia());
    }
}
