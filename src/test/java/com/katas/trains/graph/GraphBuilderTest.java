package com.katas.trains.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mock;

import com.katas.trains.graph.Graph.GraphBuilder;

@RunWith(MockitoJUnitRunner.class)
public class GraphBuilderTest {

	@Test
	public void canMakeAnEmptyGraph() throws Exception {
		GraphBuilder builder = new GraphBuilder();
		Graph graph = builder.makeGraph();
		assertThat(graph.getVertexes().isEmpty(), is(true));
	}
	
	
}
