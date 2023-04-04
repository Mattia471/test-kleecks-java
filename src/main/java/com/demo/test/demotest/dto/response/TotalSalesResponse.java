package com.demo.test.demotest.dto.response;

public class TotalSalesResponse {
    private int id;
    private String user;
    private Long year;
    private Long total;
    private Long annualReturn;

    public TotalSalesResponse(int id, String user, Long year, Long total, Long annualReturn) {
        this.id = id;
        this.user = user;
        this.year = year;
        this.total = total;
        this.annualReturn = annualReturn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getAnnualReturn() {
        return annualReturn;
    }

    public void setAnnualReturn(Long annualReturn) {
        this.annualReturn = annualReturn;
    }
}
