package pl.radek.dvd.service.raports;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.dto.raports.IncomePromotionDTO;
import pl.radek.dvd.dto.raports.IncomeRaportWrapper;
import pl.radek.dvd.dto.raports.MovieNotReturnedDto;
import pl.radek.dvd.dto.raports.TopHitsDto;

import java.util.List;

/**
 * Created by Sola on 2014-12-09.
 */
public interface RaportsFacade {
    PaginatedList<MovieNotReturnedDto> getMovieNotReturnedDtoList(ListDataRequest listDataRequest);

    PaginatedList<TopHitsDto> getTopHitsDtoList(ListDataRequest listDataRequest);

    PaginatedList<IncomePromotionDTO> getIncomeDtoList(ListDataRequest listDataRequest);

    PaginatedList<IncomePromotionDTO> getRentPromotionDtoList(ListDataRequest listDataRequest);

    IncomeRaportWrapper getIncomeWrappedList(ListDataRequest listDataRequest);

    List<GenreData> getGenres();

    List<PromotionData> getPromotions();
}
