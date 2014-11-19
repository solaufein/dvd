package pl.radek.dvd.controller.renting;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ReceiptPdf;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.rr.RentData;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.renting.RentingFacade;

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
    public String handleRequest(@RequestParam(value = "movieCopyId") int movieCopyId,
                                @RequestParam(value = "clientId") int clientId,
                                ModelMap modelMap) throws Exception {

        ClientData clientData = rentingFacade.getClient(clientId);
        RentData rentData = rentingFacade.getMovieRentData(movieCopyId);

        modelMap.addAttribute("movieCopyId", movieCopyId);
        modelMap.addAttribute("clientId", clientId);
        modelMap.addAttribute(Constants.CLIENT, clientData);
        modelMap.addAttribute("rentData", rentData);

        return "/movies/rent";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveRent(@RequestParam(value = "movieCopyId") int movieCopyId,
                           @RequestParam(value = "clientId") int clientId,
                           ModelMap modelMap) throws Exception {

        // tutaj zapisy....
        int registryId = 1;

        MovieCopyDTO movieCopy = rentingFacade.getMovieCopy(movieCopyId);

        ClientData clientData = rentingFacade.getClient(clientId);
        ReceiptPdf receiptPdfInformations = rentingFacade.getReceiptPdfInformations(registryId);

        modelMap.addAttribute("receiptInfo", receiptPdfInformations);
        modelMap.addAttribute("clientInfo", clientData);

        return "pdfView";
    }

    // 1  V  wybrac dane z bazy danych: Client, Movie, MovieCopy, Promotion oraz Employee!
    // 2     wybrac date rent i wyslac POST zapis(id client, id movieCopy, id employee, data)
    // 3     utworzyc nowe Renting Registry (client id, movieCopy id, employee id, receipt id)
    // 4     stworzyc Receipt i przypisac je do utworzonego RentingRegistry
    // 5  V   po zapisie uruchomic Controller do print Receipt (id clienta, id registry)
}
