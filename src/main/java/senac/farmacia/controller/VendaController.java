package senac.farmacia.controller;

import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import senac.farmacia.model.bo.ClienteBO;
import senac.farmacia.model.bo.VendaBO;
import senac.farmacia.model.dao.ClienteDAO;
import senac.farmacia.model.dao.EstoqueDAO;
import senac.farmacia.model.dao.RemedioDAO;
import senac.farmacia.model.dao.VendaDAO;
import senac.farmacia.model.vo.Cliente;
import senac.farmacia.model.vo.Estoque;
import senac.farmacia.model.vo.Funcionario;
import senac.farmacia.model.vo.Remedio;
import senac.farmacia.model.vo.Venda;

public class VendaController {

	private JTextField txProduto;
	private JTextField txPrecoUnitario;
	private JTextField txQuantidadeDisponivel;
	private JTextField txQuantidade;
	private JTextField txTotal;
	private JTable table;
	private JTextField txPesquisa;
	private JTable carrinho;
	private JTextField txSubtotal;
	private JTextField txDesconto;
	private JTextField txTotalFinal;
	private JTextField txCartão;
	private JFormattedTextField txDinheiro;
	private JTextField txTroco;
	private JTextField txNomec;
	private JTextField txIdC;
	private JTextField txCpfc;
	private Remedio remedio = null;
	private RemedioDAO remedioDao;
	private Venda venda = null;
	private VendaDAO vendaDao;
	private List<Estoque> remediosEmEstoque;
	private List<Remedio> remedios;
	private EstoqueDAO estoqueDao;
	private Estoque estoqueSelecionado = null;
	private List<Estoque> carrinhoTable;
	private JTextField txFieldIDProduto;
	private ClienteBO clienteBo;
	private ClienteDAO clienteDao;
	private Cliente cliente = null;
	private VendaBO vendaBo;
	private JComboBox comboBox;
	private Funcionario funcionario = null;
	private boolean resultado;

	public VendaController(JTextField txProduto, JTextField txPrecoUnitario, JTextField txQuantidadeDisponivel,
			JTextField txQuantidade, JTextField txTotal, JTable table, JTextField txPesquisa, JTable carrinho,
			JTextField txSubtotal, JTextField txDesconto, JTextField txTotalFinal, JTextField txCartão,
			JFormattedTextField txDinheiro, JTextField txTroco, JTextField txNomec, JTextField txIdC, JTextField txCpfc,
			Remedio remedio, RemedioDAO remediodao, Venda venda, VendaDAO vendadao, List<Estoque> listremedioestoque,
			List<Remedio> listremedio, EstoqueDAO estoquedao, Estoque estoque, List<Estoque> carrinhoTable,
			JTextField txFieldIDProduto, ClienteBO clientebo, ClienteDAO clientedao, Cliente cliente, VendaBO vendabo,
			JComboBox comboBox) {
		super();

		this.txProduto = txProduto;
		this.txPrecoUnitario = txPrecoUnitario;
		this.txQuantidadeDisponivel = txQuantidadeDisponivel;
		this.txQuantidade = txQuantidade;
		this.txTotal = txTotal;
		this.table = table;
		this.txPesquisa = txPesquisa;
		this.carrinho = carrinho;
		this.txSubtotal = txSubtotal;
		this.txDesconto = txDesconto;
		this.txTotalFinal = txTotalFinal;
		this.txCartão = txCartão;
		this.txDinheiro = txDinheiro;
		this.txTroco = txTroco;
		this.txNomec = txNomec;
		this.txIdC = txIdC;
		this.txCpfc = txCpfc;
		this.remedio = remedio;
		this.remedioDao = new RemedioDAO();
		this.venda = new Venda();
		this.vendaDao = new VendaDAO();
		this.remediosEmEstoque = remediosEmEstoque;
		this.remedios = listremedio;
		this.estoqueDao = new EstoqueDAO();
		this.estoqueSelecionado = new Estoque();
		this.carrinhoTable = carrinhoTable;
		this.txFieldIDProduto = txFieldIDProduto;
		this.clienteBo = new ClienteBO();
		this.clienteDao = new ClienteDAO();
		this.cliente = new Cliente();
		this.vendaBo = new VendaBO();
		this.comboBox = comboBox;

	}

	public void preencherVenda() {
		estoqueSelecionado = pegarEstoqueSelecionado();
		txProduto.setText(estoqueSelecionado.getRemedio().getNomecomercial());
		txPrecoUnitario.setText(String.valueOf("R$ " + estoqueSelecionado.getRemedio().getPrecounitario()));
		txQuantidadeDisponivel.setText(String.valueOf(estoqueSelecionado.getQuantidade()));
		txFieldIDProduto.setText(String.valueOf(estoqueSelecionado.getRemedio().getIdRemedio()));

	}

	public Estoque pegarEstoqueSelecionado() {

		int linha = table.getSelectedRow();

		return remediosEmEstoque.get(linha);

	}

	/*
	 * public void ListarAction() { listremedio = remediodao.listarTodos();
	 * DefaultTableModel model = (DefaultTableModel) table.getModel();
	 * model.setNumRows(0); for (Remedio r : listremedio) { model.addRow( new
	 * Object[] { r.getIdRemedio(), r.getNomecomercial(), r.getLaboratorio(),
	 * r.getPrecounitario() }); } }
	 */

	public void pesquisarPornome() {
		String nome = txPesquisa.getText();
		remediosEmEstoque = estoqueDao.pesquisarPorNomeTESTE(nome);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Estoque e : remediosEmEstoque) {
			model.addRow(new Object[] { e.getRemedio().getIdRemedio(), e.getRemedio().getNomecomercial(),
					e.getRemedio().getComposiçao(), e.getRemedio().getLaboratorio(), e.getRemedio().getPrecounitario(),
					e.getQuantidade() });
		}

	}

	// Pesquisa remédio por composicao
	public void pesquisarPorComposicao() {
		String composicao = txPesquisa.getText();
		remediosEmEstoque = estoqueDao.pesquisarPorComposicao(composicao);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Estoque e : remediosEmEstoque) {
			model.addRow(new Object[] { e.getRemedio().getIdRemedio(), e.getRemedio().getNomecomercial(),
					e.getRemedio().getComposiçao(), e.getRemedio().getLaboratorio(), e.getRemedio().getPrecounitario(),
					e.getQuantidade() });
		}

	}

	// Calcula o total do preco unitario do remedio x a quantidade (antes de ir pro
	// carrinho)
	public void calcularTotal() {

		if (!txQuantidade.getText().trim().isEmpty()) {

			if (!txProduto.getText().isEmpty()) {
				if (this.estoqueSelecionado.getQuantidade() == 0) {
					JOptionPane.showMessageDialog(null, "Medicamento em falta Comunique o Gerente");

				} else if (Integer.parseInt(txQuantidade.getText()) > this.estoqueSelecionado.getQuantidade()) {
					JOptionPane.showMessageDialog(null, "Quantidade não disponível,coloque uma quantidade até "
							+ this.estoqueSelecionado.getQuantidade());
				} else {
					int quantidade = Integer.parseInt(txQuantidade.getText());
					Double valor = quantidade * this.estoqueSelecionado.getRemedio().getPrecounitario();
					txTotal.setText(String.valueOf("R$ " + valor));

				}
			}
		}
	}

	// Adiciona ITEM AO CARRINHO Feito com maestria
	// corrigir a máscara de preço
	public void preencheCarrinho() {

		boolean existe = false;

		for (int i = 0; i < carrinho.getRowCount(); i++) {

			if (txFieldIDProduto.getText().equals(carrinho.getValueAt(i, 0))) {
				existe = true;
			}
		}

		int qtdeDisponivel = Integer.parseInt(txQuantidadeDisponivel.getText());
		if (!(qtdeDisponivel <= 0)) {
			if (!existe) {
				DefaultTableModel model = (DefaultTableModel) carrinho.getModel();

				String numero = txPrecoUnitario.getText();
				numero = numero.substring(2, numero.length());
				model.addRow(new Object[] { txFieldIDProduto.getText(), txProduto.getText(), numero,
						txQuantidade.getText() });

			} else {
				for (int i = 0; i < carrinho.getRowCount(); i++) {
					if (txFieldIDProduto.getText().equals(carrinho.getValueAt(i, 0))) {

						int qtdeatual = Integer.parseInt(txQuantidade.getText());
						int qtdeExistente = Integer.parseInt((String) carrinho.getValueAt(i, 3));
						int total = qtdeatual + qtdeExistente;
						if (!((qtdeExistente + qtdeatual) > Integer.parseInt(txQuantidadeDisponivel.getText()))) {

							int qtdeNova = qtdeatual + Integer.parseInt((String) carrinho.getValueAt(i, 3));
							carrinho.setValueAt(String.valueOf(qtdeNova), i, 3);

						} else {
							JOptionPane.showMessageDialog(null, "Impossível adicionar " + qtdeatual
									+ " pois totalizará " + total + " e só temos " + txQuantidadeDisponivel.getText());

						}

					}
				}

			}
		}

		/*
		 * int qtdeDisponivel = Integer.parseInt(txQuantidadeDisponivel.getText()); if
		 * (!(qtdeDisponivel <= 0)) { DefaultTableModel model = (DefaultTableModel)
		 * carrinho.getModel();
		 * 
		 * model.addRow(new Object[] { txFieldIDProduto.getText(), txProduto.getText(),
		 * txPrecoUnitario.getText(), txQuantidade.getText() });
		 * 
		 * }
		 */

	}

	// Atualiza jtexfields abaixo do carrinho (Total,subtotal,etc.....) Verificar
	// erro depois Erro corrigido
	public void pegarItemCarrinho() {
		// DefaultTableModel model;
		// model = (DefaultTableModel) carrinho.getModel();
		Double total = 0d;

		int qtdeDisponivel = Integer.parseInt(txQuantidadeDisponivel.getText());

		if (!(qtdeDisponivel <= 0)) {
			for (int i = 0; i < carrinho.getRowCount(); i++) {

				total = total + Double.parseDouble((String) carrinho.getValueAt(i, 2))
						* Double.parseDouble((String) carrinho.getValueAt(i, 3));

			}
			txSubtotal.setText(String.valueOf("R$" + total));
		}

	}

		private final static double desconto = 10d;  // desconto aplicado em uma constante
	// calcula o desconto se houver cliente retornado na pesqusisa
	public void calculaDesconto() {

		if (carrinho.getRowCount() > 0) {

			if (!txNomec.getText().trim().isEmpty()) {

				DecimalFormat dFormat = new DecimalFormat("#,###,###.00");
				String subtotalString = txSubtotal.getText().substring(2, txSubtotal.getText().length());
				double subtotal = Double.parseDouble(subtotalString);
				double totalComDesconto = subtotal - (subtotal / desconto);
				txTotalFinal.setText(String.valueOf("R$ " + totalComDesconto));

				dFormat.format(subtotal);
				txDesconto.setText(String.valueOf("R$ " + subtotal / desconto));

			}
		}
	}

	public void limparTodaTela() {
		limparCarrinhoInteiro();
		txProduto.setText("");
		txPrecoUnitario.setText("");
		txFieldIDProduto.setText("");
		txQuantidade.setText("");
		txQuantidadeDisponivel.setText("");
		txPesquisa.setText("");
		txTotal.setText("");
		txTotalFinal.setText("");
		txSubtotal.setText("");
		txDesconto.setText("");
		txDinheiro.setText("");
		txTroco.setText("");
		txNomec.setText("");
		txIdC.setText("");
		txCpfc.setText("");
		txCartão.setText("");
		txDinheiro.setValue(null);
		
		

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

	}

	/*
	 * public void verificaCliente() { if(!txCartão.getText().trim().isEmpty()) {
	 * cliente.setCartao(Integer.parseInt(txCartão.getText()));
	 * 
	 * clientedao.existeCartao(cliente.getCartao());
	 * 
	 * 
	 * }
	 * 
	 * }
	 */
	// controlle para salvar a venda, maestro
	public void salvarAction() {

		/**
		 * Salva uma nova Venda
		 * 
		 * 
		 * 
		 */
		calculaDesconto();
		int resultdialog = JOptionPane.showConfirmDialog(null, "Deseja confirmar a Venda?", "Confirmação de Venda",
				JOptionPane.YES_NO_OPTION);
		if (resultdialog == JOptionPane.YES_OPTION) {

			if (txNomec.getText().trim().isEmpty()) {
				for (int i = 0; i < carrinho.getRowCount(); i++) {

					venda.getRemedio().setIdRemedio(Integer.parseInt((String) carrinho.getValueAt(i, 0)));
					venda.setQuantidade(Integer.parseInt((String) carrinho.getValueAt(i, 3)));

					venda.setValorVendido(Double.parseDouble((String) carrinho.getValueAt(i, 2)));

					venda.setValorVenda(Double.parseDouble((String) carrinho.getValueAt(i, 2))
							* Double.parseDouble((String) carrinho.getValueAt(i, 3)));

					Funcionario funcionario = (Funcionario) comboBox.getSelectedItem();
					venda.getFuncionario().setIdFuncionario(funcionario.getIdFuncionario());

					if (!txIdC.getText().trim().isEmpty()) {
						venda.getCliente().setIdCliente(Integer.parseInt(txIdC.getText()));

					} else {
						venda.getCliente().setIdCliente(null);

					}

					resultado = vendaBo.inserir(venda);

				}

			} else {
				for (int i = 0; i < carrinho.getRowCount(); i++) {

					double valorDesconto = (Double.parseDouble((String) carrinho.getValueAt(i, 2))
							* Double.parseDouble((String) carrinho.getValueAt(i, 3)))
							- (Double.parseDouble((String) carrinho.getValueAt(i, 2))
									* Double.parseDouble((String) carrinho.getValueAt(i, 3))) / 10;
					venda.getRemedio().setIdRemedio(Integer.parseInt((String) carrinho.getValueAt(i, 0)));
					venda.setQuantidade(Integer.parseInt((String) carrinho.getValueAt(i, 3)));
					venda.setValorVendido(Double.parseDouble((String) carrinho.getValueAt(i, 2)));

					venda.setValorVenda(valorDesconto);

					Funcionario funcionario = (Funcionario) comboBox.getSelectedItem();
					venda.getFuncionario().setIdFuncionario(funcionario.getIdFuncionario());

					if (!txIdC.getText().trim().isEmpty()) {
						venda.getCliente().setIdCliente(Integer.parseInt(txIdC.getText()));

					} else {
						venda.getCliente().setIdCliente(null);

					}

					resultado = vendaBo.inserir(venda);

				}

			}
			if (resultado) {
				JOptionPane.showMessageDialog(null, "Venda efetuada");
				limparTodaTela();

			} else {
				JOptionPane.showMessageDialog(null, "Erro ao realizar venda");
			}
		}

	}

	// Limpa o carrinho todo
	public void limparCarrinhoInteiro() {
		DefaultTableModel model = (DefaultTableModel) carrinho.getModel();
		model.setNumRows(0);
		txSubtotal.setText("");
		txDesconto.setText("");
		txTotal.setText("");
		txDinheiro.setText("");
		txTroco.setText("");
		txTotalFinal.setText("");
		txTroco.setText("");
		
	}

	// Limpa linha selecionada e subtrai o do subtotal o valor que tinha na linha
	// excluída
	public void limparLinhaSelecionada() {
		DefaultTableModel model = (DefaultTableModel) carrinho.getModel();
		if (carrinho.getSelectedRow() >= 0) {

			int linha = carrinho.getSelectedRow();

			double valor = Double.parseDouble((String) carrinho.getValueAt(linha, 2))
					* Double.parseDouble((String) carrinho.getValueAt(linha, 3));
			double subtotal = Double.parseDouble(txSubtotal.getText().substring(2, txSubtotal.getText().length()));
			subtotal = subtotal - valor;

			txSubtotal.setText(String.valueOf(subtotal));

			model.removeRow(carrinho.getSelectedRow());

			carrinho.setModel(model);
		}

	}

	public void calcularTroco() {

		String money = txDinheiro.getText().substring(0, txDinheiro.getText().length() -3).replaceAll("[,.]", "");
		String totalFinal = txTotalFinal.getText().substring(2, txTotalFinal.getText().length());
		double troco = Double.parseDouble(money) - Double.parseDouble(totalFinal);
		txTroco.setText(String.valueOf("R$ " + troco));

	}
}
