package senac.farmacia.model.bo;

import javax.swing.JOptionPane;

import senac.farmacia.model.dao.RemedioDAO;
import senac.farmacia.model.vo.Remedio;

public class RemedioBO {
	RemedioDAO remediodao = new RemedioDAO();

	public String inserir(Remedio remedio) {
		String mensagemRetorno = "";
		
		if(remediodao.verificarRemedio(remedio.getNomecomercial(), remedio.getLaboratorio(), remedio.getQdtecomprimidos(),remedio.getConcentraçao())) {
			mensagemRetorno = "Remedio já cadastrado";
		} else {
			remediodao.inserir(remedio);
			mensagemRetorno = "Novo medicamento Cadastrado";
		}
		
		
		
		return mensagemRetorno;
	}
	

}
