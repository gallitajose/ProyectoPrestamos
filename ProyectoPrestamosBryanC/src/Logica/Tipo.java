package Logica;

import java.util.Map;
import java.util.HashMap;

public class Tipo {
	private String descripcion;
	private Map<Integer, Item> items = new HashMap<>();
	
	public Tipo(String descripcion) {
		this.descripcion = descripcion;
		this.items = new HashMap<>();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void agregarItem(Item item) {
	    items.put(item.getCodigo(), item);
	}

	public void eliminarItem(int codigo) {
	    items.remove(codigo);
	}

	public Map<Integer, Item> getItems() {
	    return items;
	}
	
	//hacer el toString
}