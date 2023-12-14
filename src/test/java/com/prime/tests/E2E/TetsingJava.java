package com.prime.tests.E2E;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

 public class TetsingJava {

	 public static String getPDFFileNames(String folderPath) {
         List<String> pdfFileNames = new ArrayList<>();

         // Create a File object representing the folder
         File folder = new File(folderPath);

         // Check if the folder exists and is a directory
         if (folder.exists() && folder.isDirectory()) {
             // List all files in the folder
             File[] files = folder.listFiles();

             // Check if files exist
             if (files != null) {
                 // Iterate through the files
                 for (File file : files) {
                     // Check if the file is a PDF file (you can customize the check if needed)
                     if (file.isFile() && file.getName().toLowerCase().endsWith(".pdf")) {
                         pdfFileNames.add(file.getName());
                     }
                 }
             }
         } else {
             System.out.println("The specified folder does not exist or is not a directory.");
         }

         return pdfFileNames.get(0).toString();
     }



	     public static void main(String[] args) {
	         // Specify the path to the folder containing PDF files
	         String folderPath = System.getProperty("user.dir") + File.separator + "target\\Assets";

	         // Call the method to get PDF file names
	        String pdfFileNames = getPDFFileNames(folderPath);

	         // Print the names of PDF files
	        System.out.println(pdfFileNames);
	     }

	     
	 }
