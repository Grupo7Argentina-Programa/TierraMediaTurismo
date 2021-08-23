package appSinNombre;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SistemaTests {

	@Test
	public void SugerenciasTest() {
		Usuario eowyn = new Usuario("Eowyn", 40, 12, TipoDeAtraccion.AVENTURA);
		Usuario gandalf = new Usuario("Gandalf", 100, 5, TipoDeAtraccion.PAISAJE);
		Usuario sam = new Usuario("Sam", 36, 8, TipoDeAtraccion.DEGUSTACION);
		Usuario galadriel = new Usuario("Galadriel", 120, 1, TipoDeAtraccion.PAISAJE);
		Atraccion moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		Atraccion laComarca = new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		Atraccion abismoDeHelm = new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		Atraccion lothlorien = new Atraccion("Lothlóiren", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);
		Atraccion erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion bosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA);
		Sistema app = new Sistema();

		Atraccion listaDeAtracciones[] = { moria, minasTirith, laComarca, mordor, abismoDeHelm, lothlorien, erebor,
				bosqueNegro};

		System.out.println(app.generarSugerencia(eowyn, listaDeAtracciones));
		System.out.println(app.generarSugerencia(gandalf, listaDeAtracciones));
		System.out.println(app.generarSugerencia(sam, listaDeAtracciones));
		System.out.println(app.generarSugerencia(galadriel, listaDeAtracciones));

	}
}