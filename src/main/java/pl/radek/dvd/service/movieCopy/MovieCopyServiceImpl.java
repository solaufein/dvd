package pl.radek.dvd.service.movieCopy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.dto.movies.PaginatedListMovieCopy;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.logic.movieCopy.MovieCopyDAO;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.model.MovieCopy;
import pl.radek.dvd.service.movies.MoviesServiceImpl;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class MovieCopyServiceImpl implements MovieCopyService {

    private static Logger logger = Logger.getLogger(MovieCopyServiceImpl.class);

    @Autowired
    private MovieCopyDAO movieCopyDAO;

    public void setMovieCopyDAO(MovieCopyDAO movieCopyDAO) {
        this.movieCopyDAO = movieCopyDAO;
    }

    @Override
    public List<MovieCopyDTO> getMovieCopies() {
        List<MovieCopy> movieCopies = movieCopyDAO.getMovieCopies();
        List<MovieCopyDTO> movieCopyDTOs = convertMovieCopyListToMovieCopyDTOList(movieCopies);

        return movieCopyDTOs;
    }

    @Override
    public PaginatedList<MovieCopyDTO> getMovieCopyPaginatedList(ListDataRequest request, int id) {
        List<MovieCopy> movies = movieCopyDAO.getMovieCopies(request, id);
        int noOfRecords = movieCopyDAO.getNoOfRecords(request, id);

        PaginatedListMovieCopy paginatedList = new PaginatedListMovieCopy();
        paginatedList.setMovieCopyDTOs(convertMovieCopyListToMovieCopyDTOList(movies));
        paginatedList.setNoOfRecords(noOfRecords);

        return paginatedList;
    }

    @Override
    public MovieCopyDTO getMovieCopy(int id) {
        MovieCopy movieCopy = movieCopyDAO.getMovieCopy(id);
        MovieCopyDTO movieCopyDTO = convertMovieCopyToMovieCopyDTO(movieCopy);
        return movieCopyDTO;
    }

    @Override
    public void deleteMovieCopy(int id) {
        movieCopyDAO.deleteMovieCopy(id);
    }

    @Override
    public void addMovieCopy(int movieId, MovieCopyDTO movieCopyDTO) {
        MovieCopy movieCopy = convertMovieCopyDTOtoMovieCopy(movieCopyDTO);
        movieCopyDAO.addMovieCopy(movieId, movieCopy);
    }

    @Override
    public void updateMovieCopy(MovieCopyDTO movieCopyDTO) {
        MovieCopy movieCopy = convertMovieCopyDTOtoMovieCopy(movieCopyDTO);
        movieCopyDAO.updateMovieCopy(movieCopy);

    }

    @Override
    public ReturnCommentDto getReturnData(Integer movieCopyId) {

        return movieCopyDAO.getReturnData(movieCopyId);
    }

    private MovieCopy convertMovieCopyDTOtoMovieCopy(MovieCopyDTO movieCopyDTO) {
        MovieCopy movieCopy = new MovieCopy();
        movieCopy.setId(movieCopyDTO.getId());
        movieCopy.setAvailability(movieCopyDTO.getAvailability());
        movieCopy.setConditionInfo(movieCopyDTO.getConditionInfo());
        movieCopy.setSerialNumber(movieCopyDTO.getSerialNumber());
        movieCopy.setMovie(movieCopyDTO.getMovie());
        movieCopy.setRentingRegistries(movieCopyDTO.getRentingRegistries());

        return movieCopy;
    }

    private MovieCopyDTO convertMovieCopyToMovieCopyDTO(MovieCopy movieCopy) {
        MovieCopyDTO movieCopyDTO = new MovieCopyDTO();
        movieCopyDTO.setId(movieCopy.getId());
        movieCopyDTO.setAvailability(movieCopy.getAvailability());
        movieCopyDTO.setConditionInfo(movieCopy.getConditionInfo());
        movieCopyDTO.setSerialNumber(movieCopy.getSerialNumber());
        movieCopyDTO.setMovie(movieCopy.getMovie());
        movieCopyDTO.setRentingRegistries(movieCopy.getRentingRegistries());

        return movieCopyDTO;
    }

    private List<MovieCopyDTO> convertMovieCopyListToMovieCopyDTOList(List<MovieCopy> movieCopies) {
        List<MovieCopyDTO> list = new LinkedList<MovieCopyDTO>();
        for (MovieCopy movieCopy : movieCopies) {
            MovieCopyDTO movieCopyDTO = convertMovieCopyToMovieCopyDTO(movieCopy);
            list.add(movieCopyDTO);
        }

        return list;
    }
}
