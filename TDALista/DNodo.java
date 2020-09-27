package TDALista;


/**
 * Clase que modela un nodo doblemente enlazado.
 * @author Tomás Cueto Osácar
 *
 * @param <E> Parámetro genérico de los elementos de los nodos enlazados.
 */
public class DNodo<E> implements Position<E> {

	//Atributos
	private E elemento;
	private DNodo<E> siguiente;
	private DNodo<E> anterior;
	
	
	//Constructor
	
	/**
	 * Constructor de la clase DNodo que solamente inicializa el atributo 'elemento'.
	 * Inicialmente, la referencia al nodo siguiente y al anterior, son nulas.
	 * 
	 * @param elem Elemento con el que se inicializa el atributo 'elemento'.
	 */
	
	public DNodo(E elem){
		elemento = elem;
		siguiente=null;
		anterior=null;
	}
	
	/**
	 * Constructor dos de la clase DNodo que inicializa todos sus atributos. 
	 * @param elem Elemento con el que se inicializa el atributo 'elemento'.
	 * @param sig Nodo doblemente enlazado que representa el nodo que le sigue al nodo actual.
	 * @param ant Nodo doblemente enlazado que representa el nodo anterior al nodo actual.
	 */
	
	public DNodo(E elem, DNodo<E> sig, DNodo<E> ant) {
		elemento = elem;
		siguiente = sig;
		anterior = ant;
	}
	
	
	
	/**
	 * Retorna el elemento del nodo 
	 * @return Elemento del nodo.
	 */
	
	public E element() {
		return elemento;
	}
	
	
	/**
	 * Retorna el nodo siguiente.
	 * @return El nodo siguiente.
	 */
	
	public DNodo<E> getSiguiente(){
		return siguiente;
	}
	
	/**
	 * Retorna el nodo anterior.
	 * @return El nodo anterior.
	 */
	
	public DNodo<E> getAnterior(){
		return anterior;
	}
	
	//Setters 

	/**
	 * Setea el elemento del nodo.
	 * @param elem Elemento a setear en el nodo.
	 */
	
	public void setElemento(E elem) {
		elemento = elem;
	}
	
	/**
	 * Setea el nodo siguiente.
	 * @param sig El nodo siguiente.
	 */
	
	public void setSiguiente(DNodo<E> sig) {
		siguiente = sig;
	}
	
	/**
	 * Setea el nodo anterior.
	 * @param ant El nodo anterior.
	 */
	
	public void setAnterior(DNodo<E> ant) {
		anterior = ant;
	}
	
	
	
	
	
	
}
