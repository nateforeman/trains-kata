package com.katas.trains.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Vertex {

	private final String id;
	private final Map<String, Edge> adjacentVertexIdToEdgeMap;

	public Vertex(String id) {
		if (null == id) {
			throw new RuntimeException("Can't create a Vertex will null ID");
		}
		this.id = id;
		this.adjacentVertexIdToEdgeMap = new HashMap<String, Edge>();
	}

	public String getId() {
		return id;
	}

	public Vertex add(Edge edge) {
		adjacentVertexIdToEdgeMap.put(edge.getEndVertexId(), edge);
		return this;
	}

	public Collection<Edge> getEdges() {
		return Collections.unmodifiableCollection(adjacentVertexIdToEdgeMap.values());
	}
	
	public Edge getEdgeFor(String adjacentVertexId) {
		return adjacentVertexIdToEdgeMap.get(adjacentVertexId);
	}
	
	public boolean isConnectedTo(String adjacentVertexId) {
		return adjacentVertexIdToEdgeMap.containsKey(adjacentVertexId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((adjacentVertexIdToEdgeMap == null) ? 0
						: adjacentVertexIdToEdgeMap.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (adjacentVertexIdToEdgeMap == null) {
			if (other.adjacentVertexIdToEdgeMap != null)
				return false;
		} else if (!adjacentVertexIdToEdgeMap
				.equals(other.adjacentVertexIdToEdgeMap))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [getId()=" + getId() + ", getEdges()=" + getEdges()
				+ "]";
	}

}
