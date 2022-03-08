package PdfBox;
import java.io.File;   
import java.io.IOException;  
import org.apache.pdfbox.pdmodel.PDDocument;   
  
public class RemovePage {     
    public static void main(String[] args)throws IOException {  
          
        //Loading an existing document  
          File file = new File("D:\\output2.pdf");  
          PDDocument doc = PDDocument.load(file);  
      
    //Listing the number of existing pages  
    int noOfPages= doc.getNumberOfPages();  
      
          System.out.print(noOfPages);  
      
    //Removing the pages  
    doc.removePage(0);  
      
          System.out.println("Page removed successfully.");  
  
    //Saving the document  
    doc.save(new File("D:\\Newblank.pdf"));  
  
    //Closing the document  
    doc.close();  
    }  
}  