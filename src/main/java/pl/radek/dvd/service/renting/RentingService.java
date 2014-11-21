package pl.radek.dvd.service.renting;

import pl.radek.dvd.dto.rr.NewRentDto;

/**
 * Created by Sola on 2014-11-19.
 */
public interface RentingService {
    int addRentingRegistry(NewRentDto rentDto);
}
