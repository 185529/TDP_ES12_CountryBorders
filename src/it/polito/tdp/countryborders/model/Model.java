package it.polito.tdp.countryborders.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.countryborders.model.dao.CountryDAO;

public class Model {
	
	private UndirectedGraph<Country, DefaultEdge> graph;
	private List<Country> countries;
	
	public Model(){
		
	}
	
	public void creaGrafo1(){
		
		this.graph = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		CountryDAO dao = new CountryDAO();
		
		// crea vertici grafo tutti insieme
		Graphs.addAllVertices(graph, this.getCountries());
		
		// crea archi
		
		// VERSIONE 1   ---   TROPPE QUERY
		
		// per ogni coppia, confinano o no? se sì, creo l'arco   ---   n^2 queries
		
		for(Country c1 : graph.vertexSet()){
			for(Country c2 : graph.vertexSet()){
				if(!c1.equals(c2)){
					if(dao.confinanti(c1, c2)){
						graph.addEdge(c1, c2);
					}
				}
			}
		}
	
	}
	
	public void creaGrafo2(){
		
		this.graph = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		CountryDAO dao = new CountryDAO();

		// VERSIONE 2
		
		// diamo due country al DB e chiediamo tutti gli stati che confinano
		
		for(Country c : graph.vertexSet()){
			
			List<Country> adiacenti = dao.listAdiacenti(c);
			
			for(Country c2 : adiacenti){
				graph.addEdge(c, c2);
			}
			
		}
	
	}
	
	public void creaGrafo3(){
		
		this.graph = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		CountryDAO dao = new CountryDAO();
		
		// VERSIONE 3
		
		// tutto il lavoro delegato al DAO
		
		for(CountryPair cp : dao.listCountryAdiacenti()){
			graph.addEdge(cp.getC1(), cp.getC2());
		}
		
		// visita in ampiezza del grafo --- usare Depth per visita in profondità
		
	}
	
	public void visit(){
		
		List<Country> visited = new LinkedList<Country>();
		BreadthFirstIterator<Country, DefaultEdge> bfv = new BreadthFirstIterator<Country, DefaultEdge>(graph, new Country());
		
		while(bfv.hasNext()){
			visited.add(bfv.next());
		}
		
	}
	
	public List<Country> getCountries(){
		
		if(this.countries==null){
			CountryDAO dao = new CountryDAO();
			this.countries = dao.listCountry();
		}
		
		return this.countries;
	}
	
	public List<Country> getRaggiungibili(Country partenza){
		
		UndirectedGraph<Country, DefaultEdge> g = this.getGrafo();
		
		BreadthFirstIterator<Country, DefaultEdge> bfi = new BreadthFirstIterator<Country, DefaultEdge>(g, partenza);
		
		List<Country> list = new ArrayList<Country>();
		
		while(bfi.hasNext()){
			list.add(bfi.next());
		}
		
		return list;
		
	}
	
	private UndirectedGraph<Country, DefaultEdge> getGrafo(){
		
		if(this.graph==null){
			this.creaGrafo3();
		}
		
		return this.graph;
		
	}

}
