package appSinNombre;

import static org.junit.Assert.*;

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
		esperada.add(moria);
		esperada.add(laComarca);
		esperada.add(minasTirith);
		Assert.assertEquals(esperada, app.sugerirAtraccion(eowyn, listaDeAtracciones));
	


	}
}