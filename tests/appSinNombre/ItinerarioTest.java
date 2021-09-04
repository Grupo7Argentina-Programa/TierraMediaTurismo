package appSinNombre;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class ItinerarioTest {
	
	Atraccion mordor;
	Atraccion bosqueNegro;
	Atraccion laComarca;
	Atraccion lothlorien;
	Atraccion minasTirith;
	Atraccion abismoDeHelm;
	Atraccion erebor;
	Atraccion moria;
	Atraccion bosqueBlanco;
	Usuario elias;
	Sistema app;

	@Before
	public void setUp() {
		mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		bosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA);
		laComarca = new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		lothlorien = new Atraccion("Lothlóiren", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);
		minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		abismoDeHelm = new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		bosqueBlanco = new Atraccion("Bosque blanco", 3, 6, 12, TipoDeAtraccion.AVENTURA);
		
		elias = new Usuario("Eowyn", 40, 12, TipoDeAtraccion.AVENTURA);
		
		app = new Sistema();

	}

	@Test
	public void aceptaAtracciones() {
		

		elias.aceptarAtraccion(bosqueBlanco);
		elias.aceptarAtraccion(bosqueNegro);
		ArrayList<Atraccion> esperado = new ArrayList<Atraccion>();
		esperado.add(bosqueBlanco);
		esperado.add(bosqueNegro);
		
		Assert.assertEquals(esperado, elias.getItinerario().getAtraccionesAceptadas());

	}
	
	@Test
	public void aceptaPromociones() {
		Usuario elias = new Usuario("Eowyn", 40, 12, TipoDeAtraccion.AVENTURA);
		
		Promocion packAventura = new Porcentual("Pack Aventura", 20, mordor, bosqueNegro);
		ArrayList<Atraccion> esperado = new ArrayList<Atraccion>();
		esperado.add(mordor);
		esperado.add(bosqueNegro);
		
		elias.aceptarPromocion(packAventura);
		
		Assert.assertEquals(esperado, elias.getItinerario().getAtraccionesAceptadas());
		
	}
	
	@Test
	public void aceptaPromocionesYdescuentaDinero() {
		Usuario elias = new Usuario("Eowyn", 40, 12, TipoDeAtraccion.AVENTURA);
		
		Promocion packAventura = new Porcentual("Pack Aventura", 20, mordor, bosqueNegro);
		elias.aceptarPromocion(packAventura);
		assertEquals(Integer.valueOf(23), packAventura.getCosto());
		Assert.assertEquals(17, elias.getPresupuesto());
		
	}
}