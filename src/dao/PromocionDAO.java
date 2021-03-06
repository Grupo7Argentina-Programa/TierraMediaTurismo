package dao;

import mediApp.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion>{

	public abstract Promocion findByName(String name);
	
	public abstract int getIDByPromocion(Promocion promo);
	
}
