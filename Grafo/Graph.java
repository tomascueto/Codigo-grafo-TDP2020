package Grafo;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Lista_doblemente_enlazada;
import TDALista.PositionList;
import java.util.logging.*;

public class Graph{

	//Atributos 
	
	protected PositionList<Vertice<Integer>> nodos;	//Una lista doblemente enlazada de Vertices cuyos elementos son nodos
	protected PositionList<Arco<Integer>> arcos;	//Una lista de arcos cuyos extremenos tienen elementos de tipo Integer.
	private static Logger Logger;
	
	//Constructor
	
	public Graph() {
	
		nodos = new Lista_doblemente_enlazada<Vertice<Integer>>();
		arcos = new Lista_doblemente_enlazada<Arco<Integer>>();
	
		if (Logger == null) {
			
			Logger = Logger.getLogger(Graph.class.getName());
			Handler handler = new ConsoleHandler();
			handler.setLevel(Level.WARNING);
			Logger.addHandler(handler);
			Logger.setLevel(Level.WARNING);
			
			//Desactivo el logger padre con el fin de que no se impriman dos veces los mensajes.
			Logger rootLogger = Logger.getParent();
			for (Handler h : rootLogger.getHandlers())
				h.setLevel(Level.OFF);
		
		}
		
		
		
	}

	public void addNode(int nodo) {
		Logger.info("Se quiere agregar el nodo con rotulo: "+nodo);

		try {
			if( nodoYaExistente(nodo) == null) {
				Vertice<Integer> nuevo = new Vertice(nodo);
				nodos.addLast(nuevo);
				nuevo.setPosicionEnVertices(nodos.last());			
			}
			else
				Logger.warning("Ya hay un nodo con el rotulo "+nodo+" en el grafo");

				
		}catch(EmptyListException e) {
			e.printStackTrace();
		}
		
				
	}
	
	public void removeNode(int nodo) {
		TDALista.Position<Vertice<Integer>> aEliminar = nodoYaExistente(nodo);

		Logger.info("Se quiere eliminar el nodo con rotulo: "+nodo);
		try {
			if (aEliminar != null) {
				
				PositionList<Arco<Integer>> emergentes = aEliminar.element().getEmergentes(); //Recupero la lista de arcos emergentes
				PositionList<Arco<Integer>> incidentes = aEliminar.element().getIncidentes(); //Recupero la lista de arcos incidentes
				
				nodos.remove(aEliminar);		//Remuevo el nodo de la lista de nodos
								
				//Para los arcos emergentes e incidentes realizo lo mismo, los elimino de la lista de arcos.
				
				for(TDALista.Position<Arco<Integer>> a : emergentes.positions()){
					arcos.remove(a);
				}
				
				
				for(TDALista.Position<Arco<Integer>> a : incidentes.positions()){
					arcos.remove(a);
				}	
				Logger.info("Nodo eliminado con exito");
			}
			else
				Logger.warning("Se quiso eliminar un nodo que no pertenecia a la estructura");
		}catch(TDALista.InvalidPositionException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void addEdge(int nodo1, int nodo2) {
		TDALista.Position<Vertice<Integer>> n1 = nodoYaExistente(nodo1);
		TDALista.Position<Vertice<Integer>> n2 = nodoYaExistente(nodo2);
		
		
		Logger.info("Intentando agregar arco que une el nodo "+nodo1+" con el nodo "+nodo2);
		
		try {
			
			if( n1 != null && n2 != null) {	//Lo que significaria que nodo1 y nodo2 pertenecen a la estructura
				Arco<Integer> arco = new Arco<Integer>(n1.element(),n2.element());
				arcos.addLast(arco);
				arco.setPosicionEnArcos(arcos.last());
				n1.element().getEmergentes().addLast(arco);
				n2.element().getIncidentes().addLast(arco);
			}
			else {
				Logger.warning("Uno de los nodos no pertenece a la estructura.");
			}
				
			
		}catch(EmptyListException e) {
			e.printStackTrace();
		}
	}
	
	public void removeEdge(int nodo1, int nodo2) {
		TDALista.Position<Vertice<Integer>> n1 = nodoYaExistente(nodo1);
		TDALista.Position<Vertice<Integer>> n2 = nodoYaExistente(nodo2);
		
		Logger.info("Intentado eliminar el arco que une a "+nodo1+" y al nodo "+nodo2);

		try {
			
			if( n1 != null && n2 != null) {	//Lo que significaria que nodo1 y nodo2 pertenecen a la estructura
				Vertice<Integer> primerNodo = n1.element();
				Vertice<Integer> segundoNodo = n2.element();
				
				PositionList<Arco<Integer>> emergentes = primerNodo.getEmergentes(); //Recupero la lista de arcos emergentes
				
				boolean encontre = false;
				
				TDALista.Position<Arco<Integer>> punteroListaDeArcos;
					
				if(!emergentes.isEmpty()) {
					punteroListaDeArcos = arcos.first();
						
					while(punteroListaDeArcos != null && !encontre) {
						Vertice<Integer> nodoAlFinalDelArco = punteroListaDeArcos.element().getV2();
						
						if (nodoAlFinalDelArco.equals(segundoNodo)) {
							encontre = true;
							TDALista.Position<Arco<Integer>> aux = punteroListaDeArcos;
							arcos.remove(aux);
							emergentes.remove(punteroListaDeArcos);	
							Logger.info("Arco removido con exito");
						}
						else
							punteroListaDeArcos = (punteroListaDeArcos == emergentes.last())? null : emergentes.next(punteroListaDeArcos);	
					}
						
					if(punteroListaDeArcos == null)
						Logger.warning("Se quiso elimar un arco que no pertenecia al a estructura.");
				}
				else
					Logger.warning("Se quiso eliminar un arco que unia un nodo que no contenia arcos");
			}
			else
				Logger.warning("Se quiso elimar un arco que unia al menos un nodo que no pertenecia a la estructura");
					
		}catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	/*Metodo privado para chequear que el nodo no exista en mi lista de nodos. 
	* Hace que insertar un nodo tenga O(n) siendo n la cantidad de nodos ya existentes.
	* En caso de que el nodo con rotulo "nodo" ya exista, devuelve la posicion del nodo en la lista de nodos.
	* En caso de que el nodo no exista, se retorna null.
	*/
	
	private TDALista.Position<Vertice<Integer>> nodoYaExistente(int nodo) {
		TDALista.Position<Vertice<Integer>> toReturn = null;
		
		try {
			
			if(!nodos.isEmpty()) {
				TDALista.Position<Vertice<Integer>> aComparar = nodos.first();
				boolean encontre = false;
				
				while (aComparar != null && !encontre) {
					Vertice<Integer> vertice = aComparar.element();
					if(vertice.element() == nodo) {
						toReturn = aComparar;
						encontre = true;
					}
					else 
						aComparar = (aComparar == nodos.last())? null : nodos.next(aComparar);
	
				}
			}	
		}catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		return toReturn;
		
		
	}
	
	
	

	

	
}
