package appSinNombre;

public class Porcentual extends Promocion {

	public Porcentual(int porcentajeDeDescuento, Atraccion a, Atraccion b) {
		boolean tiposDistintos = a.getTipo() != b.getTipo();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");

		this.tipoDePromocion = a.getTipo();
		this.costo = a.getCosto() + b.getCosto();
		this.descuento = this.costo * porcentajeDeDescuento / 100;
	}

	@Override
	public int getCosto() {
		return this.costo - this.descuento;
	}
}