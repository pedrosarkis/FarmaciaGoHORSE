package senac.farmacia.model.bo;

import senac.farmacia.model.dao.EstoqueDAO;
import senac.farmacia.model.dao.VendaDAO;
import senac.farmacia.model.vo.Venda;

public class VendaBO {
	VendaDAO vendadao = new VendaDAO();
	EstoqueDAO estoquedao = new EstoqueDAO();

	public boolean inserir(Venda t) {
		boolean vendido = false;
		if (vendadao.inserir(t)) {

			vendido = true;
			if (!estoquedao.subtrairEstoque(t.getRemedio().getIdRemedio(), t.getQuantidade())) {
				vendido = false;
			} else {
				vendido = true;
			}

		} else {
			vendido = false;
		}

		return vendido;
	}

}
