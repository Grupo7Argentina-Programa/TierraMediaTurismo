package mediApp;

import java.util.Objects;
import java.util.ArrayList;

public class Itinerario {

	private ArrayList<Promocion> promocionesAceptadas = new ArrayList<Promocion>();
	private ArrayList<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();
	private int dineroGastado = 0;
	private double tiempoRequeridoTotal = 0.0;
	private Usuario usuario;
	
	

	public Itinerario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void agregarPromocion(Promocion nueva) {
		this.promocionesAceptadas.add(nueva);
		this.dineroGastado += nueva.getCosto();
		this.tiempoRequeridoTotal += nueva.getTiempoNecesario();
		switch (nueva.cantidadDeAtracciones) {
		case 2:
			this.agregarAtraccionCompradaPorPromo(nueva.atraccion1);
			this.agregarAtraccionCompradaPorPromo(nueva.atraccion2);
			break;
		case 3:
			this.agregarAtraccionCompradaPorPromo(nueva.atraccion1);
			this.agregarAtraccionCompradaPorPromo(nueva.atraccion2);
			this.agregarAtraccionCompradaPorPromo(nueva.atraccion3);
			break;
		case 4:
			this.agregarAtraccionCompradaPorPromo(nueva.atraccion1);
			this.agregarAtraccionCompradaPorPromo(nueva.atraccion2);
			this.agregarAtraccionCompradaPorPromo(nueva.atraccion3);
			this.agregarAtraccionCompradaPorPromo(nueva.atraccion4);
			break;
		}
	}

	public void agregarAtraccionCompradaPorPromo(Atraccion atraccion) {
			atraccionesAceptadas.add((Atraccion) atraccion);
	}

	public void agregarAtraccion(Atraccion nueva) {
		atraccionesAceptadas.add(nueva);
		this.dineroGastado += nueva.getCosto();
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
		return "ITINERARIO\n" + "--------------------" + (atraccionesAceptadas != null ? atraccionesAceptadas + "" : "")
				+ "\nCosto total: " + dineroGastado + " monedas" + "\nTiempo requerido total: " + tiempoRequeridoTotal
				+ " horas \n" + "-------------------- \n";
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
	
	public Usuario getUsuario() {
		return this.usuario;
	}
}