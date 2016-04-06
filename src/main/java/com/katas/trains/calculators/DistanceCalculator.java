package com.katas.trains.calculators;

import java.util.HashSet;
import java.util.Set;

import com.katas.trains.graph.Edge;
import com.katas.trains.graph.Graph;
import com.katas.trains.graph.Vertex;

public class DistanceCalculator {

	private final Graph graph;

	public DistanceCalculator(Graph graph) {
		this.graph = graph;
	}

	public Integer getTotalDistanceForRoute(String... vertexIds) {
		if (!graph.containsAll(vertexIds)) {
			return null;
		}
		if (vertexIds.length <= 1) {
			return 0;
		}
		return sumEdgeWeightsForRoute(vertexIds);
	}
	
	public Integer getShortestDistanceBetween(String startVertexId, String endVertexId) {
		if (!graph.containsAll(new String[] { startVertexId, endVertexId })) {
			return null;
		}
		return getShortestDistanceBetween(graph.getVertex(startVertexId), endVertexId, new HashSet<String>());
	}

	private Integer getShortestDistanceBetween(Vertex curVertex, String endVertexId, Set<String> vertexesAlreadyVisited) {
		Integer shortestDistanceSoFar = Integer.MAX_VALUE;
		if (curVertex.isConnectedTo(endVertexId)) {
			shortestDistanceSoFar = curVertex.getEdgeFor(endVertexId).getWeight();
		}
		vertexesAlreadyVisited.add(curVertex.getId());
		for (Edge curEdge : curVertex.getEdges()) {
			if (!vertexesAlreadyVisited.contains(curEdge.getEndVertexId())) {
				Vertex nextVertex = graph.getVertex(curEdge.getEndVertexId());
				Integer currentDistance = getShortestDistanceBetween(nextVertex, endVertexId, new HashSet<String>(vertexesAlreadyVisited));
				if (null != currentDistance) {
					currentDistance += curEdge.getWeight();
					if (currentDistance < shortestDistanceSoFar) {
						shortestDistanceSoFar = currentDistance;
					}
				}
			}
		}
		return Integer.MAX_VALUE != shortestDistanceSoFar ? shortestDistanceSoFar : null;
	}

	private Integer sumEdgeWeightsForRoute(String... vertexes) {
		int distance = 0;
		int currentVertexIndex = 0;
		while (currentVertexIndex < vertexes.length-1) {
			String currentVertexId = vertexes[currentVertexIndex];
			String nextVertexId = vertexes[currentVertexIndex + 1];
			Vertex currentVertex = graph.getVertex(currentVertexId);
			Edge edgeToNextVertex = currentVertex.getEdgeFor(nextVertexId);
			if (null == edgeToNextVertex) {
				return null;
			}
			distance += edgeToNextVertex.getWeight();
			currentVertexIndex += 1;
		}
		return distance;
	}

}
