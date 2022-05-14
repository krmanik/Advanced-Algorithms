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

    public void generateGraph() {
        for (int i = 0; i < maxEdge; i++) {
            int src = random.nextInt(vertices);
            int dst = random.nextInt(vertices);
            float wgt = random.nextFloat();
            // remove self loop and single edge
            if (src == dst) {
                // i = i - 1;
                continue;
            }
            randomGraph.addEdge(src, dst, wgt);
        }
    }

    public void printGraph() {
        randomGraph.printGraph();
    }

    public Graph getRandomGraph() {
        return randomGraph;
    }
}
