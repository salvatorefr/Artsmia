package it.polito.tdp.artsmia.model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	private Graph  <ArtObject,DefaultWeightedEdge>grafo;
	private Map<Integer,ArtObject> idMap	;
	
	
	
	public Model() {
		
	grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	idMap=new HashMap<Integer,ArtObject> ();
		
	}
		 
		 
		 
	
	public Graph<ArtObject, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}




	public Map<Integer, ArtObject> getIdMap() {
		return idMap;
	}




	public void creaGrafo() {
	ArtsmiaDAO dao= new ArtsmiaDAO();	
	dao.mapObject(idMap);
	Graphs.addAllVertices(grafo,idMap.values());
	
	
	
		
	 List<Collegamento> collegamenti;
	collegamenti = dao.cercaCollegamenti();		   
		
	for (Collegamento c: collegamenti) {
		ArtObject partenza = idMap.get(c.getPartenza());
		ArtObject arrivo = idMap.get(c.getArrivo());
		int peso= c.getPeso();
		Graphs.addEdge(grafo, partenza, arrivo, peso);
	
	}	
		 
	System.out.println("GrafoCreato"+ grafo.vertexSet().size());

	
	
	
	
	
	
	
	
	
	
	
	}




	public int edgeSize() {
		
		return grafo.edgeSet().size();
	}




	public int vertexsize() {
		
		return grafo.vertexSet().size();
	}




	public ArtObject cerca(int cercato) {
	ArtObject result=	this.idMap.get(cercato);
		return result;
	}




	public List<ArtObject> trovaConnessi(ArtObject oggettoCercato) {
		 LinkedList<ArtObject> raggiungibili = new LinkedList<ArtObject>();
		 DepthFirstIterator <ArtObject,DefaultWeightedEdge> dp = new DepthFirstIterator <ArtObject,DefaultWeightedEdge> (grafo,oggettoCercato);
		 dp.next();
		 while (dp.hasNext()) {
			 System.out.println("trovato un collegamento");
		 	raggiungibili.add(dp.next());
		 	
		 	
		 }
return raggiungibili;

		
		
	}
	}
	
	
	
	
	

		 
	 
	


