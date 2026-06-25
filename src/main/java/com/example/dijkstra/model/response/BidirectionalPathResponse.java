package com.example.dijkstra.model.response;

public class BidirectionalPathResponse {

    private PathResponse forward;

    private PathResponse reverse;

	public PathResponse getForward() {
		return forward;
	}

	public void setForward(PathResponse forward) {
		this.forward = forward;
	}

	public PathResponse getReverse() {
		return reverse;
	}

	public void setReverse(PathResponse reverse) {
		this.reverse = reverse;
	}

    
}
