package pl.radek.dvd.service.raports;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.dto.raports.MovieNotReturnedDto;
import pl.radek.dvd.dto.raports.TopHitsDto;
import pl.radek.dvd.service.genres.GenresService;
import pl.radek.dvd.service.promotions.PromotionsService;

import java.util.List;

@Component
public class RaportsFacadeImpl implements RaportsFacade {
    private static Logger logger = Logger.getLogger(RaportsFacadeImpl.class);

    @Autowired
    RaportsService raportsService;

    @Autowired
    private GenresService genresService;

    @Autowired
    private PromotionsService promotionsService;

    public void setGenresService(GenresService genresService) {
        this.genresService = genresService;
    }

    public void setPromotionsService(PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    public void setRaportsService(RaportsService raportsService) {
        this.raportsService = raportsService;
    }

    @Override
    public PaginatedList<MovieNotReturnedDto> getMovieNotReturnedDtoList(ListDataRequest listDataRequest) {
        PaginatedList<MovieNotReturnedDto> movieNotReturnedDtoList = raportsService.getMovieNotReturnedDtoList(listDataRequest);

        return movieNotReturnedDtoList;
    }

    @Override
    public PaginatedList<TopHitsDto> getTopHitsDtoList(ListDataRequest listDataRequest) {
        return raportsService.getTopHitsDtoList(listDataRequest);
    }

    @Override
    public List<GenreData> getGenres() {
        return genresService.getGenres();
    }

    @Override
    public List<PromotionData> getPromotions() {
        return promotionsService.getPromotions();
    }
}
