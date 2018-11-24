package senac.farmacia.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import senac.farmacia.model.bo.ClienteBO;
import senac.farmacia.model.dao.ClienteDAO;
import senac.farmacia.model.vo.Cliente;

public class ClienteController {

	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNascimento;
	private JTextField txCartaoGerado;
	private ClienteDAO clientedao;
	private Cliente cliente = null;
	private ClienteBO clientebo;
	private JTextField txCartão;
	private JTextField txCpfc;
	private List<Cliente> clientes;
	private JTextField txNomec;
	private JTextField txId;

	public ClienteController(JTextField txtNome, JTextField txtCPF, JTextField txtDataNascimento,
			JTextField txCartaoGerado, ClienteDAO clientedao, Cliente cliente, ClienteBO clientebo, JTextField txCartão,
			JTextField txCpfc, JTextField txNomec,JTextField txId) {
		super();
		this.txtNome = txtNome;
		this.txtCPF = txtCPF;
		this.txtDataNascimento = txtDataNascimento;
		this.txCartaoGerado = txCartaoGerado;
		this.clientedao = new ClienteDAO();
		this.cliente = new Cliente();
		this.clientebo = new ClienteBO();
		this.txCartão = txCartão;
		this.txCpfc = txCpfc;
		this.txNomec = txNomec;
		this.txId = txId;
	}

	public void salvarAction() {
		/* VERIFICAR ESSA PORRA DPS */

		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		cliente.setCpf(txtCPF.getText());
		cliente.setNome(txtNome.getText());

		if (!txtNome.getText().trim().isEmpty()) {

			if (!(txtCPF.getText().length() == 11)) {
				JOptionPane.showMessageDialog(null, "Cpf Inválido");
			} else {
				if (txCartaoGerado.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Gere um cartão antes de inserir o cliente");

				} else {
					cliente.setCartao(Integer.parseInt(txCartaoGerado.getText()));

					if (!txtDataNascimento.getText().trim().isEmpty()) {

						try {
							cliente.setDtnascimento(formatador.parse(txtDataNascimento.getText()));

							SimpleDateFormat formatadorsystem = new SimpleDateFormat("dd-MM-yyyy");
							LocalDate hoje = LocalDate.now();
							String datinha = String.valueOf(hoje);
							Date datona = formatadorsystem.parse(datinha);

							if (datona.before(formatador.parse(txtDataNascimento.getText()))) {
								String resultado = clientebo.inserir(cliente);
								JOptionPane.showMessageDialog(null, resultado);

							} else {
								JOptionPane.showMessageDialog(null, "Data do cliente maior do que data atual");
							}

						} catch (ParseException e) {

							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Data no formato Errado");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Preencha uma data de nascimento");

					}

				}

			}

		}

		else {
			JOptionPane.showMessageDialog(null, "O Nome não pode estar vazio");
		}

	}

	public void buscaCliente() {
		int cartao = Integer.parseInt(txCartão.getText());
		cliente = clientedao.listarPorCartao(cartao);
		txNomec.setText(cliente.getNome());
		txCpfc.setText(cliente.getCpf());
		txId.setText(String.valueOf(cliente.getIdCliente()));
		

	}

	public void buscaClientePorCpf() {
		String cpf = txCartão.getText();
		cliente = clientedao.listarPorCPF(cpf);
		txNomec.setText(cliente.getNome());
		txCpfc.setText(cliente.getCpf());
		txId.setText(String.valueOf(cliente.getIdCliente()));
		
	}

}
