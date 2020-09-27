package TDALista;
/**
 * Clase que modela el error que ocurre cuando se quiere acceder, mediante ciertos metodos, a una lista vacia.
 * @author Tomás Cueto Osácar
 *
 */
public class EmptyListException extends Exception{
	/**
	 *Constructor de la clase EmptyListException que inicializa la excepcion, pasando como mensaje, aquel que se le otorgue en la implementacion
	 *(preferententemente, el origen del error).
	 * @param msg Mensaje que detalla el origen del error.
	 */
	public EmptyListException(String msg) {
		super(msg);
	}
}
