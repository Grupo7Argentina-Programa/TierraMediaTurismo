package appSinNombre;

import java.util.Comparator;

public class OrdenadorDeAtracciones implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion atraccion1, Atraccion atraccion2) {
		int c;
	       c = atraccion1.getCosto().compareTo(atraccion2.getCosto());
	    if (c == 0)
	       c = atraccion1.getTiempoNecesario().compareTo(atraccion2.getTiempoNecesario());
	    return c;
	}

}
