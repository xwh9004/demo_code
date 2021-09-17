package com.demo.excel;

import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

public class ExcelSheetContentsHandler implements XSSFSheetXMLHandler.SheetContentsHandler {

    private int totalRows = 0;

    private int maxSize =0;

    private int holdSize =0;
    private StringBuilder sb =new StringBuilder();

    public ExcelSheetContentsHandler(int size){
        this.maxSize =size;
        System.out.println("队列大小="+size);
    }
    @Override
    public void startRow(int rowNum) {
      sb.append("row="+rowNum +"|");
        totalRows++;
        if(totalRows>maxSize){
            holdSize=0;
            sb.setLength(0);
        }
        holdSize++;

    }

    @Override
    public void endRow(int rowNum) {
        sb.append("\n");
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
//        System.out.println("cell="+cellReference +"value="+formattedValue);
        sb.append(formattedValue);
        sb.append("|");

    }

    @Override
    public void endSheet() {
//        System.out.println(sb.toString());
        System.out.println("totalRows = "+totalRows);
    }

    public StringBuilder getContent() {
        return sb;
    }
}
