package main;

import java.util.Random;

public class GenerateRandomGraph {
    private final int vertices;
    private final int maxEdge;
    private final Graph randomGraph;
    private final Random random = new Random();

    public GenerateRandomGraph(int vertices) {
        this.vertices = vertices;
        // in undirected graph, there will be at most v*(v-1)/2 number of edges
        // from Handshake Lemma
        this.maxEdge = vertices * ((vertices - 1) / 2);

        randomGraph =  new Graph(vertices);
    }

    public void generateCompleteGraph() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 1; j < vertices; j++) {
                float wgt = random.nextFloat();
                randomGraph.addEdge(i, j, wgt);
            }
        }
    }

    public void printGraph() {
        randomGraph.printGraph();
    }

    public Graph getRandomGraph() {
        return randomGraph;
    }
}
