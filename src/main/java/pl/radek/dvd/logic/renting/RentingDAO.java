package pl.radek.dvd.logic.renting;

import pl.radek.dvd.model.*;

import java.util.Date;

public interface RentingDAO {
    int saveRentingRegistry(Employee employee, Client client, MovieCopy movieCopy, Date returnDate, Receipt receipt);

    void addRentingRegistry(RentingRegistry rentingRegistry);

    void updateRentingRegistry(RentingRegistry rentingRegistry);

    RentingRegistry getRentingRegistry(int registryId);
}
