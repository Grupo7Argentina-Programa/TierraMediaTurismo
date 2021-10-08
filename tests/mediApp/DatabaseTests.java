package mediApp;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

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
	public void creacionYEliminacionDeUsuarioTest() throws NombreInvalido, ValorInvalido, TiempoInvalido {
		UserDAO userDAO = DAOFactory.getUserDAO();
		assertEquals(10, userDAO.countAll());
		Usuario gero = new Usuario("Gero", 10, 5, TipoDeAtraccion.DEGUSTACION);
		
		userDAO.insert(gero);
		assertEquals(11, userDAO.countAll());

		userDAO.delete(gero);
		assertEquals(10, userDAO.countAll());

	}

}