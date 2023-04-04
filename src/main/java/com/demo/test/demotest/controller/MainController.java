package com.demo.test.demotest.controller;

import com.demo.test.demotest.service.JsonFormatterService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class MainController {

    @Autowired
    JsonFormatterService jsonFormatterService;

    @GetMapping(value = "json", produces = "application/json")
    public ResponseEntity<?> getJson() {
        JSONObject jsonObject = jsonFormatterService.getNewJson();
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
