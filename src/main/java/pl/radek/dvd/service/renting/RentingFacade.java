package pl.radek.dvd.service.renting;

import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ReceiptPdf;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.rr.NewRentDto;
import pl.radek.dvd.dto.rr.RentData;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.exceptions.movie.MovieCopyNotAvailableException;
import pl.radek.dvd.model.Employee;

/**
 * Created by Sola on 2014-11-19.
 */
public interface RentingFacade {

    ClientData getClient(int clientId);
    MovieCopyDTO getMovieCopy(int movieCopyId);
    RentData getMovieRentData(int movieCopyId, short avail);
    ReceiptPdf getReceiptPdfInformations(int clientId);
    Employee getEmployee(String name);
    int addRentingRegistry(NewRentDto rentDto) throws MovieCopyNotAvailableException;

    void updateRentingRegistry(ReturnCommentDto returnDto);
}
