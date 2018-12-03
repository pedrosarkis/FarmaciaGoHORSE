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
import javax.swing.JTextField;

public class ViewRelatorioMedicamento extends JInternalFrame {

	private List<Remedio> list = new ArrayList<>();
	private JTextField txMaiorQue;
	private JTextField txMenorQue;

	RemedioDAO remedioDAO = new RemedioDAO();

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

					if (list.size() == 0) {
						list = remedioDAO.listarTodos();

					}

					new GerarPlanilhaRemedio().gerarPlanilhaMedicamentos(list, caminhoEscolhido);

				}

			}
		});
		btnGerarXML.setBounds(175, 282, 117, 29);
		getContentPane().add(btnGerarXML);

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

				if (txMaiorQue.getText().isEmpty()) {
					defaultTableModel.setNumRows(0);
					list = remedioDAO.listarTodos();

					for (Remedio rem : list) {
						defaultTableModel.addRow(
								new Object[] { rem.getLaboratorio(), rem.getNomecomercial(), rem.getComposiçao(),
										rem.getConcentraçao(), rem.getQdtecomprimidos(), rem.getPrecounitario() });

					}

				} else {
					defaultTableModel.setNumRows(0);
					int maiorque = Integer.parseInt(txMaiorQue.getText());
					int menorque = Integer.parseInt(txMenorQue.getText());

					list = remedioDAO.listarRemedioPorQuantidade(maiorque, menorque);
					for (Remedio rem : list) {
						defaultTableModel.addRow(
								new Object[] { rem.getLaboratorio(), rem.getNomecomercial(), rem.getComposiçao(),
										rem.getConcentraçao(), rem.getQdtecomprimidos(), rem.getPrecounitario() });

					}

				}

			}
		});
		btnPesquisar.setBounds(175, 13, 117, 29);
		getContentPane().add(btnPesquisar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 54, 571, 216);
		getContentPane().add(scrollPane);

		scrollPane.setViewportView(table);

		JLabel lblQuantiadeComprimidos = new JLabel("Quantidade Comprimidos maior e menor que");
		lblQuantiadeComprimidos.setBounds(587, 55, 256, 14);
		getContentPane().add(lblQuantiadeComprimidos);

		txMaiorQue = new JTextField();
		txMaiorQue.setBounds(864, 52, 43, 20);
		getContentPane().add(txMaiorQue);
		txMaiorQue.setColumns(10);

		txMenorQue = new JTextField();
		txMenorQue.setBounds(943, 52, 36, 20);
		getContentPane().add(txMenorQue);
		txMenorQue.setColumns(10);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	

}
