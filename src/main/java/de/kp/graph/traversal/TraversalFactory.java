package de.kp.graph.traversal;

import com.tinkerpop.blueprints.Vertex;


/*
 * There are major conceptual differences between Gremlin/Pipes and Gremlin/Faunus:
 * 
 * Gremlin/Faunus is breadth-first. Gremlin/Pipes is depth-first.
 * 
 * Gremlin/Faunus processes rows of an adjacency matrix (vertex-centric), and  
 * Gremlin/Pipes processes elements in a linked-list (element-centric).
 * 
 * Gremlin/Faunus is inherently parallel with each vertex being operated on simultaneously. 
 * Gremlin/Pipes is inherently serial with one element at a time being pushed through the 
 * pipeline.
 */

public class TraversalFactory {

	/*
	 * Find all simple paths between 2 vertices in both directions
	 * up to a certain depth
	 */
	public PathList findSimplePaths(Vertex begin, Vertex end, int depth) {

		SimplePath simplePath = new SimplePath(begin, end, depth);
		return simplePath.get();
		
	}
}
