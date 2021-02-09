package petStoreBase.framework.handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class ToolBox {


    public String readFileAsString(String filePath) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        return IOUtils.toString(reader);
    }

    public String Parameterize(String PayloadRawString, String[] StringParameters){
        Map<String, String> valuesMap = new HashMap<String, String>();
        int paramnumber = 0;
        for (String param : StringParameters) {
            valuesMap.put(Integer.toString(paramnumber), param);
            paramnumber++;
        }

        String templateString = PayloadRawString;
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        return resolvedString;
    }

    public HashMap<String, String> convertMultiToRegularMap(MultivaluedMap<String, Object> m) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (m == null) {
            return map;
        }
        for (Map.Entry<String, List<Object>> entry : m.entrySet()) {
            StringBuilder sb = new StringBuilder();
            for (Object s : entry.getValue()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(s);
            }
            map.put(entry.getKey(), sb.toString());
        }
        return map;
    }

    public void PrintHashmap(HashMap<String, NewCookie> map) {
        for (Map.Entry<String, NewCookie> entry : map.entrySet()) {

            System.out.println(entry.getKey()+" : "+entry.getValue().toString());


        }
    }

    public void PrintHashMap(HashMap<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {

            System.out.println(entry.getKey()+" : "+entry.getValue().toString());


        }
    }
    public static String decode(String url)  {
        String decodedURL = null;
        try {
            decodedURL = URLDecoder.decode( url, "UTF-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedURL;
    }

    public boolean Exists(String NodePath, String JsonString) {
        boolean result = false, temp = true;
        JsonElement jsonElement = new JsonParser().parse(JsonString);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray;
        String[] nodePathArray = NodePath.split("\\.");
        int counter = nodePathArray.length;
        for (String node : nodePathArray) {
            JsonElement element = jsonObject.get(node);
            System.out.println("Node is : " + node + " : " + counter);
            if (counter > 1) {
                if(element == null){
                    return false;
                }
                if (element.isJsonObject()) {
                    System.out.println("Identified as JsonObject");
                    jsonObject = jsonObject.getAsJsonObject(node);
                } else {
                    if (element.isJsonArray()) {
                        System.out.println("Identified as JsonArray");
                        jsonArray = jsonObject.getAsJsonArray(node);
                        for (int i = 0; i < jsonArray.size(); i++) {
                            if (counter == 2) {
                                jsonObject = jsonArray.get(i).getAsJsonObject();
                                temp = checkKeyIsPresent(jsonObject, nodePathArray[nodePathArray.length - 1]);
                                result = result && temp;
                            } else {
                                jsonObject = jsonArray.get(i).getAsJsonObject();
                            }
                        }
                    } else {
                        System.out.println("Node is neither a json array or a json object.");
                    }
                }
            } else {
                result = jsonObject.has(node);
                System.out.println("Finally checking hasnode and result is " + result);
                System.out.println("\n\n" + jsonObject.toString());
            }
            counter--;
            if (!result) {
                System.out.println("Node '" + node + "' is missing from " + NodePath);
                System.out.println(jsonObject.toString());
            }
        }
        return result;
    }

    public boolean checkKeyIsPresent(JsonObject jobject, String Key) {
        boolean result = false;
        System.out.println("Checking key " + Key);
        result = jobject.has(Key);
        return result;
    }

}


