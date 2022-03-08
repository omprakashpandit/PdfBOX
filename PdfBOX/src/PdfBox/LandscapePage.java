package PdfBox;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class LandscapePage {

	public static void main(String[] args) throws IOException {
		// Creating PDF document object
		PDDocument doc = new PDDocument();
		//PDPage blankPage = new PDPage();
		PDPage blankPage = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));

		doc.addPage(blankPage);

		// Saving the document
		doc.save("D:\\blankSimple.pdf");

		System.out.println("PDF created");

		// Closing the document
		doc.close();
	}
}