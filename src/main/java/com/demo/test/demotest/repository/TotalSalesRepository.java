package com.demo.test.demotest.repository;

import com.demo.test.demotest.dto.response.TotalSalesResponse;
import com.demo.test.demotest.model.TotalSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TotalSalesRepository extends JpaRepository<TotalSales, Integer> {

    @Query(nativeQuery = true)
    List<TotalSalesResponse> findTotalSalesByUserAndYear();
}
