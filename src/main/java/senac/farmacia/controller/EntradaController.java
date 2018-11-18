package senac.farmacia.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.farmacia.model.bo.EntradaBO;
import senac.farmacia.model.dao.EntradaDAO;
import senac.farmacia.model.dao.RemedioDAO;
import senac.farmacia.model.vo.Entrada;
import senac.farmacia.model.vo.Remedio;

public class EntradaController {
	private JTextField txPesquisa;
	private JTable table;
	private JTextField txNomeComercial;
	private JTextField txComposicao;
	private JTextField txValorComprado;
	private JTextField txQuantidadeComprimidos;
	private JTextField txtInsiraAQuantidade;
	private JTextField txidProduto;
	private Entrada entrada = null;
	private EntradaDAO entradadao;
	private List<Remedio> remediosemestoque;
	private RemedioDAO remediodao;
	private Remedio remedio = null;
	private EntradaBO entradabo;

	public EntradaController(JTextField txPesquisa, JTable table, JTextField txNomeComercial, JTextField txComposicao,
			JTextField txValorComprado, JTextField txQuantidadeComprimidos, JTextField txtInsiraAQuantidade,
			JTextField txidProduto, Entrada entrada, EntradaDAO entradadao, List<Remedio> remediosemestoque,
			RemedioDAO remediodao,Remedio remedio,EntradaBO entradabo) {
		super();
		this.txPesquisa = txPesquisa;
		this.table = table;
		this.txNomeComercial = txNomeComercial;
		this.txComposicao = txComposicao;
		this.txValorComprado = txValorComprado;
		this.txQuantidadeComprimidos = txQuantidadeComprimidos;
		this.txtInsiraAQuantidade = txtInsiraAQuantidade;
		this.txidProduto = txidProduto;
		this.entrada = new Entrada();
		this.entradadao = new EntradaDAO();
		this.remediosemestoque = remediosemestoque;
		this.remediodao = new RemedioDAO();
		this.remedio = remedio;
		this.entradabo = new EntradaBO();
	}

	public void SalvarAction() {
		
		if (txtInsiraAQuantidade.getText().trim().isEmpty()) {
			
		} else {
		entrada.setQuantidade(Integer.parseInt(txtInsiraAQuantidade.getText()));
		entrada.setValorcomprado(Double.parseDouble(txValorComprado.getText()));
		entrada.getRemedio().setIdRemedio(Integer.parseInt(txidProduto.getText()));
		}
		
		if(txidProduto.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "É preciso selecionar um remédio antes de clicar em salvar");
				
			} else {
				String resultado = entradabo.inserir(entrada);
				JOptionPane.showMessageDialog(null, resultado);
				
			}
		}
			
		
	
	public void preencherEntrada() {
		remedio = pegaritemtable();
		txidProduto.setText(String.valueOf(remedio.getIdRemedio()));
		txNomeComercial.setText(remedio.getNomecomercial());
		txComposicao.setText(remedio.getComposiçao());
		txQuantidadeComprimidos.setText(String.valueOf(remedio.getQdtecomprimidos()));
		
	}
	public Remedio pegaritemtable() {
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();
		return remediosemestoque.get(linha);
	}

	public void pesquisarPornome() {
		String nome = txPesquisa.getText();
		remediosemestoque = remediodao.PesquisarRemedioPorNome(nome);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Remedio r : remediosemestoque) {
			model.addRow(new Object [] {r.getIdRemedio(), r.getNomecomercial(), r.getComposiçao(),r.getQdtecomprimidos()});
		}
		
	}

}
