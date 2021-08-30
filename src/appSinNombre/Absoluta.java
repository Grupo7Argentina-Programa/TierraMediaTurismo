package appSinNombre;

public class Absoluta extends Promocion {

	public Absoluta(String nombreDePromo, int costo, Atraccion a, Atraccion b) {

		boolean tiposDistintos = a.getTipo() != b.getTipo();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");

		this.nombreDePromocion = nombreDePromo;
		this.tiposDeAtracciones = a.getTipo();
		this.costo = costo;
		this.tiempoNecesario = a.getTiempoNecesario() + b.getTiempoNecesario();
		this.atraccion1 = a;
		this.atraccion2 = b;
	}

	@Override
	public Integer getCosto() {
		return costo;
	}

	@Override
	public TipoDeAtraccion getTipo() {
		return tiposDeAtracciones;
	}

	@Override
	public Double getTiempoNecesario() {
		return tiempoNecesario;
	}

	public Absoluta(int costo, Atraccion a, Atraccion b) {
		
		boolean tiposDistintos = a.getTipo() != b.getTipo();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");
		
		this.tiposDeAtracciones = a.getTipo();
		this.costo = costo;
	}
}