package dao;

import java.util.Set;
import mediApp.Atraccion;
import mediApp.Itinerario;
import mediApp.Promocion;

public interface ItinerarioDAO extends GenericDAO<Itinerario>{

	public abstract Itinerario findByUser(String name);
	
	public abstract Set<Itinerario> findItinerarios();

	public abstract int insertPromo(Itinerario itinerario, Promocion nueva);

	public abstract int insertAtraccion(Itinerario itinerario, Atraccion nueva);
	
}
