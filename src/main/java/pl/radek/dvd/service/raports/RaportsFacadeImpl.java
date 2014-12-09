package pl.radek.dvd.service.raports;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.raports.MovieNotReturnedDto;

@Component
public class RaportsFacadeImpl implements RaportsFacade {
    private static Logger logger = Logger.getLogger(RaportsFacadeImpl.class);

    @Autowired
    RaportsService raportsService;

    public void setRaportsService(RaportsService raportsService) {
        this.raportsService = raportsService;
    }

    @Override
    public PaginatedList<MovieNotReturnedDto> getMovieNotReturnedDtoList(ListDataRequest listDataRequest) {
        PaginatedList<MovieNotReturnedDto> movieNotReturnedDtoList = raportsService.getMovieNotReturnedDtoList(listDataRequest);

        return movieNotReturnedDtoList;
    }
}
