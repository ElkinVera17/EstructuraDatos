package modelo;

public class Nodo {
	private Object dato;
	private Nodo siguiente;
	//3 tipos de costructor
	public Nodo(Object dato, Nodo siguiente) {
		super();
		this.dato = dato;
		this.siguiente = siguiente;
	}

	public Nodo() {
		dato=null;
		siguiente=null;
	}

	public Nodo(Object dato) {
		this.dato = dato;
		this.siguiente = null;
	}

	//setter and getter

	public Object getDato() {
		return dato;
	}

	public void setDato(Object dato) {
		this.dato = dato;
	}

	public Nodo getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo siguiente) {
		this.siguiente = siguiente;
	}
	public String toString() {
		return dato +"-->";
	}
}
