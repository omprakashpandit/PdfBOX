package PdfBox;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class HTMLPDFGenerator {
  public static void main(String[] args) {
    try {
      // HTML file - Input
      File inputHTML = new File(HTMLPDFGenerator.class.getClassLoader().getResource("template/Test.html").getFile());
      // Converted PDF file - Output
      String outputPdf = "F:\\NETJS\\Test.pdf";
      HTMLPDFGenerator htmlToPdf = new HTMLPDFGenerator();
      //create well formed HTML
      org.w3c.dom.Document doc = htmlToPdf.createWellFormedHtml(inputHTML);
      System.out.println("Starting conversion to PDF...");
      htmlToPdf.xhtmlToPdf(doc, outputPdf);
    } catch (IOException e) {
      System.out.println("Error while converting HTML to PDF " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  // Creating well formed document
  private org.w3c.dom.Document createWellFormedHtml(File inputHTML) throws IOException {
    Document document = Jsoup.parse(inputHTML, "UTF-8");
    document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
    System.out.println("HTML parsing done...");
    return new W3CDom().fromJsoup(document);
  }
  
  private void xhtmlToPdf(org.w3c.dom.Document doc, String outputPdf) throws IOException {
    // base URI to resolve future resources 
    String baseUri = FileSystems.getDefault()
                .getPath("F:/", "Anshu/NetJs/Programs/", "src/main/resources/template")
                .toUri()
                .toString();
    OutputStream os = new FileOutputStream(outputPdf);
    PdfRendererBuilder builder = new PdfRendererBuilder();
    builder.withUri(outputPdf);
    builder.toStream(os);
    // add external font
    builder.useFont(new File(getClass().getClassLoader().getResource("fonts/PRISTINA.ttf").getFile()), "PRISTINA");
    builder.withW3cDocument(doc, baseUri);
    builder.run();
    System.out.println("PDF creation completed"); 
    os.close();
  }
}