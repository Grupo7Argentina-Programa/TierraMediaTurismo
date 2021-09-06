package appSinNombre;

import java.util.ArrayList;

public class Itinerario {

	private ArrayList<Promocion> promocionesAceptadas = new ArrayList<Promocion>();
	private ArrayList<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();
	private int dineroGastado = 0;


	public void agregarPromocion(Promocion nueva) {
		promocionesAceptadas.add(nueva);
		this.dineroGastado+=nueva.getCosto();
		switch (nueva.cantidadDeAtracciones) {
		case 2:
			this.agregarAtraccionAlArray(nueva.atraccion1);
			this.agregarAtraccionAlArray(nueva.atraccion2);
			break;
		case 3:
			this.agregarAtraccionAlArray(nueva.atraccion1);
			this.agregarAtraccionAlArray(nueva.atraccion2);
			this.agregarAtraccionAlArray(nueva.atraccion3);
			break;
		case 4:
			this.agregarAtraccionAlArray(nueva.atraccion1);
			this.agregarAtraccionAlArray(nueva.atraccion2);
			this.agregarAtraccionAlArray(nueva.atraccion3);
			this.agregarAtraccionAlArray(nueva.atraccion4);
			break;
		}
	}

	private void agregarAtraccionAlArray(Atraccion atraccion) {
		atraccionesAceptadas.add(atraccion);
		
	}

	public void agregarAtraccion(Atraccion nueva) {
		atraccionesAceptadas.add(nueva);
		this.dineroGastado+=nueva.getCosto();
		
	}

	public ArrayList<Promocion> getPromocionesAceptadas() {
		return promocionesAceptadas;
	}

	public ArrayList<Atraccion> getAtraccionesAceptadas() {
		return atraccionesAceptadas;
	}

	

	public int getDineroDelItinerario() {
		return dineroGastado;
	}
	
	
			


	@Override
	public String toString() {
		return "Itinerario: " + (atraccionesAceptadas != null ? "atraccionesAceptadas=" + atraccionesAceptadas : "\n");
				
	}

}
