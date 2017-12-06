package CS599.Final_Project;

public class GraphBuilder {

    Graph g;


    public  GraphBuilder build(int i) {
        g = new Graph(i) ;
        return  this ;
    }

    public GraphBuilder withEdge(int n1, int n2, int weight) {
        g.nodes.get(n1).addNeighbor(n2, weight) ;
        g.nodes.get(n2).addNeighbor(n1, weight) ;
        return  this ;
    }

    public Graph get() {
        return g;
    }
}
