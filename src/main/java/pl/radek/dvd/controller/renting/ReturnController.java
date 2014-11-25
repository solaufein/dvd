package pl.radek.dvd.controller.renting;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ReceiptPdf;
import pl.radek.dvd.dto.rr.RentData;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.renting.RentingFacade;

@Controller
@RequestMapping("/emp/return")
public class ReturnController {
    private static Logger logger = Logger.getLogger(ReturnController.class);

    @Autowired
    RentingFacade rentingFacade;

    public void setRentingFacade(RentingFacade rentingFacade) {
        this.rentingFacade = rentingFacade;
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public String returnForm(@RequestParam(value = "clientId") int clientId,
                             @RequestParam(value = "movieCopyId") int movieCopyId,
                             @RequestParam(value = "registryId") int registryId,
                             ModelMap modelMap) throws Exception {

        logger.info("client id = " + clientId);
        logger.info("movie copy id = " + movieCopyId);
        logger.info("registry id = " + registryId);

        ClientData clientData = rentingFacade.getClient(clientId);
        RentData rentData = rentingFacade.getMovieRentData(movieCopyId, (short) 0);
        ReturnCommentDto returnDto = new ReturnCommentDto();
        returnDto.setClientId(clientId);
        returnDto.setMovieCopyId(movieCopyId);
        returnDto.setRegistryId(registryId);

        //  MovieCopyDTO movieCopy = rentingFacade.getMovieCopy(movieCopyId);
        //  modelMap.addAttribute("movieCopy", movieCopy);

        modelMap.addAttribute("movieCopyId", movieCopyId);
        modelMap.addAttribute("clientId", clientId);
        modelMap.addAttribute("registryId", registryId);
        modelMap.addAttribute(Constants.CLIENT, clientData);
        modelMap.addAttribute("rentData", rentData);
        modelMap.addAttribute("returnDto", returnDto);

        return "/movies/return";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveReturn(@RequestParam(value = "movieCopyId") int movieCopyId,
                             @RequestParam(value = "registryId") int registryId,
                             @RequestParam(value = "clientId") int clientId,
                             @ModelAttribute("returnDto") ReturnCommentDto returnDto,
                             ModelMap modelMap) throws Exception {

        //todo: czy potrzebne @RequestParams: movieCopyId, registryId, clientId ?????? sprawdzic

        logger.info("comment = " + returnDto.getComment());
        logger.info("client id = " + returnDto.getClientId());
        logger.info("mc id = " + returnDto.getMovieCopyId());
        logger.info("rr id = " + returnDto.getRegistryId());


        // update renting registry - return:
        // set movie copy avail = 1
        // set comments in renting registry
        // set pay date in receipt
        // set price in receipt
        rentingFacade.updateRentingRegistry(returnDto);

        // go to print receipt view
        ClientData clientData = rentingFacade.getClient(clientId);
        ReceiptPdf receiptPdfInformations = rentingFacade.getReceiptPdfInformations(registryId);

        modelMap.addAttribute("receiptInfo", receiptPdfInformations);
        modelMap.addAttribute("clientInfo", clientData);

        return "pdfView";
    }
}
