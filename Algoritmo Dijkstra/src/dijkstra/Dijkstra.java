package dijkstra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dijkstra {

	private int cantidadNodos;
	private int cantidadAristas;
	private int [][] matrizAdyacencia;
	private int [] vectorSolucion;
	private ArrayList<Integer> noSolucion = new ArrayList<Integer>();
	private final int VALOR_MAXIMO = 55555; 
	
	public Dijkstra(Scanner entrada) {
		int nodo1, nodo2, costo;
		this.cantidadNodos = entrada.nextInt();
		this.cantidadAristas = entrada.nextInt();
		this.matrizAdyacencia = new int[cantidadNodos][cantidadNodos];
		for(int[] rows : matrizAdyacencia)
			Arrays.fill(rows, VALOR_MAXIMO);
		this.vectorSolucion = new int[this.cantidadNodos];
		Arrays.fill(vectorSolucion, VALOR_MAXIMO);
		for(int i=0; i<this.cantidadAristas; i++) {
			nodo1 = entrada.nextInt() - 1;
			nodo2 = entrada.nextInt() - 1;
			costo = entrada.nextInt();
			this.matrizAdyacencia[nodo1][nodo2] = costo;
		}
		for(int i=0; i<this.cantidadNodos; i++)
			this.noSolucion.add(i);
	}
	
	private void vectorSolucion(int nodo) {
		for(int i=0; i<this.cantidadNodos; i++) {
			this.vectorSolucion[i] = this.matrizAdyacencia[nodo][i];
		}
	}
	
	private Integer encontrarMenor() {
		Integer menor;
		menor = this.noSolucion.get(0);
		for(int i=1; i<this.noSolucion.size(); i++) {
			if(this.vectorSolucion[menor] > this.vectorSolucion[this.noSolucion.get(i)])
				menor = this.noSolucion.get(i);
		}
		return menor;
	}
	
	private static int retornarMenor(int valorA, int valorB) {
		return (valorA) > valorB ? valorB : valorA;
	}
	
	public void calcularDijkstra(int nodo) {
		nodo--;
		int valor;
		Integer eliminar = nodo;
		this.noSolucion.remove(eliminar);
		this.vectorSolucion(nodo);
		while(this.noSolucion.isEmpty() == false) {
			eliminar = this.encontrarMenor();
			this.noSolucion.remove(eliminar);
			for(int i=0; i<this.noSolucion.size(); i++) {
				valor = retornarMenor(this.vectorSolucion[this.noSolucion.get(i)], 
						(this.vectorSolucion[eliminar] + this.matrizAdyacencia[eliminar][this.noSolucion.get(i)]));
				this.vectorSolucion[this.noSolucion.get(i)] = valor;
			}
		}
	}
	
	private void mostrarSolucion() {
		System.out.println("Vector solucion");
		for(int i = 0; i<this.cantidadNodos; i++) {
			System.out.print(this.vectorSolucion[i] + " ");
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner entrada = new Scanner(new FileReader("dijkstra.in"));
		Dijkstra dijkstra = new Dijkstra(entrada);
		entrada.close();
		dijkstra.calcularDijkstra(1);
		dijkstra.mostrarSolucion();
	}

	

}
