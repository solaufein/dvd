package pl.radek.dvd.service.clients;

import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.clients.ClientDetails;
import pl.radek.dvd.dto.clients.ReceiptPdf;

/**
 * User: Sola
 * Date: 2014-06-02
 * Time: 17:21
 */
public interface ClientDetailsService {
    public PaginatedList<ClientDetails> getClientDetails(final ListDataRequest request, int clientId);
    public ReceiptPdf getReceiptPdfInformations(int id);
}
