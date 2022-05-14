package main;

import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {
    int vertices;
    float totalWeight;
    List<List<Edge>> adjacencyList;

    public MinimumSpanningTree(Graph graph) {
        this.vertices = graph.getVertices();
        this.adjacencyList = graph.getAdjacencyList();
        this.totalWeight = 0;
    }

    public Graph prim(int source) {
        Graph mst = new Graph(vertices);
        boolean[] visited = new boolean[vertices];

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>(new EdgeComparator());
        priorityQueue.addAll(adjacencyList.get(source));

        visited[source] = true;

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.remove();

            if (!visited[edge.getDest()]) {
                visited[edge.getDest()] = true;
                totalWeight += edge.getWeight();
                mst.addEdge(edge.getSource(), edge.getDest(), edge.getWeight());
                priorityQueue.addAll(adjacencyList.get(edge.getDest()));
            }
        }

        return mst;
    }

    public float getTotalWeight() {
        return totalWeight;
    }
}
