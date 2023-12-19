package com.accolite.pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
//import org.jfree.chart.ChartUtilities;
import com.accolite.chart.ChartGenerator;
import com.accolite.model.DataModel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class PdfGenerator {
    public static void generateNewPdf(String outputPath) {
        try {
            // PDF document with the A4 page size is created
            Document document = new Document(new PdfDocument(new PdfWriter(outputPath)));

            // Add content to the PDF here if needed

            // Close the document after adding content
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void generateBarChart(List<DataModel> data, String outputPath) throws IOException {
        try (OutputStream fos = new FileOutputStream(outputPath);
             PdfWriter writer = new PdfWriter(fos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            // Create JFreeChart
            JFreeChart chart = ChartGenerator.createChart(data);


            // Convert JFreeChart to BufferedImage
            int width = 600; // set width of the image
            int height = 800; // set height of the image
            BufferedImage bufferedImage = chart.createBufferedImage(width, height);

            // Convert BufferedImage to iTextPDF Image
            Image itextImage = new Image(ImageDataFactory.create(bufferedImage, null));

            // Add content to the PDF
            document.add(itextImage);
        }
    }
    public static void createPanelPdf(DefaultPieDataset dataset, String outputPath) {
        try (OutputStream fos = new FileOutputStream(outputPath);
             PdfWriter writer = new PdfWriter(fos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            // Create JFreeChart
            JFreeChart chart = ChartGenerator.createPieChart("Top 3 Panels",dataset);
            // Convert JFreeChart to BufferedImage
            int width = 600; // set width of the image
            int height = 800; // set height of the image
            BufferedImage bufferedImage = chart.createBufferedImage(width, height);

            // Convert BufferedImage to iTextPDF Image
            Image itextImage = new Image(ImageDataFactory.create(bufferedImage, null));

            // Add content to the PDF
            document.add(itextImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createSkillsPdf(DefaultPieDataset dataset, String outputPath) {
        try (OutputStream fos = new FileOutputStream(outputPath);
             PdfWriter writer = new PdfWriter(fos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            // Create JFreeChart
            JFreeChart chart = ChartGenerator.createPieChart("Top 3 Skills",dataset);
            // Convert JFreeChart to BufferedImage
            int width = 600; // set width of the image
            int height = 800; // set height of the image
            BufferedImage bufferedImage = chart.createBufferedImage(width, height);

            // Convert BufferedImage to iTextPDF Image
            Image itextImage = new Image(ImageDataFactory.create(bufferedImage, null));

            // Add content to the PDF
            document.add(itextImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}