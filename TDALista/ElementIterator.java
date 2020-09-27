package TDALista;

import java.util.NoSuchElementException;

/**
 * Clase que modela un iterador de elementos, ofreciendo una abstraccion en el recorrido de una lista.
 * @author Tomás Cueto Osácar
 * @param <E> Parámetro genérico de los elementos del iterador.
 */
public class ElementIterator<E> implements java.util.Iterator<E>{
	
	//Atributos
	PositionList<E>	l;
	Position<E> cursor;
	
	
	
	
	
	//Constructor
	/**
	 * Metodo que recibe una lista y setea el cursor para comenzar con la iteracion de la misma.
	 * @param lista Lista que se desea iterar.
	 */
	
	public ElementIterator(PositionList<E> lista) {
		l = lista;
		try {
			if (!l.isEmpty()) //Si la lista no es vacía, se inicializa el cursor.
				cursor = l.first();
			else
				cursor = null;
		}catch(EmptyListException e) {e.printStackTrace();}
	}
	
	//Operaciones
	
	
	/**
	 * Retorna si la lista tiene un elemento siguiente al cursor.
	 * @return Verdadero si quedan elementos por recorrer, falso caso contrario.
	 */
	public boolean hasNext() {return cursor != null;}
	
	/**
	 * Retorna el elemento en el cursor y de ser posible, avanza al siguiente elemento de la lista por recorrer. De no poder, el cursor se vuelve nulo.
	 * @return Elemento en el cursor.
	 * @throws NoSuchElementException si no existe el elemento que se quiere devolver.
	 */
	public E next() throws NoSuchElementException {
		if (cursor == null) 
			throw new NoSuchElementException("Error: no hay siguiente"); //Excepcion que se lanza si se pide el siguiente a un cursor nulo.
		E elem = cursor.element();
		
		try {	
			cursor = (cursor == l.last()) ? null : l.next(cursor);
		}catch(BoundaryViolationException | EmptyListException | InvalidPositionException e) {e.printStackTrace();
			}
		return elem;
	}
	
	
	
}
