package common;

import java.io.Serializable;

public class Nodo<E> implements Serializable{

	private E info;
	private Nodo<E> siguiente;
	private Nodo<E> anterior;

	
	public void setInfo(E info) {
		this.info = info;
	}
	public Nodo(E info) {
		this.info = info;
		siguiente = null;
		anterior = null;
	}

	public Nodo<E> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	public Nodo<E> getAnterior() {
		return anterior;
	}

	public void setAnterior(Nodo<E> anterior) {
		this.anterior = anterior;
	}

	public E getInfo() {
		return info;
	}

	@Override
	public String toString() {
		return info.toString();
	}

}
