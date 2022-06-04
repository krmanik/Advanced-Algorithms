import main.GenerateRandomGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateRandomGraphTest {

    @Test
    void generateGraphTest() {
        int vertices = 16;
        GenerateRandomGraph generateRandomGraph = new GenerateRandomGraph(vertices);
        generateRandomGraph.generateCompleteGraph();
        generateRandomGraph.printGraph();

        assertEquals(16, generateRandomGraph.getRandomGraph().getVertices());
    }
}
