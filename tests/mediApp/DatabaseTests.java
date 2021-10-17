package mediApp;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.PromocionDAO;
import dao.UserDAO;

public class DatabaseTests {

	@Test
	public void coneccionNoNulaTest() throws SQLException {
		String url = "jdbc:sqlite:mediApp.db";
		Connection connection = DriverManager.getConnection(url);
		assertNotNull(connection);
		connection.close();
	}

	@Test
	public void conteoDeUsuariosTest() throws SQLException {
		String url = "jdbc:sqlite:mediApp.db";
		Connection connection = DriverManager.getConnection(url);

		String sql = "SELECT COUNT (1) AS TOTAL FROM USUARIOS";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultados = statement.executeQuery();

		assertEquals(10, resultados.getInt("TOTAL"));

		connection.close();
	}

	@Test
	public void conteoDeAtraccionesTest() throws SQLException {
		String url = "jdbc:sqlite:mediApp.db";
		Connection connection = DriverManager.getConnection(url);

		String sql = "SELECT COUNT (1) AS TOTAL FROM ATRACCIONES";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultados = statement.executeQuery();

		assertEquals(17, resultados.getInt("TOTAL"));

		connection.close();
	}

	@Test
	public void conteoDePromocionesTest() throws SQLException {
		String url = "jdbc:sqlite:mediApp.db";
		Connection connection = DriverManager.getConnection(url);

		String sql = "SELECT COUNT (1) AS TOTAL FROM PROMOCIONES";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultados = statement.executeQuery();

		assertEquals(4, resultados.getInt("TOTAL"));

		connection.close();
	}

	@Test
	public void coneccionTest() throws SQLException {
		String url = "jdbc:sqlite:mediApp.db";
		Connection connection = DriverManager.getConnection(url);

		String sql = "SELECT COUNT (1) AS TOTAL FROM USUARIOS";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultados = statement.executeQuery();

		connection.close();
	}

	@Test
	public void userDaoNotNullTest() {
		UserDAO userDAO = DAOFactory.getUserDAO();
		assertNotNull(userDAO);
	}

	@Test
	public void conteoDeUsuarioTest() {
		UserDAO userDAO = DAOFactory.getUserDAO();
		assertEquals(10, userDAO.countAll());
	}
	
	@Test
	public void encontrarUsuarioTest() throws NombreInvalido, ValorInvalido, TiempoInvalido {
		UserDAO userDAO = DAOFactory.getUserDAO();
		Usuario gandalf = new Usuario("Gandalf", 100, 5, TipoDeAtraccion.PAISAJE);
		
		assertEquals(gandalf, userDAO.findByUsername("Gandalf"));
		assertEquals(null, userDAO.findByUsername("Pepito"));
	}
	
	@Test
	public void creacionYEliminacionDeUsuarioTest() throws NombreInvalido, ValorInvalido, TiempoInvalido {
		UserDAO userDAO = DAOFactory.getUserDAO();
		assertEquals(10, userDAO.countAll());
		Usuario gero = new Usuario("Gero", 10, 5, TipoDeAtraccion.DEGUSTACION);
		
		userDAO.insert(gero);
		assertEquals(11, userDAO.countAll());

		userDAO.delete(gero);
		assertEquals(10, userDAO.countAll());

	}
	
	@Test
	public void atraccionDaoNotNullTest() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		assertNotNull(atraccionDAO);
	}

	@Test
	public void conteoDeAtraccionTest() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		assertEquals(17, atraccionDAO.countAll());
	}
	
	@Test
	public void encontrarAtraccionTest() throws ValorInvalido, NombreInvalido, TiempoInvalido {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion mordor = new Atraccion("Mordor", 25, 3, 2, TipoDeAtraccion.AVENTURA);
		
		assertEquals(mordor, atraccionDAO.findByName("Mordor"));
		assertEquals(null, atraccionDAO.findByName("Algo"));
	}
	
	@Test
	public void creacionYEliminacionDeAtraccionTest() throws NombreInvalido, ValorInvalido, TiempoInvalido {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		assertEquals(17, atraccionDAO.countAll());
		Atraccion starkhorn = new Atraccion("Starkhorn", 10, 5, 13, TipoDeAtraccion.PAISAJE);
		assertNotNull(starkhorn);
		
		atraccionDAO.insert(starkhorn);
		assertEquals(18, atraccionDAO.countAll());

		atraccionDAO.delete(starkhorn);
		assertEquals(17, atraccionDAO.countAll());
	}
	
	@Test
	public void compraDeAtraccionTest() throws NombreInvalido, ValorInvalido, TiempoInvalido {
		UserDAO userDAO = DAOFactory.getUserDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Sistema app = new Sistema();
		
		//Testeamos el update, insert y delete
		app.cambiarUsuario("Sam");
		assertEquals(36, app.getUsuario().getDinero());
		assertEquals(8, app.getUsuario().getTiempoDisponible(), 0);
		assertEquals(32, atraccionDAO.findByName("Erebor").getCupo());
		
		app.getUsuario().aceptarAtraccion(atraccionDAO.findByName("Erebor")); //Se compra una atracción
		assertEquals(24, app.getUsuario().getDinero()); //Verificamos que el usuario tenga menos dinero
		assertEquals(5, app.getUsuario().getTiempoDisponible(), 0); //Verificamos que el usuario tenga menos tiempo
		assertEquals(31, atraccionDAO.findByName("Erebor").getCupo()); //Verificamos que el cupo se haya reducido en 1
		
		userDAO.delete(app.getUsuario()); //Se deja la base de datos como estaba al principio
		atraccionDAO.delete(atraccionDAO.findByName("Erebor"));
		Usuario sam = new Usuario("Sam", 36, 8, TipoDeAtraccion.DEGUSTACION);
		Atraccion erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		userDAO.insert(sam);
		atraccionDAO.insert(erebor);
		app.cambiarUsuario("Sam");
		assertEquals(36, app.getUsuario().getDinero());
		assertEquals(8, app.getUsuario().getTiempoDisponible(), 0);
		assertEquals(32, atraccionDAO.findByName("Erebor").getCupo());
	}
	
	@Test
	public void promocionDaoNotNullTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		assertNotNull(promocionDAO);
	}
	
	@Test
	public void conteoDePromocionTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		assertEquals(4, promocionDAO.countAll());
	}
	
	@Test
	public void encontrarPromocionTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		
		Atraccion atraccion1 = atraccionDAO.findByName("Mordor");
		Atraccion atraccion2 = atraccionDAO.findByName("Bosque Negro");
		Promocion packAventura = new Porcentual("Pack Aventura", 20, atraccion1, atraccion2);
		
		assertNotNull(packAventura);
		assertNotNull(atraccion1);
		assertNotNull(atraccion2);
		
		assertEquals(packAventura, promocionDAO.findByName("Pack Aventura"));
		assertEquals(null, promocionDAO.findByName("Algo"));

	}
	
	@Test
	public void mismoCostoDePromoTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion packAventura = promocionDAO.findByName("Pack Aventura");
		Promocion packDegustacion = promocionDAO.findByName("Pack Degustación");
		Promocion packPaisajes = promocionDAO.findByName("Pack Paisajes");
		Promocion packPosadas = promocionDAO.findByName("Pack Posadas");
		assertNotNull(packAventura);
		assertNotNull(packDegustacion);
		assertNotNull(packPaisajes);
		assertNotNull(packPosadas);
		
		assertEquals(Integer.valueOf(23), packAventura.getCosto());
		assertEquals(Integer.valueOf(36), packDegustacion.getCosto());
		assertEquals(Integer.valueOf(10), packPaisajes.getCosto());
		assertEquals(Integer.valueOf(120), packPosadas.getCosto());

	}
	
	@Test
	public void mismaDuracionDePromoTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion packAventura = promocionDAO.findByName("Pack Aventura");
		Promocion packDegustacion = promocionDAO.findByName("Pack Degustación");
		Promocion packPaisajes = promocionDAO.findByName("Pack Paisajes");
		Promocion packPosadas = promocionDAO.findByName("Pack Posadas");
		assertNotNull(packAventura);
		assertNotNull(packDegustacion);
		assertNotNull(packPaisajes);
		assertNotNull(packPosadas);
		
		assertEquals(Double.valueOf(7), packAventura.getTiempoNecesario());
		assertEquals(Double.valueOf(7.5), packDegustacion.getTiempoNecesario());
		assertEquals(Double.valueOf(17.5), packPaisajes.getTiempoNecesario());
		assertEquals(Double.valueOf(19), packPosadas.getTiempoNecesario());

	}
	
	@Test
	public void creacionYEliminacionDeAxBDe4AtraccionesTest() throws TipoDeAtraccionDistinta, NombreInvalido {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion1 = atraccionDAO.findByName("El Paso de Morgul");
		Atraccion atraccion2 = atraccionDAO.findByName("Ettenmoors");
		Atraccion atraccion3 = atraccionDAO.findByName("Dunland");
		Atraccion atraccion4 = atraccionDAO.findByName("Bosque Negro");
		assertNotNull(atraccion1);
		assertNotNull(atraccion2);
		assertNotNull(atraccion3);
		assertNotNull(atraccion4);
		
		assertEquals(4, promocionDAO.countAll());
		
		Promocion packAventuraSuper = new AxB("Pack Aventura Super", atraccion1, atraccion2, atraccion3, atraccion4);
		assertNotNull(packAventuraSuper);
		
		promocionDAO.insert(packAventuraSuper);
		assertEquals(5, promocionDAO.countAll());

		promocionDAO.delete(packAventuraSuper);
		assertEquals(4, promocionDAO.countAll());
	}
	
	@Test
	public void creacionYEliminacionDeAxBDe3AtraccionesTest() throws TipoDeAtraccionDistinta, NombreInvalido {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion1 = atraccionDAO.findByName("El Paso de Morgul");
		Atraccion atraccion2 = atraccionDAO.findByName("Ettenmoors");
		Atraccion atraccion3 = atraccionDAO.findByName("Dunland");

		assertNotNull(atraccion1);
		assertNotNull(atraccion2);
		assertNotNull(atraccion3);
		
		assertEquals(4, promocionDAO.countAll());
		
		Promocion packAventuraPiola = new AxB("Pack Aventura Piola", atraccion1, atraccion2, atraccion3);
		assertNotNull(packAventuraPiola);
		
		promocionDAO.insert(packAventuraPiola);
		assertEquals(5, promocionDAO.countAll());

		promocionDAO.delete(packAventuraPiola);
		assertEquals(4, promocionDAO.countAll());
	}
	
	@Test
	public void creacionYEliminacionDeAbsolutaTest() throws TipoDeAtraccionDistinta, NombreInvalido {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion1 = atraccionDAO.findByName("El Paso de Morgul");
		Atraccion atraccion2 = atraccionDAO.findByName("Ettenmoors");

		assertNotNull(atraccion1);
		assertNotNull(atraccion2);

		assertEquals(4, promocionDAO.countAll());
		
		Promocion packAventuraMasOMenos = new Absoluta("Pack Aventura Mas o Menos", 28, atraccion1, atraccion2);
		assertNotNull(packAventuraMasOMenos);
		
		promocionDAO.insert(packAventuraMasOMenos);
		assertEquals(5, promocionDAO.countAll());

		promocionDAO.delete(packAventuraMasOMenos);
		assertEquals(4, promocionDAO.countAll());
	}
	
	@Test
	public void creacionYEliminacionDePorcentualTest() throws TipoDeAtraccionDistinta, NombreInvalido {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion1 = atraccionDAO.findByName("El Paso de Morgul");
		Atraccion atraccion2 = atraccionDAO.findByName("Ettenmoors");

		assertNotNull(atraccion1);
		assertNotNull(atraccion2);

		assertEquals(4, promocionDAO.countAll());
		
		Promocion packAventuraLoQueHay = new Porcentual("Pack Aventura Es Lo Que Hay", 20, atraccion1, atraccion2);
		assertNotNull(packAventuraLoQueHay);
		
		promocionDAO.insert(packAventuraLoQueHay);
		assertEquals(5, promocionDAO.countAll());

		promocionDAO.delete(packAventuraLoQueHay);
		assertEquals(4, promocionDAO.countAll());
	}
	
	@Test
	public void itinerarioDaoNotNullTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		assertNotNull(itinerarioDAO);
	}
	
	@Test
	public void conteoDeItinerarioTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		assertEquals(4, itinerarioDAO.countAll());
	}
	
	@Test
	public void encontrarItinerariosTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		UserDAO userDAO = DAOFactory.getUserDAO();
		
		
		//System.out.println(itinerarioDAO.findAll().toString().replace("[", "").replace("]", "\n").replace(",", ""));
		itinerarioDAO.findAll();
		userDAO.findByUsername("Gandalf").getItinerario();
	}
}
