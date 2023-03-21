import java.util.TreeMap;
import java.util.Vector;

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
            return "Il nodo " + label + "non ha nessun link.";
        }else{
            for (Node s: links.keySet()) {
                a+=label + " --> " + s.label + ", peso: " + links.get(s) + "\n";
            }
        }
        return a;
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
