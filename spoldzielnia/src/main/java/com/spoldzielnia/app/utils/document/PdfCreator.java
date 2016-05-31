package com.spoldzielnia.app.utils.document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.spoldzielnia.app.model.Bills;
import com.spoldzielnia.app.model.Prices;

public class PdfCreator {

	@SuppressWarnings("deprecation")
	public static String Generate(Bills bill, Prices price)
	{
		System.out.println("Generowanie !!!!!!!!!!!!!!!");
		try {
            OutputStream file = new FileOutputStream(new File(""+bill.getIdBills()+".pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();

            Font bigFont = FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 12);
            Font bigFont1 = FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 22, Font.BOLD);
            
            document.add(new Paragraph("Data: "+bill.getModDate(), bigFont));
            document.add(new Paragraph("Spó³dzielnia mieszkaniowa SPRING", bigFont));
            document.add(new Paragraph("£ódŸ, Sienkiewicza 175",bigFont));
            document.add(new Paragraph("91-412 £ódŸ",bigFont));
            document.add(new Paragraph("Telefon : +48 42 632 00 00" , bigFont));
            document.add(new Paragraph("E-mail: biuro@sping.com" , bigFont));

            document.addAuthor("Spó³dzielnia mieszkaniowa");
            document.addCreationDate();
            document.addCreator("Spoldzielnia mieszkaniowa");
            document.addTitle("Bills"+bill.getIdBills());

            Paragraph paragraph = new Paragraph("RACHUNEK",new Font(Font.FontFamily.TIMES_ROMAN, 32,
          	      Font.BOLD));
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            
            int miesiac = bill.getModDate().getMonth();
            int rok  = bill.getModDate().getYear() + 1900;
            Paragraph paragraph2 = new Paragraph("za "+miesiac+"/"+rok,new Font(Font.FontFamily.TIMES_ROMAN, 16,
            	      Font.BOLD));
              paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
              paragraph2.add(new Paragraph(" "));
            //Create Paragraph
              

            document.add(paragraph);
            document.add(paragraph2);

            //Create a table in PDF
            PdfPTable pdfTable = new PdfPTable(6);
            PdfPCell cell1 = new PdfPCell(new Phrase("L.p"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Nazwa op³aty" ,bigFont));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Iloœæ" , bigFont));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Jedn." , bigFont));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Cena jedn." , bigFont));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Cena ca³kowita" , bigFont));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            
            pdfTable.setHeaderRows(1);

            //water
            pdfTable.addCell(new Phrase("1", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(new Phrase("Woda", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+bill.getWaterValue());
            pdfTable.addCell(new Phrase("m3", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+price.getWater());
            pdfTable.addCell(""+bill.getWater());
            
          //current
            pdfTable.addCell(new Phrase("2", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(new Phrase("Pr¹d", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+bill.getCurrentValue());
            pdfTable.addCell(new Phrase("kWh", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+price.getCurrent());
            pdfTable.addCell(""+bill.getCurrent());
            
          //gas
            pdfTable.addCell(new Phrase("3", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(new Phrase("Gaz", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+bill.getGasValue());
            pdfTable.addCell(new Phrase("m3", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+price.getGas());
            pdfTable.addCell(""+bill.getGas());
            
          //energy
            pdfTable.addCell(new Phrase("4", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(new Phrase("Ogrzewanie", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+bill.getEnergyValue());
            pdfTable.addCell(new Phrase("Pr¹d", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+price.getEnergy());
            pdfTable.addCell(""+bill.getEnergy());
            
          //intercom
            pdfTable.addCell(new Phrase("5", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(new Phrase("Domofon", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+bill.getOsoby());
            pdfTable.addCell(new Phrase("os.", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+price.getIntercom());
            pdfTable.addCell(""+bill.getIntercom());
            
          //trash
            pdfTable.addCell(new Phrase("6", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(new Phrase("Œmieci", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+bill.getOsoby());
            pdfTable.addCell(new Phrase("os.", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+price.getTrash());
            pdfTable.addCell(""+bill.getTrash());

          //sewage
            pdfTable.addCell(new Phrase("7", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(new Phrase("Œcieki", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+bill.getWaterValue());
            pdfTable.addCell(new Phrase("m3", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+price.getSewage());
            pdfTable.addCell(""+bill.getSewage());

          //other
            pdfTable.addCell(new Phrase("8", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(new Phrase("Inne Koszty", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+bill.getOsoby());
            pdfTable.addCell(new Phrase("os.", FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, 11)));
            pdfTable.addCell(""+price.getOther());
            pdfTable.addCell(""+bill.getOther());

            document.add(pdfTable);
            
            Paragraph paragraph3 = new Paragraph("Cena ca³kowita :"+bill.getCost()+" z³", bigFont1);
              paragraph3.setAlignment(Paragraph.ALIGN_CENTER);
              document.add(paragraph3);
            document.close();
            file.close();
            

        } catch (Exception e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
        }
		return new File(""+bill.getIdBills()+".pdf").getAbsolutePath();
	}
}
