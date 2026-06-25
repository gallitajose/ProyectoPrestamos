package Logica;

import java.io.Serializable;
import java.util.Date;

public class Alerta implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mensaje;
	private int frecuencia; 
	private Date fecha;
	
	public Alerta(String mensaje, int frecuencia, Date fecha)  {
		this.frecuencia = frecuencia;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	//hacer el toString
}