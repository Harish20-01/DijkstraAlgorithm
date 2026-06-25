package com.example.dijkstra.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dijkstra.Repository.RouteHistoryRepository;
import com.example.dijkstra.model.collection.Paths;
import com.example.dijkstra.model.collection.RouteHistory;

@Service
public class RouteHistoryService {

    private final RouteHistoryRepository repository;

    public RouteHistoryService(RouteHistoryRepository repository) {
        this.repository = repository;
    }

    public void saveHistory(Paths path) {

        RouteHistory history = new RouteHistory();

        history.setRouteId(path.getId());

        history.setSource(path.getSource());
        history.setDestination(path.getDestination());

        history.setWeight(path.getWeight());
        history.setPathString(path.getPathString());

        history.setEdges(new ArrayList<>(path.getEdges()));
        history.setPathIds(new ArrayList<>(path.getPathIds()));

        history.setCreatedAt(LocalDateTime.now());

        repository.save(history);

        trimHistory(path.getId());
    }

    private void trimHistory(String routeId){

        List<RouteHistory> histories = repository.findByRouteIdOrderByCreatedAtAsc(routeId);

        while(histories.size()>5){

            repository.delete(histories.get(0));

            histories.remove(0);
        }
    }
}
