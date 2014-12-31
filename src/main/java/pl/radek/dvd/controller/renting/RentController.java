package pl.radek.dvd.controller.renting;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ReceiptPdf;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.rr.NewRentDto;
import pl.radek.dvd.dto.rr.RentData;
import pl.radek.dvd.exceptions.movie.MovieCopyNotAvailableException;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.service.renting.RentingFacade;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/emp/rent")
public class RentController {
    private static Logger logger = Logger.getLogger(RentController.class);

    @Autowired
    RentingFacade rentingFacade;

    public void setRentingFacade(RentingFacade rentingFacade) {
        this.rentingFacade = rentingFacade;
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public String handleRequest(@RequestParam(value = "clientId") int clientId,
                                @RequestParam(value = "movieCopyId") int movieCopyId,
                                ModelMap modelMap) throws Exception {

        ClientData clientData = rentingFacade.getClient(clientId);
        RentData rentData = rentingFacade.getMovieRentData(movieCopyId, (short) 1);

        modelMap.addAttribute("clientId", clientId);
        modelMap.addAttribute("movieCopyId", movieCopyId);
        modelMap.addAttribute(Constants.CLIENT, clientData);
        modelMap.addAttribute("rentData", rentData);

        return "/movies/rent";
    }

   /* @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveRent(@RequestParam(value = "clientId") int clientId,
                           @RequestParam(value = "movieCopyId") int movieCopyId,
                           @RequestParam(value = "promotionDaysNumber") short promotionDaysNumber,
                           @RequestParam(value = "price") BigDecimal price,
                           ModelMap modelMap, Principal principal) throws Exception {

        // employee get
        String employeeEmail = principal.getName();

        // create renting registry, create receipt - set this receipt to this renting registry
        NewRentDto newRentDto = new NewRentDto(promotionDaysNumber, clientId, movieCopyId, employeeEmail, price);
        int registryId;
        try {
            registryId = rentingFacade.addRentingRegistry(newRentDto);
            logger.info("registry Id = " + registryId);
        } catch (MovieCopyNotAvailableException e) {
            // forward to remind.jsp with msg: employee not found
            modelMap.addAttribute("msg", e.getMessage());
            return "/movies/rent";
        }

        // go to print receipt view
        ClientData clientData = rentingFacade.getClient(clientId);
        ReceiptPdf receiptPdfInformations = rentingFacade.getReceiptPdfInformations(registryId);

        modelMap.addAttribute("receiptInfo", receiptPdfInformations);
        modelMap.addAttribute("clientInfo", clientData);

        return "pdfView";
    }*/

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    Integer saveMovieRent(@RequestParam(value = "clientId") int clientId,
                          @RequestParam(value = "movieCopyId") int movieCopyId,
                          @RequestParam(value = "promotionDaysNumber") short promotionDaysNumber,
                          @RequestParam(value = "price") BigDecimal price,
                          Principal principal) {

        // employee get
        String employeeEmail = principal.getName();

        // create renting registry, create receipt - set this receipt to this renting registry
        NewRentDto newRentDto = new NewRentDto(promotionDaysNumber, clientId, movieCopyId, employeeEmail, price);
        int registryId;
        try {
            registryId = rentingFacade.addRentingRegistry(newRentDto);
            logger.info("registry Id = " + registryId);
        } catch (MovieCopyNotAvailableException e) {
            // forward with msg: MovieCopyNotAvailableException
            return -1;
        }

        return registryId;
    }

    @RequestMapping(value = "/printReceipt", method = RequestMethod.POST)
    public String printReceipt(@RequestParam(value = "clientId") int clientId,
                               @RequestParam(value = "registryId") int registryId,
                               ModelMap modelMap) {

        // go to print receipt view
        ClientData clientData = rentingFacade.getClient(clientId);
        ReceiptPdf receiptPdfInformations = rentingFacade.getReceiptPdfInformations(registryId);

        modelMap.addAttribute("receiptInfo", receiptPdfInformations);
        modelMap.addAttribute("clientInfo", clientData);

        return "pdfView";
    }

}
