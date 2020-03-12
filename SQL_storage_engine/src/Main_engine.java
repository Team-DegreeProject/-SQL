
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.*;
import java.util.Iterator;
import java.util.Map;


public class Main_engine {
    public void write_json_file(String tableName, Map<String, String> innerData, int rowSize, String filePath) {
        String json_sentence = "{\"" + tableName + "\":[";
        int rowNum = innerData.size() / rowSize;
        Iterator<String> iter = innerData.keySet().iterator();

        for (int i = 0; i < innerData.size(); i++) {
            json_sentence = json_sentence + "{";
            String attributeName = iter.next();
            String attributeValue = innerData.get(attributeName);

            if (i % rowSize < rowSize) {
                json_sentence = json_sentence + "\"" + attributeName + "\"" + ":\"" + attributeValue + "\"";
            } else if (i % rowSize == rowSize) {
                json_sentence = json_sentence + "}";
            }
        }

        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            PrintWriter out = new PrintWriter(fw);
            out.write(json_sentence);
            out.println();
            fw.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> read_json_file(String filePath) {
        Map<String, String> resultMap = null;
        String path = Main_engine.class.getClassLoader().getResource(filePath).getPath();
        String s = ReadUtils.readJsonFile(path);
        JSONObject jobj = JSONObject.parseObject(s);
        JSONObject address1 = jobj.getJSONObject("address");
        String street = (String) address1.get("street");
        String city = (String) address1.get("city");
        String country = (String) address1.get("country");

        System.out.println("street :" + street);
        System.out.println("city :" + city);
        System.out.println("country :" + country);

        JSONArray links = jobj.getJSONArray("links");

        for (int i = 0; i < links.size(); i++) {
            JSONObject key1 = (JSONObject) links.get(i);
            String name = (String) key1.get("name");
            String url = (String) key1.get("url");
            System.out.println(name);
            System.out.println(url);
        }
        return resultMap;
    }


    public Map<String, String> getData(String filePath, String tableName, String attributeName) {
        Map<String ,String> resultmap = null;

        String path = Main_engine.class.getClassLoader().getResource(filePath).getPath();
        String s = ReadUtils.readJsonFile(path);
        JSONObject jsonobj = JSONObject.parseObject(s);
        JSONArray jsonArray = jsonobj.getJSONArray(tableName);
        for (int i = 0; i < jsonArray.size(); i++) {
            String value = jsonArray.getJSONObject(i).getString(attributeName);
            resultmap.put(attributeName,value);
        }

        return resultmap;
    }


    public void alterData(String tablename,String attributeName, String alteredValue){

    }
}
