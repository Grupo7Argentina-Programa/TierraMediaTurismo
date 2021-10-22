package mediApp;

import java.util.Objects;

import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.UserDAO;

public class Usuario implements Comparable<Usuario> {

	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion atraccionFavorita;
	private Itinerario itinerario;
	UserDAO userDAO = DAOFactory.getUserDAO();
	ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
	

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoDeAtraccion atraccionFavorita)
			throws NombreInvalido, ValorInvalido, TiempoInvalido {
		if (nombre == "")
			throw new NombreInvalido();
		if (presupuesto < 0)
			throw new ValorInvalido();
		if (tiempoDisponible < 0)
			throw new TiempoInvalido();
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionFavorita = atraccionFavorita;
		//this.itinerario = itinerarioDAO.findByUser(this.nombre);
		
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public TipoDeAtraccion getAtraccionFavorita() {
		return atraccionFavorita;
	}

	public Itinerario getItinerario() {
		return itinerarioDAO.findByUser(this.getNombre());
	}

	public void aceptarPromocion(Promocion promo) {
		if (this.presupuesto >= promo.getCosto()) {
			itinerario.agregarPromocionComprada(promo);
			this.presupuesto -= promo.getCosto();
			this.tiempoDisponible -= promo.getTiempoNecesario();
			promo.aceptoMostrable(this);
			userDAO.update(this);
		}
	}

	public void aceptarAtraccion(Atraccion atraccion) {
		if (this.presupuesto >= atraccion.getCosto()) {
			itinerario.agregarAtraccion(atraccion);
			this.presupuesto -= atraccion.getCosto();
			this.tiempoDisponible -= atraccion.getTiempoNecesario();
			atraccion.aceptoMostrable(this);
			userDAO.update(this);
		}
	}

	public int compareTo(Usuario otroUsuario) {
		return this.nombre.compareTo(otroUsuario.nombre);
	}

	@Override
	public String toString() {
		return (nombre != null ? nombre + ", " : "") + "Presupuesto: " + presupuesto + " monedas. Tiempo disponible: "
				+ tiempoDisponible + " horas.\n";
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre);
	}

	public int getDinero() {
		return presupuesto;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

}
