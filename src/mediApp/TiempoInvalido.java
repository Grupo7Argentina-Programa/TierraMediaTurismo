package mediApp;

@SuppressWarnings("serial")
public class TiempoInvalido extends Exception {

	public TiempoInvalido() {
		super("Ingrese un tiempo válido");
	}
}
