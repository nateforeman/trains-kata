# Problem One:  Trains

This is a Test Driven Development kata for ThoughtWorks "Trains" code assignment.
Created by Nate Foreman
nateforeman@hotmail.com
February 1st, 2015

## How to Build

This kata is implemented in java 1.7, and built with maven.

To build and run the tests, please enter the following in the root directory of the trains-kata:
	
	mvn clean package

## How to Run 	

To run the demo application, please enter the following in the root directory of the trains-kata:

	mvn exec:java
	
You may edit the input_graph.dat file to change the graph for the demo application

## Design Considerations:

### The demo app makes the following assumptions:

* The input graph must appear on the first line of the input_graph.dat file

* The input graph parser assumes that vertex Ids are only one character wide

* The demo app runs the following test cases, regardless of whatever input graph you give it:
1. The distance of the route A-B-C.
2. The distance of the route A-D.
3. The distance of the route A-D-C.
4. The distance of the route A-E-B-C-D.
5. The distance of the route A-E-D.
6. The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
8. The length of the shortest route (in terms of distance to travel) from A to C.
9. The length of the shortest route (in terms of distance to travel) from B to B.
10.The number of different routes from C to C with a distance of less than 30.  In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.