package appSinNombre;

import static org.junit.Assert.*;

import org.junit.Test;

public class SistemaTests {

	@Test
	public void SugerenciasTest() {
		Usuario eowyn = new Usuario("Eowyn", 40, 12, TipoDeAtraccion.AVENTURA);
		Atraccion moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		//Atraccion minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		Sistema app = new Sistema();
		
		Atraccion listaDeAtracciones [] = {moria, mordor};
		
		Atraccion esperadas [] = {moria, mordor};
		
		Atraccion obtenidas [] = app.generarSugerencia(eowyn, listaDeAtracciones);
		
		assertArrayEquals(esperadas, app.generarSugerencia(eowyn, listaDeAtracciones));
		
		
		
		
	}

}
