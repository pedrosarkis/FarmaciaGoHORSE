package senac.farmacia.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import senac.farmacia.database.Dao;
import senac.farmacia.model.vo.Venda;
import senac.farmacia2.BaseDAO;

public class VendaDAO extends Dao implements BaseDAO<Venda> {

	@Override
	public boolean inserir(Venda t) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"Insert into Venda (valorVenda,valorVendido, quantidade,idRemedio,idFuncionario,idCliente) values (?,?,?,?,?,?)");
			stmt.setDouble(1, t.getValorVenda());
			stmt.setDouble(2, t.getValorVendido());
			stmt.setInt(3, t.getQuantidade());
			stmt.setInt(4, t.getRemedio().getIdRemedio());
			stmt.setInt(5, t.getFuncionario().getIdFuncionario());

			if (t.getCliente().getIdCliente() == null) {
				//stmt.setObject(6, null);
				 stmt.setNull(6, java.sql.Types.INTEGER);
				 
				// stmt.setNull(6, java.sql.Types.NULL);
				 //stmt.setObject(6,t.getCliente(),java.sql.Types.INTEGER);
				// stmt.setInt(6, t.getCliente().getIdCliente());
			} else {
				stmt.setInt(6, t.getCliente().getIdCliente());	

			}

			int retorno = stmt.executeUpdate();
			return (retorno > 0);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean inserirGoHorse(Double valorVenda, double ValorVendido, int quantidade, int idRemedio) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"Insert into venda (valorVenda,valorVendido, quantidade,idRemedio) values (?,?,?,?)");
			stmt.setDouble(1, valorVenda);
			stmt.setDouble(2, ValorVendido);
			stmt.setInt(3, quantidade);
			stmt.setInt(4, idRemedio);

			int retorno = stmt.executeUpdate();
			return (retorno > 0);
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean alterar(Venda t) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(
					"Update venda set valorVenda = ? , valorVendido = ? ,idCliente = ?,quantidade = ? ,idFuncionario = ?");
			stmt.setDouble(1, t.getValorVenda());
			stmt.setDouble(2, t.getValorVendido());
			stmt.setInt(3, t.getCliente().getIdCliente());
			stmt.setInt(4, t.getQuantidade());
			stmt.setInt(5, t.getFuncionario().getIdFuncionario());
			int retorno = stmt.executeUpdate();
			return (retorno > 0);
		} catch (SQLException e) {

			return false;
		}

	}

	@Override
	public boolean excluir(Venda t) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("Delete from Venda where idVenda = ?");
			stmt.setInt(1, t.getIdVenda());
			int retorno = stmt.executeUpdate();
			return (retorno > 0);

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public List<Venda> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
