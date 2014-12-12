package pl.radek.dvd.service.raports;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.raports.MovieNotReturnedDto;
import pl.radek.dvd.dto.raports.TopHitsDto;

/**
 * Created by Sola on 2014-12-09.
 */
public interface RaportsService {
    PaginatedList<MovieNotReturnedDto> getMovieNotReturnedDtoList(ListDataRequest listDataRequest);
    PaginatedList<TopHitsDto> getTopHitsDtoList(ListDataRequest listDataRequest);
}
