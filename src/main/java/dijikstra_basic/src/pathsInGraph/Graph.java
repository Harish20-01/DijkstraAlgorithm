package dijikstra_basic.src.pathsInGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph{
    Map<Integer, List<Integer>> adjList ;

    public Graph(){
        this.adjList= new HashMap<>();
    }
    public void addVertex(int i){
        adjList.putIfAbsent(i,new ArrayList<>());
    }
    public void addEdge(int i,int node){
        adjList.get(i).add(node);
        //adjList.get(node).add(i);// the graph is undirected
    }
    public void print(){
        System.out.println("Graph representaion in adjaceny List");
        for(Map.Entry<Integer,List<Integer>> entry:adjList.entrySet()){
            System.out.print(entry.getKey()+" : ");
            for(int i:entry.getValue()){
                System.out.print("-->"+ i);
            }
            System.out.println();
        }
    }
}

