package com.example.dijkstra.service;

import org.springframework.stereotype.Service;

import com.example.dijkstra.graph.Edge;
import com.example.dijkstra.graph.Graph;
import java.util.*;

@Service
public class GraphService {

	private Graph graph;

	private CSVFileReader csvReader;

	public GraphService(Graph graph, CSVFileReader csvReader) {
		this.graph = graph;
		this.csvReader = csvReader;
	}

	public Graph buildGraph() {
		try {
			List<Edge> edgeList = csvReader.readCsv();//List<Map<String,String>>
			for (Edge edge : edgeList) {//new Edge(source,dest,weight,active)
				graph.addEdge(edge);
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid weight in the file");
		} catch (Exception e) {
			System.out.println("Error while parse source / destination to build graph [invalid ip ]");
		}

		return graph;
	}

	public void enableEdge(String source, String destiantion) {
		graph.enableEdge(source, destiantion);
	}

	public void disableEdge(String source, String destination) {
		graph.disableEdge(source, destination);
	}

}
