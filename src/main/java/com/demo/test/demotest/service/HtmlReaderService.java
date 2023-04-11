package com.demo.test.demotest.service;

import com.demo.test.demotest.model.ListActionsHtml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface HtmlReaderService {
    void htmlCompare() throws IOException;

    void runActions(ArrayList<ListActionsHtml> listAction, File page1) throws FileNotFoundException;

    String remove(File page1, String oldHtml) throws FileNotFoundException;

    String insert(File page1, String newHtml, Integer rowPage) throws FileNotFoundException;

    void move(String file, String newHtml);
}
