package appSinNombre;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Scanner;




public class Sistema {
	
	


	public ArrayList<Atraccion> sugerirAtraccion(Usuario usuario,Atraccion[] listaDisponibles) {

		Arrays.sort(listaDisponibles,new OrdenadorDeMostrables().reversed());
		// Se presenta la lista de atracciones disponibles
		// ya ordenada, y a medida que el método va
		// creando las sugerencias, se van tomando
		// primero las favoritas del usuario, luego las
		// más caras y luego las que requieren más
		// tiempo. De esa forma se entregan las
		// sugerencias ordenadas según lo pide la
		// consigna.

		
		ArrayList<Atraccion> sugerencias = new ArrayList<Atraccion>();

		for (int i = 0; i < listaDisponibles.length; i++) {

			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i].getTipo();
			boolean puedePagarlo = usuario.getPresupuesto() > listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > listaDisponibles[i].getTiempoNecesario();
			boolean estaEnItinerario = usuario.getItinerario().getAtraccionesAceptadas().contains(listaDisponibles[i]);
			
			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !estaEnItinerario) {
				sugerencias.add(listaDisponibles[i]); 
			}
		}
		

		for (int i = 0; i < listaDisponibles.length; i++) {

			boolean puedePagarlo = usuario.getPresupuesto() > listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > listaDisponibles[i].getTiempoNecesario();
			boolean estaEnItinerario = usuario.getItinerario().getAtraccionesAceptadas().contains(listaDisponibles[i]);
			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i].getTipo();

			if (puedePagarlo && tieneTiempo && !tipoDeAtraccionFavorita && !estaEnItinerario) {
				sugerencias.add(listaDisponibles[i]);
			}
		}
		
		return sugerencias;
	}

	

	public ArrayList<Promocion> sugerirPromocion(Usuario usuario,Promocion[] listaDisponibles) {

		
		Arrays.sort(listaDisponibles, new OrdenadorDeMostrables().reversed());
		ArrayList<Promocion> sugerencias = new ArrayList<Promocion>();

		for (int i = 0; i < listaDisponibles.length; i++) {

			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i].getTipo();
			boolean puedePagarlo = usuario.getPresupuesto() >= listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() >= listaDisponibles[i].getTiempoNecesario();
			boolean tieneAtraccionYaComprada = usuario.getItinerario().getAtraccionesAceptadas().contains(listaDisponibles[i].atraccion1) ||
											   usuario.getItinerario().getAtraccionesAceptadas().contains(listaDisponibles[i].atraccion2) ||
											   usuario.getItinerario().getAtraccionesAceptadas().contains(listaDisponibles[i].atraccion3) ||
											   usuario.getItinerario().getAtraccionesAceptadas().contains(listaDisponibles[i].atraccion4);
			
			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !tieneAtraccionYaComprada) {
				sugerencias.add(listaDisponibles[i]);
				
			}}

		for (int i = 0; i < listaDisponibles.length; i++) {

			boolean puedePagarlo = usuario.getPresupuesto() >= listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() >= listaDisponibles[i].getTiempoNecesario();
			boolean EstaEnSugerencias = sugerencias.contains(listaDisponibles[i]);
			boolean tieneAtraccionYaComprada = usuario.getItinerario().getAtraccionesAceptadas().contains(listaDisponibles[i].atraccion1) ||
					   usuario.getItinerario().getAtraccionesAceptadas().contains(listaDisponibles[i].atraccion2) ||
					   usuario.getItinerario().getAtraccionesAceptadas().contains(listaDisponibles[i].atraccion3) ||
					   usuario.getItinerario().getAtraccionesAceptadas().contains(listaDisponibles[i].atraccion4);

			if (puedePagarlo && tieneTiempo && !EstaEnSugerencias && !tieneAtraccionYaComprada) {
				sugerencias.add(listaDisponibles[i]);
	
			}
			}
		
		return sugerencias;
	}
	
private void mostrarPreferencia(Usuario usuario,Mostrable[] listaDisponibles)
{
	Arrays.sort(listaDisponibles, new OrdenadorDeMostrables().reversed());
	Scanner entrada = new Scanner(System.in);
	for (int i = 0; i < listaDisponibles.length; i++) {
		boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i].getTipo();
		boolean puedePagarlo = usuario.getPresupuesto() > listaDisponibles[i].getCosto();
		boolean tieneTiempo = usuario.getTiempoDisponible() > listaDisponibles[i].getTiempoNecesario();
		boolean yaFueComprada = listaDisponibles[i].estaEnItinerario(usuario.getItinerario());
		
		if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !yaFueComprada) {
			System.out.println(listaDisponibles[i]);
			System.out.println("Ingrese opcion: 1) ACEPTA   2) SIGUIENTE");
			 int opcion = entrada.nextInt();
			 if(opcion==1) {
				 listaDisponibles[i].aceptoMostrable(usuario);
				 System.out.println("COMPRADA");
				 
			 }
		}
		
	}
	
	
	
	
}
private void mostrarSinPreferencia(Usuario usuario,Mostrable[] listaDisponibles)
{
	Arrays.sort(listaDisponibles, new OrdenadorDeMostrables().reversed());
	Scanner entrada = new Scanner(System.in);
	for (int i = 0; i < listaDisponibles.length; i++) {
		boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i].getTipo();
		boolean puedePagarlo = usuario.getPresupuesto() > listaDisponibles[i].getCosto();
		boolean tieneTiempo = usuario.getTiempoDisponible() > listaDisponibles[i].getTiempoNecesario();
		boolean yaFueComprada = listaDisponibles[i].estaEnItinerario(usuario.getItinerario());
		
		if (!tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !yaFueComprada) {
			System.out.println(listaDisponibles[i]);
			System.out.println("Ingrese opcion: 1) ACEPTA   2) SIGUIENTE");
			 int opcion = entrada.nextInt();
			 if(opcion==1) {
				 listaDisponibles[i].aceptoMostrable(usuario);
				 System.out.println("COMPRADA");
				 
			 }
		}
		
	}
		
}


public void sugerirItinerario(Usuario usuario,Atraccion[] listaDeAtraccion,Promocion[] listaDePromocion) 
{
	System.out.println("PROMOCIONES POR GUSTO \n");
	this.mostrarPreferencia(usuario, listaDePromocion);
	if(usuario.getPresupuesto()>0) {
	System.out.println("\n ATRACCIONES POR GUSTO \n");
	this.mostrarPreferencia(usuario, listaDeAtraccion);}
	if(usuario.getPresupuesto()>0)
	{
		System.out.println("\n PROMOCIONES DE OTRO TIPO DE ATRACCION \n");
		this.mostrarSinPreferencia(usuario, listaDePromocion);
	}
	if(usuario.getPresupuesto()>0)
	{
		System.out.println("\n ATRACCIONES DE OTRO TIPO \n");
		this.mostrarSinPreferencia(usuario, listaDeAtraccion);
	}
	
	
	System.out.println("\n Se terminaron las oferetas \n");
	System.out.println("Su Itninerario es el siguiente \n");
	System.out.println(usuario.getItinerario());
	System.out.println("\n Su costo es:  "+usuario.getItinerario().getDineroDelItinerario());
	
	
}
}




		
		






	
