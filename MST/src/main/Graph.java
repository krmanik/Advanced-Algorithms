package main;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int vertices;
    List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);

        for(int i =0 ; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int dest, float weight) {
        Edge edge = new Edge(source, dest, weight);
        adjacencyList.get(source).add(edge);
    }

    public boolean edgeExist(int src, int dst) {
        List<Edge> linkedList = adjacencyList.get(src);
        for (Edge edge: linkedList) {
            if (edge.getDest() == dst) {
                return true;
            }
        }
        return false;
    }

    public void printGraph() {
        for (int i = 0; i< vertices; i++) {
            List<Edge> linkedList = adjacencyList.get(i);
            System.out.format("[%d]:: ", i);
            for (Edge edge : linkedList) {
                // System.out.format("vertex %d\n", i);
                // System.out.format("\twith edge %d and weight %f\n", edge.dest, edge.weight);
                // [head]: ==(weight)==> [dst]
                System.out.format("==(%f)==> [%d] ", edge.getWeight(), edge.getDest());
            }

            System.out.println();
        }
    }

    public int getVertices() {
        return vertices;
    }

    public List<List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }
}
