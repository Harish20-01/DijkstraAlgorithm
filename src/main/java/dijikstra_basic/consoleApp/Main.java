package dijikstra_basic.consoleApp;
import java.util.*;

public class Main {
	public static void main(String args[]) throws Exception{
		Graph graph = new Graph();
		List<String[]> rows = CsvReader.readCsv("C:\\Users\\haris\\Downloads\\GraphEdges.csv");
		for(String i[]:rows) {
			if(i.length==0||i[0].isEmpty()||i[1].isEmpty()||i[2].isEmpty()||i[0].equals("Source"))
				continue;
			String source = i[0].trim();
			String destination = i[1].trim();
			int weight = Integer.parseInt(i[2].trim());
			graph.addEdge(source, destination, weight);
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Source node");
		String source = sc.nextLine();
		System.out.println("Enter the destination node");
		String destination = sc.nextLine();
		
		graph.shortestPath(source, destination);
	}
}
