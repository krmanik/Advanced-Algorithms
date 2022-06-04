import main.GenerateRandomGraph;
import main.Graph;
import main.MinimumSpanningTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumSpanningTreeTest {

    @Test
    void graphMstTest() {
        int vertices = 6;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, (float) 0.4);
        graph.addEdge(0, 2, (float) 0.3);
        graph.addEdge(1, 2, (float) 0.1);
        graph.addEdge(1, 3, (float) 0.2);
        graph.addEdge(2, 3, (float) 0.4);
        graph.addEdge(3, 4, (float) 0.2);
        graph.addEdge(4, 5, (float) 0.6);
        // graph.printGraph();

        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree(graph);
        Graph mstGraph = minimumSpanningTree.prim(0);
        mstGraph.printGraph();
        System.out.println(minimumSpanningTree.getTotalWeight());
        assertEquals((float) 1.7, minimumSpanningTree.getTotalWeight());
    }

    @Test
    void randomGraphMstTest() {
        int vertices = 16;

        GenerateRandomGraph generateGraph = new GenerateRandomGraph(vertices);
        generateGraph.generateCompleteGraph();
        generateGraph.printGraph();

        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree(generateGraph.getRandomGraph());
        Graph mstGraph = minimumSpanningTree.prim(0);
        mstGraph.printGraph();
        System.out.println(minimumSpanningTree.getTotalWeight());

        assertEquals(16, mstGraph.getVertices());
    }
}