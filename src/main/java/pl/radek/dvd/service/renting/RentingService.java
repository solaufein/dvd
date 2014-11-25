package pl.radek.dvd.service.renting;

import pl.radek.dvd.dto.rr.NewRentDto;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.exceptions.movie.MovieCopyNotAvailableException;

/**
 * Created by Sola on 2014-11-19.
 */
public interface RentingService {
    int addRentingRegistry(NewRentDto rentDto) throws MovieCopyNotAvailableException;

    void updateRentingRegistry(ReturnCommentDto returnDto);
}
