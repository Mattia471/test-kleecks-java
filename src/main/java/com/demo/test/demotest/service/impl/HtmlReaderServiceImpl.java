package com.demo.test.demotest.service.impl;

import com.demo.test.demotest.model.ListActionsHtml;
import com.demo.test.demotest.service.HtmlReaderService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class HtmlReaderServiceImpl implements HtmlReaderService {

    @Override
    public void htmlCompare() throws IOException {
        File pagina1 = new File("src/main/java/com/demo/test/demotest/mock/html/pagina1.html");
        File pagina2 = new File("src/main/java/com/demo/test/demotest/mock/html/pagina2.html");
        Scanner scanner1 = new Scanner(pagina1);
        Scanner scanner2 = new Scanner(pagina2);
        //crea un oggetto di tipo ListActionsHtml
        ArrayList<ListActionsHtml> listActionsHtmlList = new ArrayList<>();
        int rowIndex = 0;
        while (scanner1.hasNextLine() || scanner2.hasNextLine()) {
            ListActionsHtml listActionsHtml = new ListActionsHtml();
            String line1 = scanner1.hasNextLine() ? scanner1.nextLine() : "";
            String line2 = scanner2.hasNextLine() ? scanner2.nextLine() : "";
            //se le righe sono diverse inserisco l'azione REMOVE e INSERT
            if (!line1.equals(line2)) {
                listActionsHtml.setAction("REMOVE");
                listActionsHtml.setHtml(line1);
                listActionsHtml.setRowPage(rowIndex);
                listActionsHtmlList.add(listActionsHtml);
                listActionsHtml = new ListActionsHtml();
                listActionsHtml.setAction("INSERT");
                listActionsHtml.setHtml(line2);
                listActionsHtml.setRowPage(rowIndex);
                listActionsHtmlList.add(listActionsHtml);
            }
            rowIndex++;
        }
        runActions(listActionsHtmlList, pagina1);
    }

    @Override
    public void runActions(ArrayList<ListActionsHtml> listAction, File page1) {
        String newPage = "";
        File newFile = page1;
        //itera la lista di azioni
        for (ListActionsHtml actionsHtml : listAction) {
            //switch per le azioni
            switch (actionsHtml.getAction()) {
                case "REMOVE":
                    try {
                        newFile = remove(newFile, actionsHtml.getHtml());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "INSERT":
                    try {
                        newFile = insert(newFile, actionsHtml.getHtml(), actionsHtml.getRowPage());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }


    @Override
    public File remove(File page1, String oldHtml) throws FileNotFoundException {
        String newPage = "";
        Scanner scanner1 = new Scanner(page1);
        while (scanner1.hasNextLine()) {
            String line1 = scanner1.hasNextLine() ? scanner1.nextLine() : "";
            if (line1.equals(oldHtml)) {
                line1 = line1.replace(oldHtml, "\n");
                newPage = newPage.concat(line1);
            } else {
                newPage = newPage.concat(line1 + "\n");
            }
        }
        return writeFile(newPage);
    }

    @Override
    public File insert(File page1, String newHtml, Integer rowPage) throws FileNotFoundException {
        String newPage = "";
        Scanner scanner1 = new Scanner(page1);
        Integer rowIndex = 0;
        while (scanner1.hasNextLine()) {
            String line1 = scanner1.hasNextLine() ? scanner1.nextLine() : "";
            if (rowIndex == rowPage) {
                line1 = line1.concat(" " + newHtml);
            }
            newPage = newPage.concat(line1 + "\n");

            rowIndex++;
        }
        return writeFile(newPage);
    }

    @Override
    public void move(String file, String newHtml) {

    }

    @Override
    public File writeFile(String newPage) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/java/com/demo/test/demotest/mock/html/risultato.html");
            fileWriter.write(newPage);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File newFile = new File("src/main/java/com/demo/test/demotest/mock/html/risultato.html");
        return newFile;
    }
}
