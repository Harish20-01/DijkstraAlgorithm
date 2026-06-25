package dijikstra_basic.src.dijikstraAlgorithm;

import java.util.*;

public class Main {
	public static  void main(String[] args){
        // eclipse installation
        // Create project Dijkstra
        // Design Principle -> POJO class
        // Creating graph from csv (V=7,E=25)
        // Console program to print shortest Path from source and destination
        // Without using PQ -> optimal implementation of Dijkstra
        // Spring Documentation (Container,Application Context,IOC )
        // Spring Boot
        // Simple Hello World App in SPring boot
        GraphWithWeight g = new GraphWithWeight();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);

        List<List<Integer>> edgesFor1 = new ArrayList<>();
        edgesFor1.add(Arrays.asList(2, 5)); // edge 1--2 weight 5
        edgesFor1.add(Arrays.asList(3, 10)); // edge 1--3 weight 10
        g.addEdges(1, edgesFor1);

        List<List<Integer>> edgesFor2 = new ArrayList<>();
        edgesFor2.add(Arrays.asList(4, 2)); // edge 2--4 weight 2
        g.addEdges(2, edgesFor2);

        List<List<Integer>> edgesFor4 = new ArrayList<>();
        edgesFor4.add(Arrays.asList(3,2)); // edge 4--3 weight 2
        edgesFor4.add(Arrays.asList(5,1)); // edge 4--5 weight 1
        g.addEdges(4,edgesFor4);

        g.print();

        DijikstraAlgorithm algo = new DijikstraAlgorithm(5,g.adjList);
        algo.findShortestPath();
        algo.print();
    }
}
