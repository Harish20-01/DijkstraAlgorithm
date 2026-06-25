package com.example.dijkstra.model.collection;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.dijkstra.graph.Edge;

@Document(collection = "shortest_paths")
public class Paths {

    @Id
    private String id;
    private String source;
    private String destination;
    private String pathString;
    private Integer weight;
    private List<Edge> edges;
    private List<String> pathIds;
	public Paths() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public List<String> getPathIds() {
		return pathIds;
	}
	public void setPathIds(List<String> pathIds) {
		this.pathIds = pathIds;
	}
	public String getPathString() {
		return pathString;
	}
	public void setPathString(String pathString) {
		this.pathString = pathString;
	}
    
}