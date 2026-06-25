package dijikstra_basic.src.graphTraversal;

import java.util.*;

class Graph{
    Map<Integer,List<Integer>> adjList;
    Graph(){
        adjList= new HashMap<>();
    }
    public void addVertex(int i){
        adjList.putIfAbsent(i, new ArrayList<>());
    }
    public void addEdges(int i,int k){
        adjList.get(i).add(k);
        adjList.get(k).add(i);
    }
    public void print(){
        for(Map.Entry<Integer,List<Integer>> entry:adjList.entrySet()){
            System.out.print(entry.getKey()+" : ");
            for(int i:entry.getValue()){
                System.out.print("-->"+i+" ");
            }
            System.out.println();
        }
    }
    public static void main(String args[]){
        Graph graph = new Graph();
        graph.addVertex(1);
        graph.addVertex((2));
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdges(1, 2);
        graph.addEdges(1,3);
        graph.addEdges(2, 4);
        System.out.println("Graph");
        graph.print();
        System.out.println("DFS Traversal");
        graph.dfs(1,new HashSet<>());
        System.out.println();
        System.out.println("BFS Traversal");
        graph.bfs(1);
    }
    public void dfs(int start,HashSet<Integer> set){
        if(set.contains(start))
            return;
        set.add(start);
        System.out.print(start+" ");
        for(int i:adjList.get(start)){
            dfs(i,set);
        }
    }
    public void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        while(!queue.isEmpty()){
            int val = queue.poll();
            System.out.print(val+" ");
            for(int i:adjList.get(val)){
                if(!visited.contains(i)){
                    queue.add(i);
                    visited.add(i);
                }
            }
        }
    }
}