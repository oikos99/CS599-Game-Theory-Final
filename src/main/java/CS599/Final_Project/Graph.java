package CS599.Final_Project;

//
//import java.util.List;
//
//public class Graph {
//    private final List<Node> vertexes;
//    private final List<Edge> edges;
//
//    public Graph(List<Node> vertexes, List<Edge> edges) {
//        this.vertexes = vertexes;
//        this.edges = edges;
//    }
//
//    public List<Node> getVertexes() {
//        return vertexes;
//    }
//
//    public List<Edge> getEdges() {
//        return edges;
//    }
//
//
//
//}



import java.util.ArrayList;

/**
 * Created by tarekray on 27/12/16.
 */
public class Graph {
    ArrayList<Node2> nodes ;

    public Graph(int numberOfNodes)
    {
        nodes = new ArrayList<Node2>();
        for(int i = 0 ; i < numberOfNodes ; i++)
        {
        	Node2 n = new Node2(i);
            nodes.add(n) ;
        }
    }

    public int shortestPath(int start, int end) {
        setAllNodesDistanceToInfinity(nodes) ;
        nodes.get(start).distance = 0 ;

        ArrayList<Integer> unvisited = new ArrayList<Integer>() ;

        for(int i = 0 ; i < nodes.size() ; i++)
        {
                unvisited.add(i);
        }

        return shortestPath(start, end, unvisited)  ;
    }

    private int shortestPath(int currentid, int end, ArrayList<Integer> unvisited) {
    	Node2 current = nodes.get(currentid) ;
        for(Tuple<Integer,Integer> t : current.neighbors)
        {
        	Node2 neighbor= getNodeById(nodes, t.first) ;
                neighbor.distance=Math.min(neighbor.distance, current.distance+t.second);
        }

        current.visited = true ;

        unvisited.remove(new Integer(currentid));

        if(nodes.get(end).visited)
        {
            return nodes.get(end).distance;
        }
        else {
            int next = 0;
            int min = Integer.MAX_VALUE;

            for (int n : unvisited) {
            	Node2 temp = nodes.get(n);
                if (temp.distance < min) {
                    next = temp.id;
                    min = temp.distance;
                }
            }
            if (min != Integer.MAX_VALUE) {
                return  shortestPath(next, end, unvisited);
            } else
            {
                return -1 ;//cant reach end node from start
            }
        }
    }


    private Node2 getNodeById(ArrayList<Node2> nodes, int first) {

        for(Node2 n : nodes)
        {
            if(n.id==first)
            {
                return  n ;
            }
        }
        return  null ;
    }

    private void setAllNodesDistanceToInfinity(ArrayList<Node2> nodes) {
        nodes.stream().forEach(n -> n.distance=Integer.MAX_VALUE );
    }
}
