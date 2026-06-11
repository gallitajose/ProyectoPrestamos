package Logica;

import java.util.ArrayList;

public class Usuario {
	private String nombre;
	private String telefono;
	private String correo;
	private ArrayList<Prestamo> itemsPrestados;
	
	
	public Usuario(String nombre, String telefono, String correo) {
		this.correo = correo;
		this.nombre = nombre;
		this.telefono = telefono;
		this.itemsPrestados = new ArrayList<>();
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void agregarPrestamo(Prestamo nuevoPrestamo) {
		itemsPrestados.add(nuevoPrestamo);
	}
	public void eliminarPrestamo(int idPrestamo) {
	    itemsPrestados.removeIf(p -> p.getIdPrestamo() == idPrestamo);
	}
	public ArrayList<Prestamo> getPrestamos() {
		return itemsPrestados;
	}
	
	
}