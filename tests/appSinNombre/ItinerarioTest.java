package appSinNombre;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItinerarioTest {

	@Test
	public void probandoItinerario() {
		Usuario elias = new Usuario("Eowyn", 40, 12, TipoDeAtraccion.AVENTURA);
		Atraccion moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		Atraccion erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion bosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA);
		Atraccion bosqueBlanco = new Atraccion("Bosque blanco", 3, 6, 12, TipoDeAtraccion.AVENTURA);
		Sistema app = new Sistema();
		elias.setAtraccion(bosqueBlanco);
		elias.setAtraccion(bosqueNegro);


		Atraccion listaDeAtracciones[] = { moria, mordor, erebor,
				bosqueNegro, bosqueBlanco};
		
		System.out.println(app.generarSugerencia(elias, listaDeAtracciones,0));
		

}
}