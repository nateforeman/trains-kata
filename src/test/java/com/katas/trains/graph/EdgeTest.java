package com.katas.trains.graph;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.katas.trains.graph.Edge;

public class EdgeTest {

	private static final String END_VERTEX_ID = "My Endpoint";
	private static final int WEIGHT = 123;
	
	private Edge target;
	
	@Before
	public void setUp() throws Exception {
		target = new Edge(END_VERTEX_ID, WEIGHT);
	}
	
	@Test
	public void hasCorrectEndVertexId() throws Exception {
		assertThat(target.getEndVertexId(), is(END_VERTEX_ID));
	}
	
	@Test
	public void hasCorrectWeight() throws Exception {
		assertThat(target.getWeight(), is(WEIGHT));
	}
}
