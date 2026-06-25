package com.example.dijkstra.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.dijkstra.model.collection.RouteHistory;

public interface RouteHistoryRepository extends MongoRepository<RouteHistory,String>{
	
	List<RouteHistory> findTop5ByRouteIdOrderByCreatedAtDesc(String routeId);

    long countByRouteId(String routeId);

    List<RouteHistory> findByRouteIdOrderByCreatedAtAsc(String routeId);
}
