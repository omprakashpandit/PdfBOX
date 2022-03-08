package PdfBox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class LoadingPage {
	public static void main(String[] args) throws IOException {
		// Loading an existing document
		File file = new File("C:\\1.pdf");
		PDDocument doc = PDDocument.load(file);

		System.out.println("PDF loaded");

//Adding a blank page to the document   
		doc.addPage(new PDPage());

//Saving the document   
		doc.save("D:\\loadPage.pdf");

//Closing the document    
	}
}
