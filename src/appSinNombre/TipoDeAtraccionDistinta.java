package appSinNombre;

@SuppressWarnings("serial")
public class TipoDeAtraccionDistinta extends Exception {

	public TipoDeAtraccionDistinta() {
		super("El tipo de atracción de la promoción debe ser el mismo");
	}
}
