package appSinNombre;

public class Atraccion {
	
	private String nombreDeAtraccion;
	private int costo;
	private double tiempoNecesario;
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

	public int getCosto() {
		return costo;
	}

	public double getTiempoNecesario() {
		return tiempoNecesario;
	}

	public TipoDeAtraccion getTipo() {
		return tipo;
	}
}
