package dijikstra_basic.src.pathsInGraph;

import java.util.*;

public class PathsInGraph {
    static List<List<Integer>> ans ;
    public static  void  main(String[] args){
        ans= new ArrayList<>();

        Graph graph = new Graph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1,2);// 1-->2
        graph.addEdge(1,3);// 1-->3
        graph.addEdge(1,5);// 1-->5

        graph.addEdge(2,3);// 2-->3
        graph.addEdge(2,4);// 2-->4
        graph.addEdge(2,5);// 2-->5

        graph.addEdge(4,5);// 4-->5

        //         1
        //       / | \
        //      2--3  5
        //      |-----|
        //      4-----|

        graph.print();

        findPath(1,5,graph.adjList,new ArrayList<>(),new HashSet<>());

        if(ans.size()==0){
            System.out.println("No path exist between source and destination");
            return;
        }
        System.out.println("Path between Source and Destination");
        for(List<Integer> temp:ans){
            for(int i=0;i<temp.size();i++){
                System.out.print(temp.get(i));
                if(i!=temp.size()-1)
                    System.out.print("-->");
            }
            System.out.println();
        }

    }
    public static void findPath(int start, int end, Map<Integer,List<Integer>> adjList, List<Integer> temp, HashSet<Integer> visited){
        if(visited.contains(start))
            return;
        if(start==end){
            temp.add(start);
            ans.add(new ArrayList<>(temp));
            temp.remove(Integer.valueOf(start));
            return;
        }
        temp.add(start);
        visited.add(start);
        for(int i:adjList.get(start)){
            findPath(i,end,adjList,temp,visited);
        }
        temp.remove(Integer.valueOf(start));
        visited.remove(start);
    }
}
