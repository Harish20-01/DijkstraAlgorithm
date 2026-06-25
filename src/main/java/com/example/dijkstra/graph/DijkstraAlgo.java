package com.example.dijkstra.graph;

import java.util.*;

import org.springframework.stereotype.Component;

import com.example.dijkstra.model.collection.Paths;

@Component
public class DijkstraAlgo {

    // Adjacency List
    private Map<String, List<Edge>> adjList;

    // All computed shortest paths
    private List<Paths> allPaths;

    private Map<String, Integer> dist;
    private Map<String, String> parent;

    public void setAdjList(Map<String, List<Edge>> adjList) {
        this.adjList = adjList;
    }

    public void buildAllPaths() {

        allPaths = new ArrayList<>();

        for (String source : adjList.keySet()) {
            shortestPath(source);
        }
    }

    public void shortestPath(String source) {

        initialize(source);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        pq.offer(new Edge(source, source, 0, true));

        while (!pq.isEmpty()) {

            Edge current = pq.poll();

            String currentVertex = current.getDestination();

            if (current.getWeight() > dist.get(currentVertex))
                continue;

            if (adjList.get(currentVertex) == null)
                continue;

            for (Edge edge : adjList.get(currentVertex)) {

                if (!edge.isActive())
                    continue;

                relaxEdge(currentVertex, edge, pq);
            }
        }

        buildPath(source);
    }

    //path between two nodes
    public void shortestPath(String source, String destination) {

        initialize(source);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        pq.offer(new Edge(source, source, 0, true));

        while (!pq.isEmpty()) {

            Edge current = pq.poll();

            String currentVertex = current.getDestination();

            if (current.getWeight() > dist.get(currentVertex))
                continue;

            if (currentVertex.equals(destination))
                break;

            if (adjList.get(currentVertex) == null)
                continue;

            for (Edge edge : adjList.get(currentVertex)) {

                if (!edge.isActive())
                    continue;

                relaxEdge(currentVertex, edge, pq);
            }
        }
    }

    
    private void initialize(String source) {

        dist = new HashMap<>();
        parent = new HashMap<>();

        for (String node : adjList.keySet()) {

            dist.put(node, Integer.MAX_VALUE);
            parent.put(node, null);
        }

        dist.put(source, 0);
    }

    private void relaxEdge(String currentVertex, Edge edge,PriorityQueue<Edge> pq) {

        String neighbour = edge.getDestination();

        int newDistance = dist.get(currentVertex) + edge.getWeight();

        if (newDistance < dist.get(neighbour)) {

            dist.put(neighbour, newDistance);

            parent.put(neighbour, currentVertex);

            pq.offer(new Edge(currentVertex, neighbour, newDistance, true));
        }
    }

    private void buildPath(String source) {

        for (String destination : parent.keySet()) {

            allPaths.add(buildSinglePath(source, destination));
        }
    }

    private Paths buildSinglePath(String source,String destination) {

        Paths pathDetail = new Paths();
        List<String> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        List<String> pathIds = new ArrayList<>();

        String curr = destination;

        while (curr != null) {
            vertices.add(curr);
            curr = parent.get(curr);
        }

        Integer weight = dist.get(destination);

        if (weight == Integer.MAX_VALUE) {
            pathDetail.setPathString("No Path Exists");
            pathDetail.setWeight(null);
            pathDetail.setEdges(Collections.emptyList());
            pathDetail.setPathIds(Collections.emptyList());

        } else {

            Collections.reverse(vertices);

            for (int i = 0; i < vertices.size() - 1; i++) {

                String u = vertices.get(i);
                String v = vertices.get(i + 1);

                Edge edge = getEdge(u, v);

                edges.add(edge);
                pathIds.add(u + "->" + v);
            }

            pathDetail.setPathString(
                    String.join("-->", vertices));

            pathDetail.setWeight(weight);
            pathDetail.setEdges(edges);
            pathDetail.setPathIds(pathIds);
        }

        pathDetail.setId(source + "->" + destination);
        pathDetail.setSource(source);
        pathDetail.setDestination(destination);

        return pathDetail;
    }

    public Paths findShortestPath(Paths affectedPath) {

        shortestPath(affectedPath.getSource(),affectedPath.getDestination());

        return buildSinglePath(affectedPath.getSource(), affectedPath.getDestination());
    }

    private Edge getEdge(String source, String destination) {

        List<Edge> edges = adjList.get(source);
        if (edges == null)
            return null;

        for (Edge edge : edges) {

            if (edge.getDestination().equals(destination))
                return edge;
        }

        return null;
    }

    public List<Paths> getAllPaths() {
        return allPaths;
    }
}
