package appSinNombre;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class SistemaTests {

	/*@Test
	public void SugerenciasTest() {
		Usuario pedro = new Usuario("pedro", 12, 12, TipoDeAtraccion.SINPREFERENCIA);
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
		Atraccion bosqueBlanco = new Atraccion("Bosque blanco", 3, 6, 12, TipoDeAtraccion.AVENTURA);
		Sistema app = new Sistema();

		Atraccion listaDeAtracciones[] = { moria, minasTirith, laComarca, mordor, abismoDeHelm, lothlorien, erebor,
				bosqueNegro, bosqueBlanco};

		System.out.println(app.generarSugerencia(eowyn, listaDeAtracciones));
		System.out.println(app.generarSugerencia(gandalf, listaDeAtracciones));
		System.out.println(app.generarSugerencia(sam, listaDeAtracciones));
		System.out.println(app.generarSugerencia(galadriel, listaDeAtracciones));
		System.out.println(app.generarSugerencia(pedro, listaDeAtracciones));
	

	}*/
	
	@Test
	public void DevuelveSugerenciaPorAtraccion() {
		//Usuario pedro = new Usuario("pedro", 12, 12, TipoDeAtraccion.SINPREFERENCIA);
		Usuario eowyn = new Usuario("Eowyn", 22, 12, TipoDeAtraccion.AVENTURA);
		//Usuario apurado = new Usuario("Galadriel", 60, 1, TipoDeAtraccion.PAISAJE);
		
		Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		Atraccion moria = new Atraccion("Moria", 20, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		Atraccion laComarca = new Atraccion("La Comarca", 5, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		Atraccion abismoDeHelm = new Atraccion("Abismo de Helm", 70, 2, 15, TipoDeAtraccion.PAISAJE);
		Atraccion bosqueNegro = new Atraccion("Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA);
		Atraccion bosqueBlanco = new Atraccion("Bosque blanco", 3, 6, 12, TipoDeAtraccion.AVENTURA);
		Sistema app = new Sistema();

		Atraccion listaDeAtracciones[] = { moria, minasTirith, laComarca, mordor, abismoDeHelm,
				bosqueNegro, bosqueBlanco};
		ArrayList<Atraccion> esperada = new ArrayList<Atraccion>();
		esperada.add(moria);
		esperada.add(bosqueBlanco);
		esperada.add(bosqueNegro);
		Assert.assertEquals(app.generarSugerencia(eowyn, listaDeAtracciones, 0), esperada);
	

	}
	
	@Test
	public void DevuelveSugerenciaSinImportarPreferencia() {
		Usuario eowyn = new Usuario("Eowyn", 22, 12, TipoDeAtraccion.AVENTURA);
		
		Atraccion mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		Atraccion moria = new Atraccion("Moria", 20, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion minasTirith = new Atraccion("Minas Tirith", 10, 2.5, 25, TipoDeAtraccion.PAISAJE);
		Atraccion laComarca = new Atraccion("La Comarca", 10, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		Atraccion abismoDeHelm = new Atraccion("Abismo de Helm", 20, 22, 15, TipoDeAtraccion.PAISAJE);
		Sistema app = new Sistema();

		Atraccion listaDeAtracciones[] = { moria, minasTirith, laComarca, mordor, abismoDeHelm,};
		ArrayList<Atraccion> esperada = new ArrayList<Atraccion>();
		esperada.add(laComarca);
		esperada.add(minasTirith);
		Assert.assertEquals(app.generarSugerencia(eowyn, listaDeAtracciones, 1), esperada);
	

	}
}