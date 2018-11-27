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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import senac.farmacia.model.dao.RemedioDAO;
import senac.farmacia.model.vo.GerarPlanilhaRemedio;
import senac.farmacia.model.vo.Remedio;

public class ViewRelatorioMedicamento extends JInternalFrame {
	
	private List<Remedio> list = new ArrayList<>();
	
	public ViewRelatorioMedicamento() {
		
		
		setClosable(true);
		getContentPane().setLayout(null);
		
		JLabel lblMedicamento = new JLabel("Medicamento:");
		lblMedicamento.setBounds(34, 18, 89, 16);
		getContentPane().add(lblMedicamento);
		
		JButton btnGerarXML = new JButton("Gerar XML");
		btnGerarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Salvar relatório como...");
				
				int resultado = jfc.showSaveDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
					
					new GerarPlanilhaRemedio().gerarPlanilhaMedicamentos(list, caminhoEscolhido);
					
					System.out.println();
//					ProdutoControler produtoControler = newProdutoControler();
//					produtoControler.gerarRelatorio(produtosConsultados, caminhoEscolhido,ProdutoControler.tipo_relatorio_XLS);
				
				
				
				
				}
				
			}
		});
		btnGerarXML.setBounds(63, 287, 117, 29);
		getContentPane().add(btnGerarXML);
		
		JButton btnPdf = new JButton("PDF");
		btnPdf.setBounds(331, 287, 117, 29);
		getContentPane().add(btnPdf);
		
		final DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Laboratório");
		defaultTableModel.addColumn("Nome Comercial");
		defaultTableModel.addColumn("Composiçao");
		defaultTableModel.addColumn("Concentraçao");
		defaultTableModel.addColumn("Quantidade de comprimidos");
		defaultTableModel.addColumn("Preço");
		table = new JTable(defaultTableModel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemedioDAO remedioDAO = new RemedioDAO();
				
				list = remedioDAO.listarTodos();
				
				for(Remedio rem : list) {
					defaultTableModel.addRow(new Object[] {
							rem.getLaboratorio(),
							rem.getNomecomercial(),
							rem.getComposiçao(),
							rem.getConcentraçao(),
							rem.getQdtecomprimidos(),
							rem.getPrecounitario()
					});
				}
				
			}
		});
		btnPesquisar.setBounds(175, 13, 117, 29);
		getContentPane().add(btnPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 54, 571, 216);
		getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		
		
	}
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
}
