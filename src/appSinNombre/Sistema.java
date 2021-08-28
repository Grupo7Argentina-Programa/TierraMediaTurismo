package appSinNombre;

import java.util.ArrayList;
import java.util.Arrays;


public class Sistema {

	public ArrayList<Atraccion> generarSugerencia(Usuario usuario, Atraccion[] listaDisponibles) {

		int bandera=0;
		ArrayList<Atraccion> sugerencias = new ArrayList<Atraccion>();
		ArrayList<Atraccion> puedePagar= new ArrayList<Atraccion>(this.atraccioneesQuePuedePagarOrdenada(usuario, listaDisponibles));
		for (int i = 0; i < puedePagar.size(); i++) {

			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i].getTipo();
			if (tipoDeAtraccionFavorita) {
				sugerencias.add(puedePagar.get(i));
				bandera=1;
			}
		}
		if(bandera==0) {
			return puedePagar;
		}
		return sugerencias;
	}
	

private ArrayList<Atraccion> atraccioneesQuePuedePagarOrdenada(Usuario usuario, Atraccion[] listaDisponibles) 
{
	Itinerario actual=usuario.getItinirario();
	ArrayList<Atraccion> sugerencias = new ArrayList<Atraccion>();
	Arrays.sort(listaDisponibles);
	int pos=0;
	while(usuario.getPresupuesto() < listaDisponibles[pos].getCosto()) {
		pos++;	
	}
	for (int i=pos;i < listaDisponibles.length; i++) {
		if(usuario.getTiempoDisponible()>listaDisponibles[i].getTiempoNecesario()) {
			if(this.atraccionDisponibleEnItinerario(actual,listaDisponibles[i]))	
			sugerencias.add(listaDisponibles[i]);}
	}
	return sugerencias;
	}

private boolean atraccionDisponibleEnItinerario(Itinerario itinerarioUsuario,Atraccion busqueda) {
	
	boolean valor = true;
	int indice=0;
	while (indice <itinerarioUsuario.getCantidadAtracciones()) {
		if(itinerarioUsuario.getAtraccionesAceptadas(indice).getNombreDeAtraccion()==busqueda.getNombreDeAtraccion()) {
			valor= false;
			indice=itinerarioUsuario.getCantidadAtracciones();}
		indice++;
		}
return valor;	
	
}
}