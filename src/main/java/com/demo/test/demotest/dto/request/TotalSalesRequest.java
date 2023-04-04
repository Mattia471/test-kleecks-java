package com.demo.test.demotest.dto.request;

import lombok.Data;

@Data
public class TotalSalesRequest {
    private int id;
    private String user;
    private Long year;
    private Long total;
}