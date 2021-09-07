package appSinNombre;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class AtraccionTests {

	@Test
	public void CreacionDeAtraccionTest() {
		Atraccion moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		assertNotNull(moria);
		Atraccion minasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		assertNotNull(minasTirith);
	}
	
	@Test
	public void DevuelveSugerenciaSinImportarPreferencia() throws IOException {
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

		//Atraccion listaDeAtracciones[] = { moria, minasTirith, laComarca, mordor, abismoDeHelm,};
		ArrayList<Atraccion> esperada = new ArrayList<Atraccion>();
		esperada.add(moria);
		esperada.add(bosqueNegro);
		esperada.add(erebor);
		esperada.add(minasTirith);
		esperada.add(abismoDeHelm);
		esperada.add(laComarca);
		Assert.assertEquals(esperada, app.sugerirAtraccion(eowyn));
	


	}
}