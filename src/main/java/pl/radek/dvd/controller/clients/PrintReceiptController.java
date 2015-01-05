package pl.radek.dvd.controller.clients;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ReceiptPdf;
import pl.radek.dvd.service.clients.ClientFacade;

@Controller
@RequestMapping("/emp/clients/printreceipt")
public class PrintReceiptController {
    private static Logger logger = Logger.getLogger(PrintReceiptController.class);

    @Autowired
    private ClientFacade clientFacade;

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@RequestParam(value = "registryid", required = false) String registryid,
                                      @RequestParam(value = "clientid", required = false) String clientid) throws Exception {
        ModelAndView modelAndView;
        logger.info("Getting PDF details for client with id=" + clientid + " and registry_id=" + registryid);

        // get required informations for receipt pdf
        ReceiptPdf receiptInfo = clientFacade.getReceiptPdfInformations(Integer.parseInt(registryid));
        ClientData clientInfo = clientFacade.getClient(Integer.parseInt(clientid));

        // follow to pdf view
        logger.info("Follow to pdf view");
        modelAndView = new ModelAndView("pdfView");
        modelAndView.addObject("receiptInfo", receiptInfo);
        modelAndView.addObject("clientInfo", clientInfo);

        return modelAndView;
    }
}
