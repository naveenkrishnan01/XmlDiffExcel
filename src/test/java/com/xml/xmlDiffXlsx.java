package com.xml;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class xmlDiffXlsx {

    public static List compareXmlFile(Reader origin, Reader dest) throws IOException, SAXException {

        //instance to comapare two files
        Diff xmlDifference = new Diff(origin, dest);

        // compare all the difference in the xml files
        DetailedDiff detailXmDiff = new DetailedDiff(xmlDifference);

        // returns a List of Differences in the xml file
        return detailXmDiff.getAllDifferences();


    }


    public class DiffPojo {

        private String differences;

        public DiffPojo(String differences) {
            this.differences = differences;
        }

        public String getDifferences() {
            return differences;
        }

        public void setDifferences(String differences) {
            this.differences = differences;
        }


    }



    public static void createList(Object diffpojo, Row row){
        Cell cell = row.createCell(0);
        cell.setCellValue(diffpojo.toString());
    }


    // XML unit has a paramter called differences of data type List
    public static void writeDifferences(List differences) {

        // create the sheet in a workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("XMLDIFF");

        int rownum = 0;


        int totalDifferences = differences.size();
        System.out.println("**************");
        System.out.println("Differences in line   " + totalDifferences );
        System.out.println("***************" + "\n");

        // loop to read thru the differences
        for (Object difference : differences) {
            Row row = sheet.createRow(rownum++);
            createList( difference, row);

        }

        // create the output file and write into the file
        try {
            FileOutputStream outputStream = new FileOutputStream("../XmlDiffExcel/src/test/java/com/util/xmlDiff.xlsx");
            workbook.write(outputStream);

        } catch (Exception e){
            System.out.println("There is an issue writing to the file");
        }

       // System.out.println(differences.toString());
        System.out.println("The data is pushed to excel file");
    }


    public static void main(String[] args) throws IOException, SAXException {

        // check is the file exist, delete it
        File f = new File("../XmlDiffExcel/src/test/java/com/util/xmlDiff.xlsx");
        if (f.exists()){
            boolean isdeleted = f.delete();
            System.out.println("file " + f + " is deleted is " + isdeleted);
        }else {
            System.out.println("File is not deleted " + f);
        }
        // The instance of the two xm file that needs to be read is created
        FileInputStream fis2 = new FileInputStream("../XmlDiffExcel/src/test/java/com/util/dest.xml");
        FileInputStream fis1 = new FileInputStream("../XmlDiffExcel/src/test/java/com/util/origin.xml");

        // The file is read for both xml
        BufferedReader origin = new BufferedReader(new InputStreamReader(fis1));
        BufferedReader dest = new BufferedReader(new InputStreamReader(fis2));

        // Comparing two xml
        List differences = compareXmlFile(origin, dest);

        // write differences
        writeDifferences(differences);
    }


}

