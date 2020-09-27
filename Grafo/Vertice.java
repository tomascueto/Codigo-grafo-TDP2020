package Grafo;


import TDALista.Lista_doblemente_enlazada;
import TDALista.PositionList;

public class Vertice<Integer> implements Vertex<Integer> {

	//Atributos de instancia
	
	private TDALista.Position<Vertice<Integer>> posicionEnListaVertices;
	private Integer rotulo;
	private PositionList<Arco<Integer>> emergentes,incidentes;	
	
	
	//Constructor
	
	public Vertice(Integer rot) {
		rotulo = rot;
		posicionEnListaVertices = null;
		emergentes = new Lista_doblemente_enlazada<Arco<Integer>>();
		incidentes = new Lista_doblemente_enlazada<Arco<Integer>>();
	}
	
	
	
	//Getters
	
	public PositionList<Arco<Integer>> getEmergentes(){
		return emergentes;
	}
	
	public PositionList<Arco<Integer>> getIncidentes(){
		return incidentes;
	}
	
	
	public TDALista.Position<Vertice<Integer>> getPosicionEnVertices(){
		return posicionEnListaVertices;
	}
	
	//Setters
	
	public void setElemento(Integer elem) {
		rotulo = elem;
	}
	
	public void setPosicionEnVertices(TDALista.Position<Vertice<Integer>> pv) {
		posicionEnListaVertices = pv;
	}
	
	public void setListadeEmergentes(PositionList<Arco<Integer>> lista) {
		emergentes = lista;
	}
	
	public void setListadeIncidentes(PositionList<Arco<Integer>> lista) {
		incidentes = lista;
	}
	
		
	public Integer element() {
		return rotulo;
	}

}
