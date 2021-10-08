package mediApp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.UserDAO;
import mediApp.Atraccion;
import mediApp.NombreInvalido;
import mediApp.Sistema;
import mediApp.TiempoInvalido;
import mediApp.TipoDeAtraccion;
import mediApp.Usuario;
import mediApp.ValorInvalido;

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
	UserDAO userDAO;

	@Before
	public void setUp() {
		try {
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
			userDAO = DAOFactory.getUserDAO();
		} catch (NombreInvalido e) {
			e.printStackTrace();
		} catch (ValorInvalido e) {
			e.printStackTrace();
		} catch (TiempoInvalido e) {
			e.printStackTrace();
		}
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
	
	@Test
	public void cambioDeUsuarioTest() {
		
		assertNull(app.getUsuario());
		app.cambiarUsuario("Legolas");
		assertNotNull(app.getUsuario());
		assertEquals(userDAO.findByUsername("Legolas"), app.getUsuario());
	}

}