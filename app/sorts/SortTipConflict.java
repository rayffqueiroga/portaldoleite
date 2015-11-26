package sorts;

import interfaces.SortTip;
import models.Dica;

public class SortTipConflict implements SortTip{

	@Override
	public int getGreatThan(Dica tip1, Dica tip2) {
		if (tip1.getDiscordancias()>tip2.getDiscordancias()) {
			return -1;
		} else if (tip1.getDiscordancias()<tip2.getDiscordancias()) {
			return 1;
		} else {
			return 0;
		}
	}

}
