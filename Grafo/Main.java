package Grafo;

public class Main {

	public static void main (String [] args) {
		
		Graph digrafo = new Graph();
		
		//Agrego 5 nodos al grafo.
		digrafo.addNode(1);
		digrafo.addNode(2);
		digrafo.addNode(3);
		digrafo.addNode(4);
		digrafo.addNode(5);
		
		
		//Agrego 3 nodos que ya existian.
		digrafo.addNode(5);
		digrafo.addNode(1);
		digrafo.addNode(2);
		
		//Creo 6 arcos de la siguiente forma:     1->2     2->3    3->4     4->1   5->2    1->4  3->5
		digrafo.addEdge(1, 2);
		digrafo.addEdge(2, 3);
		digrafo.addEdge(3, 4);
		digrafo.addEdge(4, 1);
		digrafo.addEdge(5, 2);
		digrafo.addEdge(3, 5);
		
		//Agregando un arco con nodos que no pertenecen a la estructura
		digrafo.addEdge(7, 8);
		
		digrafo.removeEdge(1,2);
		
		digrafo.removeEdge(1,2);
		
		digrafo.removeEdge(100, 100);
		
		//Removiendo 2 nodos.
		digrafo.removeNode(1);
		digrafo.removeNode(5);
		
		//Elimino un nodo que ya habia sido eliminado
		digrafo.removeNode(1);
	
	}
	
	
	
	
}
