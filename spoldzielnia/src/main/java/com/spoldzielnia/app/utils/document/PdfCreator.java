package com.spoldzielnia.app.utils.document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
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
            document.add(new Paragraph("Data: "+bill.getModDate()));
            document.add(new Paragraph("Spó³dzielnia mieszkaniowa SPRING"));
            document.add(new Paragraph("£ódŸ, Sienkiewicza 175"));
            document.add(new Paragraph("91-412 £ódŸ"));
            document.add(new Paragraph("Telefon : +48 42 632 00 00"));
            document.add(new Paragraph("E-mail: biuro@sping.com"));

            document.addAuthor("Spoldzielnia mieszkaniowa");
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

            cell1 = new PdfPCell(new Phrase("Nazwa op³aty"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Iloœæ"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Jedn."));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Cena jedn."));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("Cena ca³kowita"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell1);
            
            pdfTable.setHeaderRows(1);

            //water
            pdfTable.addCell("1");
            pdfTable.addCell("Woda");
            pdfTable.addCell(""+bill.getWaterValue());
            pdfTable.addCell("m3");
            pdfTable.addCell(""+price.getWater());
            pdfTable.addCell(""+bill.getWater());
            
          //current
            pdfTable.addCell("2");
            pdfTable.addCell("Pr¹d");
            pdfTable.addCell(""+bill.getCurrentValue());
            pdfTable.addCell("kWh");
            pdfTable.addCell(""+price.getCurrent());
            pdfTable.addCell(""+bill.getCurrent());
            
          //gas
            pdfTable.addCell("3");
            pdfTable.addCell("Gaz");
            pdfTable.addCell(""+bill.getGasValue());
            pdfTable.addCell("m3");
            pdfTable.addCell(""+price.getGas());
            pdfTable.addCell(""+bill.getGas());
            
          //energy
            pdfTable.addCell("4");
            pdfTable.addCell("Ogrzewanie");
            pdfTable.addCell(""+bill.getEnergyValue());
            pdfTable.addCell("kJ");
            pdfTable.addCell(""+price.getEnergy());
            pdfTable.addCell(""+bill.getEnergy());
            
          //intercom
            pdfTable.addCell("5");
            pdfTable.addCell("Domofon");
            pdfTable.addCell(""+bill.getOsoby());
            pdfTable.addCell("os.");
            pdfTable.addCell(""+price.getIntercom());
            pdfTable.addCell(""+bill.getIntercom());
            
          //trash
            pdfTable.addCell("6");
            pdfTable.addCell("Œmieci");
            pdfTable.addCell(""+bill.getOsoby());
            pdfTable.addCell("os.");
            pdfTable.addCell(""+price.getTrash());
            pdfTable.addCell(""+bill.getTrash());

          //sewage
            pdfTable.addCell("7");
            pdfTable.addCell("Œcieki");
            pdfTable.addCell(""+bill.getWaterValue());
            pdfTable.addCell("m3");
            pdfTable.addCell(""+price.getSewage());
            pdfTable.addCell(""+bill.getSewage());

          //other
            pdfTable.addCell("8");
            pdfTable.addCell("Inne koszty");
            pdfTable.addCell(""+bill.getOsoby());
            pdfTable.addCell("os.");
            pdfTable.addCell(""+price.getOther());
            pdfTable.addCell(""+bill.getOther());

            document.add(pdfTable);
            
            Paragraph paragraph3 = new Paragraph("Cena ca³kowita :"+bill.getCost()+" z³",new Font(Font.FontFamily.TIMES_ROMAN, 22,
            	      Font.BOLD));
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
