package appSinNombre;

import java.util.ArrayList;
import java.util.Collections;

public class Sistema {

	public ArrayList<Atraccion> generarSugerencia(Usuario usuario, Atraccion[] listaDisponibles) {

		ArrayList<Atraccion> sugerencias = new ArrayList<Atraccion>();

		for (int i = 0; i < listaDisponibles.length; i++) {

			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i].getTipo();
			boolean puedePagarlo = usuario.getPresupuesto() > listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > listaDisponibles[i].getTiempoNecesario();

			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo) {
				sugerencias.add(listaDisponibles[i]);
			}
		}
		Collections.sort(sugerencias, Collections.reverseOrder());
		return sugerencias;
	}

}
