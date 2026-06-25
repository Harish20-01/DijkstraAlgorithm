package com.example.dijkstra.model.collection;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.dijkstra.graph.Edge;

@Document(collection = "route_history")
public class RouteHistory {

    @Id
    private String id;

    private String routeId;

    private String source;
    private String destination;

    private String pathString;
    private Integer weight;

    private List<String> pathIds;

    private List<Edge> edges;

    private LocalDateTime createdAt;
    

	public RouteHistory() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
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

	public String getPathString() {
		return pathString;
	}

	public void setPathString(String pathString) {
		this.pathString = pathString;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public List<String> getPathIds() {
		return pathIds;
	}

	public void setPathIds(List<String> pathIds) {
		this.pathIds = pathIds;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

    
}