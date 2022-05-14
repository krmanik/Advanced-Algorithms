package main;

import java.util.Comparator;

public class Edge {
    private final int source;
    private final int dest;
    private final float weight;

    public Edge(int source, int dest, float weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public int getSource() {
        return source;
    }

    public int getDest() {
        return dest;
    }


    public float getWeight() {
        return weight;
    }
}

class EdgeComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge o1, Edge o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
    }
}
