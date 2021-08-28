package appSinNombre;

import java.util.ArrayList;
import java.util.Collections;




public class Sistema {

	public ArrayList<Atraccion> generarSugerencia(Usuario usuario, Atraccion[] listaDisponibles,int opcion) {

		ArrayList<Atraccion> sugerenciasPoratraccion = new ArrayList<Atraccion>();
		ArrayList<Atraccion> sugerenciasPorDineroYTiempo = new ArrayList<Atraccion>();
		for (int i = 0; i < listaDisponibles.length; i++) {

			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i].getTipo();
			boolean puedePagarlo = usuario.getPresupuesto() > listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > listaDisponibles[i].getTiempoNecesario();

			if (puedePagarlo && tieneTiempo) {
				if(this.atraccionDisponibleEnItinerario(usuario.getItinirario(),listaDisponibles[i])) {
					if(tipoDeAtraccionFavorita) {
						sugerenciasPoratraccion.add(listaDisponibles[i]);
					}else {
						sugerenciasPorDineroYTiempo.add(listaDisponibles[i]);
					}
				}
			
			}
		}
		if(opcion==1) {
			Collections.sort(sugerenciasPorDineroYTiempo);
        	return sugerenciasPorDineroYTiempo;
		      
		    } else if(opcion==2) {
		      
		    	// Tiene que devolver las promociones
		      
		    } else {
		    	System.out.println("Se devuelve sugerencia por preferencia");
		    }
		Collections.sort(sugerenciasPoratraccion);
    	return sugerenciasPoratraccion;
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