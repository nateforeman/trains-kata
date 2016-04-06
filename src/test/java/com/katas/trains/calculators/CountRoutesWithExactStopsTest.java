package com.katas.trains.calculators;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.katas.trains.calculators.RouteCounter;
import com.katas.trains.graph.data.TestData;

public class CountRoutesWithExactStopsTest {
	
	@Test
	public void whenStartVertexDoesntExistReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "Z";
		String endVertex = "B";
		int stops = 3;
		assertThat(counter.countRoutesWithExactStops(startVertex, endVertex, stops), is(0));
	}
	
	@Test
	public void whenEndVertexDoesntExistReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "Z";
		int stops = 2;
		assertThat(counter.countRoutesWithExactStops(startVertex, endVertex, stops), is(0));
	}
	
	@Test
	public void whenStopsIsNegativeReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "B";
		int stops = -1;
		assertThat(counter.countRoutesWithExactStops(startVertex, endVertex, stops), is(0));
	}
	
	@Test
	public void whenExactStopsIsZeroAndStartAndEndAreIdenticalReturnOne() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "B";
		String endVertex = "B";
		int stops = 0;
		assertThat(counter.countRoutesWithExactStops(startVertex, endVertex, stops), is(1));
	}
	
	@Test
	public void whenExactStopsIsOneAndDirectlyConnectedReturnOne() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "B";
		int stops = 1;
		assertThat(counter.countRoutesWithExactStops(startVertex, endVertex, stops), is(1));
	}
	
	@Test
	public void whenExactStopsIsOneAndNotDirectlyConnectedReturnZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "C";
		int stops = 1;
		assertThat(counter.countRoutesWithExactStops(startVertex, endVertex, stops), is(0));
	}
	
	@Test
	public void whenConnectedInFewerThanExactStopsOneWayReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "C";
		int stops = 3;
		assertThat(counter.countRoutesWithExactStops(startVertex, endVertex, stops), is(0));
	}
	
	@Test
	public void whenConnectedByExactStopsOneWayReturnsOne() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "B";
		String endVertex = "D";
		int stops = 2;
		assertThat(counter.countRoutesWithExactStops(startVertex, endVertex, stops), is(1));
	}
	
	@Test
	public void whenConnectedByExactStopsManyWays() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeFullyConnectedGraph());
		String startVertex = "A";
		String endVertex = "D";
		int stops = 3;
		assertThat(counter.countRoutesWithExactStops(startVertex, endVertex, stops), is(7));
	}
}
