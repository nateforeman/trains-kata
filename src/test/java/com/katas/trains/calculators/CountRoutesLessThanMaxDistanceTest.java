package com.katas.trains.calculators;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.katas.trains.graph.Graph;
import com.katas.trains.graph.data.TestData;

public class CountRoutesLessThanMaxDistanceTest {
	
	@Test
	public void whenStartVertexDoesntExistReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "Z";
		String endVertex = "B";
		int maxDistance = 3;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(0));
	}
	
	@Test
	public void whenEndVertexDoesntExistReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "Z";
		int maxDistance = 2;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(0));
	}
	
	@Test
	public void whenMaxDistanceIsNegativeReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "B";
		int maxDistance = -1;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(0));
	}
	
	@Test
	public void whenMaxDistanceIsZeroAndStartAndEndAreIdenticalReturnZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "B";
		String endVertex = "B";
		int maxDistance = 0;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(0));
	}
	
	@Test
	public void whenDirectlyConnectedLessThanMaxDistanceReturnsOne() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "B";
		int maxDistance = TestData.EDGE_WEIGHT_A_TO_B + 1;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(1));
	}
	
	@Test
	public void whenDirectlyConnectedWithMaxDistanceReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "B";
		int maxDistance = TestData.EDGE_WEIGHT_A_TO_B;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(0));
	}
	
	@Test
	public void whenDirectlyConnectedYesMoreThanMaxDistanceReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "B";
		int maxDistance = TestData.EDGE_WEIGHT_A_TO_B - 1;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(0));
	}
	
	@Test
	public void whenNotConnectedReturnsZero() throws Exception {
		Graph graph = TestData.makeOneWayGraph();
		graph.addEdge("A", "E", 10);
		RouteCounter counter = new RouteCounter(graph);
		String startVertex = "B";
		String endVertex = "E";
		int maxDistance = 99;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(0));
	}
	
	@Test
	public void whenConnectedLessThanMaxDistanceOneWay() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "C";
		int maxDistance = TestData.EDGE_WEIGHT_A_TO_B + TestData.EDGE_WEIGHT_B_TO_C + 1;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(1));
	}
	
	@Test
	public void whenConnectedMultipleWaysNoCycle() throws Exception {
		Graph graph = new Graph();
		graph.addEdge("A", "B", 1);
		graph.addEdge("B", "C", 1);
		graph.addEdge("A", "C", 1);
		RouteCounter counter = new RouteCounter(graph);
		String startVertex = "A";
		String endVertex = "C";
		int maxDistance = 3;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(2));
	}
	
	@Test
	public void whenConnectedMultipleWaysWithCycle() throws Exception {
		Graph graph = new Graph();
		graph.addEdge("A", "B", 1);
		graph.addEdge("B", "C", 1);
		graph.addEdge("A", "C", 1);
		graph.addEdge("C", "B", 1);
		RouteCounter counter = new RouteCounter(graph);
		String startVertex = "A";
		String endVertex = "C";
		int maxDistance = 5;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(4));
	}
	
	@Test
	public void whenConnectedMultipleWaysWithCycleSameStartAndEnd() throws Exception {
		Graph graph = new Graph();
		graph.addEdge("A", "B", 1);
		graph.addEdge("B", "C", 1);
		graph.addEdge("C", "A", 1);
		graph.addEdge("A", "C", 1);
		RouteCounter counter = new RouteCounter(graph);
		String startVertex = "A";
		String endVertex = "A";
		int maxDistance = 6;
		assertThat(counter.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(5));
	}
}
