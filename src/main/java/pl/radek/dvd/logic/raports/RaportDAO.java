package pl.radek.dvd.logic.raports;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.raports.IncomePromotionDTO;
import pl.radek.dvd.dto.raports.MovieNotReturnedDto;
import pl.radek.dvd.dto.raports.TopHitsDto;

import java.util.List;

public interface RaportDAO {

    PaginatedList<MovieNotReturnedDto> getMovieNotReturnedDtoList(ListDataRequest listDataRequest);
    PaginatedList<TopHitsDto> getTopHitsDtoList(ListDataRequest listDataRequest);
    PaginatedList<IncomePromotionDTO> getIncomeDtoList(ListDataRequest listDataRequest);
    PaginatedList<IncomePromotionDTO> getRentPromotionDtoList(ListDataRequest listDataRequest);
}
