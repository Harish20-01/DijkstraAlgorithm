package dijikstra_basic.src.dijikstraAlgorithm;

import java.util.*;

class DijikstraAlgorithm {
    private Map<Integer,List<List<Integer>>> adjList;

    //--map to store the shortest path with thier weight (1->2->3,10)
    private Map<String,Integer> paths;

    private int  source;

    //-- dist arr to store shortest distance to all the vertex in the graph
    private int dist[];

    //--parent arr to store the origin(previous node) of the vertex
    private int parent[];


    //-- used to store vertex and its weight in the Priority Queue
    private class Edge{
    	private int ver;
        private int weight;
        Edge(int weight,int ver){
            this.weight=weight;
            this.ver=ver;
        }
    	
        public int getVer() {
			return ver;
		}
		public int getWeight() {
			return weight;
		}
		
    }

    //--used to keep the shortest(leat weight) vertex at the top (Min Heap)
    private PriorityQueue<Edge> pq;

    public DijikstraAlgorithm(int source,Map<Integer,List<List<Integer>>> grapgh){
        this.source=source;
        this.adjList=grapgh;
        this.paths=new HashMap<>();
        this.dist = new int[grapgh.size()+1];
        this.parent=new int[grapgh.size()+1];
        this.pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.weight==o2.weight)
                    return o1.ver- o2.ver;
                return o1.weight-o2.weight;
            }
        });
        Arrays.fill(dist,(int)1e9);
        //System.out.println(Arrays.toString(dist));
        dist[source]=0;
        parent[source]=source;
    }
    public  void findShortestPath(){
        pq.add(new Edge(0,source));
        while(!pq.isEmpty()){
            Edge t = pq.poll();
            int vertex = t.getVer();
            int weight = t.getWeight();
            for(List<Integer> list:adjList.get(vertex)){

                if(weight+list.get(1)<dist[list.get(0)]){
                    dist[list.get(0)]=weight+list.get(1);
                    pq.add(new Edge(weight+list.get(1),list.get(0)));
                    //from current vertex it will find the shortest to the dest
                    parent[list.get(0)] = vertex;
                }
            }
        }
        mapWeightAndPath();
    }
    public void mapWeightAndPath(){
        for(int i=parent.length-1;i>0;i--){
            StringBuilder temp = new StringBuilder();
            int node =i;
            while(parent[node]!=node){
                temp.append(node).append(">--");
                node=parent[node];
            }
            temp.append(node);
            paths.put(temp.reverse().toString(),dist[i]);
        }
    }

    public void print(){
        System.out.println("Shortest Path with their distance");
        for(Map.Entry<String ,Integer> path:paths.entrySet())
            System.out.println(path.getKey()+" = "+path.getValue());
    }
}
