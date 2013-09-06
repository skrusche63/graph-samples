package de.kp.graph.traversal;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;
import com.tinkerpop.pipes.branch.LoopPipe.LoopBundle;


public class SimplePath {

	private final Vertex begin;
	private final Vertex end;
	
	private final int depth;
	
	public SimplePath(Vertex begin, Vertex end) {
		this(begin, end, 5);
	}

	public SimplePath(Vertex begin, Vertex end, int depth) {
		/*
		 * Register begin & end vertex
		 */
		this.begin = begin;
		this.end   = end;
		
		/*
		 * Register loop depth
		 */
		this.depth = depth;
		
	}

	public PathList get() {

		GremlinPipeline<Vertex, Vertex> pipeline = new GremlinPipeline<Vertex, Vertex>();
		
		/*
		 * loop(String namedStep, PipeFunction<LoopPipe.LoopBundle<E>, Boolean> whileFunction, PipeFunction<LoopBundle<E>,Boolean> emitFunction) 
	     * Add a LoopPipe to the end of the Pipeline.
		 */
		
		pipeline.start(begin).as("begin").both().loop("begin", whileFunction(), emitFunction()).has("id", end.getId()).simplePath().path();
		return new PathList(pipeline);

	}
	
	private PipeFunction<LoopBundle<Vertex>, Boolean> whileFunction() {

		return new PipeFunction<LoopBundle<Vertex>, Boolean>() {
            public Boolean compute(LoopBundle<Vertex> loopBundle) {
                return ((loopBundle.getLoops() < depth) && (loopBundle.getObject() != end));
            }
		};
	
	}

	private PipeFunction<LoopBundle<Vertex>, Boolean> emitFunction() {

        return new PipeFunction<LoopBundle<Vertex>, Boolean>() {
            public Boolean compute(LoopBundle<Vertex> lb) {
                return true;
            }
            
        };

	}

}
