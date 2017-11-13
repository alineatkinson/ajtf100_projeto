import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper {

	public Object map(ResultSet rs) throws SQLException {

		String name = rs.getString("name");
		String description = rs.getString("description");
		int codebar_item = rs.getInt("codebar_item");
		Item valor = new Item(codebar_item, name, description);
		System.out.println("name: " + name + "código de barras: " + codebar_item + "Descrição :" + description);
		return valor;
	}
}
