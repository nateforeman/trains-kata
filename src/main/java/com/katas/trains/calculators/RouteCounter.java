package com.katas.trains.calculators;

import com.katas.trains.graph.Edge;
import com.katas.trains.graph.Graph;
import com.katas.trains.graph.Vertex;

public class RouteCounter {

	private Graph graph;

	public RouteCounter(Graph graph) {
		this.graph = graph;
	}

	public int countRoutesWithMaxStops(String startVertexId, String endVertexId, int maxStops) {
		if (isNotValid(startVertexId, endVertexId)) {
			return 0;
		}
		if (maxStops < 0) {
			return 0;
		}
		if (0 == maxStops && startVertexId.equals(endVertexId)) {
			return 1;
		}
		return countRoutesWithMaxStops(graph.getVertex(startVertexId), endVertexId, maxStops);
	}
	
	public int countRoutesWithExactStops(String startVertexId, String endVertexId, int exactStops) {
		if (isNotValid(startVertexId, endVertexId)) {
			return 0;
		}
		if (exactStops < 0) {
			return 0;
		}
		if (0 == exactStops && startVertexId.equals(endVertexId)) {
			return 1;
		}
		return countRoutesWithExactStops(graph.getVertex(startVertexId), endVertexId, exactStops);
	}
	
	public int countRoutesLessThanMaxDistance(String startVertexId, String endVertexId, int maxDistance) {
		if (isNotValid(startVertexId, endVertexId)) {
			return 0;
		}
					
		return countRoutesWithMaxDistance(graph.getVertex(startVertexId), endVertexId, maxDistance);
	}

	private boolean isNotValid(String startVertexId, String endVertexId) {
		return !graph.containsAll(new String[] { startVertexId, endVertexId });
	}
	
	private int countRoutesWithMaxDistance(Vertex currentVertex, String endVertexId, int maxDistance) {
		int count = 0;
		if (maxDistance > 0) {
			Edge endEdge = currentVertex.getEdgeFor(endVertexId);
			if (null != endEdge && endEdge.getWeight() < maxDistance) {
				count += 1;
			}
			for (Edge curEdge : currentVertex.getEdges()) {
				Vertex nextVertex = graph.getVertex(curEdge.getEndVertexId());
				count += countRoutesWithMaxDistance(nextVertex, endVertexId, maxDistance-curEdge.getWeight());
			}
		}
		return count;
	}

	private int countRoutesWithExactStops(Vertex currentVertex, String endVertexId, int exactStops) {
		int count = 0;
		if (1 == exactStops && currentVertex.isConnectedTo(endVertexId)) {
			count += 1;
		}
		if (exactStops > 1) {
			for (Edge curEdge : currentVertex.getEdges()) {
				Vertex nextVertex = graph.getVertex(curEdge.getEndVertexId());
				count += countRoutesWithExactStops(nextVertex, endVertexId, exactStops-1);
			}
		}
		return count;
	}

	private int countRoutesWithMaxStops(Vertex currentVertex, String endVertexId, int maxStops) {
		int count = 0;
		if (currentVertex.isConnectedTo(endVertexId)) {
			count += 1;
		}
		if (maxStops > 1) {
			for (Edge curEdge : currentVertex.getEdges()) {
				Vertex nextVertex = graph.getVertex(curEdge.getEndVertexId());
				count += countRoutesWithMaxStops(nextVertex, endVertexId, maxStops-1);
			}
		}
		return count;
	}

}
