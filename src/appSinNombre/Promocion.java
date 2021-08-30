package appSinNombre;

public abstract class Promocion implements Comparable<Promocion> {

	protected TipoDeAtraccion tiposDeAtracciones;
	protected Integer costo;
	protected int descuento;
	protected Double tiempoNecesario;
	protected String nombreDePromocion;
	protected Atraccion atraccion1;
	protected Atraccion atraccion2;
	protected Atraccion atraccion3;
	protected Atraccion atraccion4;
	protected int cantidadDeAtracciones;

	public abstract Integer getCosto();

	public abstract TipoDeAtraccion getTipo();

	public abstract Double getTiempoNecesario();
	
	public abstract int getCantidadDeAtracciones();

	@Override
	public int compareTo(Promocion otraPromocion) {
		int comparacionPorCosto = this.costo.compareTo(otraPromocion.getCosto());

		if (comparacionPorCosto != 0)
			return comparacionPorCosto;

		return this.tiempoNecesario.compareTo(otraPromocion.tiempoNecesario);
	}

	@Override
	public String toString() {
		return "Promocion [" + (nombreDePromocion != null ? nombreDePromocion + ", " : "")
				+ (costo != null ? "costo: " + costo + ", " : "") + ", tiempo necesario:" + tiempoNecesario
				+ ". Atracciones que incluye: " + (atraccion1 != null ? atraccion1.getNombreDeAtraccion() + ", " : "")
				+ (atraccion2 != null ? atraccion2.getNombreDeAtraccion() + ", " : "")
				+ (atraccion3 != null ? atraccion3.getNombreDeAtraccion() + ", " : "")
				+ (atraccion4 != null ? atraccion4.getNombreDeAtraccion() : "") + "]";
	}
}
