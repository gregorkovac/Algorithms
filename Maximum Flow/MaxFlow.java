import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;

public class MaxFlow {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, m;
		Network net;

		n = sc.nextInt();
		m = sc.nextInt();

		net = new Network(n);

		for (int i = 0; i < m; i++) {
			int n1, n2, k;
			n1 = sc.nextInt();
			n2 = sc.nextInt();
			k = sc.nextInt();

			net.addConnection(n1, n2, k);
		}

		net.maxFlow(0, n-1);

		sc.close();
    }
}

class Node implements Comparable<Node> {	
	int id;
	//marks for the algorithm
	//------------------------------------
	boolean marked = false;
	Edge augmEdge = null; //the edge over which we brought the flow increase
	int incFlow = -1; //-1 means a potentially infinite flow
	//------------------------------------
	ArrayList<Edge> inEdges;
	ArrayList<Edge> outEdges;
	
	public Node(int i) {
		id = i;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
	}

	public int compareTo(Node n) {
		if (id == n.id) return 0;
		if (id > n.id) return 1;
		return -1;
	}
}

class Edge{
	int startID; 
	int endID;
	int capacity; 
	int currFlow;
	
	public Edge(int fromNode, int toNode, int capacity2) {
		startID = fromNode;
		endID = toNode;
		capacity = capacity2;
		currFlow = 0;
	}
}

class Network{
	Node[] nodes;
	
	/**
	 * Create a new network with n nodes (0..n-1).
	 * @param n the size of the network.
	 */
	public Network(int n){
		nodes = new Node[n];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i]= new Node(i);
		}
	}
	/**
	 * Add a connection to the network, with all the corresponding in and out edges.
	 * @param fromNode
	 * @param toNode
	 * @param capacity
	 */
	public void addConnection(int fromNode, int toNode, int capacity){
		Edge e = new Edge(fromNode, toNode, capacity);
		nodes[fromNode].outEdges.add(e);
		nodes[toNode].inEdges.add(e);
	}

	/**
	 * Reset all the marks of the algorithm, before the start of a new iteration.
	 */
	public void resetMarks(){
		for (int i = 0; i < nodes.length; i++) {
			nodes[i].marked = false;
			nodes[i].augmEdge = null;
			nodes[i].incFlow = -1;
		}
	}

	public boolean pathExists(int start, int end) {
		boolean visited[] = new boolean[nodes.length];
		Queue<Node> q = new PriorityQueue<Node>(); 
		
		visited[start] = true;
		q.add(nodes[start]);

		while (!q.isEmpty()) {
			Node curr = q.remove();

			visited[curr.id] = true;
			if (curr.id == end)
				return true;
			
			for (Edge e : curr.outEdges) {
				if (!visited[e.endID] && e.capacity > e.currFlow && !q.contains(nodes[e.endID])) {
					q.add(nodes[e.endID]);
					nodes[e.endID].augmEdge = e;
				}
			}

			for (Edge e : curr.inEdges) {
				if (!visited[e.startID] && e.currFlow > 0 && !q.contains(nodes[e.startID])) {
					q.add(nodes[e.startID]);
					nodes[e.startID].augmEdge = e;
				}
			}
		}

		return false;
	}

	public int maxFlow(int start, int end) {
		int max = 0;
		int pathFlow;

		while (pathExists(start, end)) {
			pathFlow = Integer.MAX_VALUE;

			Node curr = nodes[nodes.length - 1];
			while (curr != nodes[0]) {
				if (curr.augmEdge.endID == curr.id) {
					pathFlow = Math.min(pathFlow, curr.augmEdge.capacity - curr.augmEdge.currFlow);
					curr = nodes[curr.augmEdge.startID];
				} else {
					pathFlow = Math.min(pathFlow, curr.augmEdge.currFlow);
					curr = nodes[curr.augmEdge.endID];
				}
			}

			max += pathFlow;

			curr = nodes[nodes.length - 1];
			while (curr != nodes[0]) {
				if (curr.augmEdge.endID == curr.id) {
					curr.augmEdge.currFlow += pathFlow;
					curr = nodes[curr.augmEdge.startID];
				} else {
					curr.augmEdge.currFlow -= pathFlow;
					curr = nodes[curr.augmEdge.endID];
				}
			}

			int oneSpace = 0;

			System.out.print(pathFlow + ": ");
			curr = nodes[nodes.length - 1];
			while (curr != nodes[0]) {
				if (oneSpace == 1) {
					System.out.print(" ");
					oneSpace++;
				} else if (curr != nodes[nodes.length - 1])
					System.out.print("  ");
				System.out.print(curr.id);
				if (curr.augmEdge.endID == curr.id) {
					System.out.print("+");
					curr = nodes[curr.augmEdge.startID];
				} else {
					System.out.print("-");

					if (curr.id == 1)
						oneSpace++;

					curr = nodes[curr.augmEdge.endID];
				}
			}
			System.out.println("  0");

		}

		return max;
	}
}