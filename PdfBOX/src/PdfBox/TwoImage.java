package PdfBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

//https://pdfbox.apache.org/docs/2.0.8/javadocs/org/apache/pdfbox/pdmodel/PDPageContentStream.html
public class TwoImage {
	public static void main(String[] args) throws Exception {
		//  combineImagesIntoPDF("D:\\combineoutput.pdf","D:\\combined.png","D:\\combined2.png"); 
		  combineImagesIntoPDF("D:\\combined2.png","D:\\Signature1.png");       
}
	 private static void combineImagesIntoPDF(String... inputDirsAndFiles) throws IOException {
	        try (PDDocument doc = new PDDocument()) {
	            for (String input : inputDirsAndFiles) {
	                Files.find(Paths.get(input),
	                           Integer.MAX_VALUE,
	                           (path, basicFileAttributes) -> Files.isRegularFile(path))
	                     .forEachOrdered(path -> addImageAsNewPage(doc, path.toString()));
	            }
	           // doc.save(pdfPath);
	        }
	    }
	  private static void addImageAsNewPage(PDDocument doc, String imagePath) {
	        try {
	        	 
	        	System.out.println("imagePath: "+imagePath);
	            PDImageXObject pdImage          = PDImageXObject.createFromFile(imagePath, doc);
	            PDRectangle    pageSize       = PDRectangle.A4;
	            PDPage         page           = new PDPage(pageSize);
	            doc.addPage(page);
	              int            originalWidth  = pdImage.getWidth();
	              int            originalHeight = pdImage.getHeight();
	              float          pageWidth      = pageSize.getWidth();
	              float          pageHeight     = pageSize.getHeight();
	              float          ratio          = Math.min(pageWidth / originalWidth, pageHeight / originalHeight);
	              float          scaledWidth    = originalWidth  * ratio;
	              float          scaledHeight   = originalHeight * ratio;
	              float          x              = (pageWidth  - scaledWidth ) / 2;
	              float          y              = (pageHeight - scaledHeight) / 2;
	            
	           
	            PDPageContentStream contents = new PDPageContentStream(doc, page);
	            //contents.drawImage(pdImage, 150,-50,400,800);
	          //  contents.drawImage(pdImage, 150,-50,400,750);
	           
	              contents.drawImage(pdImage, x, y, scaledWidth, scaledHeight);
	           // contents.drawImage(pdImage, 150.0f, y, scaledWidth, 800.0f);
	          
				contents.close();
				doc.save(new File("D:\\Image.pdf"));
	        }
	        
	 catch (IOException e) {
			System.err.println("Exception while trying to create pdf document - " + e);
		}
	}
}
