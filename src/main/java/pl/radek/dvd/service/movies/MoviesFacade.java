package pl.radek.dvd.service.movies;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Genre;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.model.Promotion;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-16
 * Time: 15:27
 */
public interface MoviesFacade {
    public List<MovieDataDTO> getMovies();

    public List<GenreData> getGenres();

    public GenreData getGenre(int id);

    public List<Genre> getGenresEntity();

    public PromotionData getPromotion(int id);

    public List<Promotion> getPromotionsEntity();

    public List<PromotionData> getPromotions();

    public MovieDataDTO getMovie(int id);

    public PaginatedList<MoviesData> getMovies(final ListDataRequest request);

    public void deleteMovie(int id);

    public void addMovie(Movie movie);

    public void addMovie(MovieDataDTO movieDataDTO);

    public void updateMovie(MovieDataDTO movieDataDTO);

    public PaginatedList<MovieCopyDTO> getMovieCopyPaginatedList(final ListDataRequest request, int id);
}
