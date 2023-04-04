package com.demo.test.demotest.service.impl;

import com.demo.test.demotest.dto.request.TotalSalesRequest;
import com.demo.test.demotest.dto.response.TotalSalesResponse;
import com.demo.test.demotest.model.TotalSales;
import com.demo.test.demotest.repository.TotalSalesRepository;
import com.demo.test.demotest.service.TotalSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalSalesServiceImpl implements TotalSalesService {

    @Autowired
    TotalSalesRepository totalSalesRepository;

    @Override
    public List<TotalSalesResponse> getAllTotalSales() {
        return totalSalesRepository.findTotalSalesByUserAndYear();
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
