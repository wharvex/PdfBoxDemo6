package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;

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
      System.out.println("PDF loaded successfully. Number of pages: " + document.getNumberOfPages());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}