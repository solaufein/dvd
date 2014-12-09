package pl.radek.dvd.service.raports;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.raports.MovieNotReturnedDto;
import pl.radek.dvd.logic.raports.RaportDAO;

@Service
@Transactional
public class RaportsServiceImpl implements RaportsService {
    private static Logger logger = Logger.getLogger(RaportsServiceImpl.class);

    @Autowired
    RaportDAO raportDAO;

    public void setRaportDAO(RaportDAO raportDAO) {
        this.raportDAO = raportDAO;
    }

    @Override
    public PaginatedList<MovieNotReturnedDto> getMovieNotReturnedDtoList(ListDataRequest listDataRequest) {
        PaginatedList<MovieNotReturnedDto> movieNotReturnedDtoList = raportDAO.getMovieNotReturnedDtoList(listDataRequest);

        return movieNotReturnedDtoList;
    }
}
