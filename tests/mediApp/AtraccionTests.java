package mediApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import mediApp.Atraccion;
import mediApp.NombreInvalido;
import mediApp.Sistema;
import mediApp.TiempoInvalido;
import mediApp.TipoDeAtraccion;
import mediApp.Usuario;
import mediApp.ValorInvalido;

public class AtraccionTests {

	@Test
	public void CreacionDeAtraccionTest() {
		Atraccion moria;
		Atraccion minasTirith;
		try {
			moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
			minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
			assertNotNull(minasTirith);
			assertNotNull(moria);
		} catch (ValorInvalido e) {
			e.printStackTrace();
		} catch (NombreInvalido e) {
			e.printStackTrace();
		} catch (TiempoInvalido e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = Exception.class) 
	public void promoConCostoInvalidoTest() throws ValorInvalido, NombreInvalido, TiempoInvalido {
		Atraccion moria = new Atraccion("Moria", -10, 2, 6, TipoDeAtraccion.AVENTURA);
	}
	
	@Test (expected = Exception.class) 
	public void promoConTiempoInvalidoTest() throws ValorInvalido, NombreInvalido, TiempoInvalido {
		Atraccion moria = new Atraccion("Moria", 10, -2, 6, TipoDeAtraccion.AVENTURA);
	}
	
	@Test 
	public void disminucionDeCupoTest() throws ValorInvalido, NombreInvalido, TiempoInvalido {
		Atraccion moria = new Atraccion("Moria", 10, 2, 3, TipoDeAtraccion.AVENTURA);
		Usuario jose = new Usuario("José", 10, 20, TipoDeAtraccion.AVENTURA);
		Usuario juan = new Usuario("Juan", 10, 20, TipoDeAtraccion.AVENTURA);
		Usuario maria = new Usuario("María", 10, 20, TipoDeAtraccion.AVENTURA);

		assertEquals(3, moria.getCupo());
		jose.aceptarAtraccion(moria);
		assertEquals(2, moria.getCupo());
		juan.aceptarAtraccion(moria);
		assertEquals(1, moria.getCupo());
		maria.aceptarAtraccion(moria);
		assertEquals(0, moria.getCupo());
	}
	

	@Test
	public void DevuelveSugerenciaSinImportarPreferencia() throws IOException {
		try {
			Usuario eowyn = new Usuario("Eowyn", 22, 12, TipoDeAtraccion.AVENTURA);

			Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
			Atraccion moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
			Atraccion bosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA);
			Atraccion lothlorien = new Atraccion("Lothlóiren", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);
			Atraccion laComarca = new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
			Atraccion erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
			Atraccion minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
			Atraccion abismoDeHelm = new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
			Sistema app = new Sistema();

			Atraccion listaDeAtracciones[] = { moria, minasTirith, laComarca, mordor, abismoDeHelm, };
			ArrayList<Atraccion> esperada = new ArrayList<Atraccion>();
			esperada.add(moria);
			esperada.add(bosqueNegro);
			esperada.add(erebor);
			esperada.add(minasTirith);
			esperada.add(abismoDeHelm);
			esperada.add(laComarca);
			Assert.assertEquals(esperada, app.sugerirAtraccion(eowyn));

		} catch (NombreInvalido e) {
			e.printStackTrace();
		} catch (ValorInvalido e) {
			e.printStackTrace();
		} catch (TiempoInvalido e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void atraccionEnItinerarioTest() throws NombreInvalido, ValorInvalido, TiempoInvalido {
		Usuario eowyn = new Usuario("Eowyn", 25, 12, TipoDeAtraccion.AVENTURA);
		Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		eowyn.aceptarAtraccion(mordor);
		assertTrue(mordor.estaEnItinerario(eowyn.getItinerario()));
	}
}