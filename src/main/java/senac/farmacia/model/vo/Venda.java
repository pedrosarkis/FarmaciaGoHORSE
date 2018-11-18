package senac.farmacia.model.vo;

public class Venda {

	private int idVenda;
	private Double valorVenda;
	private Double valorVendido;
	private Cliente Cliente;
	private Remedio Remedio;
	private int quantidade;
	private Funcionario Funcionario;

	public Venda() {
		super();
		Remedio = new Remedio();
		Funcionario = new Funcionario();
		Cliente = new Cliente();

	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Double getValorVendido() {
		return valorVendido;
	}

	public void setValorVendido(Double valorVendido) {
		this.valorVendido = valorVendido;
	}

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}

	public Remedio getRemedio() {
		return Remedio;
	}

	public void setRemedio(Remedio remedio) {
		Remedio = remedio;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Funcionario getFuncionario() {
		return Funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		Funcionario = funcionario;
	}

}
