package com.example.dijkstra.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Graph {
	
	private Map<String,List<Edge>> adjList;
	
	public Graph() {
		this.adjList = new HashMap<>();
	}
	
	public void addEdge(String source,String dest,int weight,boolean active) {
		System.out.println("Source "+source+ "destination"+dest+"weight: "+weight);
		adjList.computeIfAbsent(source,k->new ArrayList<>()).add(new Edge(source,dest,weight,active)); 
		adjList.putIfAbsent(dest,new ArrayList<>());
	}
	
	public void addEdge(Edge edge) {
		adjList.computeIfAbsent(edge.getSource(), k->new ArrayList<>()).add(edge);
		adjList.putIfAbsent(edge.getDestination(), new ArrayList<>());
	}
	
	public Map<String,List<Edge>> getAdjList() {
		return adjList;
	}
	
	public void setAdjList(Map<String,List<Edge>> adjList) {
		this.adjList = adjList;
	}
	
	public void disableEdge(String source,String destination) {
		adjList.get(source)
				.stream()
				.filter(edge -> edge.getSource().equals(source) && edge.getDestination().equals(destination))
				.forEach(edge->edge.setActive(false));
	}
	
	public void enableEdge(String source,String destination) {
		adjList.get(source)
		.stream()
		.filter(edge -> edge.getSource().equals(source) && edge.getDestination().equals(destination))
		.forEach(edge->edge.setActive(true));
	}
	
}