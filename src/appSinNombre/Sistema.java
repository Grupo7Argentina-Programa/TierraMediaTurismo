package appSinNombre;

import java.util.ArrayList;
import java.util.Arrays;

public class Sistema {

	public ArrayList<Atraccion> sugerirAtraccion(Usuario usuario, Atraccion[] listaDisponibles) {

		Arrays.sort(listaDisponibles, new OrdenadorDeAtracciones().reversed());
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

			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo) {
				sugerencias.add(listaDisponibles[i]);
			}
		}

		for (int i = 0; i < listaDisponibles.length; i++) {

			boolean puedePagarlo = usuario.getPresupuesto() > listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > listaDisponibles[i].getTiempoNecesario();
			boolean EstaEnSugerencias = sugerencias.contains(listaDisponibles[i]);

			if (puedePagarlo && tieneTiempo && !EstaEnSugerencias) {
				sugerencias.add(listaDisponibles[i]);
			}
		}

		return sugerencias;
	}

	public ArrayList<Promocion> sugerirPromocion(Usuario usuario, Promocion[] listaDisponibles) {

		Arrays.sort(listaDisponibles, new OrdenadorDePromociones().reversed());
		ArrayList<Promocion> sugerencias = new ArrayList<Promocion>();

		for (int i = 0; i < listaDisponibles.length; i++) {

			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i].getTipo();
			boolean puedePagarlo = usuario.getPresupuesto() >= listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() >= listaDisponibles[i].getTiempoNecesario();

			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo) {
				sugerencias.add(listaDisponibles[i]);
			}
		}

		for (int i = 0; i < listaDisponibles.length; i++) {

			boolean puedePagarlo = usuario.getPresupuesto() >= listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() >= listaDisponibles[i].getTiempoNecesario();
			boolean EstaEnSugerencias = sugerencias.contains(listaDisponibles[i]);

			if (puedePagarlo && tieneTiempo && !EstaEnSugerencias) {
				sugerencias.add(listaDisponibles[i]);
			}
		}
		// Collections.sort(sugerencias, new OrdenadorDePromociones());

		return sugerencias;
	}
}