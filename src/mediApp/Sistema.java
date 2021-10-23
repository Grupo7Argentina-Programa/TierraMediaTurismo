package mediApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;
import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.PromocionDAO;
import dao.UserDAO;

public class Sistema {

	static Scanner entrada = new Scanner(System.in);
	static TreeSet<Atraccion> listaAtracciones = new TreeSet<Atraccion>();
	static TreeSet<Usuario> listaUsuarios = new TreeSet<Usuario>();
	static TreeSet<Promocion> listaPromociones = new TreeSet<Promocion>();
	static Usuario user;

	public static void main(String[] args) {

		leerAtracciones();
		leerPromociones();
		leerUsuarios();
		//leerItinerarios();

		try {
			Scanner entrada = new Scanner(System.in);
			int opcion = 1;
			while (opcion != 0) {
				while (user == null) {
					System.out.println("Bienvenido a MediApp");
					System.out.println("---------------------------");
					System.out.println("\n 1) CREAR USUARIO \n 2) INGRESAR \n 0) SALIR");
					System.out.println("---------------------------");
					opcion = entrada.nextInt();
					if (opcion == 1) {
						crearUsuarioNuevo();
					}
					if (opcion == 2) {
						System.out.print("Por favor, ingres� tu usuario \n");
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
					System.out
							.println("\n 1) CAMBIAR USUARIO \n 2) VER MIS SUGERENCIAS \n 3) VER MI ITINERARIO \n 4) CERRAR SESI�N \n 0) SALIR");
					System.out.println("---------------------------");
					opcion = entrada.nextInt();
					System.out.println("---------------------------");
					if (opcion == 1) {
						System.out.println("\n Por favor, ingres� tu usuario");
						String nuevoUsuario = entrada.next();
						System.out.println("---------------------------");
						new Sistema().cambiarUsuario(nuevoUsuario);
					}
					if (opcion == 2) {
						new Sistema().sugerirItinerario(user);
					}
					if (opcion == 3) {
						System.out.println(user.getItinerario().toString().replace("[", "").replace("]", "\n").replace(",", ""));
					}
					if (opcion == 4) {
						System.out.println("Hasta pronto, " + user.getNombre());
						user = null;
					}
					if (opcion == 0) {
						System.out.println("Hasta pronto, " + (user != null ? user.getNombre() : ""));
						break;
					}
				}
			}
			entrada.close();
			System.out.println("---------------------------");
			System.out.println("Gracias por usar MediApp");
		} catch (InputMismatchException e) {
			System.err.println("El valor ingresado no es v�lido");
		}
	}



	public void cambiarUsuario(String nuevoUsuario) {
		UserDAO userDAO = DAOFactory.getUserDAO();

		Sistema.user = userDAO.findByUsername(nuevoUsuario);
		if (Sistema.user == null) {
			System.err.println("Nombre de usuario no encontrado");
		}
	}

	private static TreeSet<Atraccion> leerAtracciones() {

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		listaAtracciones = new TreeSet<Atraccion>(atraccionDAO.findAll());

		return listaAtracciones;

	}

	private static TreeSet<Usuario> leerUsuarios() {
		UserDAO userDAO = DAOFactory.getUserDAO();
		listaUsuarios = new TreeSet<Usuario>(userDAO.findAll());

		return listaUsuarios;
		//TODO borrar c�digo comentado
		/*
		 * FileReader fr = null; BufferedReader br = null;
		 * 
		 * try { fr = new FileReader("usuarios.in", StandardCharsets.UTF_8); br = new
		 * BufferedReader(fr); String linea;
		 * 
		 * try { while ((linea = br.readLine()) != null) { String[] auxiliar =
		 * linea.split(","); String nombre = auxiliar[0]; Integer dinero =
		 * Integer.parseInt(auxiliar[1]); Double tiempoDisponible =
		 * Double.parseDouble(auxiliar[2]); TipoDeAtraccion tipo =
		 * TipoDeAtraccion.valueOf(auxiliar[3]);
		 * 
		 * Usuario usuario = new Usuario(nombre, dinero, tiempoDisponible, tipo);
		 * listaUsuarios.add(usuario); }
		 * 
		 * } catch (IOException | ValorInvalido | NombreInvalido | TiempoInvalido e) {
		 * e.toString(); }
		 * 
		 * } catch (FileNotFoundException e) {
		 * System.err.println("Archivo no encontrado"); } return listaUsuarios;
		 */
	}

	private static TreeSet<Promocion> leerPromociones() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		listaPromociones = new TreeSet<Promocion>(promocionDAO.findAll());

		return listaPromociones;
		//TODO borrar c�digo comentado
		/*
		 * FileReader fr = null; BufferedReader br = null; Promocion promocion;
		 * leerAtracciones();
		 * 
		 * try { fr = new FileReader("listaDePromociones.in", StandardCharsets.UTF_8);
		 * br = new BufferedReader(fr); String linea;
		 * 
		 * try { while ((linea = br.readLine()) != null) { String[] auxiliar =
		 * linea.split(","); String tipoDePromo = auxiliar[0]; if
		 * (tipoDePromo.matches("Porcentual")) { String nombreDePromo = auxiliar[1];
		 * Integer descuento = Integer.parseInt(auxiliar[2]); Atraccion atraccion1 =
		 * buscar(auxiliar[3]); Atraccion atraccion2 = buscar(auxiliar[4]); promocion =
		 * new Porcentual(nombreDePromo, descuento, atraccion1, atraccion2);
		 * listaPromociones.add(promocion); } if (tipoDePromo.matches("Absoluta")) {
		 * String nombreDePromo = auxiliar[1]; Integer costo =
		 * Integer.parseInt(auxiliar[2]); Atraccion atraccion1 = buscar(auxiliar[3]);
		 * Atraccion atraccion2 = buscar(auxiliar[4]); promocion = new
		 * Absoluta(nombreDePromo, costo, atraccion1, atraccion2);
		 * listaPromociones.add(promocion); } if (tipoDePromo.matches("AxB")) { String
		 * nombreDePromo = auxiliar[1]; Atraccion atraccion1 = buscar(auxiliar[2]);
		 * Atraccion atraccion2 = buscar(auxiliar[3]); Atraccion atraccion3 =
		 * buscar(auxiliar[4]); if (auxiliar.length > 5) { Atraccion atraccion4 =
		 * buscar(auxiliar[5]); promocion = new AxB(nombreDePromo, atraccion1,
		 * atraccion2, atraccion3, atraccion4); listaPromociones.add(promocion); } else
		 * { promocion = new AxB(nombreDePromo, atraccion1, atraccion2, atraccion3);
		 * listaPromociones.add(promocion); } } } } catch (IOException | NombreInvalido
		 * | TipoDeAtraccionDistinta e) { e.toString(); }
		 * 
		 * } catch (FileNotFoundException e) {
		 * System.err.println("Archivo no encontrado"); } return listaPromociones;
		 */
	}
	/*private static void leerItinerarios() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		for (Usuario usuario : listaUsuarios)
			itinerarioDAO.a�adirAItinerario(usuario, mostrable);
		
	}*/

	/*private static void escribirArchivo(Itinerario itinerario) {

		//TODO borrar c�digo no utilizado
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
			salida.println("Tiempo total requerido: " + itinerario.getTiempoTotal() + " horas");
			salida.println("Costo total: " + itinerario.getDineroDelItinerario() + " monedas");

			salida.close();
		} catch (IOException e) {
			System.err.println("No se pudo escribir el archivo");
		}
	}*/
	
	//TODO borrar c�digo comentado
	/*
	 * private static Atraccion buscar(String nombreDeAtraccion) {
	 * 
	 * try { leerAtracciones(); } catch (IOException e) {
	 * System.err.println("No se pudo leer el archivo"); } Atraccion[] auxiliar2 =
	 * new Atraccion[listaAtracciones.size()]; auxiliar2 =
	 * listaAtracciones.toArray(auxiliar2); Atraccion atraccion = null;
	 * 
	 * for (int i = 0; i < auxiliar2.length; i++) { if
	 * (auxiliar2[i].getNombre().compareToIgnoreCase(nombreDeAtraccion) == 0)
	 * atraccion = auxiliar2[i]; } return atraccion; }
	 */

	public ArrayList<Mostrable> sugerirAtraccion(Usuario usuario) throws

	IOException {

		leerAtracciones();
		ArrayList<Mostrable> sugerencias = new ArrayList<Mostrable>();

		for (Mostrable elemento : listaAtracciones.descendingSet()) {

			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == elemento.getTipoDeAtraccion();
			boolean puedePagarlo = usuario.getPresupuesto() >= elemento.getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() >= elemento.getTiempoNecesario();
			boolean estaEnItinerario = usuario.getItinerario().getAtraccionesAceptadas().contains(elemento);

			if (tipoDeAtraccionFavorita && puedePagarlo && tieneTiempo && !estaEnItinerario) {
				sugerencias.add(elemento);
			}
		}

		for (Mostrable elemento : listaAtracciones.descendingSet()) {

			boolean puedePagarlo = usuario.getPresupuesto() >= elemento.getCosto();
			boolean tieneTiempo = usuario.getTiempoDisponible() >= elemento.getTiempoNecesario();
			boolean estaEnItinerario = usuario.getItinerario().getAtraccionesAceptadas().contains(elemento);
			boolean tipoDeAtraccionFavorita = usuario.getAtraccionFavorita() == elemento.getTipoDeAtraccion();

			if (puedePagarlo && tieneTiempo && !tipoDeAtraccionFavorita && !estaEnItinerario) {
				sugerencias.add(elemento);
			}
		}

		return sugerencias;
	}

	public ArrayList<Mostrable> sugerirPromocion(Usuario usuario) throws IOException {

		TreeSet<Promocion> lista = leerPromociones();
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

	private void mostrarPreferencia(Usuario usuario) {

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
					System.out.println("COMPRASTE " + objeto.getNombre());
				}
			}
		}
	}

	private void mostrarSinPreferencia(Usuario usuario) {

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
		System.out.println(user.getNombre().toUpperCase() + ", TENEMOS ESTAS OFERTAS PARA VOS \n");
		this.mostrarPreferencia(usuario);

		if (usuario.getPresupuesto() > 0) {
			System.out.println("\n TAMBI�N TE PODR�A INTERESAR \n");
			this.mostrarSinPreferencia(usuario);
		}

		// escribirArchivo(usuario.getItinerario());
		System.out.println("--------------------");
		System.out.println("\n Se terminaron las ofertas \n");
		System.out.println("--------------------");
		System.out.println("Su itinerario es el siguiente \n");
		System.out.println(usuario.getItinerario());

	}

	private static void crearUsuarioNuevo() {
		UserDAO userDAO = DAOFactory.getUserDAO();

		String auxiliar[] = new String[4];
		try {
			System.out.println("Ingrese su nombre:");
			auxiliar[0] = entrada.next();
			while (auxiliar[0] == "") {
				System.err.println("Nombre de usuario inv�lido. Ingr�selo nuevamente.");
				auxiliar[0] = entrada.next();
			}

			while (userDAO.findByUsername(auxiliar[0]) != null) {
				System.err.println("Usuario ya existente. Ingrese otro nombre.");
				auxiliar[0] = entrada.next();
			}

			System.out.println("\n Ingrese dinero disponible para gastar:");
			auxiliar[1] = entrada.next();
			while (Integer.valueOf(auxiliar[1]) <= 0) {
				System.err.println("El valor no es v�lido. Ingr�selo nuevamente.");
				auxiliar[1] = entrada.next();
			}

			System.out.println("\n Ingrese tiempo disponible (en horas):");
			auxiliar[2] = entrada.next();
			while (Integer.valueOf(auxiliar[2]) <= 0) {
				System.err.println("El valor no es v�lido. Ingr�selo nuevamente.");
				auxiliar[2] = entrada.next();
			}

			System.out.println("\n Ingrese tipo de atraccion favorita:");
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
					System.err.println("El tipo de atracci�n no existe. Ingr�selo nuevamente.");
					auxiliar[3] = entrada.next();
				}
			}

			String nombre = auxiliar[0];
			Integer dinero = Integer.parseInt(auxiliar[1]);
			Double tiempo = Double.parseDouble(auxiliar[2]);
			TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.valueOf(auxiliar[3].toUpperCase());
			Usuario nuevoUsuario;

			nuevoUsuario = new Usuario(nombre, dinero, tiempo, tipoDeAtraccion);

			userDAO.insert(nuevoUsuario);
			Sistema.user = nuevoUsuario;

		} catch (NombreInvalido e1) {
			System.err.println("El nombre no es v�lido");
		} catch (ValorInvalido e1) {
			System.err.println("El dinero no es v�lido");
		} catch (TiempoInvalido e1) {
			System.err.println("El tiempo no es v�lido");
		} catch (NumberFormatException e1) {
			System.err.println("El valor ingresado no es un n�mero");
		}

	}

	public Usuario getUsuario() {
		return user;
	}

}