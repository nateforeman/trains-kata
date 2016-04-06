package com.katas.trains.calculators;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.katas.trains.calculators.RouteCounter;
import com.katas.trains.graph.data.TestData;

@RunWith(MockitoJUnitRunner.class)
public class CountRoutesWithMaxStopsTest {

	@Test
	public void whenStartVertexDoesntExistReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "Z";
		String endVertex = "B";
		int maxStops = 3;
		assertThat(counter.countRoutesWithMaxStops(startVertex, endVertex, maxStops), is(0));
	}
	
	@Test
	public void whenEndVertexDoesntExistReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "Z";
		int maxStops = 2;
		assertThat(counter.countRoutesWithMaxStops(startVertex, endVertex, maxStops), is(0));
	}
	
	@Test
	public void whenMaxStopsIsNegativeReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "B";
		int maxStops = -1;
		assertThat(counter.countRoutesWithMaxStops(startVertex, endVertex, maxStops), is(0));
	}
	
	@Test
	public void whenMaxStopsIsZeroAndStartAndEndAreIdenticalReturnOne() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "B";
		String endVertex = "B";
		int maxStops = 0;
		assertThat(counter.countRoutesWithMaxStops(startVertex, endVertex, maxStops), is(1));
	}
	
	@Test
	public void whenMaxStopsIsOneAndDirectlyConnectedReturnOne() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "B";
		int maxStops = 1;
		assertThat(counter.countRoutesWithMaxStops(startVertex, endVertex, maxStops), is(1));
	}
	
	@Test
	public void whenMaxStopsIsOneAndNotDirectlyConnectedReturnZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "C";
		int maxStops = 1;
		assertThat(counter.countRoutesWithMaxStops(startVertex, endVertex, maxStops), is(0));
	}
	
	@Test
	public void whenConnectedInFewerThanMaxStopsOneWayReturnsOne() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "C";
		int maxStops = 3;
		assertThat(counter.countRoutesWithMaxStops(startVertex, endVertex, maxStops), is(1));
	}
	
	@Test
	public void whenNotConnectedInFewerThanMaxStopsReturnsZero() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeOneWayGraph());
		String startVertex = "A";
		String endVertex = "D";
		int maxStops = 2;
		assertThat(counter.countRoutesWithMaxStops(startVertex, endVertex, maxStops), is(0));
	}
	
	@Test
	public void whenConnectedInFewerThanMaxStopsManyWays() throws Exception {
		RouteCounter counter = new RouteCounter(TestData.makeFullyConnectedGraph());
		String startVertex = "A";
		String endVertex = "C";
		int maxStops = 2;
		assertThat(counter.countRoutesWithMaxStops(startVertex, endVertex, maxStops), is(3));
	}
}
