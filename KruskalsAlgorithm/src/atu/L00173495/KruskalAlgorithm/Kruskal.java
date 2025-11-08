package atu.L00173495.KruskalAlgorithm;

//Java Program to Implement
//Kruskal's Algorithm
import java.util.*;

//Kruskal class
public class Kruskal {
	// Main method
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		//Create an instance if scanner to allow user input
		Scanner input = new Scanner(System.in);
		//Open the scanner
		try {
			// Ask user for number of vertices and edges in the Kruskal
			System.out.println("How many vertices does your graph have?");
			int V = input.nextInt();
			System.out.println("How many edges does your graph have?");
			int E = input.nextInt();
			// Create a graph with V vertices and E edges
			Graph graph = new Graph(V, E);


			System.out.println("First vertex is 0");
			System.out.println("Last vertex is " + (V - 1));
			//For every edge get the source, destination and the weight of the edge
			for (int i = 0; i < E; i++) {
				System.out.println();
				System.out.println("Edge "+(i+1)+ " of "+ E);
				System.out.println("What is the source of the edge " + (i + 1) + "?");
				int src = input.nextInt();
				System.out.println("What is the destination of the edge " + (i + 1) + "?");
				int dest = input.nextInt();
				System.out.println("What is the weight of the edge ?");
				int weight = input.nextInt();
				graph.edges[i] = new Edge(src, dest, weight);

			}
			//Once all the edges have been identified call the kruskalMST function to identify the MST
			graph.kruskalMST();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			input.close();
		}

		// Add edges to the Graph
	}

	// Edge class
	// Represents the connections between the vertices
	public static class Edge implements Comparable<Edge> {
		// int src is used to declare the starting vertex of the connection
		// int dest is used to declare the vertex that the src connects to
		// int weight is used to represent the cost of the connection.
		int src, dest, weight;

		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}

		// Compare two edges based on their weight
		// This is conducted to find the most cost effective edge
		@Override
		public int compareTo(Edge compareEdge) {
			return this.weight - compareEdge.weight;
		}
	}

	// Graph class
	// Used to store a collection of the total number of vertices and Edges
	public static class Graph {
		int V, E; // Number of vertices and edges
		Edge[] edges; // Array to store all edges

		// Constructor
		public Graph(int V, int E) {
			this.V = V;
			this.E = E;
			edges = new Edge[E];
		}

		// A utility function to find the subset of an element i
		int find(int[] parent, int i) {
			if (parent[i] == -1)
				return i;
			return find(parent, parent[i]);
		}

		// A utility function to do union of two subsets
		void union(int[] parent, int x, int y) {
			int xset = find(parent, x);
			int yset = find(parent, y);
			parent[xset] = yset;
		}

		// Function to perform Kruskal's algorithm to find the MST
		void kruskalMST() {
			// This will store the resultant MST
			Edge[] result = new Edge[V - 1];
			int e = 0; // Index variable for result[]
			int i = 0; // Index variable for sorted edges

			// Sort all the edges in non-decreasing
			// order of their weight
			Arrays.sort(edges);

			// Allocate memory for creating V subsets
			int[] parent = new int[V];
			Arrays.fill(parent, -1);

			// Number of edges to be taken is equal to V-1
			while (e < V - 1) {
				// Pick the smallest edge. Check if it forms
				// a cycle with the MST formed so far
				Edge next_edge = edges[i++];

				int x = find(parent, next_edge.src);
				int y = find(parent, next_edge.dest);

				// If including this edge does not cause a cycle, include
				// it in the result and increment the index of the result
				if (x != y) {
					result[e++] = next_edge;
					union(parent, x, y);
				}
			}

			// Print the result MST
			System.out.println("Edges in the MST:");
			for (i = 0; i < e; ++i)
				System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
		}
	}
}
