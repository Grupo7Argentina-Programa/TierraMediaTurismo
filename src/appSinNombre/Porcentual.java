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
		this.costo-=this.descuento;
		this.tiempoNecesario = a.getTiempoNecesario() + b.getTiempoNecesario();
		this.atraccion1 = a;
		this.atraccion2 = b;
		this.cantidadDeAtracciones = 2;
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
		return this.tiempoNecesario;
	}



	@Override
	public int getCantidadDeAtracciones() {
		return this.cantidadDeAtracciones;
	}



		@Override
		public void aceptoMostrable(Usuario comprador) {
			comprador.aceptarPromocion(this);
			this.atraccion1.compradaPorPromocion();
			this.atraccion2.compradaPorPromocion();
			
		}
		
	}



	
