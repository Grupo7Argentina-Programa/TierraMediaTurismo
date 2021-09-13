package mediApp;

public class Absoluta extends Promocion {

	public Absoluta(String nombreDePromo, int costo, Atraccion a, Atraccion b)
			throws NombreInvalido, TipoDeAtraccionDistinta {

		boolean tiposDistintos = a.getTipoDeAtraccion() != b.getTipoDeAtraccion();

		if (nombreDePromo == "")
			throw new NombreInvalido();
		if (tiposDistintos)
			throw new TipoDeAtraccionDistinta();
		this.nombreDePromocion = nombreDePromo;
		this.tiposDeAtracciones = a.getTipoDeAtraccion();
		this.costo = costo;
		this.tiempoNecesario = a.getTiempoNecesario() + b.getTiempoNecesario();
		this.atraccion1 = a;
		this.atraccion2 = b;
		this.cantidadDeAtracciones = 2;
	}

	@Override
	public void aceptoMostrable(Usuario comprador) {
		comprador.aceptarPromocion(this);
		atraccion1.compradaPorPromocion();
		atraccion2.compradaPorPromocion();
	}

}