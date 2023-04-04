package com.demo.test.demotest.repository;

import com.demo.test.demotest.model.TotalSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TotalSalesRepository extends JpaRepository<TotalSales, Integer>{

    @Query(value = "SELECT id,user, SUM(total) as total, year, (SUM(total) - (SELECT CASE WHEN SUM(total)>0 THEN SUM(total) ELSE 1 END as totalePrecedente FROM total_sales tsj where tsj.user=ts.user and tsj.year=ts.year-1))/(SELECT CASE WHEN SUM(total)>0 THEN SUM(total) ELSE 1 END as annualReturn FROM total_sales tsj where tsj.user=ts.user and tsj.year=ts.year-1) *100 FROM total_sales ts group by user,year", nativeQuery = true)
    List<TotalSales> findAll();
}
