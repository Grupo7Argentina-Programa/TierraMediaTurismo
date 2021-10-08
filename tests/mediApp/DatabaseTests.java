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

		System.out.println(resultados.getInt("TOTAL"));

		connection.close();
	}

	@Test
	public void userDaoNotNullTest() {
		UserDAO userDAO = DAOFactory.getUserDAO();
		assertNotNull(userDAO);
	}

	@Test
	public void conteoDeUsuarioTest() throws NombreInvalido, ValorInvalido, TiempoInvalido {
		UserDAO userDAO = DAOFactory.getUserDAO();
		assertEquals(10, userDAO.countAll());
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
	public void conteoDeAtraccionTest() throws NombreInvalido, ValorInvalido, TiempoInvalido {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		assertEquals(17, atraccionDAO.countAll());
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
		Atraccion erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.DEGUSTACION);
		userDAO.insert(sam);
		atraccionDAO.insert(erebor);
		app.cambiarUsuario("Sam");
		assertEquals(36, app.getUsuario().getDinero());
		assertEquals(8, app.getUsuario().getTiempoDisponible(), 0);
		assertEquals(32, atraccionDAO.findByName("Erebor").getCupo());
		
	}
}
