package com.katas.trains.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Graph {

	private final Map<String, Vertex> vertexMap;
	
	public Graph() {
		this.vertexMap = new HashMap<String, Vertex>();
	}
	
	public Collection<Vertex> getVertexes() {
		return Collections.unmodifiableCollection(vertexMap.values());
	}
	
	public Vertex getVertex(String vertexId) {
		return vertexMap.get(vertexId);
	}
	

	public boolean containsAll(String... vertexIds) {
		for (String curVertexId : vertexIds) {
			if (!vertexMap.containsKey(curVertexId)) {
				return false;
			}
		}
		return true;
	}

	
	public Graph addEdge(String startVertexId, String endVertexId, int weight) {
		if (null == startVertexId || null == endVertexId) {
			throw new RuntimeException("Can't add edge with null vertices.");
		}
		addVertex(startVertexId);
		getVertex(startVertexId).add(new Edge(endVertexId, weight));
		addVertex(endVertexId);
		return this;
	}

	private void addVertex(String startVertexId) {
		if (!vertexMap.containsKey(startVertexId)) {
			vertexMap.put(startVertexId, new Vertex(startVertexId));
		}
	}
	
	public static class GraphBuilder {

		public Graph makeGraph() {
			return new Graph();
		}
		
	}

	public boolean isEmpty() {
		return getVertexes().isEmpty();
	}
}
