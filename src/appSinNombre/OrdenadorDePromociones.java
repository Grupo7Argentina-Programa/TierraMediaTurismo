package appSinNombre;

import java.util.Comparator;

public class OrdenadorDePromociones implements Comparator<Promocion>{

	@Override
	public int compare(Promocion promo1, Promocion promo2) {
		int c;
	       c = promo1.getCosto().compareTo(promo2.getCosto());
	    if (c == 0)
	       c = promo1.getTiempoNecesario().compareTo(promo2.getTiempoNecesario());
	    return c;
	}
}
