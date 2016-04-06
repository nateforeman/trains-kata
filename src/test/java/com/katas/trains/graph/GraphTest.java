package com.katas.trains.graph;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.katas.trains.graph.data.TestData;

@RunWith(MockitoJUnitRunner.class)
public class GraphTest {
	
	private Graph graph;

	@Before
	public void setUp() throws Exception {
		graph = new Graph();
	}
	
	@Test
	public void emptyGraphHasNoVertexes() throws Exception {
		assertThat(graph.getVertexes(), hasSize(0));
	}
	
	@Test
	public void getVertexThatDoesntExistReturnsNull() throws Exception {
		assertThat(graph.getVertex("MissingVertexId"), nullValue());
	}
	
	@Test
	public void canAddEdgeBetweenTwoVertexes() throws Exception {
		graph.addEdge("A", "B", TestData.EDGE_WEIGHT_A_TO_B);		
		
		Vertex expectedVertexA = new Vertex("A").add(TestData.EDGE_A_TO_B);
		Vertex expectedVertexB = new Vertex("B");
		
		assertEquals(graph.getVertex("A"), expectedVertexA);
		assertEquals(graph.getVertex("B"), expectedVertexB);
	}
	
	@Test
	public void canAddMultipleEdgesToTheSameVertexInGraph() throws Exception {
		graph.addEdge("A", "B", TestData.EDGE_WEIGHT_A_TO_B);
		graph.addEdge("A", "C", TestData.EDGE_WEIGHT_A_TO_C);
		
		Vertex expectedVertexA = new Vertex("A").add(TestData.EDGE_A_TO_B).add(TestData.EDGE_A_TO_C);
		Vertex expectedVertexB = new Vertex("B");
		Vertex expectedVertexC = new Vertex("C");
		
		assertEquals(graph.getVertex("A"), expectedVertexA);
		assertEquals(graph.getVertex("B"), expectedVertexB);
		assertEquals(graph.getVertex("C"), expectedVertexC);
	}
	
	@Test
	public void canCreateGraphWithCycle() throws Exception {
		graph.addEdge("A", "B", TestData.EDGE_WEIGHT_A_TO_B);
		graph.addEdge("B", "C", TestData.EDGE_WEIGHT_B_TO_C);
		graph.addEdge("C", "A", TestData.EDGE_WEIGHT_C_TO_A);
		
		Vertex expectedVertexA = new Vertex("A").add(TestData.EDGE_A_TO_B);
		Vertex expectedVertexB = new Vertex("B").add(TestData.EDGE_B_TO_C);
		Vertex expectedVertexC = new Vertex("C").add(TestData.EDGE_C_TO_A);
		
		assertEquals(graph.getVertex("A"), expectedVertexA);
		assertEquals(graph.getVertex("B"), expectedVertexB);
		assertEquals(graph.getVertex("C"), expectedVertexC);
	}
	
	public static void assertEquals(Vertex actualVertex, Vertex expectedVertex) throws Exception {
		assertThat(actualVertex.getId(), is(expectedVertex.getId()));
		assertThat(actualVertex.getEdges(), hasSize(expectedVertex.getEdges().size()));
		assertThat(actualVertex.getEdges(), containsInAnyOrder(expectedVertex.getEdges().toArray()));
	}
	
	@Test
	public void whenGraphHasNoVertexesIsEmptyReturnsTrue() throws Exception {
		assertThat(graph.isEmpty(), is(true));
	}
	
	@Test
	public void whenGraphHasSomeVertexesIsEmptyReturnsFalse() throws Exception {
		graph.addEdge("A", "B", TestData.EDGE_WEIGHT_A_TO_B);
		assertThat(graph.isEmpty(), is(false));
	}
	
	@Test(expected=RuntimeException.class)
	public void cantAddEdgeWithNullVertices() throws Exception {
		graph.addEdge(null, null, 0);
	}
	
}
