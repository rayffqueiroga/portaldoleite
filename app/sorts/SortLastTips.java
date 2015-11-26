package sorts;

import interfaces.SortTip;
import models.Dica;

public class SortLastTips implements SortTip{

	@Override
	public int getGreatThan(Dica tip1, Dica tip2) {
		System.out.println(tip1.createdAt().toString());
		return tip2.createdAt().compareTo(tip1.createdAt());
	}

}
