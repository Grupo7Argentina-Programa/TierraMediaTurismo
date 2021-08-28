package appSinNombre;


public class Itinerario {
	
	private Promocion [] promocionesAceptadas = new Promocion[3];
	private Atraccion [] atraccionesAceptadas= new Atraccion [3];
	private int cantidadPromociones =0;
	private int cantidadAtracciones=0;
	
	public void agregarPromocion(Promocion nueva) {
		promocionesAceptadas[cantidadPromociones]= nueva;
		cantidadPromociones++;
	}
	
	public void agregarAtraccion(Atraccion nueva) {
		atraccionesAceptadas[cantidadAtracciones]= nueva;
		cantidadAtracciones++;
	}

	public Promocion getPromocionesAceptadas(int indice) {
		return promocionesAceptadas[indice];
	}

	public Atraccion getAtraccionesAceptadas(int indice) {
		return atraccionesAceptadas[indice];
	}

	public int getCantidadPromociones() {
		return cantidadPromociones;
	}

	public int getCantidadAtracciones() {
		return cantidadAtracciones;
	}
	
	
	
	

}
