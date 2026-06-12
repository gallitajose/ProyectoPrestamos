package Logica;

import java.util.HashMap;
import java.util.Map;

public class Categoria {

	private String nombre;
	private Map<Integer, Item> items = new HashMap<>();
	
	public Categoria(String nombre) {
		this.nombre = nombre;
		this.items = new HashMap<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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