package pl.radek.dvd.dto.raports;


import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.PaginatedRaportList;

import java.util.List;

public class PaginatedListIncomePromotion implements PaginatedRaportList<IncomePromotionDTO> {
    private List<IncomePromotionDTO> dtos;
    private int noOfRecords;
    private int totalRecords;

    @Override
    public List<IncomePromotionDTO> getDataList() {
        return dtos;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public int getTotalRecords() {
        return totalRecords;
    }

    public void setDtos(List<IncomePromotionDTO> dtos) {
        this.dtos = dtos;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
