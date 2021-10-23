package dao;

import mediApp.Usuario;

public interface UserDAO extends GenericDAO<Usuario> {

	public abstract Usuario findByUsername(String username);
	public abstract int getID(String username);	
}