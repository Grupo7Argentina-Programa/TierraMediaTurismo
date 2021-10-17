package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import mediApp.Atraccion;
import mediApp.Itinerario;
import mediApp.Promocion;
import mediApp.Usuario;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	UserDAO userDAO = DAOFactory.getUserDAO();
	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
	
	@Override
	public List<Itinerario> findAll() {
		try {
			String sql = "SELECT usuarios.nombre AS 'usuario', itinerarios.nombreDeAtraccion AS 'mostrable'\r\n"
					+ "FROM itinerarios\r\n" + "JOIN usuarios ON usuarios.id = itinerarios.id_usuario\r\n"
					+ "WHERE itinerarios.nombreDeAtraccion IS NOT NULL \r\n" + "UNION\r\n"
					+ "SELECT usuarios.nombre AS 'usuario' , promociones.nombre AS 'mostrable'\r\n"
					+ "FROM itinerarios\r\n" + "JOIN promociones ON promociones.id = itinerarios.id_promocion\r\n"
					+ "JOIN usuarios ON usuarios.id = itinerarios.id_usuario\r\n"
					+ "WHERE itinerarios.id_promocion IS NOT NULL";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Itinerario> itinerarios = new LinkedList<Itinerario>();
			while (resultados.next()) {
				itinerarios.add(toItinerario(resultados));
			}
			return itinerarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Itinerario toItinerario(ResultSet resultados) throws SQLException {
		UserDAO userDAO = DAOFactory.getUserDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Usuario usuario = userDAO.findByUsername(resultados.getString(1));
		Promocion promocion = promocionDAO.findByName(resultados.getString(2));
		Atraccion atraccion = atraccionDAO.findByName(resultados.getString(2));

		if (atraccion != null)
			usuario.getItinerario().agregarAtraccion(atraccion);
		else
			usuario.getItinerario().agregarPromocion(promocion);

		return usuario.getItinerario();
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT (1) as 'total'\r\n" + "FROM (SELECT DISTINCT(usuarios.nombre) AS 'usuario'\r\n"
					+ "FROM itinerarios\r\n" + "JOIN usuarios ON usuarios.id = itinerarios.id_usuario\r\n"
					+ "GROUP BY usuarios.nombre)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Itinerario itinerario) {
		//TODO Testear método
		try {
			String sql = "INSERT INTO ITINERARIOS (ID_USUARIO, NOMBREDEATRACCION) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();


			Usuario usuario = itinerario.getUsuario();
			int rows = 0;
			for (Atraccion atraccion : itinerario.getAtraccionesAceptadas()) {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, userDAO.getIDByUser(usuario));
				statement.setString(2, atraccion.getNombre());
				rows += statement.executeUpdate();
			}
			
			sql = "INSERT INTO ITINERARIOS (ID_USUARIO, ID_PROMOCION) VALUES (?, ?)";
			
			for (Promocion promocion : itinerario.getPromocionesAceptadas()) {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, userDAO.getIDByUser(usuario));
				statement.setInt(2, promocionDAO.getIDByPromocion(promocion));
				rows += statement.executeUpdate();
			}

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Itinerario itinerario) {
		//TODO Testear método
		try {
			String sql = "UPDATE ITINERARIOS SET NOMBREDEATRACCION = ? WHERE ID_USUARIO = ?";

			Connection conn = ConnectionProvider.getConnection();
			int rows = 0;
			Usuario usuario = itinerario.getUsuario();
			
			for (Atraccion atraccion : itinerario.getAtraccionesAceptadas()) {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(2, userDAO.getIDByUser(usuario));
				statement.setString(1, atraccion.getNombre());
				rows += statement.executeUpdate();
			}
			
			sql = "UPDATE ITINERARIOS SET ID_PROMOCION = ? WHERE ID_USUARIO = ?";
			
			for (Promocion promocion : itinerario.getPromocionesAceptadas()) {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(2, userDAO.getIDByUser(usuario));
				statement.setInt(1, promocionDAO.getIDByPromocion(promocion));
				rows += statement.executeUpdate();
			}
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Itinerario itinerario) {
		//TODO Testear método
		try {
			String sql = "DELETE FROM ITINERARIOS WHERE ID_USUARIO = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, itinerario.getUsuario().getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Itinerario findByUser(String name) {
		//TODO Testear método
		try {
			String sql = "SELECT usuarios.nombre AS 'usuario', itinerarios.nombreDeAtraccion AS 'mostrable'\r\n"
					+ "FROM itinerarios\r\n"
					+ "JOIN usuarios ON usuarios.id = itinerarios.id_usuario\r\n"
					+ "WHERE itinerarios.nombreDeAtraccion IS NOT NULL AND usuarios.nombre = \"?\"\r\n"
					+ "UNION\r\n"
					+ "SELECT usuarios.nombre AS 'usuario', promociones.nombre AS 'mostrable'\r\n"
					+ "FROM itinerarios\r\n"
					+ "JOIN promociones ON promociones.id = itinerarios.id_promocion\r\n"
					+ "JOIN usuarios ON usuarios.id = itinerarios.id_usuario\r\n"
					+ "WHERE itinerarios.id_promocion IS NOT NULL AND usuarios.nombre = ?";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultados = statement.executeQuery();

			Itinerario itinerario = null;

			if (resultados.next()) {
				itinerario = toItinerario(resultados);
			}

			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
