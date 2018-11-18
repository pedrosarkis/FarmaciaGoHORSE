package senac.farmacia.model.dao;

import java.sql.PreparedStatement;
import java.util.List;

import senac.farmacia.database.Dao;
import senac.farmacia.model.vo.Entrada;
import senac.farmacia2.BaseDAO;

public class EntradaDAO extends Dao implements BaseDAO <Entrada> {

	
	public boolean inserir(Entrada entrada) {
		try {
			PreparedStatement stmt; 
			stmt = conexao.prepareStatement("Insert into Entrada (quantidade,valorComprado,idRemedio) values (?,?,?)");
			stmt.setInt(1, entrada.getQuantidade());
			stmt.setDouble(2, entrada.getValorcomprado());
			stmt.setInt(3, entrada.getRemedio().getIdRemedio());
			stmt.executeUpdate();
			return true;
			
			
		} catch (Exception e) {
			return false;
		}
		
	}

	
	public boolean alterar(Entrada entrada) {
		try {
			PreparedStatement stmt; 
			stmt = conexao.prepareStatement("Update Entrada set idRemedio = ? , quantidade = ? , valorComprado =?");
			stmt.setInt(1, entrada.getQuantidade());
			stmt.setDouble(2, entrada.getValorcomprado());
			stmt.setInt(3, entrada.getRemedio().getIdRemedio());
			stmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}

	
	public boolean excluir(Entrada entrada) {
		try {
			PreparedStatement stmt; 
			stmt = conexao.prepareStatement("Delete from Entrada where id = ?");
			stmt.setInt(1, entrada.getIdEntrada());
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	
	public List<Entrada> listarTodos() {
		
		return null;
	}
	
	

}
