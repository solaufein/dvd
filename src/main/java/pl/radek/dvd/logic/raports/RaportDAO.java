package pl.radek.dvd.logic.raports;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.raports.MovieNotReturnedDto;

import java.util.List;

public interface RaportDAO {

    PaginatedList<MovieNotReturnedDto> getMovieNotReturnedDtoList(ListDataRequest listDataRequest);
}
