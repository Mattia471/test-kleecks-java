package com.demo.test.demotest.service.impl;

import com.demo.test.demotest.dto.request.TotalSalesRequest;
import com.demo.test.demotest.dto.response.TotalSalesResponse;
import com.demo.test.demotest.model.TotalSales;
import com.demo.test.demotest.repository.TotalSalesRepository;
import com.demo.test.demotest.service.TotalSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TotalSalesServiceImpl implements TotalSalesService {

    @Autowired
    TotalSalesRepository totalSalesRepository;

    @Override
    public List<TotalSalesResponse> getAllTotalSales() {
        List<TotalSales> totalSales = totalSalesRepository.findAll();
        List<TotalSalesResponse> totalSalesResponses = new ArrayList<>();
        for (TotalSales totalSale : totalSales) {
            TotalSalesResponse totalSalesResponse = new TotalSalesResponse();
            totalSalesResponse.setId(totalSale.getId());
            totalSalesResponse.setTotal(totalSale.getTotal());
            totalSalesResponse.setUser(totalSale.getUser());
            totalSalesResponse.setYear(totalSale.getYear());
            totalSalesResponses.add(totalSalesResponse);
        }
        return totalSalesResponses;
    }

    @Override
    public void saveAllRows(List<TotalSalesRequest> totalSalesRequestList) {
        for (TotalSalesRequest totalSalesRequest : totalSalesRequestList) {
            TotalSales totalSales = new TotalSales();
            totalSales.setId(totalSalesRequest.getId());
            totalSales.setTotal(totalSalesRequest.getTotal());
            totalSales.setUser(totalSalesRequest.getUser());
            totalSales.setYear(totalSalesRequest.getYear());
            totalSalesRepository.save(totalSales);
        }
    }
}
