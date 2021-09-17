package com.demo.excel;

import org.apache.poi.ooxml.util.SAXHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class ExcelUtil {

    public static void read(String xlsxFile,int size) {
        try(OPCPackage opcPackage = OPCPackage.open(xlsxFile, PackageAccess.READ);) {
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(opcPackage);
            XSSFReader xssfReader = new XSSFReader(opcPackage);
            StylesTable styles = xssfReader.getStylesTable();
            XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            int index = 0;
            ExcelSheetContentsHandler excelSheetContentsHandler = new ExcelSheetContentsHandler(size);
            while (iter.hasNext()) {
                try (InputStream stream = iter.next()) {
                    String sheetName = iter.getSheetName();
                    System.out.println(sheetName);
                    System.out.println(sheetName + " [index=" + index + "]:");
                    processSheet(styles, strings, excelSheetContentsHandler, stream);
                }
                ++index;
            }
//            System.out.println(excelSheetContentsHandler.getContent().toString());
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private static void processSheet(
            Styles styles,
            SharedStrings strings,
            XSSFSheetXMLHandler.SheetContentsHandler sheetHandler,
            InputStream sheetInputStream) throws IOException, SAXException {
        DataFormatter formatter = new DataFormatter();
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            XMLReader sheetParser = SAXHelper.newXMLReader();
            ContentHandler handler = new XSSFSheetXMLHandler(
                    styles, null, strings, sheetHandler, formatter, false);
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
        } catch(ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
        }
    }
}
