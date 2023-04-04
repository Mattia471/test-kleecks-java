package com.demo.test.demotest.service;

import com.demo.test.demotest.dto.request.TotalSalesRequest;
import com.demo.test.demotest.dto.response.TotalSalesResponse;

import java.util.List;

public interface TotalSalesService {
    List<TotalSalesResponse> getAllTotalSales();
    void saveAllRows(List<TotalSalesRequest> totalSalesRequestList);
}
