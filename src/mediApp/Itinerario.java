package mediApp;

import java.util.Objects;
import java.util.ArrayList;

public class Itinerario {

	private ArrayList<Promocion> promocionesAceptadas = new ArrayList<Promocion>();
	private ArrayList<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();
	private int dineroGastado = 0;
	private double tiempoRequeridoTotal = 0.0;

	public void agregarPromocion(Promocion nueva) {
		promocionesAceptadas.add(nueva);
		this.dineroGastado += nueva.getCosto();
		this.tiempoRequeridoTotal += nueva.getTiempoNecesario();
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
		this.tiempoRequeridoTotal += nueva.getTiempoNecesario();
		
	}

	public ArrayList<Promocion> getPromocionesAceptadas() {
		return promocionesAceptadas;
	}

	public ArrayList<Atraccion> getAtraccionesAceptadas() {
		return atraccionesAceptadas;
	}

	public double getTiempoTotal() {
		return tiempoRequeridoTotal;
	}
	
	@Override
	public String toString() {
		return "Itinerario\n" + "--------------------" 
				+ (atraccionesAceptadas != null ? atraccionesAceptadas + "" : "")
				+ "\n Costo total: " + dineroGastado + "monedas" + "\n Tiempo requerido total: " + tiempoRequeridoTotal + "horas";
	}
	
	public int getDineroDelItinerario() {
		return dineroGastado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccionesAceptadas, dineroGastado, promocionesAceptadas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerario other = (Itinerario) obj;
		return Objects.equals(atraccionesAceptadas, other.atraccionesAceptadas) && dineroGastado == other.dineroGastado
				&& Objects.equals(promocionesAceptadas, other.promocionesAceptadas);
	}
}