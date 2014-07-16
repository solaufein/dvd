package pl.radek.dvd.logic.clients;

import pl.radek.dvd.dto.clients.ClientDetails;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.clients.ReceiptPdf;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-06-02
 * Time: 17:24
 */
public interface ClientDetailsDAO {
    public int getNoOfRecords(ListDataRequest listDataRequest, int clientId);
    public List<ClientDetails> getClientDetails(ListDataRequest listDataRequest, int clientId);
    public ReceiptPdf getReceiptPdfInformations(int id);
}
