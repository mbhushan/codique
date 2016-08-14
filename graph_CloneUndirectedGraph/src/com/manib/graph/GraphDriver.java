package com.manib.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * @author manib
 * @problem: 
 * Clone an undirected graph. Each node in the graph contains a label 
 * and a list of its neighbors.
 * 
 * output:
vertices: 4
[label: 1; neighbors: 2 3 ]
[label: 2; neighbors: 1 3 4 ]
[label: 3; neighbors: 1 2 4 ]
[label: 4; neighbors: 2 3 ]
cloned graph: 
[label: 1; neighbors: 2 3 ]
[label: 2; neighbors: 1 3 4 ]
[label: 3; neighbors: 1 2 4 ]
[label: 4; neighbors: 2 3 ]
 */
public class GraphDriver {

	private Graph graph = null;
	private int vertices = 0;
	
	public static void main(String[] args) {
		GraphDriver gd = new GraphDriver();
		int vertices = 4;
		gd.initGraph(vertices);
		
		int src = 1;
		gd.cloneGraph(src);
	}
	
	public void initGraph(int vertices) {
		this.vertices = vertices;
		this.graph = new Graph(vertices);
		
		this.graph.addEdge(1, 2);
		this.graph.addEdge(1, 3);
		this.graph.addEdge(2, 3);
		this.graph.addEdge(2, 4);
		this.graph.addEdge(3, 4);
		
		this.graph.printGraph();
	}
	
	public void cloneGraph(int src) {
		GraphNode newSrc = cloneGraph(this.graph.gnodes[src]);
		//System.out.println("printing graph with cloned start node");
	}
	
	public GraphNode cloneGraph(GraphNode node) {
		//Graph newGraph = new Graph(this.vertices);
		//int start = 1;
		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		Map<GraphNode, GraphNode> hmap = new HashMap<GraphNode, GraphNode>();
		
		//queue.add(this.graph.gnodes[start]);
		queue.add(node);	
		GraphNode uNew = null;
		boolean [] visited = new boolean[this.vertices+1];
		
		
		while (!queue.isEmpty()) {
			GraphNode u = queue.remove();
			visited[u.label] = true;
			if (hmap.containsKey(u)) {
				uNew = hmap.get(u);
			} else {
				uNew = new GraphNode(u.label);
				hmap.put(u, uNew);
			}
			List<GraphNode> vlist = u.neighbors;
			int size = vlist.size();
			for (int i=0; i<size; i++) {
				GraphNode v = vlist.get(i);
				if (visited[v.label]) {
					continue;
				}
				GraphNode vNew = null;
				if (!hmap.containsKey(v)) {
					vNew = new GraphNode(v.label);
					hmap.put(v, vNew);
					queue.add(v);
				} else {
					vNew = hmap.get(v);
				}
				uNew.neighbors.add(vNew);
				vNew.neighbors.add(uNew);
			}
		}
		
		
		//print the cloned graph
		System.out.println("cloned graph: ");
		for (int i=1; i<=this.vertices; i++) {
			System.out.println(hmap.get(this.graph.gnodes[i]).toString());
		}
		
		return hmap.get(node);
	}
}

class Graph {
	GraphNode [] gnodes;
	int vertices;
	
	Graph() {}
	
	Graph(int vertices) {
		this.vertices = vertices;
		this.gnodes = new GraphNode[this.vertices+1];
		for (int i=1; i<=this.vertices; i++) {
			this.gnodes[i] = new GraphNode(i);
		}
	}
	
	public void addEdge(int u, int v) {
		gnodes[u].neighbors.add(gnodes[v]);
		gnodes[v].neighbors.add(gnodes[u]);
	}
	
	public void printGraph() {
		System.out.println("vertices: " + this.vertices);
		for (int i=1; i<=this.vertices; i++) {
			System.out.println(gnodes[i].toString());
		}
	}
}

class GraphNode {
	int label;
	List<GraphNode> neighbors;
	
	public GraphNode(int label) {
		this.label = label;
		this.neighbors = new ArrayList<GraphNode>();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[label: " + this.label +"; neighbors: ");
		if (this.neighbors != null) {
			int size = this.neighbors.size();
			for (int i=0; i<size; i++) {
				sb.append(this.neighbors.get(i).label + " ");
			}
			sb.append("]");
		}
		return sb.toString();
	}
	
}
