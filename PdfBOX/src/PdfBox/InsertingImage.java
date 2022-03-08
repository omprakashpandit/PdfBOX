package PdfBox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class InsertingImage {

	public static void main(String[] args) throws IOException {
		// Loading an existing document
		File file = new File("D:\\blank.pdf");
		PDDocument doc = PDDocument.load(file);

		// Retrieving the page
		PDPage page = doc.getPage(1);

		// Creating PDImageXObject object
		PDImageXObject pdImage = PDImageXObject.createFromFile("D:\\Dcr_Data_path.png", doc);

		// creating the PDPageContentStream object
		PDPageContentStream contents = new PDPageContentStream(doc, page);
	//	PDFont font = PDType1Font.HELVETICA_BOLD;  
		// Drawing the image in the PDF document
		//contents.drawImage(pdImage, 250, 300);
		contents.drawImage(pdImage, 0,500);//f,0,60, null
	//	contents.setFont( font, 10 );  
		System.out.println("Image inserted Successfully.");

		// Closing the PDPageContentStream object
		contents.close();

		// Saving the document
		doc.save("D:\\InsertingImage.pdf");

		// Closing the document
		doc.close();

	}

}
