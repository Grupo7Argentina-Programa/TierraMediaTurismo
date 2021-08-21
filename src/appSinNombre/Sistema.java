package appSinNombre;

public class Sistema {

	public Atraccion[] generarSugerencia(Usuario usuario, Atraccion[] listaDisponibles) {

		Atraccion[] sugerencias = new Atraccion[listaDisponibles.length];
		int j = 0;

		for (int i = 0; i < listaDisponibles.length; i++) {

			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i].getTipo();
			boolean puedePagarlo = usuario.getPresupuesto() < listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() < listaDisponibles[i].getTiempoNecesario();

			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo) {
				sugerencias[j] = listaDisponibles[i];
				j++;
			}
		}
		return sugerencias;
	}

}
