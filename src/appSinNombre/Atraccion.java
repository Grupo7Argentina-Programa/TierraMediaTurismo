package appSinNombre;

import java.util.Objects;

public class Atraccion implements Mostrable, Comparable<Atraccion> {

	private String nombreDeAtraccion;
	private Integer costo;
	private Double tiempoNecesario;
	private int cupo;
	private TipoDeAtraccion tipo;

	public Atraccion(String nombre, int costo, double tiempoNecesario, int cupo, TipoDeAtraccion tipo) {

		this.nombreDeAtraccion = nombre;
		this.costo = costo;
		this.tiempoNecesario = tiempoNecesario;
		this.cupo = cupo;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombreDeAtraccion;
	}

	public Integer getCosto() {
		return costo;
	}

	public Double getTiempoNecesario() {
		return tiempoNecesario;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipo;
	}

	@Override
	public String toString() {
		return "\n" + nombreDeAtraccion + "\n Costo: " + costo + "\n Tiempo promedio requerido: " + tiempoNecesario
				+ "\n Cupos: " + cupo + "\n Tipo de atracción: " + tipo + "\n ------------------------";
	}

	@Override
	public boolean estaEnItinerario(Itinerario actual) {
		return actual.getAtraccionesAceptadas().contains(this);
	}

	@Override
	public void aceptoMostrable(Usuario comprador) {
		this.cupo-=1;
		comprador.aceptarAtraccion(this);
		
	}

	public void compradaPorPromocion() {
		this.cupo-=1;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, nombreDeAtraccion, tiempoNecesario, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return Objects.equals(costo, other.costo) && Objects.equals(nombreDeAtraccion, other.nombreDeAtraccion)
				&& Objects.equals(tiempoNecesario, other.tiempoNecesario) && tipo == other.tipo;
	}
	

	@Override
	public int compareTo(Atraccion otraAtraccion) {
		int comparacionPorCosto = this.costo.compareTo(otraAtraccion.getCosto());
		
		if (comparacionPorCosto != 0)
			return comparacionPorCosto;

		return this.tiempoNecesario.compareTo(otraAtraccion.tiempoNecesario);
	}

	public int getCupo() {
		return this.cupo;
	}
}
