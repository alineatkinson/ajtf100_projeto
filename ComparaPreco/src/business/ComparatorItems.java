import java.util.ArrayList;
import java.util.List;

public class ComparatorItems {

	public List<Item> getItemsAvailable(String[] names){
		List<Item> items = new ArrayList();
		list = itemDao.getAll();
		Item item = null;

		for (Item oItem : list) {
			if (oItem.getName().equals(nameItem)) {
				item = oItem;
			}
		}
		return items;
	}
	
	public List<PricesByItem> quoteItems(Item[] items){
		List<PricesByItem> list = new ArrayList();
		
		
		// fazer ordenação antes de devolver
		return list;
	}
}
