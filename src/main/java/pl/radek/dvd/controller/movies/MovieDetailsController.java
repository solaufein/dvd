package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.clients.ClientRentDto;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.movies.MoviesFacade;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-01
 * Time: 13:29
 */

@Controller
@RequestMapping("/emp/movies/moviedetails")
public class MovieDetailsController {
    private static Logger logger = Logger.getLogger(MovieDetailsController.class);

    @Autowired
    private MoviesFacade moviesFacade;

    public void setMoviesFacade(MoviesFacade moviesFacade) {
        this.moviesFacade = moviesFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handleRequest(@RequestParam(value = Constants.ID, required = false) String id,
                                @RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                ModelMap modelMap) throws Exception {

        // get id from movies_list.jsp form
        logger.info("Getting details for movie with id=" + id);

        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        int noOfPages;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }
        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);
        ListDataRequest listDataRequest = new ListDataRequest(null, null, paginationInfo);

        // movie id
        int ide = Integer.parseInt(id);

        MovieDataDTO movie = moviesFacade.getMovie(ide);
        PaginatedList<MovieCopyDTO> movieCopyDTOPaginatedList = moviesFacade.getMovieCopyPaginatedList(listDataRequest, ide);
        List<MovieCopyDTO> movieCopyDTOList = movieCopyDTOPaginatedList.getDataList();
        int noOfRecords = movieCopyDTOPaginatedList.getNoOfRecords();
        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        // follow to movie_details.jsp
        logger.info("Follow to movie_details jsp");
        modelMap.addAttribute(Constants.NO_OF_PAGES, noOfPages);
        modelMap.addAttribute(Constants.CURRENTPAGE, page);
        modelMap.addAttribute(Constants.MOVIE, movie);
        modelMap.addAttribute(Constants.MOVIEDETAILS, movieCopyDTOList);

        return "/movies/movie_details";
    }

    @RequestMapping(value = "/getClient", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ClientRentDto getClients(@RequestParam("pesel") String pesel) {

        ClientRentDto data = moviesFacade.getClient(pesel);

        logger.info("client id = " + data.getId());
        logger.info("client pesel = " + data.getPesel());

        return data;
    }

    @RequestMapping(value = "/getReturnData", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    ReturnCommentDto getReturnData(@RequestParam("movieCopyId") Integer movieCopyId) {

        ReturnCommentDto data = moviesFacade.getReturnData(movieCopyId);

        logger.info("client id = " + data.getClientId());
        logger.info("registry id = " + data.getRegistryId());

        return data;
    }
}
