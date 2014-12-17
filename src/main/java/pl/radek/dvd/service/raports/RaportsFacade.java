package pl.radek.dvd.service.raports;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.dto.raports.*;

import java.util.List;

/**
 * Created by Sola on 2014-12-09.
 */
public interface RaportsFacade {
    PaginatedList<MovieNotReturnedDto> getMovieNotReturnedDtoList(ListDataRequest listDataRequest);

    PaginatedList<TopHitsDto> getTopHitsDtoList(ListDataRequest listDataRequest);

    PaginatedList<IncomePromotionDTO> getIncomeDtoList(ListDataRequest listDataRequest);

    PaginatedList<IncomePromotionDTO> getRentPromotionDtoList(ListDataRequest listDataRequest);

    PaginatedRaportWrapper<AmountPerX> getIncomeWrappedList(ListDataRequest listDataRequest);

    PaginatedRaportWrapper<AmountPerX> getRentPromotionWrappedList(ListDataRequest listDataRequest);

    List<GenreData> getGenres();

    List<PromotionData> getPromotions();
}
