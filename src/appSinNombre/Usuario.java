package appSinNombre;

public class Usuario {

	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion atraccionFavorita;
	private Itinerario itenerario;	
	
	public Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoDeAtraccion atraccionFavorita) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionFavorita = atraccionFavorita;
		this.itenerario= new Itinerario();
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
	
	public Itinerario getItinirario() {
		return itenerario;
	}
	
	public void setAtraccion(Atraccion nueva) {
		this.itenerario.agregarAtraccion(nueva);

	}
}
