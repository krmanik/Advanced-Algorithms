import main.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {

    @Test
    public void graphTest() {
        int vertices = 3;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, (float) 0.1);
        graph.addEdge(0, 2, (float) 0.3);
        graph.addEdge(1, 2, (float) 0.2);
        graph.printGraph();

        assertEquals(3, graph.getVertices());
    }
}
