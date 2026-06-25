package com.example.dijkstra.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.dijkstra.model.collection.Paths;

public interface RouteRepository extends MongoRepository<Paths,String>{

	List<Paths>findByPathIds(String pathId);
	
	boolean existsByPathIds(String pathId);
	
}
