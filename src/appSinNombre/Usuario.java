package appSinNombre;

public class Usuario {

	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion atraccionFavorita;
	private Itinerario itinerario;	
	
	public Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoDeAtraccion atraccionFavorita) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionFavorita = atraccionFavorita;
		this.itinerario = new Itinerario();
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
	
	public Itinerario getItinerario() {
		return itinerario;
	}
	
	public void aceptarPromocion(Promocion promo) {
		itinerario.agregarPromocion(promo);
		this.presupuesto -= promo.getCosto();
		this.tiempoDisponible -= promo.getTiempoNecesario();
	}
	
	public void aceptarAtraccion(Atraccion atraccion) {
		itinerario.agregarAtraccion(atraccion);
		this.presupuesto -= atraccion.getCosto();
		this.tiempoDisponible -= atraccion.getTiempoNecesario();
	}
}
