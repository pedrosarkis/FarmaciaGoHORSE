package senac.farmacia.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import senac.farmacia.database.Dao;
import senac.farmacia.model.vo.Remedio;
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
				// stmt.setObject(6, null);
				stmt.setNull(6, java.sql.Types.INTEGER);

				// stmt.setNull(6, java.sql.Types.NULL);
				// stmt.setObject(6,t.getCliente(),java.sql.Types.INTEGER);
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

	public List<Venda> listarTodos(int maiorque,int menorque) {
		try {
			PreparedStatement stmt;
			/*
			 * stmt = conexao.prepareStatement("SELECT " + "venda.idVenda, " +
			 * "	remedio.laboratorio, " + "	remedio.nomeComercial, " +
			 * "	remedio.composicao," + "	remedio.concentracao," +
			 * "    venda.valorVenda, " + "	venda.valorVendido, " + "	venda.quantidade " +
			 * "venda.idCliente" + "FROM Venda venda inner join Estoque estoque " +
			 * "inner join Remedio remedio where venda.idRemedio =  estoque.idRemedio and estoque.idRemedio = remedio.idRemedio;"
			 * );
			 */

			stmt = conexao.prepareStatement(
					"Select v.idVenda,r.laboratorio,r.nomeComercial,r.Composicao,r.concentracao,v.valorVenda,v.ValorVendido,v.Quantidade,c.nome from venda v join remedio r on v.idRemedio = r.idRemedio left join "
					+ "cliente c on v.idCliente = c.idCliente where quantidade >? and quantidade < ? order by idVenda");
			stmt.setInt(1, maiorque);
			stmt.setInt(2, menorque);
			ResultSet res = stmt.executeQuery();
			List<Venda> list = new ArrayList<>();

			while (res.next()) {
				Venda venda = new Venda();
				venda.setIdVenda(res.getInt("idVenda"));
				venda.getRemedio().setLaboratorio(res.getString("laboratorio"));
				venda.getRemedio().setNomecomercial(res.getString("nomeComercial"));
				venda.getRemedio().setComposiçao(res.getString("composicao"));
				venda.getRemedio().setConcentraçao(res.getString("concentracao"));
				venda.setValorVenda(res.getDouble("valorVenda"));
				venda.setValorVendido(res.getDouble("valorVendido"));
				venda.setQuantidade(res.getInt("quantidade"));
				venda.getCliente().setNome(res.getString("c.nome"));

				list.add(venda);

			}
			return list;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Venda> listarTodos() {
		try {
			PreparedStatement stmt;
			/*
			 * stmt = conexao.prepareStatement("SELECT " + "venda.idVenda, " +
			 * "	remedio.laboratorio, " + "	remedio.nomeComercial, " +
			 * "	remedio.composicao," + "	remedio.concentracao," +
			 * "    venda.valorVenda, " + "	venda.valorVendido, " + "	venda.quantidade " +
			 * "venda.idCliente" + "FROM Venda venda inner join Estoque estoque " +
			 * "inner join Remedio remedio where venda.idRemedio =  estoque.idRemedio and estoque.idRemedio = remedio.idRemedio;"
			 * );
			 */

			stmt = conexao.prepareStatement(
					"Select v.idVenda,r.laboratorio,r.nomeComercial,r.Composicao,r.concentracao,v.valorVenda,v.ValorVendido,v.Quantidade,c.nome from venda v join remedio r on v.idRemedio = r.idRemedio left join "
					+ "cliente c on v.idCliente = c.idCliente order by idVenda");
			
			ResultSet res = stmt.executeQuery();
			List<Venda> list = new ArrayList<>();

			while (res.next()) {
				Venda venda = new Venda();
				venda.setIdVenda(res.getInt("idVenda"));
				venda.getRemedio().setLaboratorio(res.getString("laboratorio"));
				venda.getRemedio().setNomecomercial(res.getString("nomeComercial"));
				venda.getRemedio().setComposiçao(res.getString("composicao"));
				venda.getRemedio().setConcentraçao(res.getString("concentracao"));
				venda.setValorVenda(res.getDouble("valorVenda"));
				venda.setValorVendido(res.getDouble("valorVendido"));
				venda.setQuantidade(res.getInt("quantidade"));
				venda.getCliente().setNome(res.getString("c.nome"));

				list.add(venda);

			}
			return list;

		} catch (Exception e) {
			return null;
		}
	}
}
