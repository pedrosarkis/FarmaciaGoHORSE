package senac.farmacia.model.vo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JInternalFrame;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class GerarPlanilhaRemedio {
	
	public void gerarPlanilhaMedicamentos(List<Remedio> remedios, String caminho) {
		String[] colunasPlanilha = { "Laboratório", "NomeComercial", "Composicao", "Concentracao", "Quantidade de Comprimidos", "Preco"};
		
		HSSFWorkbook planilha = new HSSFWorkbook();
		
		HSSFSheet abaPlanilha = planilha.createSheet("Remedios");
		
		Row headerRow = abaPlanilha.createRow(0);
		
		for(int i = 0; i < colunasPlanilha.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(colunasPlanilha[i]);
		}
		int rowNum = 1;
		for (Remedio rem : remedios) {
			Row novaLinha =abaPlanilha.createRow(rowNum++);
			novaLinha.createCell(0).setCellValue(rem.getLaboratorio());
			novaLinha.createCell(1).setCellValue(rem.getNomecomercial());
			novaLinha.createCell(2).setCellValue(rem.getComposiçao());
			novaLinha.createCell(3).setCellValue(rem.getConcentraçao());
			novaLinha.createCell(4).setCellValue(rem.getQdtecomprimidos());
			novaLinha.createCell(5).setCellValue(rem.getPrecounitario());
		}
		
		for(int i = 0; i < colunasPlanilha.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}
		
		FileOutputStream fileOut = null;
		
		try {
			fileOut = new FileOutputStream(caminho + ".xls");
			planilha.write(fileOut);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if (fileOut != null) {
				try {
					fileOut.close();
					planilha.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
