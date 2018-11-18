package senac.farmacia.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.farmacia.model.bo.ClienteBO;
import senac.farmacia.model.bo.VendaBO;
import senac.farmacia.model.dao.ClienteDAO;
import senac.farmacia.model.dao.EstoqueDAO;
import senac.farmacia.model.dao.RemedioDAO;
import senac.farmacia.model.dao.VendaDAO;
import senac.farmacia.model.vo.Cliente;
import senac.farmacia.model.vo.Estoque;
import senac.farmacia.model.vo.Remedio;
import senac.farmacia.model.vo.Venda;

public class VendaController {

	private JTextField textField;
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
	private JTextField txDinheiro;
	private JTextField txTroco;
	private JTextField txNomec;
	private JTextField txIdadec;
	private JTextField txCpfc;
	private Remedio remedio = null;
	private RemedioDAO remediodao;
	private Venda venda = null;
	private VendaDAO vendadao;
	private List<Estoque> remediosEmEstoque;
	private List<Remedio> remedios;
	private EstoqueDAO estoquedao;
	private Estoque estoque = null;
	private List<Estoque> carrinhoTable;
	private JTextField txFieldIDProduto;
	private ClienteBO clientebo;
	private ClienteDAO clientedao;
	private Cliente cliente = null;
	private VendaBO vendabo;

	public VendaController(JTextField textField, JTextField txProduto, JTextField txPrecoUnitario,
			JTextField txQuantidadeDisponivel, JTextField txQuantidade, JTextField txTotal, JTable table,
			JTextField txPesquisa, JTable carrinho, JTextField txSubtotal, JTextField txDesconto,
			JTextField txTotalFinal, JTextField txCartão, JTextField txDinheiro, JTextField txTroco, JTextField txNomec,
			JTextField txIdadec, JTextField txCpfc, Remedio remedio, RemedioDAO remediodao, Venda venda,
			VendaDAO vendadao, List<Estoque> listremedioestoque, List<Remedio> listremedio, EstoqueDAO estoquedao,
			Estoque estoque, List<Estoque> carrinhoTable, JTextField txFieldIDProduto, ClienteBO clientebo,
			ClienteDAO clientedao, Cliente cliente, VendaBO vendabo) {
		super();
		this.textField = textField;
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
		this.txIdadec = txIdadec;
		this.txCpfc = txCpfc;
		this.remedio = remedio;
		this.remediodao = new RemedioDAO();
		this.venda = new Venda();
		this.vendadao = new VendaDAO();
		this.remediosEmEstoque = remediosEmEstoque;
		this.remedios = listremedio;
		this.estoquedao = new EstoqueDAO();
		this.estoque = new Estoque();
		this.carrinhoTable = carrinhoTable;
		this.txFieldIDProduto = txFieldIDProduto;
		this.clientebo = new ClienteBO();
		this.clientedao = new ClienteDAO();
		this.cliente = new Cliente();
		this.vendabo = new VendaBO();

	}

	public void preencherVenda() {
		estoque = pegaritemtable();
		txProduto.setText(estoque.getRemedio().getNomecomercial());
		txPrecoUnitario.setText(String.valueOf(estoque.getRemedio().getPrecounitario()));
		txQuantidadeDisponivel.setText(String.valueOf(estoque.getQuantidade()));
		txFieldIDProduto.setText(String.valueOf(estoque.getRemedio().getIdRemedio()));

	}

	public Estoque pegaritemtable() {
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
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
		remediosEmEstoque = estoquedao.PesquisarPorNomeTESTE(nome);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Estoque e : remediosEmEstoque) {
			model.addRow(new Object[] { e.getRemedio().getIdRemedio(), e.getRemedio().getNomecomercial(),
					e.getRemedio().getLaboratorio(), e.getRemedio().getPrecounitario(), e.getQuantidade() });
		}

	}

	public void calcularTotal() {

		if (!txProduto.getText().isEmpty()) {
			if (this.estoque.getQuantidade() == 0) {
				JOptionPane.showMessageDialog(null, "Medicamento em falta Comunique o Gerente");

			} else if (Integer.parseInt(txQuantidade.getText()) > this.estoque.getQuantidade()) {
				JOptionPane.showMessageDialog(null,
						"Quantidade não disponível,coloque uma quantidade até " + this.estoque.getQuantidade());
			} else {
				int quantidade = Integer.parseInt(txQuantidade.getText());
				Double valor = quantidade * this.estoque.getRemedio().getPrecounitario();
				txTotal.setText(String.valueOf(valor));

			}
		}
	}

	public void preencheCarrinho() {

		int qtdeDisponivel = Integer.parseInt(txQuantidadeDisponivel.getText());

		if (!(qtdeDisponivel <= 0)) {
			DefaultTableModel model = (DefaultTableModel) carrinho.getModel();

			model.addRow(new Object[] { txFieldIDProduto.getText(), txProduto.getText(), txPrecoUnitario.getText(),
					txQuantidade.getText() });

		}

	}

	public void pegaritemCarrinho() {
		// DefaultTableModel model;
		// model = (DefaultTableModel) carrinho.getModel();
		double total = 0d;

		int qtdeDisponivel = Integer.parseInt(txQuantidadeDisponivel.getText());

		if (!(qtdeDisponivel <= 0)) {
			for (int i = 0; i < carrinho.getRowCount(); i++) {

				total = total + Double.parseDouble((String) carrinho.getValueAt(i, 2))
						* Double.parseDouble((String) carrinho.getValueAt(i, 3));

			}
			txSubtotal.setText(String.valueOf(total));
		}

	}

	public void calculaDesconto() {

		if (!txNomec.getText().trim().isEmpty()) {

			double subtotal = Double.parseDouble(txSubtotal.getText());
			double totalComDesconto = subtotal - (subtotal * 10) / 100;
			txTotal.setText(String.valueOf(totalComDesconto));

		}
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

	public void salvarAction() {

		for (int i = 0; i < carrinho.getRowCount(); i++) {

			venda.getRemedio().setIdRemedio(Integer.parseInt((String) carrinho.getValueAt(i, 0)));
			venda.setQuantidade(Integer.parseInt((String) carrinho.getValueAt(i, 3)));

			venda.setValorVendido(Double.parseDouble((String) carrinho.getValueAt(i, 2)));

			venda.setValorVenda(Double.parseDouble((String) carrinho.getValueAt(i, 2))
					* Double.parseDouble((String) carrinho.getValueAt(i, 3)));
			String resultado = vendabo.inserir(venda);
			JOptionPane.showMessageDialog(null, resultado);

		}

	}
}
