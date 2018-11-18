package senac.farmacia.model.bo;

import senac.farmacia.model.dao.EntradaDAO;
import senac.farmacia.model.dao.EstoqueDAO;
import senac.farmacia.model.dao.EstoqueDAO;
import senac.farmacia.model.vo.Entrada;

public class EntradaBO {
	
	EntradaDAO entradadao = new EntradaDAO();
	EstoqueDAO estoquedao = new EstoqueDAO();
	
	
	
	
public String inserir (Entrada t) {
		String mensagemRet = "";
		
		entradadao.inserir(t);
		if (estoquedao.existeMedicamentoEstoque(t.getRemedio().getIdRemedio())) {
			if (estoquedao.acrescentarEstoque(t.getRemedio().getIdRemedio(),t.getQuantidade())) {
			
			mensagemRet = "Medicamento já existia, foi acrescido a quantidade " + t.getQuantidade();
			}
			
						
		} else {
			estoquedao.inserir2(t.getRemedio().getIdRemedio() , t.getQuantidade());
			mensagemRet = "Remedio não existia no estoque, inserido pela primeira vez";
		}
		
		return mensagemRet;
	}

}
 
	
