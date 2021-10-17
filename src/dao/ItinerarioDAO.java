package dao;

import mediApp.Itinerario;

public interface ItinerarioDAO extends GenericDAO<Itinerario>{

	public abstract Itinerario findByUser(String name);
	
}
