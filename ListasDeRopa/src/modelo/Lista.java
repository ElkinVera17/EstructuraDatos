package modelo;

public class Lista {
	    private Nodo inicio;

	    public Lista() {
	        inicio = null;
	    }

	    public Nodo getInicio() {
	        return inicio;
	    }

	    public void setInicio(Nodo inicio) {
	        this.inicio = inicio;
	   } 

	    // ---- Métodos principales ----

	    public boolean esVacia() {
	        return inicio == null;
	    }

	    public void insertarInicio(Object d) {
	        Nodo nuevo = new Nodo(d);
	        nuevo.setSiguiente(inicio);
	        inicio = nuevo;
	    }

	    public void insertarFinal(Object d) {
	        Nodo nuevo = new Nodo(d, null);

	        if (esVacia()) {
	            inicio = nuevo;
	        } else {
	            Nodo actual = inicio;
	            while (actual.getSiguiente() != null) {
	                actual = actual.getSiguiente();
	            }
	            actual.setSiguiente(nuevo);
	        }
	    }

	    public void mostrarElementos() {
	        Nodo actual = inicio;

	        if (esVacia()) {
	            System.out.println("La lista está vacía.");
	        } else {
	            while (actual != null) {
	                System.out.println(actual.getDato());
	                actual = actual.getSiguiente();
	            }
	        }
	    }

	    public int contarElementos() {
	        int n = 0;
	        Nodo actual = inicio;
	        while (actual != null) {
	            n++;
	            actual = actual.getSiguiente();
	        }
	        return n;
	    }

	    public boolean buscarElemento(Object buscado) {
	        Nodo actual = inicio;
	        while (actual != null) {
	            if (actual.getDato().equals(buscado)) {
	                return true;
	            }
	            actual = actual.getSiguiente();
	        }
	        return false;
	    }

	    public Nodo buscarNodo(Object buscado) {
	        Nodo actual = inicio;
	        while (actual != null) {
	            if (actual.getDato().equals(buscado)) {
	                return actual;
	            }
	            actual = actual.getSiguiente();
	        }
	        return null;
	    }

	    // ---- Métodos para eliminar ----

	    public Nodo eliminarAlInicio() {
	        if (esVacia()) return null;

	        Nodo eliminado = inicio;
	        inicio = inicio.getSiguiente();
	        eliminado.setSiguiente(null);
	        return eliminado;
	    }

	    public Nodo eliminarAlFinal() {
	        if (esVacia()) return null;

	        if (inicio.getSiguiente() == null) {
	            Nodo eliminado = inicio;
	            inicio = null;
	            return eliminado;
	        }

	        Nodo actual = inicio;
	        while (actual.getSiguiente().getSiguiente() != null) {
	            actual = actual.getSiguiente();
	        }

	        Nodo eliminado = actual.getSiguiente();
	        actual.setSiguiente(null);
	        return eliminado;
	    }
	}
