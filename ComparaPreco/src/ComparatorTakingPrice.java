import java.util.Comparator;

public class ComparatorTakingPrice implements Comparator<TakingPrice>{//Comparable<TakingPrice> {

	public int compare(TakingPrice tp1, TakingPrice tp2) {

		if (tp1.getPrice() < tp2.getPrice()) {
			return -1;
		}

		if (tp1.getPrice() > tp2.getPrice()) {
			return 1;
		}

		return 0;
	}
}


