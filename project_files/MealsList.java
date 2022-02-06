package SR.venueSR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.text.DefaultFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MealsList extends JComboBox<String> {
	
	public static  File sourceFile;
	public static  FileInputStream flStream;
	public static Workbook workBook;
	public static XSSFSheet xslSheet;

	
	public static String cellValue;
	public static String theEntry;
	public static ArrayList<String> menuAsArrList;
	public static String [] mealsArrayString;
		
	
	public static String SpaceFiller;

	public static String flag;

	
	public static ArrayList<String> readExcelMenu() throws IOException {
		
		System.out.println("readExcelMenu()");
		
		menuAsArrList = new ArrayList<>();
		
		sourceFile = new File(".\\Databases\\menuCourses.xlsx");
		flStream = new FileInputStream(sourceFile);
		workBook = new XSSFWorkbook (flStream);
		xslSheet = (XSSFSheet) workBook.getSheetAt(0);
		
		int cn = 0;
		Iterator<Row> sheetRowsIterator = xslSheet.iterator();
		
			while (sheetRowsIterator.hasNext()) {
				Row rowReader = (Row) sheetRowsIterator.next();
				
					if ( rowReader.getRowNum() == 0 ) {
						continue;
					}
				
					Iterator<Cell> rowCellsIterator = rowReader.cellIterator();
				while (rowCellsIterator.hasNext()) {
					Cell cellReader = (Cell) rowCellsIterator.next();
					
					if ( cellReader.getRowIndex() != 0 && cellReader.getCellType() != null && cellReader.getCellType() != CellType.BLANK ) {
						switch (cellReader.getCellType()) {
						case NUMERIC:
							DataFormatter formatter = new DataFormatter();
							cellValue = formatter.formatCellValue(cellReader).toString();
							break;
						case STRING:
							cellValue = cellReader.toString();
							break;

						default:
							break;
						}
					} else {
						continue;
					//	cellValue = "";
					} // eo if
					cn++;
					if (cn == 1) {
						SpaceFiller = " |*| ";
					} else {
						SpaceFiller = " | ";
					}
					
					theEntry += cellValue + SpaceFiller;
				
					if (cn == 3) {
						menuAsArrList.add(theEntry);
						theEntry = "";
					}
					if (cn == 3 ) {
						cn = 0;
					}
					
				} // eo while 2
				
			} // eo while 1
			
			mealsArrayString();
		
		return menuAsArrList ;
	}
	
	public static String [] mealsArrayString() {
		
		mealsArrayString = new String [ menuAsArrList.size() ];
		
			for (int i = 0; i < mealsArrayString.length; i++) {
				mealsArrayString [i] = menuAsArrList.get(i);
			}
//			Panel3.createComboMeals(mealsArrayString);
			
			Panel3.getComboArrays( "meals" );
			
		
		return mealsArrayString;
	}
	

	public static void main(String[] args) throws IOException {
	//	readExcelMenu();

	}

}
