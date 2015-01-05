package pl.radek.dvd.service.renting;

import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ReceiptPdf;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.rr.NewRentDto;
import pl.radek.dvd.dto.rr.RentData;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.dto.rr.ReturnData;
import pl.radek.dvd.exceptions.movie.MovieCopyNotAvailableException;
import pl.radek.dvd.model.Employee;

public interface RentingFacade {

    ClientData getClient(int clientId);
    MovieCopyDTO getMovieCopy(int movieCopyId);
    RentData getMovieRentData(int movieCopyId, short avail);
    ReturnData getMovieReturnData(int movieCopyId, short avail);
    ReceiptPdf getReceiptPdfInformations(int clientId);
    ReceiptPdf getReceiptPdfInformationsReturnMovie(int id);
    Employee getEmployee(String name);
    int addRentingRegistry(NewRentDto rentDto) throws MovieCopyNotAvailableException;

    boolean updateRentingRegistry(ReturnCommentDto returnDto);
}
