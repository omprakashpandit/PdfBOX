package PdfBox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class AddingTwoImageSinglePdf {

	public static void main(String[] args) throws IOException {
		insertImage("D:\\output2.pdf","D:\\Signature1.png");
	}
	public static void insertImage(String pdfpath,String imagepath) throws IOException
	{
		PDDocument pdd=PDDocument.load(new File(pdfpath));
		PDPage page=pdd.getPage(0);
		PDImageXObject pdImage=PDImageXObject.createFromFile(imagepath, pdd);
		PDPageContentStream cs=new PDPageContentStream(pdd,page,PDPageContentStream.AppendMode.APPEND,false);
		//cs.drawImage(pdImage, 450,150,100,100);
		cs.drawImage(pdImage, 350,150,200,80);
		System.out.println("Image inserted..");
		cs.close();
		pdd.save(new File("D:\\SingleImage.pdf"));
	}
}
