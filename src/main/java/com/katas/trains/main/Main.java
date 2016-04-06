package com.katas.trains.main;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileReader;

import com.katas.trains.calculators.DistanceCalculator;
import com.katas.trains.calculators.RouteCounter;
import com.katas.trains.graph.Graph;

public class Main {

	public static void main(String[] args) throws Exception {
		Graph graph = readGraphFromInputFile();
		DistanceCalculator distanceCalculator = new DistanceCalculator(graph);
		RouteCounter routeCounter = new RouteCounter(graph);
		
		out.println("Output# 1: " + formattedDistance(distanceCalculator, new String[] {"A", "B", "C"}));
		out.println("Output# 2: " + formattedDistance(distanceCalculator, new String[] {"A", "D"}));
		out.println("Output# 3: " + formattedDistance(distanceCalculator, new String[] {"A", "D", "C"}));
		out.println("Output# 4: " + formattedDistance(distanceCalculator, new String[] {"A", "E", "B", "C", "D"}));
		out.println("Output# 5: " + formattedDistance(distanceCalculator, new String[] {"A", "E", "D"}));
		out.println("Output# 6: " + routeCounter.countRoutesWithMaxStops("C", "C", 3));
		out.println("Output# 7: " + routeCounter.countRoutesWithExactStops("A", "C", 4));
		out.println("Output# 8: " + distanceCalculator.getShortestDistanceBetween("A", "C"));
		out.println("Output# 9: " + distanceCalculator.getShortestDistanceBetween("B", "B"));
		out.println("Output# 10: " + routeCounter.countRoutesLessThanMaxDistance("C", "C", 30));
		out.print("\n\n");
	}

	private static String formattedDistance(DistanceCalculator distanceCalculator, String... route) {
		Integer distance = distanceCalculator.getTotalDistanceForRoute(route);
		return null == distance ? "NO SUCH ROUTE" : distance.toString();
	}
	
	private static Graph readGraphFromInputFile() {
		Graph graph = new Graph();
		try (BufferedReader reader = new BufferedReader(new FileReader("input_graph.dat"))) {
			String graphString = reader.readLine();
			out.println("\n\nGraph:" + graphString + "\n");
			if (graphString.isEmpty()) {
				throw new RuntimeException("Graph string is empty");
			}
			String[] edges = graphString.split(",");
			if (edges.length == 0) {
				throw new RuntimeException("No edges found in graph string");
			}
			for (String edge : edges) {
				edge = edge.trim();
				if (edge.length() < 3) {
					throw new RuntimeException("Invalid format for edge: " + edge);
				}
				String startVertexId = edge.substring(0, 1);
				String endVertexId = edge.substring(1, 2);
				int weight = Integer.valueOf(edge.substring(2));
				graph.addEdge(startVertexId, endVertexId, weight);
			}
		} catch (Exception e) {
			String message = "Failed to read graph from input file, because: " + e.getMessage();
			out.println(message);
			throw new RuntimeException(message);
		}
		return graph;
	}

}
