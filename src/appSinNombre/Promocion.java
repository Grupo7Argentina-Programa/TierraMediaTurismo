package appSinNombre;
import java.util.Objects;

public abstract class Promocion implements Mostrable, Comparable<Promocion> {

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

	public Integer getCosto() {
		return this.costo;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		return this.tiposDeAtracciones;
	}

	public Double getTiempoNecesario() {
		return this.tiempoNecesario;
	}
	
	public int getCantidadDeAtracciones() {
		return this.cantidadDeAtracciones;
	}
	
	public boolean estaEnItinerario(Itinerario actual) {
		return actual.getPromocionesAceptadas().contains(this);
	}

	public String getNombre() {
		return this.nombreDePromocion;
	}

	@Override
	public int compareTo(Promocion otraPromocion) {
		OrdenadorDeMostrables ordenador = new OrdenadorDeMostrables();

		return ordenador.compare(this, otraPromocion);
	}

	@Override
	public String toString() {
		return "\n" + "PROMOCION " + (nombreDePromocion != null ? nombreDePromocion + "" : "")
				+ (costo != null ? "\n Costo: " + costo: "") + "\n Tiempo necesario: " + tiempoNecesario
				+ "\n Atracciones que incluye: " 
				+ (atraccion1 != null ? atraccion1.getNombre() + "" : "")
				+ (atraccion2 != null ? ", " + atraccion2.getNombre() : "\n")
				+ (atraccion3 != null ? ", " + atraccion3.getNombre() : "\n")
				+ (atraccion4 != null ? ", " + atraccion4.getNombre() : "\n") 
				+ "------------------------ \n";
	}
	@Override
	public int hashCode() {
		return Objects.hash(atraccion1, atraccion2, atraccion3, atraccion4, cantidadDeAtracciones, costo, descuento,
				nombreDePromocion, tiempoNecesario, tiposDeAtracciones);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccion1, other.atraccion1) && Objects.equals(atraccion2, other.atraccion2)
				&& Objects.equals(atraccion3, other.atraccion3) && Objects.equals(atraccion4, other.atraccion4)
				&& cantidadDeAtracciones == other.cantidadDeAtracciones && Objects.equals(costo, other.costo)
				&& descuento == other.descuento && Objects.equals(nombreDePromocion, other.nombreDePromocion)
				&& Objects.equals(tiempoNecesario, other.tiempoNecesario)
				&& tiposDeAtracciones == other.tiposDeAtracciones;
	}
}
