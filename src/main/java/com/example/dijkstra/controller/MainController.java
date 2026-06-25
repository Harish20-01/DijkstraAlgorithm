package com.example.dijkstra.controller;

import org.springframework.stereotype.Component;
import com.example.dijkstra.graph.DijkstraAlgo;
import com.example.dijkstra.graph.*;
import com.example.dijkstra.model.collection.Paths;
import com.example.dijkstra.service.GraphService;
import com.example.dijkstra.service.MongoService;
import java.util.*;

@Component
public class MainController {
	private GraphService graphService;
	private DijkstraAlgo dijikstraAlgorithm;
	private MongoService mongoService;
	public MainController(GraphService gService,DijkstraAlgo algorithm,MongoService mService) {
		this.graphService = gService;
		this.dijikstraAlgorithm = algorithm;
		this.mongoService=mService;
	}
	
	public void run() {
		Graph graph = graphService.buildGraph();
		dijikstraAlgorithm.setAdjList(graph.getAdjList());
		dijikstraAlgorithm.buildAllPaths();
		List<Paths> paths = dijikstraAlgorithm.getAllPaths();
		
		mongoService.saveAllPath(paths);		
	}
}
