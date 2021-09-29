package mediApp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mediApp.Absoluta;
import mediApp.Atraccion;
import mediApp.NombreInvalido;
import mediApp.Porcentual;
import mediApp.Promocion;
import mediApp.Sistema;
import mediApp.TiempoInvalido;
import mediApp.TipoDeAtraccion;
import mediApp.TipoDeAtraccionDistinta;
import mediApp.Usuario;
import mediApp.ValorInvalido;

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
		try {
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
		} catch (ValorInvalido e) {
			e.printStackTrace();
		} catch (NombreInvalido e) {
			e.printStackTrace();
		} catch (TiempoInvalido e) {
			e.printStackTrace();
		}

	}

	@Test

	public void probandoItinerario() {

		elias.aceptarAtraccion(bosqueBlanco);
		elias.aceptarAtraccion(bosqueNegro);

		Atraccion listaDeAtracciones[] = { moria, mordor, erebor, bosqueNegro, bosqueBlanco };

		// System.out.println(app.generarSugerencia(elias, listaDeAtracciones, 0));

	}

	@Test
	public void itinerarioParaEliasTest() {
		Usuario elias;
		try {
			elias = new Usuario("Elias", 40, 12, TipoDeAtraccion.AVENTURA);

			Promocion packAventura = new Porcentual("Pack Aventura", 20, mordor, bosqueNegro);
			ArrayList<Atraccion> esperado = new ArrayList<Atraccion>();
			esperado.add(mordor);
			esperado.add(bosqueNegro);

			elias.aceptarPromocion(packAventura);

			Assert.assertEquals(esperado, elias.getItinerario().getAtraccionesAceptadas());

		} catch (NombreInvalido e) {
			e.printStackTrace();
		} catch (ValorInvalido e) {
			e.printStackTrace();
		} catch (TiempoInvalido e) {
			e.printStackTrace();
		}
	}

	public void aceptaAtraccionesYSumaDineroAlItinerario() {

		elias.aceptarAtraccion(bosqueBlanco);
		elias.aceptarAtraccion(bosqueNegro);
		ArrayList<Atraccion> esperado = new ArrayList<Atraccion>();
		esperado.add(bosqueBlanco);
		esperado.add(bosqueNegro);

		Assert.assertEquals(esperado, elias.getItinerario().getAtraccionesAceptadas());
		assertEquals(6, elias.getItinerario().getDineroDelItinerario(), 0.01);

	}

	@Test
	public void aceptaPromociones() {
		try {
			Usuario elias = new Usuario("Elias", 40, 12, TipoDeAtraccion.AVENTURA);

			Promocion packAventura = new Porcentual("Pack Aventura", 20, mordor, bosqueNegro);
			ArrayList<Atraccion> esperado = new ArrayList<Atraccion>();
			esperado.add(mordor);
			esperado.add(bosqueNegro);

			elias.aceptarPromocion(packAventura);

			Assert.assertEquals(esperado, elias.getItinerario().getAtraccionesAceptadas());
		} catch (NombreInvalido e) {
			e.printStackTrace();
		} catch (ValorInvalido e) {
			e.printStackTrace();
		} catch (TiempoInvalido e) {
			e.printStackTrace();
		}

	}

	@Test
	public void aceptaPromocionesYSumaDineroAlItinerario() {
		try {
			Usuario eowyn = new Usuario("Eowyn", 140, 22, TipoDeAtraccion.AVENTURA);

			Promocion packDegustacion = new Absoluta("Pack Degustación", 36, laComarca, lothlorien);
			eowyn.aceptarPromocion(packDegustacion);
			assertEquals(36, eowyn.getItinerario().getDineroDelItinerario(), 0.01);
		} catch (NombreInvalido e) {
			e.printStackTrace();
		} catch (ValorInvalido e) {
			e.printStackTrace();
		} catch (TiempoInvalido e) {
			e.printStackTrace();
		} catch (TipoDeAtraccionDistinta e) {
			e.printStackTrace();
		}

	}

	@Test
	public void aceptaPromocionesYdescuentaDinero() {
		try {
			Usuario elias = new Usuario("Elias", 40, 12, TipoDeAtraccion.AVENTURA);

			Promocion packAventura = new Porcentual("Pack Aventura", 20, mordor, bosqueNegro);
			elias.aceptarPromocion(packAventura);
			assertEquals(Integer.valueOf(23), packAventura.getCosto());
			Assert.assertEquals(17, elias.getPresupuesto());
		} catch (NombreInvalido e) {
			e.printStackTrace();
		} catch (ValorInvalido e) {
			e.printStackTrace();
		} catch (TiempoInvalido e) {
			e.printStackTrace();
		}
	}
}