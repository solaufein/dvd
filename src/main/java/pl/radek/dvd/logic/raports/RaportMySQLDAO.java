package pl.radek.dvd.logic.raports;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.raports.MovieNotReturnedDto;
import pl.radek.dvd.dto.raports.PaginatedListMoviesNotReturned;
import pl.radek.dvd.dto.raports.PaginatedListTopHits;
import pl.radek.dvd.dto.raports.TopHitsDto;
import pl.radek.dvd.logic.builder.ChoiceFiltreQueryFactory;
import pl.radek.dvd.logic.builder.MultiFiltreChoice;
import pl.radek.dvd.logic.builder.MultiRaportFilterChoice;
import pl.radek.dvd.logic.builder.TopHitsFilterChoice;
import pl.radek.dvd.model.Constants;

import java.util.List;

@Repository
public class RaportMySQLDAO implements RaportDAO {
    private static Logger logger = Logger.getLogger(RaportMySQLDAO.class);

    private JdbcTemplate jdbcTemplate;
    private HibernateTemplate hibernateTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }


    @Override
    public PaginatedList<MovieNotReturnedDto> getMovieNotReturnedDtoList(ListDataRequest listDataRequest) {
        logger.info("Getting MovieNotReturnedDtoList ");

        MultiFiltreChoice multiFiltreChoice = ChoiceFiltreQueryFactory.getMultiFiltreChoice(listDataRequest, hibernateTemplate, "MovieNotReturnedDto");
        Query query = multiFiltreChoice.filtreQuery();
        List<MovieNotReturnedDto> results = (List<MovieNotReturnedDto>) query.list();
        int noOfRecords = multiFiltreChoice.getNoOfRecords();

        PaginatedListMoviesNotReturned paginatedList = new PaginatedListMoviesNotReturned();
        paginatedList.setDtos(results);
        paginatedList.setNoOfRecords(noOfRecords);

        logger.info("Got MovieNotReturnedDtoList. ");

        return paginatedList;
    }

    @Override
    public PaginatedList<TopHitsDto> getTopHitsDtoList(ListDataRequest listDataRequest) {
        logger.info("Getting TopHitsDtoList ");

        MultiRaportFilterChoice multiFiltreChoice = (MultiRaportFilterChoice) ChoiceFiltreQueryFactory.getMultiFiltreChoice(listDataRequest, hibernateTemplate, "TopHitsDto");
        Query query = multiFiltreChoice.filtreQuery();
        List<TopHitsDto> results = (List<TopHitsDto>) query.list();
        int noOfRecords = multiFiltreChoice.getNoOfRecords();
        int totalRecords = multiFiltreChoice.getTotalRecords();
        logger.info(" totalRecords = " + totalRecords);
        logger.info(" noOfRecords = " + noOfRecords);

        PaginatedListTopHits paginatedList = new PaginatedListTopHits();
        paginatedList.setDtoList(results);
        paginatedList.setNoOfRecords(noOfRecords);
        paginatedList.setTotalRecords(totalRecords);

        logger.info("Got TopHitsDtoList. ");

        return paginatedList;
    }


}
