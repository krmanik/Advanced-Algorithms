package main;

import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.stream.IntStream;

public class Main {
    static int[] nVertex = {16, 32, 64, 128, 256, 512, 1024};
    static float[] nWeight = new float[nVertex.length];
    static long[] nTime = new long[nVertex.length];
    // vertex - weight
    static DefaultCategoryDataset datasetVertexWeight = new DefaultCategoryDataset();
    // vertex - time
    static DefaultCategoryDataset datasetVertexTime = new DefaultCategoryDataset();

    public static void main(String[] args) {
        // calculateTimeWeight();
        calculateTimeWeightNVertex(1024);

        SwingUtilities.invokeLater(() -> {
            LineChart lineChart1 = new LineChart("vertex - weight", "vertex", "weight", datasetVertexWeight);
            LineChart lineChart2 = new LineChart("vertex - time", "vertex", "time", datasetVertexTime);

            lineChart1.plotLineChart();
            lineChart2.plotLineChart();
        });
    }

    /**
     * calculate mst for n vertex graph
     * @param n number of vertex
     */
    static void calculateTimeWeightNVertex(int n) {
        // number of vertex start from 3 to number of vertex n
        nVertex = IntStream.rangeClosed(3, n).toArray();
        nWeight = new float[n];
        nTime = new long[n];

        for (int i = 3; i < nVertex.length; i++) {
            long startTime = System.currentTimeMillis();
            System.out.format("Calculating MST for vertex: %d, ", nVertex[i]);

            generateNVertexGraph(nVertex[i], i);

            long endTime = System.currentTimeMillis();
            nTime[i] = endTime - startTime;

            datasetVertexWeight.addValue(nWeight[i], "weight", String.valueOf(nVertex[i]));
            datasetVertexTime.addValue(nTime[i], "time", String.valueOf(nVertex[i]));

            System.out.format("weight: %f, time: %d\n", nWeight[i], nTime[i]);
        }
    }

    /**
     * calculate mst for vertex in nVertex array
     */
    static void calculateTimeWeight() {
        for (int i = 0; i < nVertex.length; i++) {
            long startTime = System.currentTimeMillis();
            System.out.format("Calculating MST for vertex: %d, ", nVertex[i]);

            int vertices = nVertex[i];
            generateNVertexGraph(vertices, i);

            long endTime = System.currentTimeMillis();
            nTime[i] = endTime - startTime;

            datasetVertexWeight.addValue(nWeight[i], "weight", String.valueOf(nVertex[i]));
            datasetVertexTime.addValue(nTime[i], "time", String.valueOf(nVertex[i]));

            System.out.format("weight: %f, time: %d\n", nWeight[i], nTime[i]);
        }
    }

    /**
     * generate n vertex graph and its mst
     * @param n number of vertex
     * @param index store weight at index in weight array
     */
    static void generateNVertexGraph(int n, int index) {
        GenerateRandomGraph generateGraph = new GenerateRandomGraph(n);
        generateGraph.generateGraph();
        // generateGraph.printGraph();

        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree(generateGraph.getRandomGraph());
        Graph mstGraph = minimumSpanningTree.prim(0);
        // mstGraph.printGraph();
        nWeight[index] = minimumSpanningTree.getTotalWeight();
        // System.out.println(minimumSpanningTree.getTotalWeight());
    }
}