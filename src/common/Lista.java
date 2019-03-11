package common;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class Lista<E> implements Serializable {

	private Nodo<E> cabeza;
	private Nodo<E> cola;
	private int cantidad;

	public Lista() {

		this.cabeza = null;
		this.cola = null;
		this.cantidad = 0;
	}

	public Nodo<E> getCabeza() {
		return cabeza;
	}

	public void setCabeza(Nodo<E> cabeza) {
		this.cabeza = cabeza;
	}

	public Nodo<E> getCola() {
		return cola;
	}

	public void setCola(Nodo<E> cola) {
		this.cola = cola;
	}

	public int getCantidad() {
		return cantidad;
	}

	public E obtenerPosicion(int posicion) {

		Nodo<E> nodo = cabeza;
		E info = null;
		if (posicion <= cantidad) {
			for (int i = 0; i < posicion; i++)
				nodo = nodo.getSiguiente();
			info = nodo.getInfo();
		}

		return info;
	}

	public boolean insertar(E info) {
		boolean i = false;
		Nodo<E> n = new Nodo<E>(info);

		if (cantidad == 0) {
			cabeza = n;
			cola = n;
			cantidad++;
			i = true;
		} else {
			if (encontrar(n)){
				i= false;
			}else {
			cola.setSiguiente(n);
			n.setAnterior(cola);
			cola = n;
			cantidad++;
			i=true;
			}
		}
		return i;
	}

	public void eliminar(E objeto) {
		Nodo<E> aux = cabeza;

		if (cantidad == 1) {
			cabeza = null;
		} else {
			if (aux.equals(cabeza) == true) {
				cabeza = aux.getSiguiente();
			} else {
				aux.getAnterior().setSiguiente(aux.getSiguiente());
			}
		}
		cantidad--;
	}

	@Override
	public String toString() {
		return "Lista [\n" + "cantidad= " + cantidad + "\n" + cabeza + " ]\n";
	}

	public void eliminarPosicion(int posicion) {
		Nodo<E> aux = cabeza;

		if (cantidad == 1) {
			cabeza = null;
		} else {
			if (posicion == 0) {
				cabeza = aux.getSiguiente();
			} else {
				for (int i = 0; i < posicion; i++) {
					aux = aux.getSiguiente();
				}
				if (aux.getAnterior() == null) {
					aux.getSiguiente().setAnterior(null);
					cabeza = aux.getSiguiente();
				} else if (aux.getSiguiente() == null) {
					aux.getAnterior().setSiguiente(null);
				} else {
					aux.getAnterior().setSiguiente(aux.getSiguiente());
					aux.getSiguiente().setAnterior(aux.getAnterior());
				}

			}
		}
		cantidad--;
	}
	
	public boolean encontrar(Nodo<E> info) {
			boolean existe=false;
			Nodo<E> aux = cabeza;
		while(aux!=null && !existe) {
			existe = aux.getInfo().equals(info.getInfo());
			aux = aux.getSiguiente();
		}
		return existe;
	}
	
	

}
