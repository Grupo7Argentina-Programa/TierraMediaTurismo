package appSinNombre;

public class AxB extends Promocion {


	public AxB(String nombreDePromo, Atraccion a, Atraccion b, Atraccion c, Atraccion d) {

		boolean tiposDistintos = a.getTipo() != b.getTipo() || b.getTipo() != c.getTipo() || c.getTipo() != d.getTipo();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");

		this.nombreDePromocion = nombreDePromo;
		this.tiposDeAtracciones = a.getTipo();
		this.costo = a.getCosto() + b.getCosto() + c.getCosto();
		this.tiempoNecesario = a.getTiempoNecesario() + b.getTiempoNecesario() + c.getTiempoNecesario()
				+ d.getTiempoNecesario();
		this.atraccion1 = a;
		this.atraccion2 = b;
		this.atraccion3 = c;
		this.atraccion4 = d;
		this.cantidadDeAtracciones = 4;
	}

	public AxB(String nombreDePromo, Atraccion a, Atraccion b, Atraccion c) {
		boolean tiposDistintos = a.getTipo() != b.getTipo() || b.getTipo() != c.getTipo();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");

		this.nombreDePromocion = nombreDePromo;
		this.tiposDeAtracciones = a.getTipo();
		this.costo = a.getCosto() + b.getCosto();
		this.tiempoNecesario = a.getTiempoNecesario() + b.getTiempoNecesario() + c.getTiempoNecesario();
		this.atraccion1 = a;
		this.atraccion2 = b;
		this.atraccion3 = c;
		this.cantidadDeAtracciones = 3;
	}

	@Override
	public Integer getCosto() {
		return this.costo;
	}

	@Override
	public TipoDeAtraccion getTipo() {
		return tiposDeAtracciones;
	}

	@Override
	public Double getTiempoNecesario() {
		return tiempoNecesario;
	}

	@Override
	public int getCantidadDeAtracciones() {
		return cantidadDeAtracciones;
	}

}