package pe.mil.ejercito.sipr.commons.generateExcel;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

//Find jar from here "http://poi.apache.org/download.html"
/*
 * http://chuwiki.chuidiang.org/index.php?title=Crear_archivo_Excel_desde_Java_con_POI
 * http://monillo007.blogspot.com/2014/01/3-sencillos-pasos-para-generar-archivos.html
 * http://www.avajava.com/tutorials/lessons/how-do-i-write-to-an-excel-file-using-poi.html
 * http://www.java-tips.org/other-api-tips/jexcel/how-to-create-an-excel-file.html
 * http://www.avajava.com/tutorials/lessons/how-do-i-write-to-an-excel-file-using-poi.html
 * http://stackoverflow.com/questions/11221202/write-data-to-excel-file-in-java-using-apache-poi
 */
public class Excel {

	public static void main(String[] args) {
	    try{
	        String filename="C:/NewExcelFile.xls" ;
	        HSSFWorkbook workbook=new HSSFWorkbook();
	        HSSFSheet sheet =  workbook.createSheet("FirstSheet");  

	        HSSFRow rowhead=   sheet.createRow((short)0);
	        rowhead.createCell(0).setCellValue("No.");
	        rowhead.createCell(1).setCellValue("Name");
	        rowhead.createCell(2).setCellValue("Address");
			rowhead.createCell(3).setCellValue("Email");

	        HSSFRow row=   sheet.createRow((short)1);
	        row.createCell(0).setCellValue("1");
	        row.createCell(1).setCellValue("Sankumarsingh");
	        row.createCell(2).setCellValue("India");
	        row.createCell(3).setCellValue("sankumarsingh@gmail.com");

	        FileOutputStream fileOut =  new FileOutputStream(filename);
	        workbook.write(fileOut);
	        fileOut.close();
	        System.out.println("Your excel file has been generated!");

	        } catch ( Exception ex ) {
	            System.out.println(ex);

	        }
	           }

}

