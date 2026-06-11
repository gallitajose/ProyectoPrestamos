package Logica;

public class Alerta {

	private String mensaje;
	private int frecuencia;
	
	public Alerta(String mensaje, int frecuencia) {
		this.frecuencia = frecuencia;
		this.mensaje = mensaje;
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
	
	//hacer el toString
}