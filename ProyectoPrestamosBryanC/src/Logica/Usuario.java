package Logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String telefono;
	private String correo;
	private ArrayList<Prestamo> prestamos;
	
	
	public Usuario(String nombre, String telefono, String correo) {
		this.correo = correo;
		this.nombre = nombre;
		this.telefono = telefono;
		this.prestamos = new ArrayList<>();
		
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
		prestamos.add(nuevoPrestamo);
	}
	public void eliminarPrestamo(int pos) {
		prestamos.remove(pos);
	}
	public ArrayList<Prestamo> getPrestamos() {
		return prestamos;
	}
	
	
}