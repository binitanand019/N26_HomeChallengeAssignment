package petStoreBase.framework.helper;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import java.io.IOException;

public class JsonHelper {

    /**
     * Convert JavaObject to JSON
     *
     * @param className
     * @return String
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     */
    public String getObjectToJSON(Object className) throws IOException {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        json = mapper.writeValueAsString(className);
        return json;

    }

    public Object getObjectFromJson(String json, String className) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Class ResponseClass = Class.forName(className);
            Object responseObject = ResponseClass.newInstance();
            responseObject = mapper.readValue(json, responseObject.getClass());
            return responseObject;

        } catch (ClassNotFoundException cnfe) {
            System.out.println("JSON to POJO Mapping Error:  Unable to find Class " + className);
            System.out.println("Error :" + cnfe.getMessage());
            return null;
        } catch (InstantiationException ie) {
            System.out.println("JSON to POJO Mapping Error:  Unable to Intantiate Class " + className);
            System.out.println("Error :" + ie.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("JSON to POJO Mapping Error:   " + e.getMessage());
            return null;
        }

    }
}
