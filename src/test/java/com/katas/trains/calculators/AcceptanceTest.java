package com.katas.trains.calculators;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.katas.trains.calculators.RouteCounter;
import com.katas.trains.calculators.DistanceCalculator;
import com.katas.trains.graph.data.AcceptanceTestData;


@RunWith(MockitoJUnitRunner.class)
public class AcceptanceTest {
	
	private final DistanceCalculator distanceCalculator = new DistanceCalculator(AcceptanceTestData.makeForGraphForAcceptanceTest());
	private final RouteCounter routeCalculator = new RouteCounter(AcceptanceTestData.makeForGraphForAcceptanceTest());

	@Test
	public void testCase1_CalculateRouteDistance() throws Exception {
		String[] route = { "A", "B", "C" };
		Integer expectedDistance = 9;
		assertThat(distanceCalculator.getTotalDistanceForRoute(route), is(expectedDistance));
	}
	
	@Test
	public void testCase2_CalculateRouteDistance() throws Exception {
		String[] route = { "A", "D" };
		Integer expectedDistance = 5;
		assertThat(distanceCalculator.getTotalDistanceForRoute(route), is(expectedDistance));
	}
	
	@Test
	public void testCase3_CalculateRouteDistance() throws Exception {
		String[] route = { "A", "D", "C" };
		Integer expectedDistance = 13;
		assertThat(distanceCalculator.getTotalDistanceForRoute(route), is(expectedDistance));
	}
	
	@Test
	public void testCase4_CalculateRouteDistance() throws Exception {
		String[] route = { "A", "E", "B", "C", "D" };
		Integer expectedDistance = 22;
		assertThat(distanceCalculator.getTotalDistanceForRoute(route), is(expectedDistance));
	}
	
	@Test
	public void testCase5_CalculateRouteDistance() throws Exception {
		String[] route = { "A", "E", "D" };
		assertThat(distanceCalculator.getTotalDistanceForRoute(route), nullValue());
	}
	
	@Test
	public void testCase6_CountRoutesWithMaxStops() throws Exception {
		String startVertex = "C";
		String endVertex = "C";
		int maxStops = 3;
		assertThat(routeCalculator.countRoutesWithMaxStops(startVertex, endVertex, maxStops), is(2));
	}
	
	@Test
	public void testCase7_CountRoutesWithExactStops() throws Exception {
		String startVertex = "A";
		String endVertex = "C";
		int exactStops = 4;
		assertThat(routeCalculator.countRoutesWithExactStops(startVertex, endVertex, exactStops), is(3));
	}
	
	@Test
	public void testCase8_CalculateShortestDistanceBetweenTwoStops() throws Exception {
		String startVertex = "A";
		String endVertex = "C";
		assertThat(distanceCalculator.getShortestDistanceBetween(startVertex, endVertex), is(9));
	}
	
	@Test
	public void testCase9_CalculateShortestDistanceBetweenTwoStops() throws Exception {
		String startVertex = "B";
		String endVertex = "B";
		assertThat(distanceCalculator.getShortestDistanceBetween(startVertex, endVertex), is(9));
	}
	
	@Test
	public void testCase10_CountRoutesWithMaxDistance() throws Exception {
		String startVertex = "C";
		String endVertex = "C";
		int maxDistance = 30;
		assertThat(routeCalculator.countRoutesLessThanMaxDistance(startVertex, endVertex, maxDistance), is(7));
	}
}
