package pl.radek.dvd.views;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ReceiptPdf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Map;

public class PdfBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // get data model which is passed by the Spring container
        ReceiptPdf receiptInfo = (ReceiptPdf) model.get("receiptInfo");
        ClientData clientInfo = (ClientData) model.get("clientInfo");

        doc.add(new Paragraph("@ DVD Rentals  "));
        doc.add(new Paragraph("Receipt number: " + receiptInfo.getBillNumber()));
        doc.add(new Paragraph("Customer data:  "));

        // *** First table - client ***
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("Firstname", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Lastname", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Pesel", font));
        table.addCell(cell);

        // write table row data
        table.addCell(clientInfo.getFirstName());
        table.addCell(clientInfo.getLastName());
        table.addCell(clientInfo.getPesel());

        // *** Second table - receipt ***
        PdfPTable table2 = new PdfPTable(6);
        table2.setWidthPercentage(100.0f);
        table2.setWidths(new float[]{3.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f});
        table2.setSpacingBefore(10);

        // write table header
        cell.setPhrase(new Phrase("Title", font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("Serial Number", font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("Rent Date", font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("Expected Return Date", font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("Return Date", font));    // PayDate from Receipt
        table2.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table2.addCell(cell);

        // write table row data
        table2.addCell(receiptInfo.getTitle());
        table2.addCell(receiptInfo.getSerialNumber());

        table2.addCell(sdfDate.format(receiptInfo.getRentDate()));
        table2.addCell(sdfDate.format(receiptInfo.getReturnDate()));

        if (receiptInfo.getPayDate() == null) {
            table2.addCell("");
        } else {
            table2.addCell(sdfDate.format(receiptInfo.getPayDate()));
        }

        if (receiptInfo.getPrice() == null) {
            table2.addCell("");
        } else {
            table2.addCell(String.valueOf(receiptInfo.getPrice()));
        }

        doc.add(table);
        doc.add(new Paragraph("Movie rental details:  "));
        doc.add(table2);
    }
}
