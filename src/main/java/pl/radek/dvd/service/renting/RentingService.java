package pl.radek.dvd.service.renting;

import pl.radek.dvd.dto.rr.NewRentDto;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.exceptions.movie.MovieCopyNotAvailableException;

public interface RentingService {
    int addRentingRegistry(NewRentDto rentDto) throws MovieCopyNotAvailableException;

    boolean updateRentingRegistry(ReturnCommentDto returnDto);
}
