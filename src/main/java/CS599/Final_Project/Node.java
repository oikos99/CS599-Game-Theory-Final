package CS599.Final_Project;

import java.util.ArrayList;

public class Node {

	int nodeID = -1;
	int degree = 0;
	ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public Node() {  }
	
	public Node(int i) {
		nodeID = i;
		
	}
	
	public void incrementDegree() {
		degree++;
	}
	
	public void decrementDegree() {
		degree--;
	}
	
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nodeID == -1) ? 0 : nodeID+"".hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (nodeID == -1) {
            if (other.nodeID != -1)
                return false;
        } else if (!(nodeID == other.nodeID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return nodeID + "";
    }
}
