package com.demo.test.demotest.service.impl;

import com.demo.test.demotest.service.HtmlReaderService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Service
public class HtmlReaderServiceImpl implements HtmlReaderService {

    @Override
    public void htmlCompare() throws IOException {
        File pagina1 = new File("src/main/java/com/demo/test/demotest/mock/html/pagina1.html");
        File pagina2 = new File("src/main/java/com/demo/test/demotest/mock/html/pagina2.html");
        Scanner scanner1 = new Scanner(pagina1);
        Scanner scanner2 = new Scanner(pagina2);
        String filesDiff = "";
        while (scanner1.hasNextLine() || scanner2.hasNextLine()) {
            String line1 = scanner1.hasNextLine() ? scanner1.nextLine() : "";
            String line2 = scanner2.hasNextLine() ? scanner2.nextLine() : "";
            if (line1.equals(line2) && !line1.isEmpty()) {
                filesDiff = filesDiff.concat(line1 + "\n");
            }else{
                filesDiff = filesDiff.concat(line2 + "\n");
            }
        }
        FileWriter fileWriter = new FileWriter("src/main/java/com/demo/test/demotest/mock/html/risultato.html");
        fileWriter.write(filesDiff);
        fileWriter.close();
    }

    @Override
    public void remove() {

    }

    @Override
    public void insert() {

    }

    @Override
    public void move() {

    }
}
