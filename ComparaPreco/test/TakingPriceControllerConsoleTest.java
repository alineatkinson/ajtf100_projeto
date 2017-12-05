import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import presentation.TakingPriceControllerConsole;

public class TakingPriceControllerConsoleTest {

	@Test
	public void listTakingPriceTest() {
		TakingPriceControllerConsole tp = new TakingPriceControllerConsole();
		List<String> data = tp.getData();
		for(String item: data) {
			Assert.assertTrue(item.contains("[Código do item]"));
		}
	}
}
