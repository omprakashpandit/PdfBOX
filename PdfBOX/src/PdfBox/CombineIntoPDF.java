package PdfBox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CombineIntoPDF {
    public static void main(String[] args) throws IOException {
        combineImagesIntoPDF("D:\\combineoutput.pdf", 
                            // "D:\\inputDir1",
                             "D:\\combined.png", 
                             "D:\\combined2.png"); 
                            // "D:\\anotherInputDir");
    }

    private static void combineImagesIntoPDF(String pdfPath, String... inputDirsAndFiles) throws IOException {
        try (PDDocument doc = new PDDocument()) {
            for (String input : inputDirsAndFiles) {
                Files.find(Paths.get(input),
                           Integer.MAX_VALUE,
                           (path, basicFileAttributes) -> Files.isRegularFile(path))
                     .forEachOrdered(path -> addImageAsNewPage(doc, path.toString()));
            }
            doc.save(pdfPath);
        }
    }

    private static void addImageAsNewPage(PDDocument doc, String imagePath) {
        try {
        	System.out.println("imagePath: "+imagePath);
            PDImageXObject image          = PDImageXObject.createFromFile(imagePath, doc);
            PDRectangle    pageSize       = PDRectangle.A4;

            int            originalWidth  = image.getWidth();
            int            originalHeight = image.getHeight();
            float          pageWidth      = pageSize.getWidth();
            float          pageHeight     = pageSize.getHeight();
            float          ratio          = Math.min(pageWidth / originalWidth, pageHeight / originalHeight);
            float          scaledWidth    = originalWidth  * ratio;
            float          scaledHeight   = originalHeight * ratio;
            float          x              = (pageWidth  - scaledWidth ) / 2;
            float          y              = (pageHeight - scaledHeight) / 2;

            PDPage         page           = new PDPage(pageSize);
            doc.addPage(page);
            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
              //  contents.drawImage(image, x, y, scaledWidth, scaledHeight);
                contents.drawImage(image, 150.0f, y, scaledWidth, 800.0f);
            }
            System.out.println("Added: " + imagePath);
        } catch (IOException e) {
            System.err.println("Failed to process: " + imagePath);
            e.printStackTrace(System.err);
        }
    }
}