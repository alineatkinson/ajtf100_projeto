import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAOJDBC extends ComparePriceDAOJDBC implements UserDAO {

	private SQLHandler<TakingPrice> sh = new TakingPriceSQLHandler();
	private RowMapper rm = new TakingPriceRowMapper();
	Printer printer = new Printer();

	public void createTable(SQLHandler sh) {
		String sql = sh.getCreateTable();
		int qdtEdited = super.executeQuery(sql);
		printer.printMsg("Tabela TakingPrices criada com sucesso");
	}

	public Map getAll() {
		String sql = sh.getSelectAll();
		ResultSet rs = super.getResultSet(sql);

		List<User> takingprices = new ArrayList<>();

		try {
			while (rs.next()) {
				int codebar_item = rs.getInt("codebar_item");
				takingprices.add(this.get(codebar_item));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return takingprices;
	}

	public void delete(Number codebar_item) {

		String sql = sh.getDeleteSQL() + codebar_item;
		int qtdRemovidos = 0;

		qtdRemovidos = executeQuery(sql);
		System.out.println("taking_prices excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	@Override
	public void save(User object) {
		String cpf = object.getCpf();
		super.save(object, cpf, sh);
	}

	@Override
	public User get(String cpf) {
		String sql = sh.getSelectSQL() + cpf;
		return (User) super.get(cpf, rm, sql);
	}

	@Override
	public boolean checksExistence(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		
	}

}