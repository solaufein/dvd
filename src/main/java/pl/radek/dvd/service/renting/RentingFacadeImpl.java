package pl.radek.dvd.service.renting;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ReceiptPdf;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.rr.NewRentDto;
import pl.radek.dvd.dto.rr.RentData;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.service.clients.ClientDetailsService;
import pl.radek.dvd.service.clients.ClientsService;
import pl.radek.dvd.service.employees.EmployeeService;
import pl.radek.dvd.service.movieCopy.MovieCopyService;
import pl.radek.dvd.service.movies.MoviesService;

@Component
public class RentingFacadeImpl implements RentingFacade {
    private static Logger logger = Logger.getLogger(RentingFacadeImpl.class);

    @Autowired
    ClientsService clientsService;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    MovieCopyService movieCopyService;

    @Autowired
    MoviesService moviesService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RentingService rentingService;


    public void setRentingService(RentingService rentingService) {
        this.rentingService = rentingService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    public void setMoviesService(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    public void setMovieCopyService(MovieCopyService movieCopyService) {
        this.movieCopyService = movieCopyService;
    }

    public void setClientsService(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @Override
    public Employee getEmployee(String name) {
        return employeeService.getEmployee(name);
    }

    @Override
    public int addRentingRegistry(NewRentDto rentDto) {
        return rentingService.addRentingRegistry(rentDto);
    }

    @Override
    public ClientData getClient(int clientId) {
        return clientsService.getClient(clientId);
    }

    @Override
    public MovieCopyDTO getMovieCopy(int movieCopyId) {
        return movieCopyService.getMovieCopy(movieCopyId);
    }

    @Override
    public RentData getMovieRentData(int movieCopyId) {
        return moviesService.getMovieRentData(movieCopyId);
    }

    @Override
    public ReceiptPdf getReceiptPdfInformations(int clientId) {
        return clientDetailsService.getReceiptPdfInformations(clientId);
    }
}
