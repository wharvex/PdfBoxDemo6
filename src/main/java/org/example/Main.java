package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    System.out.printf("Hello and welcome!");


    for (int i = 1; i <= 5; i++) {
      //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
      // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
      System.out.println("i = " + i);
    }

    // Load File from file system.
    String currentWorkingDirectory = System.getProperty("user.dir");
    System.out.println("Current working directory: " + currentWorkingDirectory);
    File file = new File("test.pdf");

    // Load PDF document from File.
    try (PDDocument document = Loader.loadPDF(file)) {
      int numberOfPages = document.getNumberOfPages();
      for (int curPageNum = 0; curPageNum < numberOfPages; curPageNum++) {
        System.out.println("Page " + (curPageNum + 1));
        PDPage page = document.getPage(curPageNum);
        try (var contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true,
            true)) {
          contentStream.beginText();
          contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
          // Set text color to black
          PDColor black = new PDColor(new float[]{0, 0, 0}, PDDeviceRGB.INSTANCE);
          PDColor red = new PDColor(new float[]{255, 0, 0}, PDDeviceRGB.INSTANCE);
          PDColor blue = new PDColor(new float[]{0, 0, 255}, PDDeviceRGB.INSTANCE);
          contentStream.setNonStrokingColor(black);
          contentStream.newLineAtOffset(100, 700);
          contentStream.showText("This is a new line of text added to the PDF.");

          contentStream.setNonStrokingColor(red);
          contentStream.newLineAtOffset(0, -20);
          contentStream.showText("This is a new line of text added to the PDF.");

          contentStream.setNonStrokingColor(blue);
          contentStream.newLineAtOffset(0, -20);
          contentStream.showText("This is a new line of text added to the PDF.");

          contentStream.endText();

          // Draw a rounded rectangle
          contentStream.setStrokingColor(0, 0, 0); // Black border
          drawRoundedRectangle(contentStream, 50, 650, 200, 100, 5);
        }
      }
      // Save the updated document to a new file.
      saveUpdatedDocument(document);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void drawRoundedRectangle(PDPageContentStream contentStream, float x, float y, float width,
                                           float height, float radius) throws Exception {
    // Draw a rounded rectangle using lines and Bezier curves
    contentStream.moveTo(x + radius, y);
    contentStream.lineTo(x + width - radius, y);
    contentStream.curveTo(x + width, y, x + width, y, x + width, y + radius);
    contentStream.lineTo(x + width, y + height - radius);
    contentStream.curveTo(x + width, y + height, x + width, y + height, x + width - radius, y + height);
    contentStream.lineTo(x + radius, y + height);
    contentStream.curveTo(x, y + height, x, y + height, x, y + height - radius);
    contentStream.lineTo(x, y + radius);
    contentStream.curveTo(x, y, x, y, x + radius, y);
    contentStream.closePath();
    contentStream.stroke();
  }

  private static void saveUpdatedDocument(PDDocument document) {
    // Save the updated document as "updated_test_x.pdf" where x is the next available number
    int fileIndex = 1;
    File outputFile;
    do {
      outputFile = new File("updated_test_" + fileIndex + ".pdf");
      fileIndex++;
    } while (outputFile.exists());
    try {
      document.save(outputFile);
      System.out.println("Updated PDF saved as " + outputFile.getName());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}