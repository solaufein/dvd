package pl.radek.dvd.service.movies;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.actor.ActorDataTag;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ClientRentDto;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.dto.movies.MoviesRentData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.exceptions.client.ClientNotFoundException;
import pl.radek.dvd.model.Actor;
import pl.radek.dvd.model.Genre;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.model.Promotion;

import java.util.List;

public interface MoviesFacade {
    public ClientData getClient(int id);

    public Actor getActor(int id);

    public List<ActorDataTag> getActorTags(String tagName);

    public List<MovieDataDTO> getMovies();

    public List<GenreData> getGenres();

    public GenreData getGenre(int id);

    public List<Genre> getGenresEntity();

    public PromotionData getPromotion(int id);

    public List<Promotion> getPromotionsEntity();

    public List<PromotionData> getPromotions();

    public MovieDataDTO getMovie(int id);

    public PaginatedList<MoviesData> getMovies(final ListDataRequest request);

    public PaginatedList<MoviesRentData> getMoviesRentData(ListDataRequest request);

    public void deleteMovie(int id);

    public void deleteMovieCopy(int id);

    public void addMovie(Movie movie);

    public void addMovie(MovieDataDTO movieDataDTO);

    public void addMovieCopy(int movieId, MovieCopyDTO movieCopyDTO);

    public void updateMovie(MovieDataDTO movieDataDTO);

    public PaginatedList<MovieCopyDTO> getMovieCopyPaginatedList(final ListDataRequest request, int movieId);

    List<ClientRentDto> getClients(String term) throws ClientNotFoundException;

    ClientRentDto getClient(String term);

    ReturnCommentDto getReturnData(Integer movieCopyId);
}
