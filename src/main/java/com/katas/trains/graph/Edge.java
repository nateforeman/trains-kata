package com.katas.trains.graph;

public class Edge {

	private final String endVertexId;
	private final int weight;

	public Edge(String endVertexId, int weight) {
		this.endVertexId = endVertexId;
		this.weight = weight;
	}

	public String getEndVertexId() {
		return endVertexId;
	}

	public Integer getWeight() {
		return weight;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endVertexId == null) ? 0 : endVertexId.hashCode());
		result = prime * result + weight;
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
		Edge other = (Edge) obj;
		if (endVertexId == null) {
			if (other.endVertexId != null)
				return false;
		} else if (!endVertexId.equals(other.endVertexId))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Edge [endVertexId=" + endVertexId + ", weight=" + weight + "]";
	}

}
