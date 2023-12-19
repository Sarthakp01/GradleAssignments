package com.accolite;// Main.java
import com.accolite.chart.ChartGenerator;
import com.accolite.database.DbOperations;
import com.accolite.excel.ExcelReader;
import com.accolite.model.DataModel;
import com.accolite.pdf.PdfGenerator;
import org.jfree.chart.JFreeChart;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Step 1: Create table in the database
            DbOperations.createTable();

            // Step 2: Read Excel data
            List<DataModel> data = ExcelReader.readExcel("C:\\Users\\sarthak.parande\\GradleDatabaseAssignment\\src\\main\\resources\\Accolite.xlsx");

            // Step 3: Insert data into the database
            DbOperations.insertData(data);
            DbOperations.teamWithMaxInterviews();
            DbOperations.teamWithMinInterviews();
            DbOperations.top3Skills();
            DbOperations.top3Panels();
            DbOperations.skillsInPeakTime();

            // Step 4: Generate charts
            ChartGenerator.createChart(data);

            // Step 5: Generate PDF and embed charts
            PdfGenerator.generateBarChart(data, "C:/Users/sarthak.parande/GradleDatabaseAssignment/output/Output1.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}