package appSinNombre;

import java.util.ArrayList;

public class Itinerario {
	
	private ArrayList <Promocion> promocionesAceptadas = new ArrayList<Promocion>();
	private ArrayList <Atraccion> atraccionesAceptadas= new ArrayList<Atraccion>();
	private int cantidadPromociones =0;
	private int cantidadAtracciones=0;
	
	public void agregarPromocion(Promocion nueva) {
		promocionesAceptadas.add(nueva);
		cantidadPromociones++;
	}
	
	public void agregarAtraccion(Atraccion nueva) {
		atraccionesAceptadas.add(nueva);
		cantidadAtracciones++;
	}

	public ArrayList<Promocion> getPromocionesAceptadas() {
		return promocionesAceptadas;
	}

	public ArrayList<Atraccion> getAtraccionesAceptadas() {
		return atraccionesAceptadas;
	}

	public int getCantidadPromociones() {
		return cantidadPromociones;
	}

	public int getCantidadAtracciones() {
		return cantidadAtracciones;
	}
	
	
	
	

}
