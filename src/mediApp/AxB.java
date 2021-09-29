package mediApp;

public class AxB extends Promocion {

	public AxB(String nombreDePromo, Atraccion a, Atraccion b, Atraccion c, Atraccion d) throws NombreInvalido, TipoDeAtraccionDistinta {

		boolean tiposDistintos = a.getTipoDeAtraccion() != b.getTipoDeAtraccion()
				|| b.getTipoDeAtraccion() != c.getTipoDeAtraccion() || c.getTipoDeAtraccion() != d.getTipoDeAtraccion();


			if (nombreDePromo == "") {
				throw new NombreInvalido();
			}

			if (tiposDistintos) {
				throw new TipoDeAtraccionDistinta();
			}

			this.nombreDePromocion = nombreDePromo;
			this.tiposDeAtracciones = a.getTipoDeAtraccion();
			this.costo = a.getCosto() + b.getCosto() + c.getCosto();
			this.tiempoNecesario = a.getTiempoNecesario() + b.getTiempoNecesario() + c.getTiempoNecesario()
					+ d.getTiempoNecesario();
			this.atraccion1 = a;
			this.atraccion2 = b;
			this.atraccion3 = c;
			this.atraccion4 = d;
			this.cantidadDeAtracciones = 4;
	}

	public AxB(String nombreDePromo, Atraccion a, Atraccion b, Atraccion c) throws NombreInvalido, TipoDeAtraccionDistinta {
		boolean tiposDistintos = a.getTipoDeAtraccion() != b.getTipoDeAtraccion()
				|| b.getTipoDeAtraccion() != c.getTipoDeAtraccion();

			if (nombreDePromo == "") {
				throw new NombreInvalido();
			}

			if (tiposDistintos) {
				throw new TipoDeAtraccionDistinta();
			}

			this.nombreDePromocion = nombreDePromo;
			this.tiposDeAtracciones = a.getTipoDeAtraccion();
			this.costo = a.getCosto() + b.getCosto();
			this.tiempoNecesario = a.getTiempoNecesario() + b.getTiempoNecesario() + c.getTiempoNecesario();
			this.atraccion1 = a;
			this.atraccion2 = b;
			this.atraccion3 = c;
			this.cantidadDeAtracciones = 3;

	}

	@Override
	public void aceptoMostrable(Usuario comprador) {
		comprador.aceptarPromocion(this);
	}
}