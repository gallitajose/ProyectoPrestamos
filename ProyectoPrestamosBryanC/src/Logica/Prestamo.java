package Logica;

import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Prestamo {

	private int idPrestamo;
	private Date fecha;
	private Usuario usuario;
	private Alerta alerta;
	private Map<Integer, Item> items = new HashMap<>() ;
	
	
	public Prestamo(int idPrestamo, Date fecha, Usuario usuario, Alerta alerta){
		this.idPrestamo = idPrestamo;
		this.usuario = usuario;
		this.fecha = fecha;
		this.alerta = alerta;
		this.items = new HashMap<>();
		
		
	}

	public int getIdPrestamo() {
	    return idPrestamo;
	}

	public Date getFecha() {
	    return fecha;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void agregarItem(Item nuevoItem) {
	    items.put(nuevoItem.getCodigo(), nuevoItem);
	}

	public void eliminarItem(int codigo) {
	    items.remove(codigo);
	}

	public Map<Integer, Item> getItems() {
	    return items;
	}

	public Alerta getAlerta() {
		return alerta;
	}

	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}
	
	
}