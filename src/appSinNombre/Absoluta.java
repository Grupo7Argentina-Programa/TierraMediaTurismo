package appSinNombre;

public class Absoluta extends Promocion {

	public Absoluta(String nombreDePromo, int costo, Atraccion a, Atraccion b) {

		boolean tiposDistintos = a.getTipoDeAtraccion() != b.getTipoDeAtraccion();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");

		this.nombreDePromocion = nombreDePromo;
		this.tiposDeAtracciones = a.getTipoDeAtraccion();
		this.costo = costo;
		this.tiempoNecesario = a.getTiempoNecesario() + b.getTiempoNecesario();
		this.atraccion1 = a;
		this.atraccion2 = b;
		this.cantidadDeAtracciones = 2;
	}

	@Override
	public Integer getCosto() {
		return costo;
	}

	@Override
	public TipoDeAtraccion getTipoDeAtraccion() {
		return tiposDeAtracciones;
	}

	@Override
	public Double getTiempoNecesario() {
		return tiempoNecesario;
	}

	public Absoluta(int costo, Atraccion a, Atraccion b) {

		boolean tiposDistintos = a.getTipoDeAtraccion() != b.getTipoDeAtraccion();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");

		this.tiposDeAtracciones = a.getTipoDeAtraccion();
		this.costo = costo;
	}

	@Override
	public int getCantidadDeAtracciones() {
		return cantidadDeAtracciones;
	}

	@Override
	public void aceptoMostrable(Usuario comprador) {
		comprador.aceptarPromocion(this);
		atraccion1.compradaPorPromocion();
		atraccion2.compradaPorPromocion();
	}


}