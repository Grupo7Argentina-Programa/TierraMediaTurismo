package appSinNombre;

import java.util.Objects;

public class Usuario implements Comparable<Usuario> {

	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion atraccionFavorita;
	private Itinerario itinerario;

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoDeAtraccion atraccionFavorita)
			throws NombreInvalido, ValorInvalido, TiempoInvalido {
		if (nombre == "")
			throw new NombreInvalido();
		if (presupuesto <= 0)
			throw new ValorInvalido();
		if (tiempoDisponible <= 0)
			throw new TiempoInvalido();
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

	public int compareTo(Usuario otroUsuario) {
		return this.nombre.compareTo(otroUsuario.nombre);
	}

	@Override
	public String toString() {
		return ("[" + nombre != null ? nombre + ", " : "") + "presupuesto: " + presupuesto + ", tiempoDisponible: "
				+ tiempoDisponible + "]";
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
