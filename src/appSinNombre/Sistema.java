package appSinNombre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Sistema {

	Scanner entrada = new Scanner(System.in);
	TreeSet<Atraccion> listaAtracciones = new TreeSet<Atraccion>();
	TreeSet<Usuario> listaUsuarios = new TreeSet<Usuario>();
	TreeSet<Promocion> listaPromociones = new TreeSet<Promocion>();

	private TreeSet<Atraccion> leerAtracciones() throws IOException {

		FileReader fr = null;
		BufferedReader br = null;
		// List<Mostrable> listaAtracciones = new ArrayList<Mostrable>();

		try {
			fr = new FileReader("listaDeAtracciones.in", StandardCharsets.UTF_8);
			br = new BufferedReader(fr);
			String linea;

			try {
				while ((linea = br.readLine()) != null) {
					String[] auxiliar = linea.split(",");
					String nombre = auxiliar[0];
					Integer costo = Integer.parseInt(auxiliar[1]);
					Double tiempoRequerido = Double.parseDouble(auxiliar[2]);
					Integer cupo = Integer.parseInt(auxiliar[3]);
					TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(auxiliar[4]);

					Atraccion atraccion = new Atraccion(nombre, costo, tiempoRequerido, cupo, tipo);
					this.listaAtracciones.add(atraccion);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return this.listaAtracciones;
	}

	private TreeSet<Usuario> leerUsuarios() throws IOException {

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader("usuarios.in", StandardCharsets.UTF_8);
			br = new BufferedReader(fr);
			String linea;

			try {
				while ((linea = br.readLine()) != null) {
					String[] auxiliar = linea.split(",");
					String nombre = auxiliar[0];
					Integer dinero = Integer.parseInt(auxiliar[1]);
					Double tiempoDisponible = Double.parseDouble(auxiliar[2]);
					TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(auxiliar[3]);

					Usuario usuario = new Usuario(nombre, dinero, tiempoDisponible, tipo);
					listaUsuarios.add(usuario);
					System.out.println(usuario);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}

	private TreeSet<Promocion> leerPromociones() throws IOException {

		FileReader fr = null;
		BufferedReader br = null;
		Promocion promocion;
		this.leerAtracciones();

		try {
			fr = new FileReader("listaDePromociones.in", StandardCharsets.UTF_8);
			br = new BufferedReader(fr);
			String linea;

			try {
				while ((linea = br.readLine()) != null) {
					String[] auxiliar = linea.split(",");
					String tipoDePromo = auxiliar[0];
					if (tipoDePromo.matches("Porcentual")) {
						String nombreDePromo = auxiliar[1];
						Integer descuento = Integer.parseInt(auxiliar[2]);
						Atraccion atraccion1 = buscar(auxiliar[3]);
						Atraccion atraccion2 = buscar(auxiliar[4]);
						promocion = new Porcentual(nombreDePromo, descuento, atraccion1, atraccion2);
						listaPromociones.add(promocion);
					}
					if (tipoDePromo.matches("Absoluta")) {
						String nombreDePromo = auxiliar[1];
						Integer costo = Integer.parseInt(auxiliar[2]);
						Atraccion atraccion1 = buscar(auxiliar[3]);
						Atraccion atraccion2 = buscar(auxiliar[4]);
						promocion = new Absoluta(nombreDePromo, costo, atraccion1, atraccion2);
						listaPromociones.add(promocion);
					}
					if (tipoDePromo.matches("AxB")) {
						String nombreDePromo = auxiliar[1];
						Atraccion atraccion1 = buscar(auxiliar[2]);
						Atraccion atraccion2 = buscar(auxiliar[3]);
						Atraccion atraccion3 = buscar(auxiliar[4]);
						if (auxiliar.length > 5) {
							Atraccion atraccion4 = buscar(auxiliar[5]);
							promocion = new AxB(nombreDePromo, atraccion1, atraccion2, atraccion3, atraccion4);
							listaPromociones.add(promocion);
						} else {
							promocion = new AxB(nombreDePromo, atraccion1, atraccion2, atraccion3);
							listaPromociones.add(promocion);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listaPromociones;
	}

	private Atraccion buscar(String nombreDeAtraccion) throws IOException {

		this.leerAtracciones();
		Atraccion[] auxiliar2 = new Atraccion[listaAtracciones.size()];
		auxiliar2 = listaAtracciones.toArray(auxiliar2);
		Atraccion atraccion = null;

		for (int i = 0; i < auxiliar2.length; i++) {
			if (auxiliar2[i].getNombre().compareToIgnoreCase(nombreDeAtraccion) == 0)
				atraccion = auxiliar2[i];
		}
		return atraccion;
	}

	public ArrayList<Mostrable> sugerirAtraccion(Usuario usuario) throws IOException {

		// TreeSet.sort(lista, new OrdenadorDeMostrables().reversed());

		ArrayList<Mostrable> sugerencias = new ArrayList<Mostrable>();

		this.leerAtracciones();
		for (Mostrable elemento : listaAtracciones.descendingSet()) {

			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == elemento.getTipoDeAtraccion();
			boolean puedePagarlo = usuario.getPresupuesto() > elemento.getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > elemento.getTiempoNecesario();
			boolean estaEnItinerario = usuario.getItinerario().getAtraccionesAceptadas().contains(elemento);

			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !estaEnItinerario) {
				sugerencias.add(elemento);
			}
		}

		for (Mostrable elemento : listaAtracciones.descendingSet()) {

			boolean puedePagarlo = usuario.getPresupuesto() > elemento.getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > elemento.getTiempoNecesario();
			boolean estaEnItinerario = usuario.getItinerario().getAtraccionesAceptadas().contains(elemento);
			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == elemento.getTipoDeAtraccion();

			if (puedePagarlo && tieneTiempo && !tipoDeAtraccionFavorita && !estaEnItinerario) {
				sugerencias.add(elemento);
			}
		}

		return sugerencias;
	}

	public ArrayList<Mostrable> sugerirPromocion(Usuario usuario) throws IOException {

		TreeSet<Promocion> lista = this.leerPromociones();
		lista.descendingSet();
		ArrayList<Mostrable> sugerencias = new ArrayList<Mostrable>();

		for (Promocion promo : listaPromociones.descendingSet()) {

			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == promo.getTipoDeAtraccion();
			boolean puedePagarlo = usuario.getPresupuesto() >= promo.getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() >= promo.getTiempoNecesario();
			boolean tieneAtraccionYaComprada = usuario.getItinerario().getAtraccionesAceptadas().contains(
					promo.atraccion1) || usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion2)
					|| usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion3)
					|| usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion4);

			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !tieneAtraccionYaComprada) {
				sugerencias.add(promo);

			}
		}

		for (Promocion promo : listaPromociones.descendingSet()) {

			boolean puedePagarlo = usuario.getPresupuesto() >= promo.getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() >= promo.getTiempoNecesario();
			boolean EstaEnSugerencias = sugerencias.contains(promo);
			boolean tieneAtraccionYaComprada = usuario.getItinerario().getAtraccionesAceptadas().contains(
					promo.atraccion1) || usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion2)
					|| usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion3)
					|| usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion4);

			if (puedePagarlo && tieneTiempo && !EstaEnSugerencias && !tieneAtraccionYaComprada) {
				sugerencias.add(promo);

			}
		}

		return sugerencias;
	}

	private void mostrarPreferencia(Usuario usuario, Mostrable[] listaDisponibles) {

		Arrays.sort(listaDisponibles, new OrdenadorDeMostrables().reversed());

		for (int i = 0; i < listaDisponibles.length; i++) {
			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i]
					.getTipoDeAtraccion();
			boolean puedePagarlo = usuario.getPresupuesto() > listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > listaDisponibles[i].getTiempoNecesario();
			boolean yaFueComprada = listaDisponibles[i].estaEnItinerario(usuario.getItinerario());

			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !yaFueComprada) {
				System.out.println(listaDisponibles[i]);
				System.out.println("Ingrese opcion: 1) ACEPTA   2) SIGUIENTE");
				int opcion = entrada.nextInt();
				if (opcion == 1) {
					listaDisponibles[i].aceptoMostrable(usuario);
					System.out.println("COMPRADA");
				}
			}
		}
	}

	private void mostrarSinPreferencia(Usuario usuario, Mostrable[] listaDisponibles) {
		Arrays.sort(listaDisponibles, new OrdenadorDeMostrables().reversed());

		for (int i = 0; i < listaDisponibles.length; i++) {
			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == listaDisponibles[i]
					.getTipoDeAtraccion();
			boolean puedePagarlo = usuario.getPresupuesto() > listaDisponibles[i].getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > listaDisponibles[i].getTiempoNecesario();
			boolean yaFueComprada = listaDisponibles[i].estaEnItinerario(usuario.getItinerario());

			if (!tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !yaFueComprada) {
				System.out.println(listaDisponibles[i]);
				System.out.println("Ingrese opcion: 1) ACEPTA   2) SIGUIENTE");
				int opcion = entrada.nextInt();
				if (opcion == 1) {
					listaDisponibles[i].aceptoMostrable(usuario);
					System.out.println("COMPRADA");
				}
			}
		}
	}

	public void sugerirItinerario(Usuario usuario, Atraccion[] listaDeAtraccion, Promocion[] listaDePromocion) {
		System.out.println("PROMOCIONES POR GUSTO \n");
		this.mostrarPreferencia(usuario, listaDePromocion);
		if (usuario.getPresupuesto() > 0) {
			System.out.println("\n ATRACCIONES POR GUSTO \n");
		}
		if (usuario.getPresupuesto() > 0) {
			System.out.println("\n PROMOCIONES DE OTRO TIPO DE ATRACCION \n");
			this.mostrarSinPreferencia(usuario, listaDePromocion);
		}
		if (usuario.getPresupuesto() > 0) {
			System.out.println("\n ATRACCIONES DE OTRO TIPO \n");
			this.mostrarSinPreferencia(usuario, listaDeAtraccion);
		}

		System.out.println("\n Se terminaron las ofertas \n");
		System.out.println("Su itinerario es el siguiente \n");
		System.out.println(usuario.getItinerario());
		System.out.println("\n Su costo es: " + usuario.getItinerario().getDineroDelItinerario());
	}
}
