package com.katas.trains.graph.data;

import com.katas.trains.graph.Edge;
import com.katas.trains.graph.Graph;

public class TestData {
	
	public static final int EDGE_WEIGHT_A_TO_B = 1;
	public static final int EDGE_WEIGHT_A_TO_C = 2;
	public static final int EDGE_WEIGHT_A_TO_D = 3;
	
	public static final int EDGE_WEIGHT_B_TO_A = 4;
	public static final int EDGE_WEIGHT_B_TO_C = 5;
	public static final int EDGE_WEIGHT_B_TO_D = 6;
	
	public static final int EDGE_WEIGHT_C_TO_A = 7;
	public static final int EDGE_WEIGHT_C_TO_B = 8;
	public static final int EDGE_WEIGHT_C_TO_D = 9;
	
	public static final int EDGE_WEIGHT_D_TO_A = 10;
	public static final int EDGE_WEIGHT_D_TO_B = 11;
	public static final int EDGE_WEIGHT_D_TO_C = 12;
	
	public static final Edge EDGE_A_TO_B = new Edge("B", EDGE_WEIGHT_A_TO_B);
	public static final Edge EDGE_A_TO_C = new Edge("C", EDGE_WEIGHT_A_TO_C);
	public static final Edge EDGE_A_TO_D = new Edge("D", EDGE_WEIGHT_A_TO_D);
	
	public static final Edge EDGE_B_TO_A = new Edge("A", EDGE_WEIGHT_B_TO_A);
	public static final Edge EDGE_B_TO_C = new Edge("C", EDGE_WEIGHT_B_TO_C);
	public static final Edge EDGE_B_TO_D = new Edge("D", EDGE_WEIGHT_B_TO_D);
	
	public static final Edge EDGE_C_TO_A = new Edge("A", EDGE_WEIGHT_C_TO_A);
	public static final Edge EDGE_C_TO_B = new Edge("B", EDGE_WEIGHT_C_TO_B);
	public static final Edge EDGE_C_TO_D = new Edge("D", EDGE_WEIGHT_C_TO_D);
	
	public static final Edge EDGE_D_TO_A = new Edge("A", EDGE_WEIGHT_D_TO_A);
	public static final Edge EDGE_D_TO_B = new Edge("B", EDGE_WEIGHT_D_TO_B);
	public static final Edge EDGE_D_TO_C = new Edge("C", EDGE_WEIGHT_D_TO_C);
	
	public static Graph makeOneWayGraph() {
		return new Graph()
			.addEdge("A", "B", EDGE_WEIGHT_A_TO_B)
			.addEdge("B", "C", EDGE_WEIGHT_B_TO_C)
			.addEdge("C", "D", EDGE_WEIGHT_C_TO_D);
	}
	
	public static Graph makeFullyConnectedGraph() {
		return new Graph()
			.addEdge("A", "B", EDGE_WEIGHT_A_TO_B)
			.addEdge("A", "C", EDGE_WEIGHT_A_TO_C)
			.addEdge("A", "D", EDGE_WEIGHT_A_TO_D)
			
			.addEdge("B", "A", EDGE_WEIGHT_B_TO_A)
			.addEdge("B", "C", EDGE_WEIGHT_B_TO_C)
			.addEdge("B", "D", EDGE_WEIGHT_B_TO_D)
			
			.addEdge("C", "A", EDGE_WEIGHT_C_TO_A)
			.addEdge("C", "B", EDGE_WEIGHT_C_TO_B)
			.addEdge("C", "D", EDGE_WEIGHT_C_TO_D)
			
			.addEdge("D", "A", EDGE_WEIGHT_D_TO_A)
			.addEdge("D", "B", EDGE_WEIGHT_D_TO_B)
			.addEdge("D", "C", EDGE_WEIGHT_D_TO_C);
	}
}
