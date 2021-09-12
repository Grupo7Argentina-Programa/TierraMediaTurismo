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
	static TreeSet<Atraccion> listaAtracciones = new TreeSet<Atraccion>();
	static TreeSet<Usuario> listaUsuarios = new TreeSet<Usuario>();
	static TreeSet<Promocion> listaPromociones = new TreeSet<Promocion>();
	static Usuario user;

	public static void main(String[] args) {

		try {
			leerAtracciones();
			leerPromociones();
			leerUsuarios();
		} catch (IOException e) {
			System.err.println("El archivo no se pudo leer");
		}

		Scanner entrada = new Scanner(System.in);
		int opcion = 1;
		while (opcion != 0) {
			while (user == null) {
				System.out.println("Bienvenido a Tierra MediApp");
				System.out.println("---------------------------");
				System.out.println("\n 1) CREAR USUARIO \n 2) INGRESAR \n 0) SALIR");
				System.out.println("---------------------------");
				opcion = entrada.nextInt();
				if (opcion == 1) {
					crearUsuarioNuevo();
				}
				if (opcion == 2) {
					System.out.print("Por favor, ingresá tu usuario \n");
					String usuario = entrada.next();
					System.out.println("---------------------------");
					new Sistema().cambiarUsuario(usuario);
				}
				if (opcion == 0) {
					break;
				}
			}
			while (user != null) {
				System.out.println("Te damos la bienvenida, " + user.getNombre());
				System.out.println("\n 1) CAMBIAR USUARIO \n 2) VER MIS SUGERENCIAS \n 3) CERRAR SESIÓN \n 0) SALIR");
				System.out.println("---------------------------");
				opcion = entrada.nextInt();
				System.out.println("---------------------------");
				if (opcion == 1) {
					System.out.println("\n Por favor, ingresá tu usuario");
					String nuevoUsuario = entrada.next();
					System.out.println("---------------------------");
					new Sistema().cambiarUsuario(nuevoUsuario);
				}
				if (opcion == 2) {
					new Sistema().sugerirItinerario(user);
				}
				if (opcion == 3) {
					System.out.println("Hasta pronto, " + user.getNombre());
					new Sistema().cambiarUsuario(null);
				}
				if (opcion == 0) {
					break;
				}
			}
		}
		entrada.close();
		System.out.println("---------------------------");
		System.out.println("Gracias por usar Tierra MediApp");
	}

	private void cambiarUsuario(String nuevoUsuario) {
		boolean usuarioEncontrado = false;
		if (nuevoUsuario == null) {
			Sistema.user = null;
		} else {
			Usuario[] auxiliar = new Usuario[listaUsuarios.size()];
			auxiliar = listaUsuarios.toArray(auxiliar);

			for (int i = 0; i < auxiliar.length; i++) {
				if (auxiliar[i].getNombre().compareToIgnoreCase(nuevoUsuario) == 0) {
					Sistema.user = auxiliar[i];
					usuarioEncontrado = true;
				}
			}
			if (!usuarioEncontrado)
				System.err.println("Nombre de usuario no encontrado");
		}
	}

	private static TreeSet<Atraccion> leerAtracciones() throws IOException {

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
					listaAtracciones.add(atraccion);
				}

			} catch (IOException | ValorInvalido | NombreInvalido | TiempoInvalido e) {
				e.toString();
			}

		} catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado");
		}
		return listaAtracciones;
	}

	private static TreeSet<Usuario> leerUsuarios() throws IOException {

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

			} catch (IOException | ValorInvalido | NombreInvalido | TiempoInvalido e) {
				e.toString();
			}

		} catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado");
		}
		return listaUsuarios;
	}

	private static TreeSet<Promocion> leerPromociones() throws IOException {

		FileReader fr = null;
		BufferedReader br = null;
		Promocion promocion;
		leerAtracciones();

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
			} catch (IOException | NombreInvalido | TipoDeAtraccionDistinta e) {
				e.toString();
			}

		} catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado");
		}
		return listaPromociones;
	}

	private static void escribirArchivo(Itinerario itinerario) {

		File file = new File("itinerario" + user.getNombre() + ".out");
		PrintWriter salida;
		try {
			salida = new PrintWriter(new FileWriter(file));

			StringBuilder builder = new StringBuilder();

			for (Atraccion value : itinerario.getAtraccionesAceptadas()) {
				builder.append(value);
			}
			String texto = builder.toString();
			salida.println("Itinerario de " + user.getNombre());
			salida.println(texto);

			salida.close();
		} catch (IOException e) {
			System.err.println("No se pudo escribir el archivo");
		}
	}

	private static Atraccion buscar(String nombreDeAtraccion) {

		try {
			leerAtracciones();
		} catch (IOException e) {
			System.err.println("No se pudo leer el archivo");
		}
		Atraccion[] auxiliar2 = new Atraccion[listaAtracciones.size()];
		auxiliar2 = listaAtracciones.toArray(auxiliar2);
		Atraccion atraccion = null;

		for (int i = 0; i < auxiliar2.length; i++) {
			if (auxiliar2[i].getNombre().compareToIgnoreCase(nombreDeAtraccion) == 0)
				atraccion = auxiliar2[i];
		}
		return atraccion;
	}

	/*
	 * public ArrayList<Mostrable> sugerirAtraccion(Usuario usuario) throws
	 * IOException {
	 * 
	 * ArrayList<Mostrable> sugerencias = new ArrayList<Mostrable>();
	 * 
	 * for (Mostrable elemento : listaAtracciones.descendingSet()) {
	 * 
	 * boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() ==
	 * elemento.getTipoDeAtraccion(); boolean puedePagarlo =
	 * usuario.getPresupuesto() >= elemento.getCosto(); boolean tieneTiempo =
	 * usuario.getTiempoDisponible() >= elemento.getTiempoNecesario(); boolean
	 * estaEnItinerario =
	 * usuario.getItinerario().getAtraccionesAceptadas().contains(elemento);
	 * 
	 * if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo &&
	 * !estaEnItinerario) { sugerencias.add(elemento); } }
	 * 
	 * for (Mostrable elemento : listaAtracciones.descendingSet()) {
	 * 
	 * boolean puedePagarlo = usuario.getPresupuesto() >= elemento.getCosto();
	 * boolean tieneTiempo = usuario.getTiempoDisponible() >=
	 * elemento.getTiempoNecesario(); boolean estaEnItinerario =
	 * usuario.getItinerario().getAtraccionesAceptadas().contains(elemento); boolean
	 * tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() ==
	 * elemento.getTipoDeAtraccion();
	 * 
	 * if (puedePagarlo && tieneTiempo && !tipoDeAtraccionFavorita &&
	 * !estaEnItinerario) { sugerencias.add(elemento); } }
	 * 
	 * return sugerencias; }
	 */

	/*
	 * public ArrayList<Mostrable> sugerirPromocion(Usuario usuario) throws
	 * IOException {
	 * 
	 * TreeSet<Promocion> lista = leerPromociones(); lista.descendingSet();
	 * ArrayList<Mostrable> sugerencias = new ArrayList<Mostrable>();
	 * 
	 * for (Promocion promo : listaPromociones.descendingSet()) {
	 * 
	 * boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() ==
	 * promo.getTipoDeAtraccion(); boolean puedePagarlo = usuario.getPresupuesto()
	 * >= promo.getCosto(); boolean tieneTiempo = usuario.getTiempoDisponible() >=
	 * promo.getTiempoNecesario(); boolean tieneAtraccionYaComprada =
	 * usuario.getItinerario().getAtraccionesAceptadas().contains( promo.atraccion1)
	 * ||
	 * usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion2)
	 * ||
	 * usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion3)
	 * ||
	 * usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion4);
	 * 
	 * if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo &&
	 * !tieneAtraccionYaComprada) { sugerencias.add(promo);
	 * 
	 * } }
	 * 
	 * for (Promocion promo : listaPromociones.descendingSet()) {
	 * 
	 * boolean puedePagarlo = usuario.getPresupuesto() >= promo.getCosto(); boolean
	 * tieneTiempo = usuario.getTiempoDisponible() >= promo.getTiempoNecesario();
	 * boolean EstaEnSugerencias = sugerencias.contains(promo); boolean
	 * tieneAtraccionYaComprada =
	 * usuario.getItinerario().getAtraccionesAceptadas().contains( promo.atraccion1)
	 * ||
	 * usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion2)
	 * ||
	 * usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion3)
	 * ||
	 * usuario.getItinerario().getAtraccionesAceptadas().contains(promo.atraccion4);
	 * 
	 * if (puedePagarlo && tieneTiempo && !EstaEnSugerencias &&
	 * !tieneAtraccionYaComprada) { sugerencias.add(promo);
	 * 
	 * } }
	 * 
	 * return sugerencias; }
	 */

	private void mostrarPreferencia(Usuario usuario) {

		// Arrays.sort(listaDisponibles, new OrdenadorDeMostrables().reversed());

		ArrayList<Mostrable> listaDisponibles = new ArrayList<Mostrable>();

		listaDisponibles.addAll(listaPromociones.descendingSet());
		listaDisponibles.addAll(listaAtracciones.descendingSet());

		for (Mostrable objeto : listaDisponibles) {
			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == objeto.getTipoDeAtraccion();
			boolean puedePagarlo = usuario.getPresupuesto() >= objeto.getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() >= objeto.getTiempoNecesario();
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

	private void mostrarSinPreferencia(Usuario usuario) {
		// Arrays.sort(listaDisponibles, new OrdenadorDeMostrables().reversed());

		ArrayList<Mostrable> listaDisponibles = new ArrayList<Mostrable>();

		listaDisponibles.addAll(listaPromociones.descendingSet());
		listaDisponibles.addAll(listaAtracciones.descendingSet());

		for (Mostrable objeto : listaDisponibles) {
			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == objeto.getTipoDeAtraccion();
			boolean puedePagarlo = usuario.getPresupuesto() >= objeto.getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() >= objeto.getTiempoNecesario();
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

	public void sugerirItinerario(Usuario usuario) {
		System.out.println("PROMOCIONES POR GUSTO \n");
		this.mostrarPreferencia(usuario);
		if (usuario.getPresupuesto() > 0) {
			System.out.println("\n ATRACCIONES POR GUSTO \n");
		}
		if (usuario.getPresupuesto() > 0) {
			System.out.println("\n PROMOCIONES DE OTRO TIPO DE ATRACCION \n");
			this.mostrarSinPreferencia(usuario);
		}

		escribirArchivo(usuario.getItinerario());
		System.out.println("\n Se terminaron las ofertas \n");
		System.out.println("Su itinerario es el siguiente \n");
		System.out.println(usuario.getItinerario());
		System.out.println("\n Su costo es: " + usuario.getItinerario().getDineroDelItinerario() + " monedas");
	}

	private static void crearUsuarioNuevo() {
		File file = new File("usuarios.in");
		PrintWriter salida;

		String auxiliar[] = new String[4];
		try {
			System.out.println("Ingrese su nombre:  ");
			auxiliar[0] = entrada.next();
			while (auxiliar[0] == "") {
				System.err.println("Nombre de usuario inválido. Ingréselo nuevamente.");
				auxiliar[0] = entrada.next();
			}
			while (listaUsuarios.contains(new Usuario(auxiliar[0], 1, 1, null))) {
				System.err.println("Usuario ya existente. Ingrese otro nombre.");
				auxiliar[0] = entrada.next();
			}

			System.out.println("\n Ingrese dinero disponible para gastar:  ");
			auxiliar[1] = entrada.next();
			while (Integer.valueOf(auxiliar[1]) <= 0) {
				System.err.println("El valor no es válido. Ingréselo nuevamente.");
				auxiliar[1] = entrada.next();
			}

			System.out.println("\n Ingrese tiempo disponible:  ");
			auxiliar[2] = entrada.next();
			while (Integer.valueOf(auxiliar[2]) <= 0) {
				System.err.println("El valor no es válido. Ingréselo nuevamente.");
				auxiliar[2] = entrada.next();
			}

			System.out.println("\n Ingrese tipo de atraccion favorita: ");
			System.out.println(Arrays.asList(TipoDeAtraccion.values()));
			auxiliar[3] = entrada.next();
			boolean existeTipo = false;
			while (!existeTipo) {
				for (TipoDeAtraccion tipo : TipoDeAtraccion.values()) {
					if (auxiliar[3].equalsIgnoreCase(tipo.name())) {
						existeTipo = true;
						break;
					}
				}
				if (!existeTipo) {
					System.err.println("El tipo de atracción no existe. Ingréselo nuevamente.");
					auxiliar[3] = entrada.next();
				}
			}

			String nombre = auxiliar[0];
			Integer dinero = Integer.parseInt(auxiliar[1]);
			Double tiempo = Double.parseDouble(auxiliar[2]);
			TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.valueOf(auxiliar[3].toUpperCase());
			Usuario nuevoUsuario;

			nuevoUsuario = new Usuario(nombre, dinero, tiempo, tipoDeAtraccion);

			listaUsuarios.add(nuevoUsuario);
			Sistema.user = nuevoUsuario;

			salida = new PrintWriter(new FileWriter(file, true));
			salida.println("\n" + nombre + "," + dinero + "," + tiempo + "," + tipoDeAtraccion);
			salida.close();

		} catch (IOException e) {
			System.err.println("No se pudo escribir el archivo");
		} catch (NombreInvalido e1) {
			e1.printStackTrace();
		} catch (ValorInvalido e1) {
			e1.printStackTrace();
		} catch (TiempoInvalido e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e1) {
			System.err.println("El valor ingresado no es un número");
		}

	}

}