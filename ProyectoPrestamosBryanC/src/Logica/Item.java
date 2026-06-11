package Logica;

import java.util.Map;
import java.util.HashMap;

public class Item {
	
	private int codigo;
	private String nombre;
	private String descripcion;
	private Tipo tipo;
	private Map<String, Categoria> categorias = new HashMap<>();
	
	public Item(String descripcion, Tipo tipo, String nombre, int codigo){
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.tipo = tipo;
		this.categorias = new HashMap<>();
		
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
	public void agregarCategoria(Categoria nuevaCategoria) {
	    categorias.put(nuevaCategoria.getNombre(), nuevaCategoria);
	}

	public void eliminarCategoria(String nombre) {
	    categorias.remove(nombre);
	}

	public Map<String, Categoria> getCategorias() {
	    return categorias;
	}
	
	//get set prestamo.

}