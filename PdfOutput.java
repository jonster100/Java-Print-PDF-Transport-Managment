package continuas_effective_managment;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfOutput {
	private Font mainTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private Font normalPara = new Font(Font.FontFamily.TIMES_ROMAN, 12);
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException
     * @throws    IOException
     */
    public void createManagementPdf(String filename,String systemOutput)
	throws DocumentException, IOException {

        // step 1
    	  Document document = new Document();
          // step 2
          // Creating a PDF 1.7 document
          PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
          writer.setPdfVersion(PdfWriter.VERSION_1_7);
          document.open();
          document.setPageSize(PageSize.A4);

          document.add(new Paragraph("Assessing the Ability to Excercise Continous and Effective Managment",mainTitle));
          document.add( Chunk.NEWLINE );
          Paragraph outputPara = new Paragraph(systemOutput,normalPara);
          outputPara.setAlignment(Element.ALIGN_JUSTIFIED);
          document.add(outputPara);
          document.add( Chunk.NEWLINE );
          document.add(new Paragraph("The suggested amounts of time offer a broad guideline as to what the traffic commissioners might expect in terms of hours worked.",normalPara));
          document.add( Chunk.NEWLINE );
          this.createTable(document);
          document.add( Chunk.NEWLINE );
          Paragraph defaultPara = new Paragraph("These guidelines indicate the starting point "
            		+ "in terms of weekly hours to be specified by a transport manager. "
              		+ "The level of hours required for any other employment or activities "
              		+ "in which the proposed transport manager is enganged may resrict his/"
              		+ "her ability to devote sufficient time to the duties of a transport "
              		+ "manager on any operators licence",normalPara);
          defaultPara.setAlignment(Element.ALIGN_JUSTIFIED);
          document.add(defaultPara);
          document.close();
    }

    private void createTable(Document doc) throws BadElementException {
    	    PdfPTable table = new PdfPTable(2);
    	    PdfPCell c1 = new PdfPCell(new Phrase("Motor Vehicles"));
    	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    table.addCell(c1);

    	    c1 = new PdfPCell(new Phrase("Proposed Hours Per Week"));
    	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    table.addCell(c1);
    	    table.setHeaderRows(1);
    	    //row 1
    	    table.addCell("2 Or Less");
    	    table.addCell("2 - 4");
    	    //row 2
    	    table.addCell("3 to 5");
    	    table.addCell("4 - 8");
    	    //row 3
    	    table.addCell("6 to 10");
    	    table.addCell("8 - 12");
    	    //row 4
    	    table.addCell("11 to 14");
    	    table.addCell("12 - 20");
    	    //row 5
    	    table.addCell("15 to 29");
    	    table.addCell("20 - 30");
    	    //row 6
    	    table.addCell("30 to 50");
    	    table.addCell("30 to Full Time");
    	    //row 7
    	    table.addCell("Above 50");
    	    table.addCell("Full Time With Additional Assitance");
    	    //row 8
    	    table.addCell("Additional Hour may be required for trailers");
    	    try {
				doc.add(table);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	  }

    private void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
      }

  /*  private static void addTitlePage(Document document)
    	      throws DocumentException {
    	    Paragraph preface = new Paragraph();
    	    // We add one empty line
    	    addEmptyLine(preface, 1);
    	    // Lets write a big header
    	    preface.add(new Paragraph("Title of the document", catFont));

    	    addEmptyLine(preface, 1);
    	    // Will create: Report generated by: _name, _date
    	    preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    	        smallBold));
    	    addEmptyLine(preface, 3);
    	    preface.add(new Paragraph("This document describes something which is very important ",
    	        smallBold));

    	    addEmptyLine(preface, 8);

    	    preface.add(new Paragraph("This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
    	        redFont));

    	    document.add(preface);
    	    // Start a new page
    	    document.newPage();
    	  }*/
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
          paragraph.add(new Paragraph(" "));
        }
      }
}
