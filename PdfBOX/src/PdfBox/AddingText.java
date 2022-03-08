package PdfBox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class AddingText {
	public static void main(String[] args) throws IOException {
		// Loading an existing document
		File file = new File("D:\\blank.pdf");
		PDDocument doc = PDDocument.load(file);

		// Retrieving the pages of the document
		PDPage page = doc.getPage(2);
		PDPageContentStream contentStream = new PDPageContentStream(doc, page);

		// Begin the Content stream
		contentStream.beginText();

		// Setting the font to the Content stream
		contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);

		// Setting the position for the line
		contentStream.newLineAtOffset(20, 450);

		String text = "Hi!!! This is the first sample PDF document.";

		// Adding text in the form of string
		contentStream.showText(text);

		// Ending the content stream
		contentStream.endText();

		System.out.println("New Text Content is added in the PDF Document.");

		// Closing the content stream
		contentStream.close();

		// Saving the document
		doc.save(new File("D:\\Adding.pdf"));

		// Closing the document
		doc.close();
	}

}
