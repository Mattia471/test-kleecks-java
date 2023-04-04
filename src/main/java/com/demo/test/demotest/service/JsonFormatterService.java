package com.demo.test.demotest.service;

import org.json.simple.JSONObject;

import java.io.FileReader;

public interface JsonFormatterService {
    JSONObject getJsonFromFile(FileReader fileReader);

    JSONObject getNewJson();
}
