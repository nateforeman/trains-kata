package com.katas.trains.calculators;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.katas.trains.graph.Graph;
import com.katas.trains.graph.data.TestData;

public class CalculateShortestDistanceTest {

	DistanceCalculator calculator;
	
	@Test
	public void whenStartVertexDoesntExistReturnsNull() throws Exception {
		calculator = new DistanceCalculator(TestData.makeOneWayGraph());
		String startVertex = "Z";
		String endVertex = "B";
		assertThat(calculator.getShortestDistanceBetween(startVertex, endVertex), nullValue());
	}
	
	@Test
	public void whenEndVertexDoesntExistReturnsNull() throws Exception {
		calculator = new DistanceCalculator(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "X";
		assertThat(calculator.getShortestDistanceBetween(startVertex, endVertex), nullValue());
	}
	
	@Test
	public void whenAdjacentReturnEdgeWeight() throws Exception {
		calculator = new DistanceCalculator(TestData.makeOneWayGraph());
		String startVertex = "B";
		String endVertex = "C";
		assertThat(calculator.getShortestDistanceBetween(startVertex, endVertex), is(TestData.EDGE_WEIGHT_B_TO_C));
	}
	
	@Test
	public void whenIndirectlyConnectedInOneWayReturnsSumOfEdgeWeights() throws Exception {
		calculator = new DistanceCalculator(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "C";
		int expectedDistance = TestData.EDGE_WEIGHT_A_TO_B + TestData.EDGE_WEIGHT_B_TO_C;
		assertThat(calculator.getShortestDistanceBetween(startVertex, endVertex), is(expectedDistance));
	}
	
	@Test
	public void whenNotConnectedReturnsNull() throws Exception {
		Graph graph = TestData.makeOneWayGraph();
		graph.addEdge("A","E", 2);
		calculator = new DistanceCalculator(graph);
		String startVertex = "B";
		String endVertex = "E";
		assertThat(calculator.getShortestDistanceBetween(startVertex, endVertex), nullValue());
	}
	
	@Test
	public void whenConnectedInManyWaysReturnsRouteWithTheSmallestSumOfEdgeWeights() throws Exception {
		Graph graph = TestData.makeOneWayGraph();
		graph.addEdge("A","D", 100);
		calculator = new DistanceCalculator(graph);
		String startVertex = "A";
		String endVertex = "D";
		int expectedDistance = TestData.EDGE_WEIGHT_A_TO_B + TestData.EDGE_WEIGHT_B_TO_C + TestData.EDGE_WEIGHT_C_TO_D;
		assertThat(calculator.getShortestDistanceBetween(startVertex, endVertex), is(expectedDistance));
	}
	
	@Test
	public void whenConnectedWithCycleReturnsRouteWithTheSmallestSumOfEdgeWeights() throws Exception {
		Graph graph = TestData.makeOneWayGraph();
		graph.addEdge("A","D", 100);
		graph.addEdge("D","A", 1);
		calculator = new DistanceCalculator(graph);
		String startVertex = "A";
		String endVertex = "D";
		int expectedDistance = TestData.EDGE_WEIGHT_A_TO_B + TestData.EDGE_WEIGHT_B_TO_C + TestData.EDGE_WEIGHT_C_TO_D;
		assertThat(calculator.getShortestDistanceBetween(startVertex, endVertex), is(expectedDistance));
	}
	
	@Test
	public void whenStartAndEndAreIdenticalAndConnectedReturnTheSmallestSumOfEdgeWeights() throws Exception {
		Graph graph = TestData.makeOneWayGraph();
		graph.addEdge("B","A", 100);
		graph.addEdge("C","A", TestData.EDGE_WEIGHT_C_TO_A);
		calculator = new DistanceCalculator(graph);
		String startVertex = "A";
		String endVertex = "A";
		int expectedDistance = TestData.EDGE_WEIGHT_A_TO_B + TestData.EDGE_WEIGHT_B_TO_C + TestData.EDGE_WEIGHT_C_TO_A;
		assertThat(calculator.getShortestDistanceBetween(startVertex, endVertex), is(expectedDistance));
	}

	@Test
	public void whenStartAndEndAreIdenticalAndNotConnectedReturnNull() throws Exception {
		calculator = new DistanceCalculator(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "A";
		assertThat(calculator.getShortestDistanceBetween(startVertex, endVertex), nullValue());
	}
	
}
