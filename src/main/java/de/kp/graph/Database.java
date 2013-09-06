package de.kp.graph;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

public class Database {

	private TitanGraph graph;
	
	public Database(String host, String port) {
		
		/*
		 * Configure and connect to C*
		 */
		Configuration conf = new BaseConfiguration();
		
		conf.setProperty("storage.backend", "cassandra");

		conf.setProperty("storage.hostname", host);
		conf.setProperty("storage.port", Integer.valueOf(port));
		
		graph = TitanFactory.open(conf);
		
		/*
		 * Create index for vertices based on property 'uid'
		 */
		graph.createKeyIndex("uid", Vertex.class);
		
	}

	public void commit() {
		graph.commit();
	}
	
	public TitanGraph getGraph() {
		return graph;
	}
	
	public Vertex createVertex(String uid) {
		Vertex v = graph.addVertex(null);
		v.setProperty("uid", uid);
		
		return v;
	}
	
	public Vertex getVertex(String uid) {		
		return graph.getVertices("uid", uid).iterator().next();
	}

}
