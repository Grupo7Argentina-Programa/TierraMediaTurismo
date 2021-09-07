package appSinNombre;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SistemaConInteraccionTest {

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
		lothlorien = new Atraccion("Lothlóiren", 15, 1, 30, TipoDeAtraccion.DEGUSTACION);
		minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		abismoDeHelm = new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		bosqueBlanco = new Atraccion("Bosque blanco", 3, 6, 12, TipoDeAtraccion.AVENTURA);

		elias = new Usuario("Eowyn", 40, 12, TipoDeAtraccion.AVENTURA);

	}

	/*
	 * @Test public void aceptaAtraccionesBosqueBlancoYNegro() {
	 * 
	 * 
	 * Atraccion listaDeAtracciones[] = { moria, minasTirith, mordor,
	 * abismoDeHelm,lothlorien, bosqueNegro, bosqueBlanco};
	 * app.sugerirAtraccion(elias, listaDeAtracciones); ArrayList<Atraccion>
	 * esperado = new ArrayList<Atraccion>(); esperado.add(bosqueBlanco);
	 * esperado.add(bosqueNegro);
	 * 
	 * Assert.assertEquals(esperado,
	 * elias.getItinerario().getAtraccionesAceptadas()); }
	 */

	/*
	 * @Test public void AceptaPromocion() { Usuario sam = new Usuario("Sam", 136,
	 * 20, TipoDeAtraccion.DEGUSTACION); Promocion packAventura = new
	 * Porcentual("Pack Aventura", 20, mordor, bosqueNegro); Promocion
	 * packDegustacion = new Absoluta("Pack Degustación", 36, laComarca,
	 * lothlorien); Promocion packPaisajes = new AxB("Pack Paisajes", minasTirith,
	 * abismoDeHelm, erebor); Promocion listaPromos[] = { packAventura,
	 * packDegustacion, packPaisajes };
	 * 
	 * 
	 * ArrayList<Promocion> sugerenciasParaSam = new ArrayList<Promocion>();
	 * sugerenciasParaSam.add(packDegustacion); app.sugerirPromocion(sam,
	 * listaPromos); Assert.assertEquals(sugerenciasParaSam,
	 * sam.getItinerario().getPromocionesAceptadas()); Assert.assertEquals(100,
	 * sam.getPresupuesto()); }
	 */
	@Test
	public void AceptaPromocionPackAventuraYatraccionLaComarca() {
		Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		Atraccion bosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA);
		Atraccion laComarca = new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		Atraccion lothlorien = new Atraccion("Lothlóiren", 15, 1, 30, TipoDeAtraccion.DEGUSTACION);
		Atraccion minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		Atraccion abismoDeHelm = new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		Atraccion erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion bosqueBlanco = new Atraccion("Bosque blanco", 3, 6, 12, TipoDeAtraccion.AVENTURA);

		Usuario sam = new Usuario("Sam", 136, 20, TipoDeAtraccion.AVENTURA);

		Promocion packAventura = new Porcentual("Pack Aventura", 20, mordor, bosqueNegro);
		Promocion packDegustacion = new Absoluta("Pack Degustación", 36, laComarca, lothlorien);
		Promocion packPaisajes = new AxB("Pack Paisajes", minasTirith, abismoDeHelm, erebor);
		Promocion listaPromos[] = { packAventura, packDegustacion, packPaisajes };
		Atraccion listaDeAtracciones[] = { moria, minasTirith, mordor, abismoDeHelm, lothlorien, bosqueNegro,
				bosqueBlanco, laComarca };

		ArrayList<Promocion> sugerenciasParaSam = new ArrayList<Promocion>();
		sugerenciasParaSam.add(packAventura);
		Sistema app = new Sistema();
		ArrayList<Atraccion> esperado = new ArrayList<Atraccion>();
		esperado.add(mordor);
		esperado.add(bosqueNegro);
		esperado.add(laComarca);
		app.sugerirItinerario(sam, listaDeAtracciones, listaPromos);
		Assert.assertEquals(sugerenciasParaSam, sam.getItinerario().getPromocionesAceptadas());
		Assert.assertEquals(esperado, sam.getItinerario().getAtraccionesAceptadas());
	}
}
