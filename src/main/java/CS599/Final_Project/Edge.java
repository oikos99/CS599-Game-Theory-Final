package CS599.Final_Project;

public class Edge {

	int edgeID;
	int node1;
	int node2;
	Node source;
	Node destination;
	int weight;	
	
	public Edge() { }
	public Edge(int n1, int n2) {
		node1=n1;
		node2=n2;
	}
    public int getId() {
        return edgeID;
    }
    public Node getDestination() {
        return destination;
    }

    public Node getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
