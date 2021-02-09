package petStoreBase.framework.interfaces;

import net.minidev.json.JSONArray;

import javax.ws.rs.core.NewCookie;
import java.util.HashMap;
import java.util.LinkedHashMap;

public interface  Validator {

    boolean DoesNodeExists(String JsonPath, String Payload);
    boolean DoesNodeExists(String NodePath);
    String GetNodeValue(String JsonPath);
    Boolean GetNodeValueAsBool(String JsonPath);
    int GetNodeValueAsInt(String jsonpath);
    long GetNodeValueAsLong(String jsonpath);
    double GetNodeValueAsDouble(String jsonpath);
    String GetBodyAsText();
    HashMap<String, String> GetHeaders();
    HashMap<String, NewCookie> GetCookies();
    void ComparewithExpectedResponse(String Payload, HashMap<String, String> nodestoexclude);
    String GetNodeValueAsStringFromJsonArray(String jsonpath);
    JSONArray GetNodeValueAsJsonArray(String jsonpath);
    LinkedHashMap getResponseAsObject();
    LinkedHashMap getNodeValueAsObject(String jsonPath);
    int GetResponseCode();
}
