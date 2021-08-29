package appSinNombre;

public class Porcentual extends Promocion {

	public Porcentual(String nombreDePromo, int porcentajeDeDescuento, Atraccion a, Atraccion b) {
		boolean tiposDistintos = a.getTipo() != b.getTipo();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");

		this.nombreDePromocion = nombreDePromo;
		this.tiposDeAtracciones = a.getTipo();
		this.costo = a.getCosto() + b.getCosto();
		this.descuento = this.costo * porcentajeDeDescuento / 100;
		this.tiempoNecesario = a.getTiempoNecesario() + b.getTiempoNecesario();
		this.atraccion1 = a;
		this.atraccion2 = b;
	}

	@Override
	public Integer getCosto() {
		return this.costo - this.descuento;
	}

	@Override
	public TipoDeAtraccion getTipo() {
		return tiposDeAtracciones;
	}

	@Override
	public Double getTiempoNecesario() {
		return this.tiempoNecesario;
	}
}