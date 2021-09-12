package appSinNombre;

@SuppressWarnings("serial")
public class ValorInvalido extends Exception {
	
	public ValorInvalido(){
		super("Ingrese un valor válido");
	}

}
