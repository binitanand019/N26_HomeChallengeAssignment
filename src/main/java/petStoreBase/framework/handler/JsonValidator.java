package petStoreBase.framework.handler;

import petStoreBase.framework.interfaces.Validator;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class JsonValidator implements Validator {

    public String Body= null;
    public HashMap<String, String> headers = null;
    public HashMap<String, NewCookie> cookies = null;
    private ToolBox utilities = new ToolBox();
    public Response testresponse = null;

    public JsonValidator(String Payload){
        Body = Payload;

    }

    public JsonValidator(Response response){
        testresponse = response;
        Body = response.readEntity(String.class);
        System.out.println("Body = " + Body);
        headers = utilities.convertMultiToRegularMap(response.getHeaders());
        utilities.PrintHashMap(headers);
        cookies = new HashMap<String, NewCookie>(response.getCookies());
    }
    @Override
    public String GetBodyAsText(){
        return Body;
    }

    @Override
    public HashMap<String, String> GetHeaders(){
        return headers;
    }

    @Override
    public boolean DoesNodeExists(String JsonPath, String Body) {

        return true;
    }


    /**
     *
     * @param NodePath
     * NodePath (A dot seperated jsonpath ex: "data.node.success")
     * @return
     */
    @Override
    public boolean DoesNodeExists(String NodePath) {
        return utilities.Exists(NodePath,Body);
    }


    @Override
    public String GetNodeValue(String jsonpath) {
        return com.jayway.jsonpath.JsonPath.read(Body, jsonpath);
    }

    @Override
    public Boolean GetNodeValueAsBool(String jsonpath) {
        return com.jayway.jsonpath.JsonPath.read(Body, jsonpath);
    }

    @Override
    public String GetNodeValueAsStringFromJsonArray(String jsonpath) {
        return com.jayway.jsonpath.JsonPath.read(Body, jsonpath).toString();
    }

    @Override
    public JSONArray GetNodeValueAsJsonArray(String jsonpath) {
        return com.jayway.jsonpath.JsonPath.read(Body, jsonpath);
    }

    @Override
    public LinkedHashMap getNodeValueAsObject(String jsonPath) {
        return  JsonPath.parse(Body).read(jsonPath);
    }

    @Override
    public LinkedHashMap getResponseAsObject() {
        return  JsonPath.parse(Body).read("$");
    }
    @Override
    public long GetNodeValueAsLong(String jsonpath) {
        return com.jayway.jsonpath.JsonPath.read(Body, jsonpath);
    }

    @Override
    public int GetNodeValueAsInt(String jsonpath) {
        return com.jayway.jsonpath.JsonPath.read(Body, jsonpath);
    }

    @Override
    public double GetNodeValueAsDouble(String jsonpath) {
        return com.jayway.jsonpath.JsonPath.read(Body, jsonpath);
    }

    @Override
    public HashMap<String, NewCookie> GetCookies(){
        System.out.println("Printing Cookies ");
        utilities.PrintHashmap(cookies);
        return cookies;
    }

    @Override
    public void ComparewithExpectedResponse(String payload, HashMap<String, String> exclusions) {
        try {
            String actual = GetBodyAsText();
            String expected = utilities.readFileAsString("./Data/ExpectedResponses/rms_login");

            new Customization("data.access_token",new RegularExpressionValueMatcher<Object>("(.*)"));
            JSONAssert.assertEquals(expected, actual, new CustomComparator(JSONCompareMode.STRICT,
                    new Customization("data.access_token",
                            new RegularExpressionValueMatcher<Object>("(.*)")),new Customization("data.access_token",
                    new RegularExpressionValueMatcher<Object>("(.*)"))));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int GetResponseCode() {
        // TODO Auto-generated method stub
        return testresponse.getStatus();
    }
}


