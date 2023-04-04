package com.demo.test.demotest.controller;

import com.demo.test.demotest.dto.request.TotalSalesRequest;
import com.demo.test.demotest.dto.response.TotalSalesResponse;
import com.demo.test.demotest.service.JsonFormatterService;
import com.demo.test.demotest.service.TotalSalesService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class MainController {

    @Autowired
    JsonFormatterService jsonFormatterService;

    @Autowired
    TotalSalesService totalSalesService;

    @GetMapping(value = "json", produces = "application/json")
    public ResponseEntity<?> getJson() {
        JSONObject jsonObject = jsonFormatterService.getNewJson();
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping(value = "total-sales", produces = "application/json")
    public ResponseEntity<List<TotalSalesResponse>> getTotalSales() {
        return new ResponseEntity<>(totalSalesService.getAllTotalSales(), HttpStatus.OK);
    }

    @PostMapping(value = "save", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody List<TotalSalesRequest> totalSalesRequestList) {
        totalSalesService.saveAllRows(totalSalesRequestList);
        return ResponseEntity.ok("DATI INSERITI");
    }


}
