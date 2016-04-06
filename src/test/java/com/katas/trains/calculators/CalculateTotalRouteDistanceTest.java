package com.katas.trains.calculators;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.katas.trains.calculators.DistanceCalculator;
import com.katas.trains.graph.data.AcceptanceTestData;
import com.katas.trains.graph.data.TestData;

@RunWith(MockitoJUnitRunner.class)
public class CalculateTotalRouteDistanceTest {
	
	DistanceCalculator calculator;
	
	@Before
	public void setUp() throws Exception {
		calculator = new DistanceCalculator(TestData.makeOneWayGraph());
	}
	
	@Test
	public void withEmptyPathReturnsZero() throws Exception {
		String[] emptyRoute = new String[] {};
		assertThat(calculator.getTotalDistanceForRoute(emptyRoute), is(0));
	}
	
	@Test
	public void whenMissingVertexReturnsNull() throws Exception {
		String[] route = { "Z" };
		assertThat(calculator.getTotalDistanceForRoute(route), nullValue());
	}
	
	@Test
	public void withSingleVertexPathReturnsZero() throws Exception {
		String[] route = { "B" };
		assertThat(calculator.getTotalDistanceForRoute(route), is(0));
	}
	
	@Test
	public void withAdjacentDuplicateVertexesReturnsNull() throws Exception {
		String[] route = { "B", "B" };
		assertThat(calculator.getTotalDistanceForRoute(route), nullValue());
	}
	
	@Test
	public void withTwoConnectedVertexesReturnsEdgeWeight() throws Exception {
		String[] route = { "B", "C" };
		Integer expectedDistance = TestData.EDGE_WEIGHT_B_TO_C;
		assertThat(calculator.getTotalDistanceForRoute(route), is(expectedDistance));
	}
	
	@Test
	public void withMultipleConnectedVertexesReturnsSumOfEdgeWeights() throws Exception {
		String[] route = { "B", "C", "D" };
		Integer expectedDistance = TestData.EDGE_WEIGHT_B_TO_C + TestData.EDGE_WEIGHT_C_TO_D;
		assertThat(calculator.getTotalDistanceForRoute(route), is(expectedDistance));
	}
	
	@Test
	public void acceptanceTestCase1() throws Exception {
		calculator = new DistanceCalculator(AcceptanceTestData.makeForGraphForAcceptanceTest());
		String[] route = { "A", "B", "C" };
		Integer expectedDistance = 9;
		assertThat(calculator.getTotalDistanceForRoute(route), is(expectedDistance));
	}
}
