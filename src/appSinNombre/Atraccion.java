package appSinNombre;


public class Atraccion implements Comparable<Atraccion> {

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

	public String getNombreDeAtraccion() {
		return nombreDeAtraccion;
	}

	public Integer getCosto() {
		return costo;
	}

	public Double getTiempoNecesario() {
		return tiempoNecesario;
	}

	public TipoDeAtraccion getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "[" + nombreDeAtraccion + ", Costo: " + costo + ", Tiempo promedio requerido: " + tiempoNecesario
				+ ", Cupos: " + cupo + ", Tipo de atracción: " + tipo + "]";
	}

	@Override
	public int compareTo(Atraccion otraAtraccion) {
		int comparacionPorCosto = this.costo.compareTo(otraAtraccion.getCosto());
		
		if (comparacionPorCosto != 0)
			return comparacionPorCosto;

		return this.tiempoNecesario.compareTo(otraAtraccion.tiempoNecesario);
	}
}
