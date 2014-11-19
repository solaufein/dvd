package pl.radek.dvd.service.renting;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RentingServiceImpl implements RentingService {
    private static Logger logger = Logger.getLogger(RentingServiceImpl.class);


}
