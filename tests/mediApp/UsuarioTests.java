package mediApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTests {

	Usuario gero;
	Atraccion moria;
	
	@Before
	public void setUp() throws NombreInvalido, ValorInvalido, TiempoInvalido {
			gero = new Usuario("Gero", 20, 10, TipoDeAtraccion.AVENTURA);
			moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
	}
	
	@Test
	public void creacionDeUsuarioTest() {		
		assertNotNull(gero);
	}
	
	@Test (expected = Exception.class)
	public void usuarioConDineroInvalidoTest() throws NombreInvalido, ValorInvalido, TiempoInvalido {
			Usuario pepito = new Usuario("Pepito", -20, 5, TipoDeAtraccion.DEGUSTACION);	
	}
	
	@Test (expected = Exception.class)
	public void usuarioConTiempoInvalidoTest() throws NombreInvalido, ValorInvalido, TiempoInvalido {
			Usuario pepito = new Usuario("Pepito", 20, -5, TipoDeAtraccion.DEGUSTACION);	
	}
	
	@Test
	public void compraDeAtraccionTest() {
		
		assertEquals(20, gero.getPresupuesto());
		assertEquals(10, gero.getTiempoDisponible(), 0);
		
		gero.aceptarAtraccion(moria);
		
		assertEquals(10, gero.getPresupuesto());
		assertEquals(8, gero.getTiempoDisponible(), 0);
	}
	
	
}
