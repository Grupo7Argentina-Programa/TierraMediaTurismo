package appSinNombre;

public class AxB extends Promocion {

	public AxB(Atraccion a, Atraccion b, Atraccion c, Atraccion d) {

		boolean tiposDistintos = a.getTipo() != b.getTipo() || b.getTipo() != c.getTipo() || c.getTipo() != d.getTipo();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");
		
		this.tipoDePromocion = a.getTipo();
		this.costo = a.getCosto() + b.getCosto() + c.getCosto();

	}

	public AxB(Atraccion a, Atraccion b, Atraccion c) {
		boolean tiposDistintos = a.getTipo() != b.getTipo() || b.getTipo() != c.getTipo();

		if (tiposDistintos)
			throw new Error("El tipo de atracción debe ser el mismo");
		
		this.tipoDePromocion = a.getTipo();
		this.costo = a.getCosto() + b.getCosto();
	}

	@Override
	public int getCosto() {
		return this.costo;
	}

}