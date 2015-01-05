package pl.radek.dvd.service.clients;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.clients.ClientDetails;
import pl.radek.dvd.dto.clients.PaginatedListClientDetails;
import pl.radek.dvd.dto.clients.ReceiptPdf;
import pl.radek.dvd.logic.clients.ClientDetailsDAO;

import java.util.List;

@Service
@Transactional
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private static Logger logger = Logger.getLogger(ClientDetailsServiceImpl.class);

    @Autowired
    private ClientDetailsDAO clientDetailsDAO;

    public void setClientDetailsDAO(ClientDetailsDAO clientDetailsDAO) {
        this.clientDetailsDAO = clientDetailsDAO;
    }

    @Override
    public PaginatedList<ClientDetails> getClientDetails(ListDataRequest request, int id) {
        int records = clientDetailsDAO.getNoOfRecords(request, id);
        List<ClientDetails> data = clientDetailsDAO.getClientDetails(request, id);

        PaginatedListClientDetails paginatedList = new PaginatedListClientDetails();
        paginatedList.setDataList(data);
        paginatedList.setNoOfRecords(records);

        return paginatedList;
    }

    @Override
    public ReceiptPdf getReceiptPdfInformations(int id) {
        return clientDetailsDAO.getReceiptPdfInformations(id);
    }

    @Override
    public ReceiptPdf getReceiptPdfInformationsReturnMovie(int id) {
        return clientDetailsDAO.getReceiptPdfInformationsReturnMovie(id);
    }

}
