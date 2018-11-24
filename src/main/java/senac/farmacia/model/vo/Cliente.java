package senac.farmacia.model.vo;

import java.util.Date;

public class Cliente {

	private Integer idCliente;
	private String nome;
	private String cpf;
	private Date dtnascimento;
	private int cartao;
	
	public Cliente() {
		super();
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDtnascimento() {
		return dtnascimento;
	}
	public void setDtnascimento(Date dtnascimento) {
		this.dtnascimento = dtnascimento;
	}
	public int getCartao() {
		return cartao;
	}
	public void setCartao(int cartao) {
		this.cartao = cartao;
	}
	
}
