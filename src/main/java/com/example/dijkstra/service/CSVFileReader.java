package com.example.dijkstra.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.example.dijkstra.graph.Edge;
import com.opencsv.CSVReader;

import org.springframework.stereotype.Service;

@Service
public class CSVFileReader {
	private  String filePath ="C:\\Users\\haris\\Downloads\\GraphEdges.csv";
	
	public ArrayList<Edge> readCsv() {
    	ArrayList<Edge> ans = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
            	if(line[0].isEmpty()||line[1].isEmpty()||line[2].isEmpty()||line[0].equals("Source"))
            		continue;
            	String source=line[0].trim();
            	String destination=line[1].trim();
            	int weight=Integer.parseInt(line[2].trim());
            	boolean linkState = line[3].toLowerCase().equals("up"); 
            	if (!isValidIp(source) || !isValidIp(destination))
					throw new Exception();
                System.out.println("Column1: " + line[0] + ", Column2: " + line[1]+" Column3 "+line[2]+"state"+linkState);
                ans.add(new Edge(source,destination,weight,linkState));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
        	System.out.println("Error in parsing file");
        }
        //System.out.println("before returning+ "+ans);
        return ans;
    }
	
	public boolean isValidIp(String ip) throws NumberFormatException {
		String arr[] = ip.split("\\.");
		if (arr.length != 4)
			return false;
		for (String i : arr) {
			int val = Integer.parseInt(i);
			if (!(val >= 0 && val <= 255))
				return false;
		}
		return true;
	}
	
}
