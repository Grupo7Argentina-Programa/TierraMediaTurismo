package appSinNombre;

public interface Mostrable {
	
	String getNombre();
	Integer getCosto();
	Double getTiempoNecesario();
	TipoDeAtraccion getTipoDeAtraccion();
	boolean estaEnItinerario(Itinerario actual);
	void aceptoMostrable(Usuario comprador);
}
