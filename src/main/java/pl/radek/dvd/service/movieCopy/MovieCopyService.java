package pl.radek.dvd.service.movieCopy;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.model.MovieCopy;

import java.util.List;

public interface MovieCopyService {
    public List<MovieCopyDTO> getMovieCopies();

    public PaginatedList<MovieCopyDTO> getMovieCopyPaginatedList(final ListDataRequest request, int movieId);

    public MovieCopyDTO getMovieCopy(int id);

    public void deleteMovieCopy(int id);

    public void addMovieCopy(int movieId ,MovieCopyDTO movieCopy);

    public void updateMovieCopy(MovieCopyDTO movieCopy);

    ReturnCommentDto getReturnData(Integer movieCopyId);
}
