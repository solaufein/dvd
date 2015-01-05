package pl.radek.dvd.service.home;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.clients.ClientRentDto;
import pl.radek.dvd.dto.movies.MovieBySerialData;
import pl.radek.dvd.exceptions.client.ClientNotFoundException;
import pl.radek.dvd.exceptions.movie.MovieNotFoundException;
import pl.radek.dvd.logic.movieCopy.MovieCopyDAO;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.service.clients.ClientsService;
import pl.radek.dvd.service.movies.MoviesService;

import java.util.List;

@Component
public class HomeFacadeImpl implements HomeFacade {
    private static Logger logger = Logger.getLogger(HomeFacadeImpl.class);

    @Autowired
    ClientsService clientsService;

    @Autowired
    MoviesService moviesService;

    public void setClientsService(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    public void setMoviesService(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @Override
    public List<ClientRentDto> getClients(String pesel) throws ClientNotFoundException {
        return clientsService.getClients(pesel);
    }

    @Override
    public ClientRentDto getClient(String pesel) {
        return clientsService.getClient(pesel);
    }

    @Override
    public List<Client> getClientByLastName(String lastName) throws ClientNotFoundException {
        return clientsService.getClientByLastName(lastName);

    }

    @Override
    public List<Movie> getMovieByTitle(String title) throws MovieNotFoundException {
        return moviesService.getMovieByTitle(title);
    }

    @Override
    public MovieBySerialData getMovieBySerialNumber(String serialNumber) throws MovieNotFoundException {
        return moviesService.getMovieBySerialNumber(serialNumber);
    }

    @Override
    public List<Movie> getMoviesBySerialNumber(String serialNumber) throws MovieNotFoundException {
        return moviesService.getMoviesBySerialNumber(serialNumber);
    }
}
