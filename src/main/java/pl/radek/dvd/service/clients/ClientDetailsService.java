package pl.radek.dvd.service.clients;

import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.clients.ClientDetails;
import pl.radek.dvd.dto.clients.ReceiptPdf;

public interface ClientDetailsService {
    public PaginatedList<ClientDetails> getClientDetails(final ListDataRequest request, int clientId);
    public ReceiptPdf getReceiptPdfInformations(int id);
    public ReceiptPdf getReceiptPdfInformationsReturnMovie(int id);
}
