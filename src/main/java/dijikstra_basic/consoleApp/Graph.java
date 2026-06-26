package dijikstra_basic.consoleApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
	
	private Map<String,List<Edge>> adjList;
	
	public Graph() {
		this.adjList = new HashMap<>();
	}
	
	public void addEdge(String source,String dest,int weight) {
		System.out.println("Source "+source+ "  destination "+dest+"  weight: "+weight);
		adjList.putIfAbsent(source,new ArrayList<>());
		adjList.putIfAbsent(dest, new ArrayList<>());
		adjList.get(source).add(new Edge(dest,weight));
		adjList.get(dest).add(new Edge(source, weight));
		 
	}
	
	public Map<String,List<Edge>> getAdjList() {
		return adjList;
	}
	
	public void setAdjList(Map<String,List<Edge>> adjList) {
		this.adjList = adjList;
	}
	 
	public void shortestPath(String src, String dest) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> parent = new HashMap<>(); // track predecessors

        for (String node : adjList.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
            parent.put(node, null);
        }
        dist.put(src, 0);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.getWeight()));
        pq.add(new Edge(src, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            String u = curr.getVertex();
            int d = curr.getWeight();

            if (u.equals(dest)) break; // early exit

            if (d > dist.get(u)) continue;
            
            if(adjList.get(u)==null)
            	continue;
            for (Edge edge : adjList.get(u)) {
        
                String v = edge.getVertex();
                int w = edge.getWeight();
                if (dist.get(u) + w < dist.get(v)) {
                    dist.put(v, dist.get(u) + w);
                    parent.put(v, u);
                    pq.add(new Edge(v, dist.get(v)));
                }
            }
        }

        // reconstruct path
        List<String> path = new ArrayList<>();
        String curr = dest;
        while (curr != null) {
            path.add(curr);
            curr = parent.get(curr);
        }
        if(path.isEmpty()) {
        	System.out.println("No Path Exist");
        	return;
        }
        Collections.reverse(path);
        for(int i=0;i<path.size();i++) {
        	System.out.print(path.get(i));
        	if(i!=path.size()-1)
        		System.out.print("-->");
        }
        System.out.println("[Weight => "+dist.get(dest)+"]");
    }
}