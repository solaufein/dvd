package pl.radek.dvd.service.home;

import pl.radek.dvd.dto.clients.ClientRentDto;
import pl.radek.dvd.dto.movies.MovieBySerialData;
import pl.radek.dvd.exceptions.client.ClientNotFoundException;
import pl.radek.dvd.exceptions.movie.MovieNotFoundException;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Movie;

import java.util.List;

/**
 * Created by Sola on 2014-12-02.
 */
public interface HomeFacade {
    List<ClientRentDto> getClients(String pesel) throws ClientNotFoundException;
    ClientRentDto getClient(String pesel);
    List<Client> getClientByLastName(String lastName) throws ClientNotFoundException;
    public List<Movie> getMovieByTitle(String title) throws MovieNotFoundException;
    public MovieBySerialData getMovieBySerialNumber(String serialNumber) throws MovieNotFoundException;
    public List<Movie> getMoviesBySerialNumber(String serialNumber) throws MovieNotFoundException;
}
