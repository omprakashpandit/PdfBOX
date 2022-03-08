package PdfBox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

//https://pdfbox.apache.org/docs/2.0.8/javadocs/org/apache/pdfbox/pdmodel/PDPageContentStream.html
public class Image {
	public static void main(String[] args) throws Exception {

		try (final PDDocument doc = new PDDocument()) {

			//PDPage page = new PDPage();
			PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
			doc.addPage(page);
			PDImageXObject pdImage = PDImageXObject.createFromFile("D:\\combined2.png", doc);
			PDPageContentStream contents = new PDPageContentStream(doc, page);
			//drawImage(PDInlineImage inlineImage, float x, float y, float width, float height)
			//contents.drawImage(pdImage, 150, -50, 400, 800);
			PDRectangle    pageSize       = PDRectangle.A4;
			int            originalWidth  = pdImage.getWidth();
            int            originalHeight = pdImage.getHeight();
            float          pageWidth      = pageSize.getWidth();
            float          pageHeight     = pageSize.getHeight();
            float          ratio          = Math.min(pageWidth / originalWidth, pageHeight / originalHeight);
            System.out.println("ratio :"+ratio);
            float          scaledWidth    = originalWidth  * ratio;
            float          scaledHeight   = originalHeight * ratio;
            float          startX              = (pageWidth  - scaledWidth ) / 2;
            float          startY              = (pageHeight - scaledHeight) / 2;
            contents.drawImage(pdImage, 150.0f, startY, scaledWidth, 800.0f);
         
			
			
			
			contents.close();
			doc.save(new File("D:\\Image.pdf"));
			System.out.println("Done");
		} catch (IOException e) {
			System.err.println("Exception while trying to create pdf document - " + e);
		}
	}
}
