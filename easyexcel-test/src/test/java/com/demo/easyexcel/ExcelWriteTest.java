package com.demo.easyexcel;

//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelWriter;

import com.alibaba.excel.EasyExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelWriteTest {

    @Test
    public void sXSSFWorkbookTest() throws Throwable{
        // keep 100 rows in memory, exceeding rows will be flushed to disk
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        for(int rownum = 0; rownum < 1000; rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum = 0; cellnum < 10; cellnum++){
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }
        }
        // Rows with rownum < 900 are flushed and not accessible
        for(int rownum = 0; rownum < 900; rownum++){
            Assert.assertNull(sh.getRow(rownum));
        }
        // ther last 100 rows are still in memory
        for(int rownum = 900; rownum < 1000; rownum++){
            Assert.assertNotNull(sh.getRow(rownum));
        }
        FileOutputStream out = new FileOutputStream("D://excel/sxssf.xlsx");
        wb.write(out);
        out.close();
        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }

    @Test
    public void excelWriteWithDataModelBySXSSFWorkbookTest() throws Throwable{
        List<List<String>> heads = getHeads(10);
        List<DataDemo> data = getRowDataDataDemo(49999);

        // keep 100 rows in memory, exceeding rows will be flushed to disk
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();

        Row head = sh.createRow(0);
        for (int i=0;i<10;i++){
            Cell cell = head.createCell(i);
            cell.setCellValue("列"+i);
        }

        for(int rownum = 0; rownum < data.size(); rownum++){
            DataDemo rowData = data.get(rownum);
            Row row = sh.createRow(rownum+1);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(rowData.getColumn0());

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(rowData.getColumn1());

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(rowData.getColumn2());

            Cell cell3 = row.createCell(3);
            cell3.setCellValue(rowData.getColumn3());

            Cell cell4 = row.createCell(4);
            cell4.setCellValue(rowData.getColumn4());

            Cell cell5 = row.createCell(5);
            cell5.setCellValue(rowData.getColumn5());

            Cell cell6 = row.createCell(6);
            cell6.setCellValue(rowData.getColumn6());

            Cell cell7 = row.createCell(7);
            cell7.setCellValue(rowData.getColumn7());

            Cell cell8 = row.createCell(8);
            cell8.setCellValue(rowData.getColumn8());

            Cell cell9 = row.createCell(9);
            cell9.setCellValue(rowData.getColumn9());
        }
        // Rows with rownum < 900 are flushed and not accessible
//        for(int rownum = 0; rownum < 900; rownum++){
//            Assert.assertNull(sh.getRow(rownum));
//        }
//        // ther last 100 rows are still in memory
//        for(int rownum = 900; rownum < 1000; rownum++){
//            Assert.assertNotNull(sh.getRow(rownum));
//        }
        FileOutputStream out = new FileOutputStream("D://excel//easyExcelDataDemo2.xlsx");
        wb.write(out);
        out.close();
        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }

    @Test
    public void easyExcelWriteWithDataModelTest() {
        List<List<String>> heads = getHeads(10);
        List<DataDemo> data = getRowDataDataDemo(1000);

        File out = new File("D://excel//easyExcelDataDemo1.xlsx");
        EasyExcel.write(out,DataDemo.class).head(heads).sheet().doWrite(data);

    }

    @Test
    public void easyExcelWriteByFillWithDataModelTest() {
        List<List<String>> heads = getHeads(10);
        List<DataDemo> data = getRowDataDataDemo(50000);
        String template = "D://excel//easyExcelTemplate.xlsx";
        File out = new File("D://excel//easyExcelDataDemoFill1.xlsx");
        EasyExcel.write(out,DataDemo.class).withTemplate(template).head(heads).sheet().doFill(data);

    }
    @Test
    public void easyExcelWriteTest() {
        List<List<String>> heads = getHeads(10);
        List<List<String>> data = getRowData(1000, 10);

        File out = new File("D://excel//easyExcel1.xlsx");
        EasyExcel.write(out).head(heads).sheet().doWrite(data);
        System.out.println(out.length());

    }

    private List<List<String>> getHeads(int columns) {
        List<List<String>> heads = new ArrayList<List<String>>();

        for (int i = 0; i < columns; i++) {
            List<String> head = new ArrayList<>();
            head.add("列" + i);
            heads.add(head);
        }
        return heads;
    }

    private List<List<String>> getRowData(int rows, int columns) {
        List<List<String>> data = new ArrayList<List<String>>();
        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add("人生不可能是十全十美，人生路上亦不可能一辈子都平平坦坦。再挫折面前，有人一蹶不振、碌碌无为而抱憾终生；有人迎难而上，在人生路上添写辉煌的一笔。为何人生各异呢？我想，最主要是在挫折面前人的心志、斗志、毅力各不相同吧" + i);
            }
            data.add(row);
        }
        return data;
    }
    private List<DataDemo> getRowDataDataDemo(int rows) {
        String str="人生不可能是十全十美，人生路上亦不可能一辈子都平平坦坦。再挫折面前，有人一蹶不振、碌碌无为而抱憾终生；有人迎难而上，在人生路上添写辉煌的一笔。为何人生各异呢？我想，最主要是在挫折面前人的心志、斗志、毅力各不相同吧";

        List<DataDemo> data = new ArrayList<DataDemo>();
        for (int i = 0; i < rows; i++) {
            DataDemo dataDemo = new DataDemo();
            dataDemo.setColumn0(str.concat("0"));
            dataDemo.setColumn1(str.concat("1"));
            dataDemo.setColumn2(str.concat("2"));
            dataDemo.setColumn3(str.concat("3"));
            dataDemo.setColumn4(str.concat("4"));
            dataDemo.setColumn5(str.concat("5"));
            dataDemo.setColumn6(str.concat("6"));
            dataDemo.setColumn7(str.concat("7"));
            dataDemo.setColumn8(str.concat("8"));
            dataDemo.setColumn9(str.concat("9"));
            data.add(dataDemo);
        }
        return data;
    }

}
