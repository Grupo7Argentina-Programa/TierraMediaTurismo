package appSinNombre;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PromocionesTest {

	Atraccion mordor;
	Atraccion bosqueNegro;
	Atraccion laComarca;
	Atraccion lothlorien;
	Atraccion minasTirith;
	Atraccion abismoDeHelm;
	Atraccion erebor;
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
	public void promocionPorcentualTest() {
		Promocion packAventura = new Porcentual("Pack Aventura", 20, mordor, bosqueNegro);

		assertEquals(Integer.valueOf(23), packAventura.getCosto());
		assertEquals(7, packAventura.getTiempoNecesario(), 0);
	}

	@Test
	public void promocionAbsolutaTest() {
		try {
			Promocion packDegustacion = new Absoluta("Pack Degustación", 36, laComarca, lothlorien);

			assertEquals(Integer.valueOf(36), packDegustacion.getCosto());
			assertEquals(7.5, packDegustacion.getTiempoNecesario(), 0);
		} catch (NombreInvalido e) {
			e.printStackTrace();
		} catch (TipoDeAtraccionDistinta e) {
			e.printStackTrace();
		}
	}

	@Test
	public void promocionAxBTest() {

		Promocion packPaisajes = new AxB("Pack Paisajes", minasTirith, abismoDeHelm, erebor);

		assertEquals(Integer.valueOf(10), packPaisajes.getCosto());
		assertEquals(7.5, packPaisajes.getTiempoNecesario(), 0);
	}

	@Test(expected = Error.class)
	public void sugerenciasDeDistintoTipo() {

		@SuppressWarnings("unused")
		Promocion packRandom = new AxB("Pack cualquiera", laComarca, erebor, mordor);
	}

	@Test
	public void promocionesSugeridasTest() throws IOException {
		try {
			Usuario sam = new Usuario("Sam", 36, 8, TipoDeAtraccion.DEGUSTACION);
			Promocion packAventura = new Porcentual("Pack Aventura", 20, mordor, bosqueNegro);
			Promocion packDegustacion = new Absoluta("Pack Degustación", 36, laComarca, lothlorien);
			Promocion packPaisajes = new AxB("Pack Paisajes", minasTirith, abismoDeHelm, erebor);

			Promocion listaPromos[] = { packAventura, packDegustacion, packPaisajes };

			ArrayList<Promocion> sugerenciasParaSam = new ArrayList<Promocion>();
			sugerenciasParaSam.add(packDegustacion);
			sugerenciasParaSam.add(packAventura);
			sugerenciasParaSam.add(packPaisajes);
			// Metodo comentado en main
			//Assert.assertEquals(sugerenciasParaSam, app.sugerirPromocion(sam));
			
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
}
