package senac.farmacia.model.vo;

public class Estoque {

	private int idEstoque;
	private int quantidade;
	private Remedio remedio;

	public Estoque() {
		super();
		remedio = new Remedio();
	}

	public int getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Remedio getRemedio() {
		return remedio;
	}

	public void setRemedio(Remedio remedio) {
		this.remedio = remedio;
	}

}