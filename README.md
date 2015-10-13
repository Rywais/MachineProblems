**CPEN 221 / Fall 2015: Machine Problem 3**
The Graph ADT and Social Networks
===

### Logistics

+ This is a group assignment and you will be assigned to groups.
+ This is a 2-week machine problem, and will be due Oct/26.
+ The weight for this machine problem is 7%.

### Background

In this assignment, you will implement a `Graph` interface using two different graph representations. You will then develop several algorithms that use the `Graph` interface that might be used in a social network.

Your goals for this machine problem are to:
+ Understand and apply the concept of encapsulation;
+ Understand interfaces;
+ Understand what graphs are and how they can be represented;
+ Implement some basic graph algorithms.

### Instructions

#### Graph Implementations
First, write two classes that implement the `ca.ubc.ece.cpen221.mp3.staff.Graph` interface, which represents a _directed_ graph.
+ **Adjacency List**: Inside the package `ca.ubc.ece.cpen221.mp3.graph`, implement the `AdjacencyListGraph` class. Your implementation must internally represent the graph as an _adjacency list_. If you are not familiar with the adjacency list representation of graphs, see the Wikipedia page on the adjacency list representation as a reference.
+ **Adjacency Matrix**: Next, implement the `AdjacencyMatrixGraph` class in the `ca.ubc.ece.cpen221.mp3.graph` package. Your implementation must internally represent the graph as an adjacency matrix. If you are not familiar with the adjacency matrix representation of graphs, see the Wikipedia page on the adjacency matrix representation as a reference.

#### Algorithm Implementations
For this part of the assignment, you will write three algorithms that might be used for social network analysis using your graph implementations. 

Your algorithms must use only the methods provided in the interface, and can not use any features specific to the implementation of `Graph` being used. Your algorithms must work correctly on any correct implementation of a `Graph`, including your `AdjacencyMatrixGraph` and `AdjacencyListGraph`.

+ **Breadth first search (BFS)**: Implement the breadth first search algorithm to traverse a graph.
+ **Depth first search (DFS)**: Implement the depth first search algorithm to traverse a graph.
+ **Shortest distance**: Implement a method to find the shortest distance between two vertices in an unweighted graph. In an unweighted graph _G_, given two vertices _s_ and _t_, the shortest distance between the two vertices is the number of edges that would have to be traversed to get to _b_ from _a_. The distance between a vertex and itself is 0. If no path exists from _a_ to _b_ then your method should take appropriate action.
+ **Common upstream vertices**: Given a graph _G_ and two vertices _a_ and _b_ in _G_, your implementation should return a list of all vertices _u_ such that there is an edge from _u_ to _a_ and an edge from _u_ to _b_. If there are no such vertices then your implementation should return an empty list.
+ **Common downstream vertices**: Given a graph _G_ and two vertices _a_ and _b_ in _G_, your implementation should return a list of all vertices _v_ such that there is an edge from _a_ to _v_ and an edge from _b_ to _v_.  

### Testing Your Code
Use JUnit to test the correctness of your implementation. Write tests that check the correctness of normal cases as well as edge cases of the Graph ADT and the algorithms.

### Evaluation
To earn full credit you must:
+ Properly encapsulate your implementation. Use the most restrictive access level that makes sense for each of your fields and methods (i.e., use `private` unless you have a good reason not to). Instead of manipulating class fields directly, make them `private` and implement getter and setter methods to manipulate them from outside of the class. 
+ Not edit any files in the `ca.ubc.ece.cpen221.mp3.staff` package or any of the method declarations we’ve initially provided for you.
+ Make sure your code is readable. Use proper indentation and whitespace, abide by standard Java naming conventions, and add additional comments as necessary to document your code.
+ Follow the Java code conventions, especially for naming and commenting. Hint: Also use <kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>F</kbd> in Eclipse to auto-format your code.

### Additional hints
+ You may create helper classes and helper methods to help you with the assignment, as long as your code is compatible with the provided interfaces.
+ The tasks may be underspecified. Use your judgment. Write specifications. You can ask reasonable questions on Piazza.
+ As long as your code runs in a reasonable amount of time, and returns the correct values, you do not need to worry about the complexity of your algorithms.

### Grading guidelines
We will grade your work _approximately_ as follows:
+ Correct graph implementations: 35%
+ Correct algorithm implementations: 35%
+ Tests: 10%
+ Good style and program design: 20%