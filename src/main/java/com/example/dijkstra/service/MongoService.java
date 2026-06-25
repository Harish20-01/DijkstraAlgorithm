package com.example.dijkstra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.dijkstra.Repository.RouteRepository;
import com.example.dijkstra.model.PathInfo;
import com.example.dijkstra.model.collection.Paths;
import com.example.dijkstra.model.request.PathRequest;
import com.example.dijkstra.model.response.BidirectionalPathResponse;
import com.example.dijkstra.model.response.PathResponse;

@Component
public class MongoService {
	private RouteRepository mongoDBService;

	public MongoService(RouteRepository service) {
		this.mongoDBService = service;
	}

	public void saveAllPath(List<Paths> paths) {
		mongoDBService.saveAll(paths);
	}

	public PathInfo getPath(String source, String destination) {
		PathInfo pathInfo = null;
		try {
			Optional<Paths> hasPath = mongoDBService.findById(source+"->"+destination);
			if (hasPath.isPresent()) {
				Paths path = hasPath.get();
				pathInfo = new PathInfo(path.getPathString(),path.getWeight());
			}
		} catch (Exception e) {
			System.out.println("Error in finding path info");
		}
		return pathInfo;
	}

	public BidirectionalPathResponse getBidirectionalPath(PathRequest request) {
		// TODO Auto-generated method stub
		String source = request.getSource();
		String destination = request.getDestination();
		PathInfo path = getPath(source, destination);
		PathResponse forward = new PathResponse();
		forward.setSource(source);
		forward.setDestination(destination);
		if (path == null) {
			forward.setPath("No path exist");
			forward.setWeight(0);
		} else {
			forward.setPath(path.getPath());
			forward.setWeight(path.getWeight());
		}
		path = getPath(destination, source);
		PathResponse reverse = new PathResponse();
		reverse.setDestination(source);
		reverse.setSource(destination);
		if (path == null) {
			reverse.setPath("No path exist");
			reverse.setWeight(0);
		} else {
			reverse.setPath(path.getPath());
			reverse.setWeight(path.getWeight());
		}
		BidirectionalPathResponse response = new BidirectionalPathResponse();
		response.setForward(forward);
		response.setReverse(reverse);

		return response;
	}

	public PathResponse getPath(PathRequest request) {
		// TODO Auto-generated method stub
		String source = request.getSource();
		String destination = request.getDestination();
		PathInfo pathInfo = getPath(source, destination);
		PathResponse response = new PathResponse();

		response.setSource(source);
		response.setDestination(destination);
		response.setPath(pathInfo.getPath());
		response.setWeight(pathInfo.getWeight());

		return response;
	}

	public List<Paths> getAffectedPaths(String pathId) {
		return mongoDBService.findByPathIds(pathId);
		
	}
	
	public void updatePath(Paths path) {
		mongoDBService.save(path);
	}
	
	public boolean isEdgeExist(String edge) {
		return mongoDBService.existsByPathIds(edge);
	}

}
