package dijikstra_basic.src.dijikstraAlgorithm;

import java.util.*;

class  GraphWithWeight{
    Map<Integer,List<List<Integer>>> adjList;
    
    public GraphWithWeight(){
        this.adjList= new HashMap<>();
    }
    public void addVertex(int i){
        adjList.putIfAbsent(i,new ArrayList<>());
    }
    public void addEdges(int i,List<List<Integer>> node){
        adjList.putIfAbsent(i, new ArrayList<>());
        for(List<Integer> temp : node){
            int n = temp.get(0);
            //1->[[2,5],[3,6]]
            int weight = temp.get(1);
            // add edge i to n

            adjList.get(i).add(Arrays.asList(n, weight));

            // add reverse edge n to i
            adjList.putIfAbsent(n, new ArrayList<>());
            adjList.get(n).add(Arrays.asList(i, weight));
        }
    }
    public void print(){
        for(Map.Entry<Integer,List<List<Integer>>> entry:adjList.entrySet()){
            System.out.print(entry.getKey()+" : ");
            for(List<Integer> temp: entry.getValue()){
                System.out.print("--|W="+temp.get(1)+"|-->"+temp.get(0));
            }
            System.out.println();
        }
    }
}
