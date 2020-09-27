package TDALista;
/**
 * Interface que modela el TDA Position.
 * @author Tom�s Cueto Os�car
 *
 * @param <E> Par�metro gen�rico a utilizar en la clase.
 */
public interface Position<E> {
	
	/**
	 * Retorna el elemento en la posicion de tipo Position.
	 * @return elemento de tipo E correspondiente a la posicion.
	 */
	public E element();
}
