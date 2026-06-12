package Logica;

import java.util.HashMap;
import java.util.Map;

public class Categoria {

	private String nombre;
	private Map<Integer, Item> items = new HashMap<>();
	
	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	//hacer el toString
	
}