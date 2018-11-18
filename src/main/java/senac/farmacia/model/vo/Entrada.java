package senac.farmacia.model.vo;

public class Entrada {
	private int idEntrada;
	private int quantidade;
	private Double Valorcomprado;
	private Remedio Remedio;

	public Entrada() {
		super();
		Remedio = new Remedio();
	}

	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorcomprado() {
		return Valorcomprado;
	}

	public void setValorcomprado(Double valorcomprado) {
		Valorcomprado = valorcomprado;
	}

	public Remedio getRemedio() {
		return Remedio;
	}

	public void setRemedio(Remedio remedio) {
		Remedio = remedio;
	}
	
}