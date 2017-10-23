import java.util.Comparator;

public class ComparatorItem implements Comparator{
	
	public int compare(Object o1, Object o2) {
			Item item1 = (Item) o1;
			Item item2 = (Item) o2;
			return item1.getBarCode() - item2.getBarCode();
		}

}
