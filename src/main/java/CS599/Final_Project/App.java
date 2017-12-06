package CS599.Final_Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


/**
 * Hello world!
 *
 */
public class App 
{
	static ArrayList<Node> nodes = new ArrayList<Node>();
	static ArrayList<Edge> edges = new ArrayList<Edge>();
	static int numNodes;
	
	
	public static Node getNode(int id) {
		Node found = null;
		for(int i=0; i< nodes.size(); i++) {
			if(nodes.get(i).nodeID == id) {
				found = nodes.get(i);
			}
		}
		return found;
	}
	public static ArrayList<Node> getNodesFromEdge(Edge ed) {
		ArrayList<Node> nds = new ArrayList<Node>();
		for(int i=0; i< nodes.size(); i++) {
			if(nodes.get(i).nodeID == ed.node1) {
				nds.add(nodes.get(i));
			}
			if(nodes.get(i).nodeID == ed.node2) {
				nds.add(nodes.get(i));
			}
		}
		return nds;
	}
	
	public ArrayList<Edge> getEdgesFromNode(Node nd) {
		return nd.edges;
	}
	
	public static ArrayList<Node> getNeighbor(Node nd) {
		ArrayList<Node> nds = new ArrayList<Node>();
		for(int i=0; i< nd.edges.size(); i++) {
			ArrayList<Node> ndstemp = getNodesFromEdge(nd.edges.get(i));
			for(int j=0; j< ndstemp.size(); j++) {
				if(ndstemp.get(j) != nd) {
					nds.add(ndstemp.get(j));
				}
			}
		}
		return nds;
	}
	
	public static ArrayList<Node> getNotNeighbor(Node nd) {
		ArrayList<Node> nds = new ArrayList<Node>();
		for(int i=0; i< nd.edges.size(); i++) {
			ArrayList<Node> ndstemp = getNodesFromEdge(nd.edges.get(i));
			for(int j=0; j< ndstemp.size(); j++) {
				if(ndstemp.get(j) != nd) {
					nds.add(ndstemp.get(j));
				}
			}
		}
		
		ArrayList<Node> nds2 = new ArrayList<Node>();
		for(int i=0; i< nodes.size(); i++) {
//			for(int j=0; j< nds.size(); j++) {
				if (!nds.contains(nodes.get(i))) {
					nds2.add(nodes.get(i));
				}				
				
//			}

			
		}
		return nds2;
	}
	
	public static void addLink(int x, int y) {
		int exist = 0;
		Node source = getNode(x);
		Node destination = getNode(y);
		for(int i=0; i< edges.size(); i++) {
			Edge e = edges.get(i);
			
			int a = e.node1;
			int b = e.node2;
			if((a == x && b == y) || (b == x && a == y)) {
				exist++;
				break;
			}
			
		}
		
		if(exist == 0) {
			Edge ed = new Edge(x, y);
			ed.source = source;
			ed.destination = destination;
			edges.add(ed);
			
//			Edge ed2 = new Edge(x, y);
//			ed.source = destination;
//			ed.destination = source;
//			edges.add(ed2);		
			
			Node nd = getNode(x);
			nd.incrementDegree();
			nd.edges.add(ed);
			Node nd2 = getNode(y);
			nd2.incrementDegree();	
			nd2.edges.add(ed);
		}
	}
	
	public static void printNodes(ArrayList<Node> arr) {
		for(int i=0; i< arr.size(); i++) {
			System.out.println(arr.get(i).nodeID);
		}
	}
	
	public static void printEdges(ArrayList<Edge> arr) {
		for(int i=0; i< arr.size(); i++) {
			System.out.println(arr.get(i).node1 + ", " + arr.get(i).node2);
		}
	}
	
	public static void removeLink(int x, int y) {

		for(int i=0; i< edges.size(); i++) {
			Edge e = edges.get(i);
			
			int a = e.node1;
			int b = e.node2;
			if((a == x && b == y) || (b == x && a == y)) {
				// link/edge exists
				e.destination = null;
				e.source = null;
				edges.remove(i);
				
				
				
				
				break;
			}
		}
		
		Node nd = getNode(x);

		for(int i=0; i< nd.edges.size(); i++) {
			Edge e = nd.edges.get(i);
			int a = e.node1;
			int b = e.node2;
			if((a == x && b == y) || (b == x && a == y)) {
				nd.edges.remove(i);
						nd.decrementDegree();
				break;
			}
		}

		Node nd2 = getNode(y);


		for(int i=0; i< nd2.edges.size(); i++) {
			Edge e = nd2.edges.get(i);
			int a = e.node1;
			int b = e.node2;
			if((a == x && b == y) || (b == x && a == y)) {
				nd2.edges.remove(i);
						nd2.decrementDegree();
				break;
			}
		}
	}
	
	public static int getNumberOfHops(int x, int y) {
//		int smaller = Math.min(x, y);
//		int larger = Math.max(x, y);
//        Graph graph = new Graph(nodes, edges);
//        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
//        dijkstra.execute(getNode(smaller));
//        LinkedList<Node> path = dijkstra.getPath(getNode(larger));
//
////        System.out.println(path.size());
////
////        for (Node vertex : path) {
////            System.out.println(vertex);
////        }
//		return path.size();
		
        GraphBuilder gb = new GraphBuilder();
        gb.build(numNodes);
//        gb.withEdge(1, 2, 1);
//        gb.withEdge(2, 3, 1);
//        gb.withEdge(8, 3, 1);

        for(int i=0; i< edges.size(); i++) {
        	Edge e = edges.get(i);	
        	gb.withEdge(e.node1, e.node2, 1);
        }
        
        //System.out.println(gb.get().shortestPath(1, 8));
		
		return gb.get().shortestPath(x, y);
	}
	
	public static void getNodeDegreeDistribution() {
		int totalNumNodes = numNodes;
		int[] freq = new int[totalNumNodes];
		for(int i=0; i< freq.length; i++) {
			freq[nodes.get(i).degree]++;
		}
		for(int i=0; i< freq.length; i++) {
			double freqq = (freq[i] / (double)totalNumNodes);
	        System.out.println(i + " " + freqq + "%, " + freq[i]);
		}
	}
	
	public static int getCostOfGraphPart2(int i) {
		int sum = 0;
		//for(int i=0; i< nodes.size(); i++) {
			for(int j=0; j< nodes.size(); j++) {
				if(i != nodes.get(j).nodeID) {
					if(getNumberOfHops(i, nodes.get(j).nodeID) == -1) {
						return 99999999;
					}
					sum += getNumberOfHops(i, nodes.get(j).nodeID);	
				}
			}			
		//}
		return sum;
	}
	
	public static int getCostOfGraphPart1(String model, int i, double a) {
		ArrayList<Node> neighbor = getNeighbor(getNode(i));
		int sum = 0;
		for(int k=0; k< neighbor.size(); k++) {
			sum += getLinkingCost(model, neighbor.get(k).nodeID);
		}

		return (int) (a * sum);
	}

	public static void linkAdditionCalc(String model, int i, double a) {
		ArrayList<Node> notNeighbor = getNotNeighbor(getNode(i));
		
//		Random ran = new Random();
//		int x = ran.nextInt(notNeighbor.size());
		
	    Integer[] arr = new Integer[notNeighbor.size()];
	    for (int k = 0; k < arr.length; k++) {
	        arr[k] = k;
	    }
	    Collections.shuffle(Arrays.asList(arr));
		
	    for (int l = 0; l < arr.length; l++) {
			int randomJ = notNeighbor.get(arr[l]).nodeID;
			
			int costOld = getCostOfGraphPart1(model, i, a) + getCostOfGraphPart2(i);
	        System.out.println("oldcost " + costOld);

			addLink(i,randomJ);
			
			int costNew = getCostOfGraphPart1(model, i, a) + getCostOfGraphPart2(i);
	        System.out.println("newcost " + costNew);

			if(costOld > costNew) {
				break;
			}
			else {
				removeLink(i,randomJ);
			}
	    }
	    
	}
	
	public static void linkDroppingCalc(String model, int i, double a) {
		ArrayList<Node> neighbor = getNeighbor(getNode(i));
		int nodeToDropID = -1;
		int minCost = getCostOfGraphPart1(model, i, a) + getCostOfGraphPart2(i);

		
	    Integer[] arr = new Integer[neighbor.size()];
	    for (int k = 0; k < arr.length; k++) {
	        arr[k] = k;
	    }
	    Collections.shuffle(Arrays.asList(arr));
		
	    for (int l = 0; l < arr.length; l++) {
		
		
		//for(int j=0; j< neighbor.size(); j++) {
			
	        System.out.println("mincost " + minCost);

			
			int jIndex = neighbor.get(arr[l]).nodeID;
			removeLink(i,jIndex);
			int costNew = getCostOfGraphPart1(model, i, a) + getCostOfGraphPart2(i);
	        System.out.println("newcost " + costNew);
	        addLink(i,jIndex);
			if(minCost > costNew) {
				minCost = costNew;
				nodeToDropID = jIndex;
				
			}
		}
		if(nodeToDropID != -1) {
			removeLink(i,nodeToDropID);
		}
		
	}
	
	public static int getLinkingCost(String model, int j) {
		if(model == "unitcountout") {
			return 1;
		}
		else if(model == "unitnodedegree") {
			return getNode(j).degree;
		}
		else
			return -99999999;
	}
	
	public static double getPathLength() {
		double pairsCount = 0;
		double totalHops = 0;
		for(int i=0; i< nodes.size(); i++) {
			for(int j=i; j< nodes.size(); j++) {
				totalHops += getNumberOfHops(i, j);
				pairsCount++;
			}
		}
		return (totalHops / pairsCount);
	}
	
	public static void generateCompleteGraph(int n) {
//		int idCounter = 0;
		for(int i=0; i< n; i++) {
			Node nd = new Node(i);
			nodes.add(nd);
		}
		for(int i=0; i< n; i++) {
            for(int j=i+1; j< n; j++) {
                //System.out.println(i + ", " + j);
                addLink(i, j);
            }
        }
		
	}
	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        numNodes = 20;
        double alpha = 1;
        generateCompleteGraph(numNodes);
////	      addLink(1,2);
////	      addLink(2,3);
////	      addLink(2,4);
////	      addLink(1,6);
////	      addLink(1,5);
////	      addLink(6,7);        
//	      addLink(0,1);
//	      addLink(1,2);
//	      addLink(1,3);
//	      addLink(0,5);
//	      addLink(0,4);
//	      addLink(5,6);        
        
        
        System.out.println(edges.size());
//        printNodes(nodes);
//        printEdges(edges);
        
//        removeLink(7, 1);
//        removeLink(5,7);
//        
//        addLink(5,7);
//        addLink(5,7);
//        addLink(7,5);
//        removeLink(7, 1);
//        removeLink(1, 7);


//        ArrayList<Node> neighbor = getNotNeighbor(getNode(9));
//        printNodes(neighbor);
//        System.out.println(edges.size());
        
        
        System.out.println("#hops: " + getNumberOfHops(2,0));
        //System.out.println(getNode(7).degree);
        getNodeDegreeDistribution();
        
//        linkAdditionCalc("unitcountout", 1, 50);
//        System.out.println(edges.size());
        int exeCount=0;
        while(exeCount < 50) {
    	    Integer[] arr_add = new Integer[nodes.size()];
    	    Integer[] arr_del = new Integer[nodes.size()];

    	    for (int k = 0; k < arr_add.length; k++) {
    	    	arr_add[k] = k;
    	    	arr_del[k] = k;
    	    }
    	    Collections.shuffle(Arrays.asList(arr_add));
    	    Collections.shuffle(Arrays.asList(arr_del));

    	    for (int l = 0; l < arr_add.length; l++) {
        	linkAdditionCalc("unitcountout", arr_add[l], alpha);
	        linkDroppingCalc("unitcountout", arr_del[l], alpha);
	        
//        	linkAdditionCalc("unitnodedegree", arr_add[l], 60);
//	        linkDroppingCalc("unitnodedegree", arr_del[l], 60);	        
	        
	        System.out.println(edges.size());
	        exeCount++;
        }
        }
        getNodeDegreeDistribution();

        System.out.println("path avg: " + getPathLength());
        
        int totalCost = 0;
        for (int m = 0; m < nodes.size(); m++) {
        	totalCost += getCostOfGraphPart1("unitnodedegree", m, alpha) + getCostOfGraphPart2(m);

        	
        }
        
        System.out.println("cost: " + totalCost);

        //System.out.println("edges: " + edges.size() + ", " + (double)(edges.size()/(double)numNodes) + "%");

        int resultEdgeAmount1 = edges.size();
        
        //randomRemovePercentageNodes(10);
        randomRemovePercentageNodes2(20);
        
        int resultEdgeAmount2 = edges.size();
        System.out.println("edges1: " + resultEdgeAmount1 + ", " + "edges2: " + resultEdgeAmount2 + ", " + (double)(resultEdgeAmount2/(double)resultEdgeAmount1)*100 + "%");

    }
    
	public static void randomRemovePercentageNodes(double n) {
		Random ran = new Random();
		int numToRemove = (int) (nodes.size() * (double)(n/100));
		for(int i=0; i< numToRemove; i++) {
			int x = ran.nextInt(nodes.size());
			
			edges.remove(nodes.get(x).edges.get(0));
			edges.remove(nodes.get(x).edges.get(1));

			
		}
		
	}
	
	public static void randomRemovePercentageNodes2(double n) {
		Random ran = new Random();
		int numToRemove = (int) (nodes.size() * (double)(n/100));
		int largestDegree, largestIndex;
		for(int i=0; i< numToRemove; i++) {
			largestDegree = -9999;
			largestIndex = 0;
			for(int j=0; j< nodes.size(); j++) {
				if(nodes.get(j).degree > largestDegree) {
					largestDegree = nodes.get(j).degree;
					largestIndex = j;
				}
		        //System.out.println(nodes.get(j).degree);

			}
			
			for(int j=0; j< nodes.get(largestIndex).edges.size(); j++) {
			edges.remove(nodes.get(largestIndex).edges.get(j));
//			edges.remove(nodes.get(largestIndex).edges.get(1));

			}
	        //System.out.println("ld: " + largestDegree + ", " + "edges2: " + largestIndex);

		}
		
	}
}



