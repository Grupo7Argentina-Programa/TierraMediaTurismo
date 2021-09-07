package appSinNombre;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unused")
public class SistemaTests {

	Usuario eowyn;
	Usuario gandalf;
	Usuario sam;
	Usuario galadriel;
	Atraccion moria;
	Atraccion minasTirith;
	Atraccion laComarca;
	Atraccion mordor;
	Atraccion abismoDeHelm;
	Atraccion lothlorien;
	Atraccion erebor;
	Atraccion bosqueNegro;
	Sistema app;

	@Before
	public void setUp() {
		eowyn = new Usuario("Eowyn", 40, 12, TipoDeAtraccion.AVENTURA);
		gandalf = new Usuario("Gandalf", 100, 5, TipoDeAtraccion.PAISAJE);
		sam = new Usuario("Sam", 36, 8, TipoDeAtraccion.DEGUSTACION);
		galadriel = new Usuario("Galadriel", 120, 1, TipoDeAtraccion.PAISAJE);

		mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		bosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA);
		lothlorien = new Atraccion("Lothlóiren", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);
		laComarca = new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		abismoDeHelm = new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		app = new Sistema();
	}

	@Test
	public void sugerenciasParaEowynTest() throws IOException {

		Atraccion listaDeAtracciones[] = { moria, minasTirith, laComarca, mordor, abismoDeHelm, lothlorien, erebor,
				bosqueNegro };

		ArrayList<Atraccion> sugerenciasParaEowyn = new ArrayList<Atraccion>();
		sugerenciasParaEowyn.add(mordor);
		sugerenciasParaEowyn.add(moria);
		sugerenciasParaEowyn.add(bosqueNegro);
		sugerenciasParaEowyn.add(lothlorien);
		sugerenciasParaEowyn.add(erebor);
		sugerenciasParaEowyn.add(minasTirith);
		sugerenciasParaEowyn.add(abismoDeHelm);
		sugerenciasParaEowyn.add(laComarca);
		Assert.assertEquals(app.sugerirAtraccion(eowyn), sugerenciasParaEowyn);

	}

	@Test
	public void sugerenciasParaGandalfTest() throws IOException {

		Atraccion listaDeAtracciones[] = { moria, minasTirith, laComarca, mordor, abismoDeHelm, lothlorien, erebor,
				bosqueNegro };

		ArrayList<Atraccion> sugerenciasParaGandalf = new ArrayList<Atraccion>();
		sugerenciasParaGandalf.add(erebor);
		sugerenciasParaGandalf.add(minasTirith);
		sugerenciasParaGandalf.add(abismoDeHelm);
		sugerenciasParaGandalf.add(lothlorien);
		sugerenciasParaGandalf.add(mordor);
		sugerenciasParaGandalf.add(moria);
		sugerenciasParaGandalf.add(bosqueNegro);

		Assert.assertEquals(sugerenciasParaGandalf, app.sugerirAtraccion(gandalf));
	}

//	@Test
//	public void DevuelveSugerenciaPorAtraccion() {
//
//		Usuario eowyn = new Usuario("Eowyn", 22, 12, TipoDeAtraccion.AVENTURA);
//		Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
//		Atraccion moria = new Atraccion("Moria", 20, 2, 6, TipoDeAtraccion.AVENTURA);
//		Atraccion minasTirith = new Atraccion("Minas Tirith", 35, 2.5, 25, TipoDeAtraccion.PAISAJE);
//		Atraccion abismoDeHelm = new Atraccion("Abismo de Helm", 70, 2, 15, TipoDeAtraccion.PAISAJE);
//		Atraccion bosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA);
//		Atraccion bosqueBlanco = new Atraccion("Bosque blanco", 3, 6, 12, TipoDeAtraccion.AVENTURA);
//		Sistema app = new Sistema();
//
//		Atraccion listaDeAtracciones[] = { moria, minasTirith, mordor, abismoDeHelm, bosqueNegro, bosqueBlanco };
//		ArrayList<Atraccion> esperada = new ArrayList<Atraccion>();
//		esperada.add(moria);
//		esperada.add(bosqueBlanco);
//		esperada.add(bosqueNegro);
//		Assert.assertEquals(esperada, app.sugerirAtraccion(eowyn));
//	}

//	@Test
//	public void DevuelveSugerenciaSinImportarPreferencia() {
//		Usuario eowyn = new Usuario("Eowyn", 22, 12, TipoDeAtraccion.AVENTURA);
//
//		Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
//		Atraccion moria = new Atraccion("Moria", 20, 2, 6, TipoDeAtraccion.AVENTURA);
//		Atraccion minasTirith = new Atraccion("Minas Tirith", 10, 2.5, 25, TipoDeAtraccion.PAISAJE);
//		Atraccion laComarca = new Atraccion("La Comarca", 10, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
//		Atraccion abismoDeHelm = new Atraccion("Abismo de Helm", 20, 22, 15, TipoDeAtraccion.PAISAJE);
//		Sistema app = new Sistema();
//
//		Atraccion listaDeAtracciones[] = { moria, minasTirith, laComarca, mordor, abismoDeHelm, };
//		ArrayList<Atraccion> esperada = new ArrayList<Atraccion>();
//		esperada.add(moria);
//		esperada.add(laComarca);
//		esperada.add(minasTirith);
//		Assert.assertEquals(esperada, app.sugerirAtraccion(eowyn));
//	}
}