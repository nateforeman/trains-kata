package com.katas.trains.graph;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.katas.trains.graph.data.TestData;

@RunWith(MockitoJUnitRunner.class)
public class GraphContainsVertexesTest {
	
	private Graph graph;

	@Before
	public void setUp() throws Exception {
		graph = TestData.makeOneWayGraph();
	}
	
	@Test
	public void whenGraphIsNotEmptyAndGivenVertexesIsEmptyReturnsTrue() throws Exception {
		String[] vertexIds = {};
		assertThat(graph.containsAll(vertexIds), is(true));
	}
	
	@Test
	public void whenGraphIsEmptyAndGivenVertexesIsEmptyReturnsTrue() throws Exception {
		graph = new Graph();
		String[] vertexIds = {};
		assertThat(graph.containsAll(vertexIds), is(true));
	}
	
	@Test
	public void whenGraphContainsAllGivenVertexesReturnsTrue() throws Exception {
		String[] vertexIds = { "B", "C", };
		assertThat(graph.containsAll(vertexIds), is(true));
	}
	
	@Test
	public void whenGraphDoesntContainOneOfTheGivenVertexesReturnsFalse() throws Exception {
		String[] vertexIds = { "B", "Z", };
		assertThat(graph.containsAll(vertexIds), is(false));
	}
	
}
