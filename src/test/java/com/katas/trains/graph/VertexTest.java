package com.katas.trains.graph;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VertexTest {
	
	private static final String ID = "My Id";
	private static final Edge EDGE_1 = new Edge("A", 1);
	private static final Edge EDGE_2 = new Edge("B", 2);
	private Vertex target;
	
	@Before
	public void setUp() throws Exception {
		target = new Vertex(ID);
	}
	
	@Test
	public void hasCorrectId() throws Exception {
		assertThat(target.getId(), is(ID));
	}
	
	@Test
	public void initiallyHasNoEdges() throws Exception {
		assertThat(target.getEdges().size(), is(0));
	}
	
	@Test
	public void canAddOneEdge() throws Exception {
		target.add(EDGE_1);
		assertThat(target.getEdges().size(), is(1));
		assertThat(target.getEdges(), containsInAnyOrder(EDGE_1));
	}
	
	@Test
	public void canAddMultipleDistinctEdges() throws Exception {
		target.add(EDGE_1);
		target.add(EDGE_2);
		assertThat(target.getEdges().size(), is(2));
		assertThat(target.getEdges(), containsInAnyOrder(EDGE_1, EDGE_2));
	}
	
	@Test
	public void addingTheSameEdgeDoesNothing() throws Exception {
		target.add(EDGE_1);
		target.add(EDGE_1);
		assertThat(target.getEdges().size(), is(1));
		assertThat(target.getEdges(), containsInAnyOrder(EDGE_1));
	}
	
	@Test
	public void getEdgeForAdjacentVertexIdWhenDoesntExistReturnsNull() throws Exception {
		target.add(EDGE_1);
		assertThat(target.getEdgeFor("Foo"), nullValue());
	}
	
	
	@Test
	public void getEdgeForAdjacentVertexIdWhenExistsReturnsTheEdge() throws Exception {
		target.add(EDGE_1);
		target.add(EDGE_2);
		assertThat(target.getEdgeFor(EDGE_1.getEndVertexId()), sameInstance(EDGE_1));
	}
	
	@Test(expected=RuntimeException.class)
	public void cantCreateVertexWithNullId() throws Exception {
		new Vertex(null);
	}
}
