package controllers;

import models.Payment;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class BillPDFController {

    public static void createBill(Payment payment){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy  HH:mm");

        try {
            File file = new File("D:/SE/LittleBearBuffet/out/Bill/testPdf.pdf");
            PDDocument document = PDDocument.load(file);

            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setLeading(14.5f);

            contentStream.setFont(PDType0Font.load(document, new File("c:/windows/fonts/times.ttf")), 12);
            contentStream.newLineAtOffset(10, 700);

            LocalDateTime localDateTime = LocalDateTime.now();
            Instant instant = Instant.from(localDateTime.atZone(ZoneId.systemDefault()));
            contentStream.showText(String.format("%50s", StringUtils.center("LittleBearBuffet", 50)));
            contentStream.newLine();
            contentStream.showText(String.format("%50s", StringUtils.center("Date:" + format.format(Date.from(instant)), 50)));
            contentStream.newLine();
            contentStream.showText(String.format("%50S", StringUtils.center("***TABLE "+payment.getTable() +"***", 50)));
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText(String.format("%20s%20s", payment.getaPackage().getName(), payment.getaPackage().getPrice()+" Baht"));
            contentStream.newLine();
            contentStream.showText(String.format("%20s%20s", "Each:", payment.getAmt() + " persons"));
            contentStream.newLine();
            contentStream.showText(String.format("%20s%20s", "Total:", payment.getAmt() * payment.getaPackage().getPrice()+" Baht"));
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText(String.format("%50s", StringUtils.center("****Thank you*****", 50)));




            contentStream.endText();

            System.out.println("Content added");


            contentStream.close();

            document.save(new File("D:/SE/LittleBearBuffet/out/Bill/" + payment.getId() +".pdf"));

            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static class StringUtils {

        public static String center(String s, int size) {
            return center(s, size, ' ');
        }

        public static String center(String s, int size, char pad) {
            if (s == null || size <= s.length())
                return s;

            StringBuilder sb = new StringBuilder(size);
            for (int i = 0; i < (size - s.length()) / 2; i++) {
                sb.append(pad);
            }
            sb.append(s);
            while (sb.length() < size) {
                sb.append(pad);
            }
            return sb.toString();
        }
    }


}
