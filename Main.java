import java.util.Vector;
public class Main {
    public static void main(String[] args) {
    //Simple Dijkstra algorithm implementation in Java.
        Vector<Node> nodi = new Vector<>();
        for (int i=0; i<5; i++){
            nodi.add(new Node("" + (char) ('A' + i)));
        }
        nodi.elementAt(0).link(nodi.elementAt(1), 1);
        nodi.elementAt(0).link(nodi.elementAt(2), 1);
        nodi.elementAt(1).link(nodi.elementAt(4), 3);
        nodi.elementAt(2).link(nodi.elementAt(3), 1);
        nodi.elementAt(3).link(nodi.elementAt(4), 1);
        System.out.println(nodi.elementAt(0).printLinks());
        for (Node a: nodi) {
            System.out.println(a.printLinks());
        }
    }
    public Vector dijkstra(Vector<Node> nodi){
        Vector<Node> best_path = new Vector<>();

        return best_path;
    }
}