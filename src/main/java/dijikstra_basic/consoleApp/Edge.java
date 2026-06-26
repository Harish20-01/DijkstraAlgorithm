package dijikstra_basic.consoleApp;

public class Edge {
	private String dest;
	private int weight;
	public Edge(String d,int w) {
		dest=d;
		weight=w;
	}
	public String getVertex() {
		return dest;
	}
	public int getWeight() {
		return weight;
	}
	public String toString() {
		return "-->"+dest+ "(W=>"+weight+")";
	}
}
