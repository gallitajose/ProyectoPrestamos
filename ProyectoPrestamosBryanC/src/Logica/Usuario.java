package Logica;



public class Usuario {
	private String nombre;
	private String telefono;
	private String correo;
	private int idUsuario;
	
	public Usuario(String nombre, String telefono, String correo, int idUsuario) {
		this.correo = correo;
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.telefono = telefono;
		
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
	public int getIdUsuario() {
		return idUsuario;
	}
	
	
	
}
