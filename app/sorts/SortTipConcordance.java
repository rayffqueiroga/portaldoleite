package sorts;

import interfaces.SortTip;
import models.Dica;

public class SortTipConcordance implements SortTip{

	@Override
	public int getGreatThan(Dica tip1, Dica tip2) {
		if (tip1.getConcordancias()>tip2.getConcordancias()) {
			return -1;
		} else if (tip1.getConcordancias()<tip2.getConcordancias()) {
			return 1;
		} else {
			return 0;
		}
	}
}
