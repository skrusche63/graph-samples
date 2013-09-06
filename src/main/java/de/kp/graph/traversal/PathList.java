package de.kp.graph.traversal;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;

public class PathList {

	private int total;
	private List<Integer> counts;
	
	public PathList(GremlinPipeline<Vertex, Vertex> pipeline) {
		
		/*
		 * Evaluate pipeline content
		 */		
		counts = new ArrayList<Integer>();
		
	    for (Object path:pipeline) {
	    	
	    	ArrayList<?> list = (ArrayList<?>)path;
	    	counts.add(list.size());
	    	
	    }

	    total = counts.size();
		
	}
	
	public int getTotal() {
		return total;
	}
}
