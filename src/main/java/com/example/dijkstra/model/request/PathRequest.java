package com.example.dijkstra.model.request;

public class PathRequest {

    private String source;

    private String destination;

    private boolean bothDirection;

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

	public boolean isBothDirection() {
		return bothDirection;
	}

	public void setBothDirection(boolean bothDirection) {
		this.bothDirection = bothDirection;
	}

}
