package com.demo.test.demotest.dto.response;

import lombok.Data;

@Data
public class TotalSalesResponse {
    private int id;
    private String user;
    private Long year;
    private Long total;
    private Long annualReturn;
}
