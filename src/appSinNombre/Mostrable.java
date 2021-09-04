package appSinNombre;

public interface Mostrable {
	
	Integer getCosto();
	Double getTiempoNecesario();
	TipoDeAtraccion getTipo();
	boolean estaEnItinerario(Itinerario actual);
	void aceptoMostrable(Usuario comprador);
	
	

}
