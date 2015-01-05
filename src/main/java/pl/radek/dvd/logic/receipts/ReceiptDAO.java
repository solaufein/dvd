package pl.radek.dvd.logic.receipts;

import pl.radek.dvd.model.Receipt;

public interface ReceiptDAO {
    public void deleteReceipt(int id);
    public void addReceipt(Receipt receipt);
    public void updateReceipt(Receipt receipt);
}
