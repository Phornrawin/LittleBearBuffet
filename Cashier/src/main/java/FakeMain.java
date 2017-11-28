
import javafx.application.Application;
import javafx.stage.Stage;
import models.StringAlignUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;

public class FakeMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        File file = new File("D:/SE/LittleBearBuffet/out/Bill/testPdf.pdf");
        PDDocument document = PDDocument.load(file);

        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.setLeading(14.5f);

        contentStream.setFont(PDType0Font.load(document, new File("c:/windows/fonts/times.ttf")), 12);
        contentStream.newLineAtOffset(10, 700);

        contentStream.showText(String.format("%50s", StringUtils.center("LittleBearBuffet", 50)));
        contentStream.newLine();
        contentStream.showText(String.format("%50s", StringUtils.center("Date: 25/11/2107 Time: 20.40", 50)));
        contentStream.newLine();
        contentStream.showText(String.format("%50S", StringUtils.center("***TABLE 9***", 50)));
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(String.format("%20s%20s", "Panda Set", "299 Baht"));
        contentStream.newLine();
        contentStream.showText(String.format("%20s%20s", "Each:", "4 persons"));
        contentStream.newLine();
        contentStream.showText(String.format("%20s%20s", "Total:", "1,196 Baht"));
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(String.format("%50s", StringUtils.center("****Thank you*****", 50)));




        contentStream.endText();

        System.out.println("Content added");


        contentStream.close();

        document.save(new File("D:/SE/LittleBearBuffet/out/Bill/new2.pdf"));

        document.close();
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
