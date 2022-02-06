package SR.venueSR;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JComboBox;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class nonAlchogolDrinks extends JComboBox<String> {
	private static final long serialVersionUID = 1L;	
	
	public static  File sourceFile;
	public static  FileInputStream fileInputStream;
	public static Workbook workBook;
	public static XSSFSheet xslSheet;
	
	public static String cellValue;
	public static String nonAlchoEntry;
	public static ArrayList<String> nonAlchoList;
	public static String [] nonAlchoArray;
	public static JComboBox<String> nonAlchoBox;
	
	
	nonAlchogolDrinks() {
		
	}
	
	
	
	public static ArrayList<String> listFromExcel() throws IOException {
		
		nonAlchoList = new ArrayList<>();
		
		sourceFile = new File(".\\Databases\\nonAlchogolBeverages.xlsx");
		fileInputStream = new FileInputStream(sourceFile);
		workBook = new XSSFWorkbook(fileInputStream);
		xslSheet = (XSSFSheet) workBook.getSheetAt(0);
		
		int cn = 1;
		
		Iterator<Row> rowIterator = xslSheet.iterator();
		
			while (rowIterator.hasNext()) {
				Row rowReader = (Row) rowIterator.next();
					if ( rowReader.getRowNum() == 0 ) {
						continue;
					}
					Iterator<Cell> cellIterator = rowReader.cellIterator();
						while (cellIterator.hasNext()) {
							Cell cellReader = (Cell) cellIterator.next();
								
								if ( cellReader.getRowIndex() != 0 && cellReader.getCellType() != null && cellReader.getCellType() != CellType.BLANK ) {
									switch ( cellReader.getCellType() ) {
									case NUMERIC:
										DataFormatter formatter = new DataFormatter();
										cellValue = formatter.formatCellValue(cellReader).toString();
										break;
									case STRING:
										cellValue = cellReader.toString();
										break;

									default:
										break;
									}// eo switch
								} else {
									cellValue = null;
								} // eo if
								
								nonAlchoEntry += cellValue + " ";
								
								if ( cn == 2 ) {
									nonAlchoList.add(nonAlchoEntry);
									nonAlchoEntry = "";
								}
								
								
								if ( cn > 2 ) {
									cn = 1;
								}
								cn++;
						
						} // eo while
			}
	//		System.out.println(nonAlchoList);
			nonAlchogolArray ();
		return nonAlchoList;
	}
	
	public static String [] nonAlchogolArray () {
		
		nonAlchoArray = new String [ nonAlchoList.size() ];
		
			for (int i = 0; i < nonAlchoArray.length; i++) {
				nonAlchoArray [i] = nonAlchoList.get(i);
				}
			Panel3.getComboArrays( "nonAlcho" );

			return nonAlchoArray;
		
	}
	public static String[] getNonAlchoArray() {
		return nonAlchoArray;
	}



	public static void setNonAlchoArray(String[] nonAlchoArray) {
		nonAlchogolDrinks.nonAlchoArray = nonAlchoArray;
	}
	
	public static void main(String[] args) throws IOException {
//		listFromExcel();

	}

}
