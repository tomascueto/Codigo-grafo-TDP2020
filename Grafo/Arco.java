package Grafo;

public class Arco<V> implements Edge<V> {

	//Atributos de instancia
	private Vertice<V> v1,v2;
	private TDALista.Position<Arco<V>> posicionEnEmergentes;
	private TDALista.Position<Arco<V>> posicionEnIncidentes;
	private TDALista.Position<Arco<V>> posicionEnArcos;
	
	
	public Arco(Vertice<V> pre,Vertice<V> suc) {
		v1 = pre;
		v2 = suc;
	}
	
	//Getters
	
	public Vertice<V> getV1(){
		return v1;
	}
	
	public Vertice<V> getV2(){
		return v2;
	}
	
	public TDALista.Position<Arco<V>> getPosicionEnArcos(){
		return posicionEnArcos;
	}
	

	public TDALista.Position<Arco<V>> getPosicionEnEmergentes(){
		return posicionEnEmergentes;
	}
	
	public TDALista.Position<Arco<V>> getPosicionEnIncidentes(){
		return posicionEnIncidentes;
	}
	
	
	//Setters
	
	public void setV1(Vertice<V> v) {
		v1 = v;
	}
	
	public void setV2(Vertice<V> v) {
		v2 = v;
	}
	
	public void setPosicionEnArcos(TDALista.Position<Arco<V>> p) {
		posicionEnArcos = p;
	}

	public void setPosicionEnEmergentes(TDALista.Position<Arco<V>> pos) {
		posicionEnEmergentes = pos;
	}
	
	public void setPosicionEnIncidentes(TDALista.Position<Arco<V>> pos) {
		posicionEnIncidentes = pos;
	}
	
	public V element() {
		return null;
	}
	
}
