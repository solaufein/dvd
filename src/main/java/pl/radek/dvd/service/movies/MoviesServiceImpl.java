package pl.radek.dvd.service.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.dto.movies.PaginatedListMovies;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.logic.movies.MoviesDAO;
import pl.radek.dvd.model.Genre;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.model.Promotion;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-16
 * Time: 15:46
 */

@Service
@Transactional
public class MoviesServiceImpl implements MoviesService {

    private static Logger logger = Logger.getLogger(MoviesServiceImpl.class);

    @Autowired
    private MoviesDAO moviesDAO;

    public void setMoviesDAO(MoviesDAO moviesDAO) {
        this.moviesDAO = moviesDAO;
    }

    @Override
    public List<MovieDataDTO> getMovies() {
        List<MovieDataDTO> movieDataDTOs = convertMovieListToMovieDataDTOList(moviesDAO.getMovies());
        return movieDataDTOs;
    }

    @Override
    public MovieDataDTO getMovie(int id) {
        MovieDataDTO movieDataDTO = convertMovieToMovieDataDTO(moviesDAO.getMovie(id));
        return movieDataDTO;
    }

    @Override
    public PaginatedList<MoviesData> getMovies(ListDataRequest request) {
        List<MoviesData> movies = moviesDAO.getMovies(request);
        int noOfRecords = moviesDAO.getNoOfRecords(request);

        PaginatedListMovies paginatedList = new PaginatedListMovies();
        paginatedList.setMoviesDatas(movies);
        paginatedList.setNoOfRecords(noOfRecords);

        return paginatedList;
    }

    @Override
    public void deleteMovie(int id) {
        moviesDAO.deleteMovie(id);
    }

    @Override
    public void addMovie(MovieDataDTO movieDataDTO) {
        Movie movie = convertMovieDataDTOToMovie(movieDataDTO);
        moviesDAO.addMovie(movie);

    }

    @Override
    public void addMovie(Movie movie) {
        moviesDAO.addMovie(movie);
    }

    @Override
    public void updateMovie(MovieDataDTO movieDataDTO) {
        Movie movie = convertMovieDataDTOToMovie(movieDataDTO);
        moviesDAO.updateMovie(movie);
    }

    private List<MovieDataDTO> convertMovieListToMovieDataDTOList(List<Movie> movieList) {
        List<MovieDataDTO> movieDataList = new LinkedList<MovieDataDTO>();
        for (Movie m : movieList) {
            MovieDataDTO movieDataDTO = convertMovieToMovieDataDTO(m);
            movieDataList.add(movieDataDTO);
        }

        return movieDataList;
    }

    private List<Movie> convertMovieDataDTOListToMovieList(List<MovieDataDTO> movieDataDTOList) {
        List<Movie> movieList = new LinkedList<Movie>();
        for (MovieDataDTO movieDataDTO : movieDataDTOList) {
            Movie movie = convertMovieDataDTOToMovie(movieDataDTO);
            movieList.add(movie);
        }

        return movieList;
    }

    private MovieDataDTO convertMovieToMovieDataDTO(Movie movie) {
        MovieDataDTO movieDataDTO = new MovieDataDTO();
        movieDataDTO.setId(movie.getId());
        movieDataDTO.setTitle(movie.getTitle());
        movieDataDTO.setGenre(convertGenreToGenreData(movie.getGenre()));
        movieDataDTO.setPromotion(convertPromotionToPromotionData(movie.getPromotion()));
        movieDataDTO.setDirector(movie.getDirector());
        movieDataDTO.setDescription(movie.getDescription());
        movieDataDTO.setProductionYear(movie.getProductionYear());
        movieDataDTO.setActorset(movie.getActorset());
        movieDataDTO.setMovieCopies(movie.getMovieCopies());

        return movieDataDTO;
    }

    public static Movie convertMovieDataDTOToMovie(MovieDataDTO movieDataDTO) {
        Movie movie = new Movie();
        movie.setId(movieDataDTO.getId());
        movie.setTitle(movieDataDTO.getTitle());
        movie.setGenre(convertGenreDataToGenre(movieDataDTO.getGenre()));
        movie.setPromotion(convertPromotionDataToPromotion(movieDataDTO.getPromotion()));
        movie.setDirector(movieDataDTO.getDirector());
        movie.setDescription(movieDataDTO.getDescription());
        movie.setProductionYear(movieDataDTO.getProductionYear());
        movie.setActorset(movieDataDTO.getActorset());
        movie.setMovieCopies(movieDataDTO.getMovieCopies());

        return movie;
    }

    private GenreData convertGenreToGenreData(Genre genre){
        GenreData genreData = new GenreData(genre.getId(), genre.getGenre(), genre.getMovies());
        return genreData;
    }

    private static Genre convertGenreDataToGenre(GenreData genreData){
        Genre genre = new Genre();
        genre.setId(genreData.getId());
        genre.setGenre(genreData.getName());
        genre.setMovies(genreData.getMovies());
        return genre;
    }

    private PromotionData convertPromotionToPromotionData(Promotion promotion){
        PromotionData promotionData = new PromotionData();
        promotionData.setId(promotion.getId());
        promotionData.setName(promotion.getName());
        promotionData.setValue(promotion.getValue());
        promotionData.setMovies(promotion.getMovies());
        promotionData.setPromotionDaysNumber(promotion.getPromotionDaysNumber());
        return promotionData;
    }

    private static Promotion convertPromotionDataToPromotion(PromotionData promotionData){
        Promotion promotion = new Promotion();
        promotion.setId(promotionData.getId());
        promotion.setName(promotionData.getName());
        promotion.setValue(promotionData.getValue());
        promotion.setMovies(promotionData.getMovies());
        promotion.setPromotionDaysNumber(promotionData.getPromotionDaysNumber());
        return promotion;
    }

}
