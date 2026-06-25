package com.example.dijkstra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import com.example.dijkstra.model.request.EdgeRequest;
import com.example.dijkstra.model.request.PathRequest;
import com.example.dijkstra.model.response.BidirectionalPathResponse;
import com.example.dijkstra.model.response.PathResponse;
import com.example.dijkstra.service.CSVFileReader;
import com.example.dijkstra.service.GraphService;
import com.example.dijkstra.service.MongoService;
import com.example.dijkstra.service.RoutingService;

@RestController
public class ControllerRest {
	private MongoService mongoService;
	private RoutingService routeService;
	private CSVFileReader csvReaderService;
	public ControllerRest(MongoService mService, GraphService gService,RoutingService rService,CSVFileReader cService) {
		mongoService = mService;
		routeService = rService;
		csvReaderService= cService;
	}

	@GetMapping("/")
	public ResponseEntity<?> getHome() {
		return ResponseEntity.status(200).body("server is running");
	}

	@PostMapping("/path")
	public ResponseEntity<?> getPath( @RequestBody PathRequest request) {
		try {
			String source = request.getSource().trim();
			String destination = request.getDestination().trim();
			boolean validSource = csvReaderService.isValidIp(source) ;
			boolean validDestination = csvReaderService.isValidIp(destination);
			if (!validSource || !validDestination) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Source or destination not found.");
			}
			
	    if (request.isBothDirection()) {

	        BidirectionalPathResponse response =mongoService.getBidirectionalPath(request);

	        return ResponseEntity.ok(response);
	    }

	    PathResponse response = mongoService.getPath(request);

	    return ResponseEntity.ok(response);
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid Ip address");
		}
	}
	
	@PostMapping("/edge/down")
	public ResponseEntity<?> disableEdge(@RequestBody EdgeRequest request) {
		try {
			String source = request.getSource();
			String destination = request.getDestination();
			boolean validSource = csvReaderService.isValidIp(source);
			boolean validDestination = csvReaderService.isValidIp(destination);
			if(!validSource||!validDestination)
				return ResponseEntity.status(404).body("Invalid source or Destination");
			if(!mongoService.isEdgeExist(source+"->"+destination))
				return ResponseEntity.status(404).body("Edge Not Found");
			routeService.disableEdge(source, destination);
			
			return ResponseEntity.status(HttpStatus.OK).body("Edge Disabled successfully");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid Ip address");
		}
	}

}
