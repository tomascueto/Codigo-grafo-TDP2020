package TDALista;

/**
 * Clase que modela el error que ocurre cuando una posicion de la lista es invalida, es decir:
 * la posicion es nula o la posicion pertenece a la lista.
 * @author Tomás Cueto Osácar
 *
 */
public class InvalidPositionException extends Exception {
	/**
	 *Constructor de la clase InvalidPositionException que inicializa la excepcion, pasando como mensaje, aquel que se le otorgue en la implementacion
	 *(preferententemente, el origen del error).
	 * @param msg Mensaje que detalla el origen del error.
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
