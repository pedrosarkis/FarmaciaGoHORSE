package senac.farmacia.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import senac.farmacia.database.Dao;
import senac.farmacia.model.vo.Estoque;
import senac.farmacia2.BaseDAO;

public class EstoqueDAO extends Dao implements BaseDAO<Estoque> {

	private RemedioDAO remediodao;

	public boolean inserir(Estoque estoque) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Insert into Estoque (idRemedio,quantidade) Values (?,?)");
			stmt.setInt(1, estoque.getRemedio().getIdRemedio());
			stmt.setInt(2, estoque.getQuantidade());
			stmt.executeUpdate();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean inserir2(int id, int quantidade) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Insert into Estoque (idRemedio,quantidade) Values (?,?)");
			stmt.setInt(1, id);
			stmt.setInt(2, quantidade);
			stmt.executeUpdate();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean acrescentarEstoque(int id, int quantidade) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Update Estoque set  quantidade = quantidade + ? where idRemedio = ?");
			stmt.setInt(1, quantidade);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean subtrairEstoque(int id, int quantidade) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Update Estoque set  quantidade = quantidade - ? where idRemedio = ?");
			stmt.setInt(1, quantidade);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean excluir(Estoque estoque) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Delete From Estoque where idEstoque = ? ");
			stmt.setInt(1, estoque.getIdEstoque());
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List<Estoque> listarTodos() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Select idEstoque,quantidade,idRemedio from Estoque");
			ResultSet res = stmt.executeQuery();
			List<Estoque> list = new ArrayList<>();
			while (res.next()) {
				Estoque e = new Estoque();
				e.setIdEstoque(res.getInt("idEstoque"));
				e.setQuantidade(res.getInt("quantidade"));
				e.getRemedio().setIdRemedio(res.getInt("idRemedio"));
				list.add(e);
			}
			return list;

		} catch (Exception e) {
			return null;

		}
	}

	public List<Estoque> pesquisarPorNomeTESTE(String nome) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"Select r.idRemedio,r.nomeComercial,r.laboratorio,r.precoUnitario,r.composicao, e.quantidade from Remedio r join Estoque e on r.idRemedio = e.idRemedio"
							+ "  where nomeComercial like ?");
			stmt.setString(1, nome + '%');

			ResultSet res = stmt.executeQuery();
			List<Estoque> list = new ArrayList<>();
			while (res.next()) {
				Estoque e = new Estoque();
				e.getRemedio().setIdRemedio(res.getInt("r.idRemedio"));
				e.getRemedio().setNomecomercial(res.getString("r.nomeComercial"));
				e.getRemedio().setLaboratorio(res.getString("r.laboratorio"));
				e.getRemedio().setPrecounitario(res.getDouble("r.precoUnitario"));
				e.getRemedio().setComposiçao(res.getString("r.composicao"));
				e.setQuantidade(res.getInt("e.quantidade"));

				list.add(e);

			}
			return list;
		} catch (Exception e) {
			return null;
		}

	}
	
	public List<Estoque> pesquisarPorComposicao(String nome) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"Select r.idRemedio,r.nomeComercial,r.laboratorio,r.precoUnitario,r.composicao, e.quantidade from Remedio r join Estoque e on r.idRemedio = e.idRemedio"
							+ "  where composicao like ?");
			stmt.setString(1,'%' +nome + '%');

			ResultSet res = stmt.executeQuery();
			List<Estoque> list = new ArrayList<>();
			while (res.next()) {
				Estoque e = new Estoque();
				e.getRemedio().setIdRemedio(res.getInt("r.idRemedio"));
				e.getRemedio().setNomecomercial(res.getString("r.nomeComercial"));
				e.getRemedio().setLaboratorio(res.getString("r.laboratorio"));
				e.getRemedio().setPrecounitario(res.getDouble("r.precoUnitario"));
				e.getRemedio().setComposiçao(res.getString("r.composicao"));
				e.setQuantidade(res.getInt("e.quantidade"));

				list.add(e);

			}
			return list;
		} catch (Exception e) {
			return null;
		}

	}

	public boolean existeMedicamentoEstoque(int id) {
		PreparedStatement stmt;
		boolean existe = false;
		try {
			stmt = conexao.prepareStatement("Select count(idRemedio) from Estoque where idRemedio = ? ");
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				int quantidade = res.getInt(1);
				existe = quantidade > 0;
			}
			return existe;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}
	/*
	 * Testar dps public List<Estoque> PesquisarPorNomeTESTEdokrl(String nome) { try
	 * { PreparedStatement stmt; stmt = conexao.prepareStatement(
	 * "Select r.idRemedio,r.nomeComercial,r.laboratorio,r.precoUnitario, e.quantidade from Remedio r join Estoque e on r.idRemedio = e.idRemedio"
	 * + "  where nomeComercial like ?"); stmt.setString(1, nome + '%');
	 * 
	 * ResultSet res = stmt.executeQuery(); List<Estoque> list = new ArrayList<>();
	 * while (res.next()) { Remedio remedio = new Remedio(); remedio =
	 * remediodao.obterPorId(remedio.getIdRemedio()); Estoque e = new Estoque();
	 * e.getRemedio().setIdRemedio(res.getInt("r.idRemedio"));
	 * e.getRemedio().setNomecomercial(res.getString("r.nomeComercial"));
	 * e.getRemedio().setLaboratorio(res.getString("r.laboratorio"));
	 * e.getRemedio().setPrecounitario(res.getDouble("r.precoUnitario"));
	 * e.setQuantidade(res.getInt("e.quantidade"));
	 * 
	 * 
	 * 
	 * list.add(e);
	 * 
	 * } return list; } catch (Exception e) { return null; }
	 * 
	 * }
	 */

	@Override
	public boolean alterar(Estoque t) {
		// TODO Auto-generated method stub
		return false;
	}

}
