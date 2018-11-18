package senac.farmacia.database;

import java.sql.Connection;

public class Dao {
	protected Connection conexao;
	
	public Dao() {
		conexao = Conexao.getConexao();

	}

}
