import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.PriorityQueue;

import static java.lang.Integer.MAX_VALUE;

public class Node {
    private String label;
    private int weight = MAX_VALUE;
    private TreeMap<Node, Integer> links;
    private Node prev = null;

    public Node(String label) {
        this.label = label;
        links = new TreeMap<>((a, b) -> a.label.compareTo(b.label));
    }
    public void link(Node nodo2, Integer weight) {
        links.put(nodo2, weight);
        nodo2.links.put(this, weight);
    }
    public String getLabel(){
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String printLinks(){
        String a = "Link di " + label + "\n";
        if(links.isEmpty() == true){
            return "Il nodo " + label + " non ha nessun link.";
        }else{
            for (Node s: links.keySet()) {
                a+=label + " --> " + s.label + ", peso: " + links.get(s) + "\n";
            }
        }
        return a;
    }
    public void dijkstra(Node destinazione) {
        // Inizializzazione
        for (Node n : links.keySet()) {
            n.setWeight(Integer.MAX_VALUE);
            n.setPrev(null);
        }
        setWeight(0);

        // Creazione della coda di priorità
        PriorityQueue <Node> coda = new PriorityQueue<>((a, b) -> a.getWeight() - b.getWeight());
        coda.add(this);

        // Ciclo principale
        while (!coda.isEmpty()) {
            Node u = coda.poll();
            for (Map.Entry<Node, Integer> entry : u.getLinks().entrySet()) {
                Node v = entry.getKey();
                int pesoUV = entry.getValue();
                int pesoAlt = u.getWeight() + pesoUV;
                if (pesoAlt < v.getWeight()) {
                    coda.remove(v);
                    v.setWeight(pesoAlt);
                    v.setPrev(u);
                    coda.add(v);
                }
            }
        }

        // Stampa il percorso più breve
        System.out.println(getPathTo(destinazione));
    }

    private String getPathTo(Node destinazione) {
        if (destinazione.getPrev() == null) {
            return "Impossibile raggiungere la destinazione.";
        }
        String out = destinazione.getPath();
        if (destinazione != this) {
            out += " (peso totale: " + destinazione.getWeight() + ")";
        }
        return out;
    }

    public TreeMap<Node, Integer> getLinks() {
        return links;
    }

    public void setLinks(TreeMap<Node, Integer> links) {
        this.links = links;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public String getPath() {
        String weight = "";
        if (this.weight < MAX_VALUE)
            weight += this.weight;
        else
            weight = "inf";
        String out = "(" + label + "-" + weight + ")";
        if (prev != null)
            out = prev.getPath() + "-" + prev.links.get(this) + "->" + out;
        return out;
    }
}
