package CS599.Final_Project;

import java.util.ArrayList;


public class Node2 {

    int id ;
    ArrayList<Tuple<Integer, Integer>> neighbors ;
    int distance ;
    boolean  visited ;
    public Node2(int id)
    {
        this.visited = false ;
        this.id = id ;
        neighbors = new ArrayList<Tuple<Integer, Integer>>();
    }

    public Node2 addNeighbor(int neighborID, int weight)
    {
        neighbors.add(new Tuple<Integer, Integer>(neighborID, weight)) ;
        return  this ;
    }



}
