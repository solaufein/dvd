package pl.radek.dvd.logic.clients;

import pl.radek.dvd.dto.clients.ClientDetails;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.clients.ReceiptPdf;

import java.util.List;

public interface ClientDetailsDAO {
    public int getNoOfRecords(ListDataRequest listDataRequest, int clientId);
    public List<ClientDetails> getClientDetails(ListDataRequest listDataRequest, int clientId);
    public ReceiptPdf getReceiptPdfInformations(int id);
    public ReceiptPdf getReceiptPdfInformationsReturnMovie(int id);
}
