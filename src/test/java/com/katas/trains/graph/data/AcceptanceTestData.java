package com.katas.trains.graph.data;

import com.katas.trains.graph.Graph;

public class AcceptanceTestData {
	
	public static Graph makeForGraphForAcceptanceTest() {
		// AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		return new Graph()
			.addEdge("A", "B", 5)
			.addEdge("B", "C", 4)
			.addEdge("C", "D", 8)
			.addEdge("D", "C", 8)
			.addEdge("D", "E", 6)
			.addEdge("A", "D", 5)
			.addEdge("C", "E", 2)
			.addEdge("E", "B", 3)
			.addEdge("A", "E", 7);
	}
}
