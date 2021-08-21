package appSinNombre;

public class Usuario {

	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion atraccionFavorita;
	
	
	
	
	
	public Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoDeAtraccion atraccionFavorita) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionFavorita = atraccionFavorita;
	}
	public int getPresupuesto() {
		return presupuesto;
	}
	public double getTiempoDisponible() {
		return tiempoDisponible;
	}
	public TipoDeAtraccion getAtraccionFavorita() {
		return atraccionFavorita;
	}
}
