package senac.farmacia.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import senac.farmacia.model.dao.VendaDAO;
import senac.farmacia.model.vo.GerarPlanilhaVenda;
import senac.farmacia.model.vo.Venda;


public class ViewRelatorioVenda extends JInternalFrame{
	VendaDAO vendaDAO = new VendaDAO(); 
	private List<Venda> listarTodasVendas = new ArrayList<>();
	private JTable table;
	
	
	public ViewRelatorioVenda() throws Exception {
		setClosable(true);
		setTitle("Relatório Vendas");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 107, 610, 189);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblRelatorioVenda = new JLabel("Relatório venda");
		lblRelatorioVenda.setBounds(67, 33, 142, 16);
		getContentPane().add(lblRelatorioVenda);
		
		JButton btnGerarXml = new JButton("Gerar XML");
		btnGerarXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Salvar relatório como...");
				
				int resultado = jfc.showSaveDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
					
					if(listarTodasVendas.size() == 0) {
						listarTodasVendas = vendaDAO.listarTodos();
						
					}
					
					new GerarPlanilhaVenda().gerarPlanilhaVendas(listarTodasVendas, caminhoEscolhido);
					
					
				
				
				
				}
				
				
				
				
			}
		});
		btnGerarXml.setBounds(237, 301, 117, 29);
		getContentPane().add(btnGerarXml);
		
		
		
		final DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("id");
		defaultTableModel.addColumn("Laboratório");
		defaultTableModel.addColumn("Nome Comercial");
		defaultTableModel.addColumn("Composiçao");
		defaultTableModel.addColumn("Concentraçao");
		defaultTableModel.addColumn("valorVenda");
		defaultTableModel.addColumn("valorVendido");
		defaultTableModel.addColumn("quantidade");
		
		table = new JTable(defaultTableModel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				listarTodasVendas = vendaDAO.listarTodos();
				
				
				
				for(Venda venda : listarTodasVendas) {
					defaultTableModel.addRow(new Object[] {
							venda.getIdVenda(),
							venda.getRemedio().getLaboratorio(),
							venda.getRemedio().getNomecomercial(),
							venda.getRemedio().getComposiçao(),
							venda.getRemedio().getConcentraçao(),
							venda.getValorVenda(), 
							venda.getValorVendido(),
							venda.getQuantidade()
						
							
					});
				}
			}
		});
		
		btnPesquisar.setBounds(221, 28, 117, 29);
		getContentPane().add(btnPesquisar);
		scrollPane.setViewportView(table);
	}
	
}
