package PdfBox;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Main {

	public static void main(String[] args) throws IOException {
		// Creating PDF document object
		PDDocument doc = new PDDocument();

		// Adding Page
		//for (int i = 0; i < 5; i++) {
			// Creating a blank page
			PDPage blankPage = new PDPage();

			// Adding the blank page to the document
			doc.addPage(blankPage);
	//	}

		// Saving the document
		doc.save("D:\\blankSimple.pdf");

		System.out.println("PDF created");

		// Closing the document
		doc.close();
	}
}