package pl.radek.dvd.logic.renting;

import pl.radek.dvd.model.*;

import java.util.Date;

/**
 * Created by Sola on 2014-11-19.
 */
public interface RentingDAO {
    int saveRentingRegistry(Employee employee, Client client, MovieCopy movieCopy, Date returnDate, Receipt receipt);

    void addRentingRegistry(RentingRegistry rentingRegistry);

    void updateRentingRegistry(RentingRegistry rentingRegistry);

    RentingRegistry getRentingRegistry(int registryId);
}
