package pl.radek.dvd.service.renting;

import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ReceiptPdf;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.rr.RentData;

/**
 * Created by Sola on 2014-11-19.
 */
public interface RentingFacade {

    ClientData getClient(int clientId);
    MovieCopyDTO getMovieCopy(int movieCopyId);
    RentData getMovieRentData(int movieCopyId);
    ReceiptPdf getReceiptPdfInformations(int clientId);
}
