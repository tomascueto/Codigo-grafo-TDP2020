package TDALista;

/**
 * Clase que modela el error que ocurre cuando se sale del perimetro de la lista, es decir, se pide el elemento anterior al primer elemento
 * de la lista, o el siguiente al ultimo.
 * @author Tomás Cueto Osácar
 *
 */
public class BoundaryViolationException extends Exception {
	/**
	 *Constructor de la clase BoundaryViolationException que inicializa la excepcion, indicando en el mensaje el origen del error.
	 * @param msg Mensaje que detalla el origen del error.
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}
