package com.demo.test.demotest.service.impl;

import com.demo.test.demotest.service.JsonFormatterService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@Service
public class JsonFormatterServiceImpl implements JsonFormatterService {
    @Override
    public JSONObject getJsonFromFile(FileReader fileReader) {
        //creo il json parser
        JSONParser jsonParser = new JSONParser();
        //creo il json object
        JSONObject jsonObject = new JSONObject();
        //leggo il file
        try {
            Object obj = jsonParser.parse(fileReader);
            jsonObject = (JSONObject) obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //ritorno il json object
        return jsonObject;
    }

    @Override
    public JSONObject getNewJson() {
        //creo il json di risposta
        JSONObject jsonResponse = new JSONObject();
        //creo la lista di chiavi
        List<String> list = Arrays.asList("HTTP_302", "HTTP_NO", "MIME_ALL", "abcde");
        try {
            JSONObject jsonObject = getJsonFromFile(new FileReader("src/main/java/com/demo/test/demotest/mock/request.json"));
            //creo i json array e li inizializzo
            JSONArray http302 = new JSONArray();
            JSONArray httpNo = new JSONArray();
            JSONArray mimeAll = new JSONArray();
            //creo la classe di riferimento per i json object
            var ref = new Object() {
                JSONObject http302Obj;
                JSONObject httpNoObj;
                JSONObject mimeAllObj;
            };
            //scorro il json object
            for (Object keyObj : jsonObject.keySet()) {
                //creo i json object e li inizializzo
                ref.mimeAllObj = new JSONObject();
                ref.httpNoObj = new JSONObject();
                ref.http302Obj = new JSONObject();
                //aggiungo crawlId in tutti e tre gli array
                ref.http302Obj.put("crawlId", keyObj);
                ref.httpNoObj.put("crawlId", keyObj);
                ref.mimeAllObj.put("crawlId", keyObj);

                //aggiungo gli array nei json object
                http302.add(ref.http302Obj);
                httpNo.add(ref.httpNoObj);
                mimeAll.add(ref.mimeAllObj);

                //scorro il json object interno
                JSONObject subObj = (JSONObject) jsonObject.get(keyObj);
                //sotto chiave ALL ottengo il suo contenuto
                JSONObject subValueObj = (JSONObject) subObj.get("ALL");
                subValueObj.forEach((k, v) -> {
                    //scorro la lista di chiavi
                    list.forEach(s -> {
                        //verifico se la chiave del json object interno contiene una delle chiavi della lista
                        if (k.toString().contains(s)) {
                            if (k.equals("INT-HTTP_302")) {
                                ref.http302Obj.put("totalInt", v);
                            } else if (k.equals("INT-HTTP_NO")) {
                                ref.httpNoObj.put("totalInt", v);
                            } else if (k.equals("INT-MIME_ALL")) {
                                ref.mimeAllObj.put("totalInt", v);
                            } else if (k.equals("EXT-HTTP_302")) {
                                ref.http302Obj.put("totalExt", v);
                            } else if (k.equals("EXT-HTTP_NO")) {
                                ref.httpNoObj.put("totalExt", v);
                            } else if (k.equals("EXT-MIME_ALL")) {
                                ref.mimeAllObj.put("totalExt", v);
                            } else if (k.equals("HTTP_NO")) {
                                ref.httpNoObj.put("total", v);
                            } else if (k.equals("MIME_ALL")) {
                                ref.mimeAllObj.put("total", v);
                            } else if (k.equals("HTTP_302")) {
                                ref.http302Obj.put("total", v);
                            }
                        }
                    });
                });
            }
            //aggiungo gli array al json di risposta
            jsonResponse.put("HTTP_302", http302);
            jsonResponse.put("HTTP_NO", httpNo);
            jsonResponse.put("MIME_ALL", mimeAll);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonResponse;
    }

}
