package senac.farmacia.model.vo;

public class Remedio {
	private int idRemedio;
	private String laboratorio;
	private String nomecomercial;
	private String composiçao;
	private String concentraçao;
	private int qdtecomprimidos;
	private Double precounitario;
	
	public Remedio() {
		super();
	}
	public int getIdRemedio() {
		return idRemedio;
	}
	public void setIdRemedio(int idRemedio) {
		this.idRemedio = idRemedio;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	public String getNomecomercial() {
		return nomecomercial;
	}
	public void setNomecomercial(String nomecomercial) {
		this.nomecomercial = nomecomercial;
	}
	public String getComposiçao() {
		return composiçao;
	}
	public void setComposiçao(String composiçao) {
		this.composiçao = composiçao;
	}
	public String getConcentraçao() {
		return concentraçao;
	}
	public void setConcentraçao(String concentraçao) {
		this.concentraçao = concentraçao;
	}
	public int getQdtecomprimidos() {
		return qdtecomprimidos;
	}
	public void setQdtecomprimidos(int qdtecomprimidos) {
		this.qdtecomprimidos = qdtecomprimidos;
	}
	public Double getPrecounitario() {
		return precounitario;
	}
	public void setPrecounitario(Double precounitario) {
		this.precounitario = precounitario;
	}
	
	
}