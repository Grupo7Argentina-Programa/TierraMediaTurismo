package appSinNombre;

public class Absoluta extends Promocion {

	public Absoluta(int costo, Atraccion a, Atraccion b) {
		
		boolean tiposDistintos = a.getTipo() != b.getTipo();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");
		
		this.tipoDePromocion = a.getTipo();
		this.costo = costo;
	}

	@Override
	public int getCosto() {
		return costo;
	}

}
