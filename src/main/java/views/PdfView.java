package views;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import entity.Phone;
import entity.User;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class PdfView extends AbstractPdfView {

    private static final Logger logger = Logger.getLogger(PdfView.class);
    @Override
    protected void buildPdfDocument(Map<String, Object> map,
                                    Document document,
                                    PdfWriter pdfWriter,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) throws Exception {

        logger.debug("test");
        BaseFont times =
                BaseFont.createFont("c:/windows/fonts/times.ttf", "cp1251", BaseFont.EMBEDDED);

        PdfPTable table = new PdfPTable(6);
        PdfPCell header1 = new PdfPCell(new Phrase("#"));
        PdfPCell header2 = new PdfPCell(new Phrase("Login"));
        PdfPCell header3 = new PdfPCell(new Phrase("First Name"));
        PdfPCell header4 = new PdfPCell(new Phrase("Last Name"));
        PdfPCell header5 = new PdfPCell(new Phrase("E-mail"));
        PdfPCell header6 = new PdfPCell(new Phrase("Proceeds"));

        Map<User, Long> topTenUsers = (Map<User, Long>) map.get("topTenUsers");
        int i = 0;
        for (Map.Entry<User, Long> entry: topTenUsers.entrySet())
        {
            i++;
            table.addCell(String.valueOf(i));
            table.addCell(entry.getKey().getLogin());
            table.addCell(entry.getKey().getFirstName());
            table.addCell(entry.getKey().getSecondName());
            table.addCell(entry.getKey().getEmail());
            table.addCell(String.valueOf(entry.getValue()/100.0));
            /*modelAndView.addObject("topTenUsers", userService.getTopTenUsers());
        modelAndView.addObject("topTenPhones", userService.getTopTenPhones());
            <th>Login</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>E-mail</th>
                        <th>Proceeds</th>
             */
        }

        document.add(table);

    }
}
