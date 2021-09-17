package com.demo.excel;

//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelWriter;

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

import java.io.*;
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
    public void poiExcelUtilReadTest() throws Exception {
        String fileName = "D://excel/test1.xlsx";
        ExcelUtil.read(fileName,1000);
    }
    @Test
    public void poiExcelReadTest() throws Exception {
        String fileName = "D://excel/test1.xlsx";
        XLSX2CSV.main(new String[]{fileName});
    }

    private void doPoiReadWriteTest(String fileName) throws IOException, InvalidFormatException {
//        FileInputStream in = new FileInputStream(new File(fileName));
//        ExcelParser.parse(fileName);
    }

    @Test
    public void poiExcelWriteTest() {
        String fileName = "D://excel/poiExcelTest2.xlsx";
        doPoiExcelWriteTest(fileName, 50000, 10);
    }


    private void doPoiExcelWriteTest(String fileName, int rowSize, int columnSize) {

        long start = System.currentTimeMillis();
        //创建工作簿
        Workbook wb = new HSSFWorkbook();
        //创建 Sheet页
        Sheet sheet = wb.createSheet();
        Row firstRow = sheet.createRow(0);//第一行表头
        List<List<String>> head = getHeads(columnSize);

        for (int i = 0; i < head.size(); i++) {
            Cell cell = firstRow.createCell(i);
            cell.setCellValue(head.get(i).get(0));
        }
        List<List<String>> data = getRowData(rowSize, columnSize);
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            List<String> rowData = data.get(i);
            for (int j = 0; j < rowData.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowData.get(j));
            }
        }

        System.out.println("创建数据耗时：" + (System.currentTimeMillis() - start) + "ms");
        try {
            //路径需要存在
            FileOutputStream fos = new FileOutputStream(fileName);
            start = System.currentTimeMillis();
            wb.write(fos);
            fos.close();
            System.out.println("写数据耗时：" + (System.currentTimeMillis() - start) + "ms");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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


}
