package TDALista;

import java.util.Iterator;

/**
 * Clase que modela las operaciones de una lista implementada con nodos doblemente enlazados.
 * @author Tomás Cueto Osácar
 *  
 * @param <E> Parámetro genérico de los elementos de la lista.
 */
public class Lista_doblemente_enlazada<E> implements PositionList<E>{

	//Atributos
		protected DNodo<E> header,trailer;
		protected int size;
		
	//Constructor
		
		/**
		 * Constructor de la lista doblemente enlazada. Crea una lista con dos nodos llamados centinelas.
		 * La lista se inicializa como una lista que contiene 0 elementos.
		 * Se inicializan tambien, los nodos centinelas.
		 */
		
		public Lista_doblemente_enlazada(){
			header = new DNodo<E>(null);
			trailer = new DNodo<E>(null);
			
			header.setSiguiente(trailer);	//Al primer nodo centinela le inicializo el nodo siguiente (que es el trailer), y el nodo anterior (nulo).
			header.setAnterior(null);
			
			trailer.setAnterior(header);	//Al primer nodo centinela le inicializo el nodo anterior (que es el header), y el nodo siguiente (nulo). 
			trailer.setSiguiente(null);
			
			size = 0;						//La lista se crea con tamaño 0.
		}
	
		
		/*Método privado checkPosition. Se asegura que la posicion no es nula y que corresponde a la lista. Ademas
		* castea la posicion en un nodo.
		*/

		/**
		 * Método privado checkPosition. Se asegura que la posicion no es nula y que corresponde a la lista y castea la posicion en un nodo.
		 * @param p posicion a chequear
		 * @return	La posicion p, casteada en un TNodo	
		 * @throws InvalidPositionException En caso de que la posicion sea invalida.
		 */
		private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
			try {
				if (p == null) throw new InvalidPositionException("Posicion nula.");
				return (DNodo<E>) p;
			}catch(ClassCastException e) {
				throw new InvalidPositionException("p no pertenece a la lista.");
			}
		}

		
		
			
		//Pregunta si la lista es vacia.
		public boolean isEmpty() {
			return size == 0;
		}	
		
		//Retorna el tamaño de la lista.
		public int size() {
			return size;
		}
	
		
		
		public Position<E> first() throws EmptyListException{
			if (size == 0)
				throw new EmptyListException("first() con lista vacia");	
			return header.getSiguiente();	//Retorno el siguiente nodo al nodo centinela header.
		}
		
		public Position<E> last() throws EmptyListException{
			if (isEmpty())
				throw new EmptyListException("last() con lista vacia.");
			return trailer.getAnterior();	//Retorno el nodo anterior al nodo centinela trailer.
		}
		
		public void addFirst(E elem) {
			DNodo<E> nuevo = new DNodo<E>(elem);			//Creo el nuevo nodo con elemento 'elem'
			header.getSiguiente().setAnterior(nuevo);		//Al nodo que le sigue al centinela header, le referencio su anterior con el nuevo nodo.
			nuevo.setSiguiente(header.getSiguiente());		//El nodo siguiente del nuevo nodo, es el siguiente del que antes estaba primero.
			nuevo.setAnterior(header);						//El nodo anterior al nuevo nodo, es el centinela header.
			header.setSiguiente(nuevo);						//header tendra como siguiente al nuevo nodo.
			size++;											//Aumento el tamaño de la lista
		}

		public void addLast(E elem) {
			DNodo<E> nuevo = new DNodo<E>(elem);			//Creo el nuevo nodo con elemento 'elem'
			nuevo.setSiguiente(trailer);					//Al nodo nuevo le seteo como siguiente al trailer.
			nuevo.setAnterior(trailer.getAnterior());		//y como anterior, al que antes estaba como nodo anterior a trailer.
			trailer.getAnterior().setSiguiente(nuevo);		//El nodo anterior a trailer, ahora tiene como siguiente al nodo nuevo.
			trailer.setAnterior(nuevo);						//Al centinela trailer le referencio el nodo anterior, con el nodo nuevo.
			size++;											//Aumento el tamaño de la lista
		}
		
		public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
			DNodo<E> pos = checkPosition(p);
			if(pos.getSiguiente() == trailer)	//Debo verificar no salirme del limite de la lista. 
					throw new BoundaryViolationException("next(p) con p siendo la ultima posicion");

			return pos.getSiguiente();
		}

		public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
			DNodo<E> pos = checkPosition(p);
			if(pos.getAnterior() == header)		//Debo verificar no salirme del limite de la lista. 
					throw new BoundaryViolationException("prev(p) con p siendo la primer posicion");
			return pos.getAnterior();
		}
		
		public void addAfter(Position<E> p,E elem) throws InvalidPositionException {
			DNodo<E> pos = checkPosition(p);
			DNodo<E> nuevo = new DNodo<E>(elem);		//Creo un nuevo nodo con elemento 'elem'.
			pos.getSiguiente().setAnterior(nuevo);		//Al nodo por parametro le pido el nodo siguiente, y le seto como anterior, el nuevo nodo.
			nuevo.setSiguiente(pos.getSiguiente());		//Al nuevo nodo le seteo como siguiente, el siguiente de la posicion del parametro del metodo.
			nuevo.setAnterior(pos);						//El nuevo nodo debe tener como anterior, el nodo que paso por parametro.
			pos.setSiguiente(nuevo);					//Y el nodo que paso por parametro, debe tener como siguiente al nuevo nodo.
			size++;										//Aumento el tamaño de la lista.
		}
		
		public void addBefore(Position<E> p, E elem) throws InvalidPositionException{
			DNodo<E> pos = checkPosition(p);		
			DNodo<E> nuevo = new DNodo<E>(elem);		//Creo un nuevo nodo con elemento 'elem'.
			nuevo.setSiguiente(pos);					//El nuevo nodo tiene como nodo siguiente a la posicion pasada por parametro.
			nuevo.setAnterior(pos.getAnterior());		//Y como anterior, al nodo anterior de la posicion pasada por parametro.
			pos.getAnterior().setSiguiente(nuevo);		//Al nodo anterior a la posicion pasada por parametro, le seteo como siguiente el nuevo nodo.
			pos.setAnterior(nuevo);						//Y a la posicion pasada por parametro le seteo como nodo anterior, el nuevo nodo.
			size++;										//Aumento el tamaño de la lista.
		}

		public E remove(Position<E> p) throws InvalidPositionException{
			if (size == 0)
				throw new InvalidPositionException("remove(p) con la lista vacia");

			DNodo<E> pos = checkPosition(p);
			E toReturn = p.element();					//Guardo en una variable el elemento a retornar.
			
			DNodo<E> anteriorPos = pos.getAnterior();	//Guardo tambien, el nodo anterior y siguiente del nodo a eliminar.
			DNodo<E> sigPos = pos.getSiguiente();
			
			
			
			if (anteriorPos != null)
				anteriorPos.setSiguiente(sigPos);	//Al nodo anterior a la posicion a eliminar, le seteo como siguiente al siguiente de la posicion a eliminar.
			
			if(sigPos != null)
				sigPos.setAnterior(anteriorPos);	//Analogamente, el anterior de la posicion a eliminar, pasa a ser el anterior de la posicion siguiente a pos.
	
			pos.setElemento(null);				//Seteo en nulo todos los atributos de la posicion a eliminar.
			pos.setAnterior(null);
			pos.setSiguiente(null);
							
			size--;								//Por ultimo, decremento el tamaño.
			return toReturn;
		}
		
		public E set(Position<E> p, E element) throws InvalidPositionException {
			DNodo<E> pos = checkPosition(p);	
			E toReturn = p.element();		//Guardo en una variable el elemento viejo del nodo.
			pos.setElemento(element);		//Le seteo un nuevo elemento.
			return toReturn;				//Retorno el viejo elemento.
		}

		
		public Iterator<E> iterator(){
			return new ElementIterator<E>(this); //Devuelvo un objeto de clase ElementIterator, con la lista que recibe el mensaje.
		}
		
		public Iterable<Position<E>> positions(){
			//Creo una lista de posiciones, y por cada posicion, la agrego.
			PositionList<Position<E>> l = new Lista_doblemente_enlazada<Position<E>>();
			
			try {
				//Solo lo hago si la lista no esta vacia.
				
				if(size > 0) {
					Position<E> p = first();
					while(p != last()) {	//Mientras que la posicion no sea la ultima, la agrego a la lista y avanzo de posicion.
						l.addLast(p);
						p = next(p);
					}
					l.addLast(p);			//Como sali del while, p quedo siendo la ultima posicion, que tambien debo agregar a la lista.
				}
			}catch(EmptyListException | BoundaryViolationException | InvalidPositionException e) {e.printStackTrace();}
			return l;
		}

}

