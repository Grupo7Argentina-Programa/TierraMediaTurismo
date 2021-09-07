package appSinNombre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Sistema {

	static Scanner entrada = new Scanner(System.in);
	TreeSet<Atraccion> listaAtracciones = new TreeSet<Atraccion>();
	TreeSet<Usuario> listaUsuarios = new TreeSet<Usuario>();
	TreeSet<Promocion> listaPromociones = new TreeSet<Promocion>();
	static Usuario user;

	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Bienvenido a Tierra MediApp");
		System.out.println("---------------------------");
		while (user == null) {
			System.out.println("\n 1) CREAR USUARIO \n 2) INGRESAR");
			int opcion = entrada.nextInt();
			if (opcion == 1) {
				System.out.println("\n Esto todav�a no funciona. Gracias, vuelva prontos.");
			}
			if (opcion == 2) {
				System.out.print("Por favor, ingres� tu usuario \n");
				String usuario = entrada.next();
				new Sistema().cambiarUsuario(usuario);
			} else
				break;
		}
		if (user != null) {
			System.out.println("\n 1) CAMBIAR USUARIO \n 2) VER MIS SUGERENCIAS");
			int opcion = entrada.nextInt();
			if (opcion == 1) {
				System.out.println("\n Ingres� tu usuario");
				String nuevoUsuario = entrada.nextLine();
				new Sistema().cambiarUsuario(nuevoUsuario);
			}
			if (opcion == 2) {
				new Sistema().sugerirItinerario(user);
			}
		}
		entrada.close();
	}

	private void cambiarUsuario(String nuevoUsuario) throws IOException {
		this.leerUsuarios();
		Usuario[] auxiliar = new Usuario[listaUsuarios.size()];
		auxiliar = listaUsuarios.toArray(auxiliar);

		for (int i = 0; i < auxiliar.length; i++) {
			if (auxiliar[i].getNombre().compareToIgnoreCase(nuevoUsuario) == 0)
				Sistema.user = auxiliar[i];
		}
	}

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

	private void escribirArchivo(Itinerario itinerario) throws IOException {

		File file = new File("itinerario" + user.getNombre() + ".out");
		PrintWriter salida = new PrintWriter(new FileWriter(file));

		salida.println(itinerario);

		salida.close();
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

	private void mostrarPreferencia(Usuario usuario) throws IOException {

		// Arrays.sort(listaDisponibles, new OrdenadorDeMostrables().reversed());
		this.leerAtracciones();
		this.leerPromociones();
		this.leerUsuarios();
		ArrayList<Mostrable> listaDisponibles = new ArrayList<Mostrable>();

		listaDisponibles.addAll(listaPromociones.descendingSet());
		listaDisponibles.addAll(listaAtracciones.descendingSet());

		for (Mostrable objeto : listaDisponibles) {
			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == objeto.getTipoDeAtraccion();
			boolean puedePagarlo = usuario.getPresupuesto() > objeto.getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > objeto.getTiempoNecesario();
			boolean yaFueComprada = objeto.estaEnItinerario(usuario.getItinerario());

			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !yaFueComprada) {
				System.out.println(objeto);
				System.out.println("Ingrese opcion: 1) ACEPTA   2) SIGUIENTE");
				int opcion = entrada.nextInt();
				if (opcion == 1) {
					objeto.aceptoMostrable(usuario);
					System.out.println("COMPRADA");
				}
			}
		}
	}

	private void mostrarSinPreferencia(Usuario usuario) throws IOException {
		// Arrays.sort(listaDisponibles, new OrdenadorDeMostrables().reversed());
		this.leerAtracciones();
		this.leerPromociones();
		this.leerUsuarios();
		ArrayList<Mostrable> listaDisponibles = new ArrayList<Mostrable>();

		listaDisponibles.addAll(listaPromociones.descendingSet());
		listaDisponibles.addAll(listaAtracciones.descendingSet());

		for (Mostrable objeto : listaDisponibles) {
			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == objeto.getTipoDeAtraccion();
			boolean puedePagarlo = usuario.getPresupuesto() > objeto.getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() > objeto.getTiempoNecesario();
			boolean yaFueComprada = objeto.estaEnItinerario(usuario.getItinerario());

			if (!tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !yaFueComprada) {
				System.out.println(objeto);
				System.out.println("Ingrese opcion: 1) ACEPTA   2) SIGUIENTE");
				int opcion = entrada.nextInt();
				if (opcion == 1) {
					objeto.aceptoMostrable(usuario);
					System.out.println("COMPRADA");
				}
			}
		}
	}

	public void sugerirItinerario(Usuario usuario) throws IOException {
		System.out.println("PROMOCIONES POR GUSTO \n");
		this.mostrarPreferencia(usuario);
		if (usuario.getPresupuesto() > 0) {
			System.out.println("\n ATRACCIONES POR GUSTO \n");
		}
		if (usuario.getPresupuesto() > 0) {
			System.out.println("\n PROMOCIONES DE OTRO TIPO DE ATRACCION \n");
			this.mostrarSinPreferencia(usuario);
		}
		/*
		 * if (usuario.getPresupuesto() > 0) {
		 * System.out.println("\n ATRACCIONES DE OTRO TIPO \n");
		 * this.mostrarSinPreferencia(usuario); }
		 */
		this.escribirArchivo(usuario.getItinerario());
		System.out.println("\n Se terminaron las ofertas \n");
		System.out.println("Su itinerario es el siguiente \n");
		System.out.println(usuario.getItinerario());
		System.out.println("\n Su costo es: " + usuario.getItinerario().getDineroDelItinerario() + " monedas");
	}
}
