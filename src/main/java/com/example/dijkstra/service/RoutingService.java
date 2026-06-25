package com.example.dijkstra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dijkstra.graph.DijkstraAlgo;
import com.example.dijkstra.graph.Graph;
import com.example.dijkstra.model.collection.Paths;

@Service
public class RoutingService {

	private final Graph graph;
	private final DijkstraAlgo dijkstra;
	private final MongoService mongoService;
	private final RouteHistoryService historyService;

	public RoutingService(Graph graph, DijkstraAlgo dijkstra, MongoService mongoService,RouteHistoryService hService) {

		this.graph = graph;
		this.dijkstra = dijkstra;
		this.mongoService = mongoService;
		this.historyService= hService;
	}

	public void disableEdge(String source,String destination) {
		
		graph.disableEdge(source, destination);
		
		List<Paths> affectedPaths = mongoService.getAffectedPaths(source+"->"+destination);
		
		for(Paths affectedPath:affectedPaths) {
			Paths newPath = dijkstra.findShortestPath(affectedPath);
			historyService.saveHistory(affectedPath);
			mongoService.updatePath(newPath);
		}
	}
}