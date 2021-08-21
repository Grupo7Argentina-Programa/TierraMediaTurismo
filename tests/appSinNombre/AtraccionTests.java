package appSinNombre;

import static org.junit.Assert.*;

import org.junit.Test;

public class AtraccionTests {

	@Test
	public void CreacionDeAtraccionTest() {
		Atraccion moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		assertNotNull(moria);
		Atraccion minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		assertNotNull(minasTirith);
	}
}