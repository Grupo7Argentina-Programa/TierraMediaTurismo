package appSinNombre;

import static org.junit.Assert.*;

import org.junit.Test;

public class PromocionesTest {

	@Test
	public void promocionPorcentualTest() {
		Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		Atraccion bosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA);

		Promocion packAventura = new Porcentual(20, mordor, bosqueNegro);

		assertEquals(23, packAventura.getCosto());
	}

	@Test
	public void promocionAbsolutaTest() {
		Atraccion laComarca = new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		Atraccion lothlorien = new Atraccion("Lothlóiren", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);

		Promocion packDegustacion = new Absoluta(36, laComarca, lothlorien);

		assertEquals(36, packDegustacion.getCosto());
	}

	@Test
	public void promocionAxBTest() {
		Atraccion minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		Atraccion abismoDeHelm = new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		Atraccion erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		
		Promocion packPaisajes = new AxB(minasTirith, abismoDeHelm, erebor);
		
		assertEquals(10, packPaisajes.getCosto());
	
	}
}
