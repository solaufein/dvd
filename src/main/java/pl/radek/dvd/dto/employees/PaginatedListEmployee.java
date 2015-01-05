package pl.radek.dvd.dto.employees;

import pl.radek.dvd.dto.PaginatedList;

import java.util.List;

public class PaginatedListEmployee implements PaginatedList<EmployeeData> {
    private List<EmployeeData> employeeDataList;
    private int noOfRecords;

    public void setEmployeeDataList(List<EmployeeData> employeeDataList) {
        this.employeeDataList = employeeDataList;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    @Override
    public List<EmployeeData> getDataList() {
        return employeeDataList;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }
}
