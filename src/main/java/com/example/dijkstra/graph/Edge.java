package com.example.dijkstra.graph;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Edge {
	@Id
	private String id;
	private String source;
	private String destination;
	private int weight;
	private boolean active;
	public Edge(String source, String destination, int weight, boolean active) {
		super();
		this.id=source+"->"+destination;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.active=active;
	}
	public Edge() {
		
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String toString() {
		return this.source+" "+this.destination+" "+this.weight;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
