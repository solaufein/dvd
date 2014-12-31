package pl.radek.dvd.controller.renting;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ReceiptPdf;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.dto.rr.ReturnData;
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
    public String returnFormClient(@RequestParam(value = "clientId") int clientId,
                                   @RequestParam(value = "movieCopyId") int movieCopyId,
                                   @RequestParam(value = "registryId") int registryId,
                                   ModelMap modelMap) throws Exception {

        ReturnData returnData = rentingFacade.getMovieReturnData(movieCopyId, (short) 0);
        ReturnCommentDto returnDto = new ReturnCommentDto();
        returnDto.setClientId(clientId);
        returnDto.setMovieCopyId(movieCopyId);
        returnDto.setRegistryId(registryId);

        modelMap.addAttribute("movieCopyId", movieCopyId);
        modelMap.addAttribute("clientId", clientId);
        modelMap.addAttribute("registryId", registryId);
        modelMap.addAttribute("returnData", returnData);
        modelMap.addAttribute("returnDto", returnDto);

        return "/movies/return";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public boolean saveReturn(@ModelAttribute("returnDto") ReturnCommentDto returnDto) throws Exception {

        boolean isLate = rentingFacade.updateRentingRegistry(returnDto);

        return isLate;
    }

    @RequestMapping(value = "/printReceipt", method = RequestMethod.POST)
    public String printReceipt(@RequestParam(value = "clientId") int clientId,
                               @RequestParam(value = "registryId") int registryId,
                               ModelMap modelMap) {

        // go to print receipt view
        ClientData clientData = rentingFacade.getClient(clientId);
        ReceiptPdf receiptPdfInformations = rentingFacade.getReceiptPdfInformationsReturnMovie(registryId);

        modelMap.addAttribute("receiptInfo", receiptPdfInformations);
        modelMap.addAttribute("clientInfo", clientData);

        return "pdfView";
    }
}
