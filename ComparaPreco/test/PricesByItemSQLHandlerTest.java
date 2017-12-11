import static org.junit.Assert.*;

import org.junit.Test;

import persistence.PricesByItemSQLHandler;
import persistence.RowMapper;
import persistence.TakingPriceRowMapper;

public class PricesByItemSQLHandlerTest {
	PricesByItemSQLHandler pbiSqlHandler = new PricesByItemSQLHandler();

	@Test
	public void getSelectSQLtest() {
		org.junit.Assert.assertEquals(
				"select tp.codebar_item, tp.code_supermarket, tp.price, tp.date, it.name, it.description from taking_prices as tp inner join items as it on it.codebar_item = tp.codebar_item where it.codebar_item = 123 order by tp.price ASC",
				pbiSqlHandler.getSelectSQL(123));
	}

	
	  public void getRowMapperTest() { 
		// Como testar a criação de objetos
		  RowMapper rowMapper = pbiSqlHandler.getRowMapper();
	//	  rowMapper.
	  }
	 

}