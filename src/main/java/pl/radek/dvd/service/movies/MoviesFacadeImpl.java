package pl.radek.dvd.service.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import pl.radek.dvd.service.genres.GenresService;
import pl.radek.dvd.service.movieCopy.MovieCopyService;
import pl.radek.dvd.service.promotions.PromotionsService;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-16
 * Time: 16:01
 */

@Component
public class MoviesFacadeImpl implements MoviesFacade {

    private static Logger logger = Logger.getLogger(MoviesFacadeImpl.class);

    @Autowired
    private MovieCopyService movieCopyService;

    @Autowired
    private GenresService genresService;

    @Autowired
    private PromotionsService promotionsService;

    @Autowired
    private MoviesService moviesService;

    public void setMoviesService(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    public void setGenresService(GenresService genresService) {
        this.genresService = genresService;
    }

    public void setPromotionsService(PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    public void setMovieCopyService(MovieCopyService movieCopyService) {
        this.movieCopyService = movieCopyService;
    }

    @Override
    public List<MovieDataDTO> getMovies() {
        return moviesService.getMovies();
    }

    @Override
    public List<GenreData> getGenres() {
        return genresService.getGenres();
    }

    @Override
    public GenreData getGenre(int id) {
        return genresService.getGenre(id);
    }

    @Override
    public List<Genre> getGenresEntity() {
        return genresService.getGenresEntity();
    }

    @Override
    public PromotionData getPromotion(int id) {
        return promotionsService.getPromotion(id);
    }

    @Override
    public List<Promotion> getPromotionsEntity() {
        return promotionsService.getPromotionsEntity();
    }

    @Override
    public List<PromotionData> getPromotions() {
        return promotionsService.getPromotions();
    }

    @Override
    public MovieDataDTO getMovie(int id) {
        return moviesService.getMovie(id);
    }

    @Override
    public PaginatedList<MoviesData> getMovies(ListDataRequest request) {
        return moviesService.getMovies(request);
    }

    @Override
    public void deleteMovie(int id) {
        moviesService.deleteMovie(id);
    }

    @Override
    public void addMovie(MovieDataDTO movieDataDTO) {
        moviesService.addMovie(movieDataDTO);
    }

    @Override
    public void addMovie(Movie movie) {
        moviesService.addMovie(movie);
    }

    @Override
    public void updateMovie(MovieDataDTO movieDataDTO) {
        moviesService.updateMovie(movieDataDTO);
    }

    @Override
    public PaginatedList<MovieCopyDTO> getMovieCopyPaginatedList(ListDataRequest request, int id) {
        return movieCopyService.getMovieCopyPaginatedList(request, id);
    }


}
