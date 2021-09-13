package mediApp;

public class AxB extends Promocion {

	public AxB(String nombreDePromo, Atraccion a, Atraccion b, Atraccion c, Atraccion d) {

		boolean tiposDistintos = a.getTipoDeAtraccion() != b.getTipoDeAtraccion()
				|| b.getTipoDeAtraccion() != c.getTipoDeAtraccion() || c.getTipoDeAtraccion() != d.getTipoDeAtraccion();

		try {
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
		} catch (TipoDeAtraccionDistinta e) {
			System.err.println("Las atracciones deben ser del mismo tipo");
		} catch (NombreInvalido e) {
			System.err.println("El nombre no es válido");
		}
	}

	public AxB(String nombreDePromo, Atraccion a, Atraccion b, Atraccion c) {
		boolean tiposDistintos = a.getTipoDeAtraccion() != b.getTipoDeAtraccion()
				|| b.getTipoDeAtraccion() != c.getTipoDeAtraccion();

		try {
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
		} catch (TipoDeAtraccionDistinta e) {
			System.err.println("Las atracciones deben ser del mismo tipo");
		} catch (NombreInvalido e) {
			System.err.println("El nombre no es válido");
		}
	}

	@Override
	public void aceptoMostrable(Usuario comprador) {
		comprador.aceptarPromocion(this);
	}
}