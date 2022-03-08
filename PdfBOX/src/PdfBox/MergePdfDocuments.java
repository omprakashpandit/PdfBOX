package PdfBox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.io.File;
import java.io.IOException;

public class MergePdfDocuments {
	public static void main(String[] args) throws IOException {

		// Loading an existing PDF document
		File file1 = new File("D:\\merged.pdf");
		PDDocument document1 = PDDocument.load(file1);
		File file2 = new File("D:\\adding.pdf");
		PDDocument document2 = PDDocument.load(file2);

		// Create PDFMergerUtility class object
		PDFMergerUtility PDFmerger = new PDFMergerUtility();

		// Setting the destination file path
		PDFmerger.setDestinationFileName("D:\\mergedFinal.pdf");

		// adding the source files
		PDFmerger.addSource(file1);
		PDFmerger.addSource(file2);

		// Merging the documents
		PDFmerger.mergeDocuments(null);

		System.out.println("PDF Documents merged to a single file successfully");

        //Close documents  
		document1.close();
		document2.close();
  
	}
}