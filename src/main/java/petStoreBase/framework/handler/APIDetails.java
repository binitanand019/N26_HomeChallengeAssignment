package petStoreBase.framework.handler;

public class APIDetails {
    public String APIName;
    public String APIPath;
    public String RequestMethod;
    public String PayloadRequired;
    public String QueryParamsRequired;
    public String PayloadType;
    public String ServiceName;
    public String PayloadBody;
    public String Accept;

    public APIDetails(String apiname, String apipath, String requestMethod,
                      String payloadRequired, String queryParamsRequired, String payloadtype, String servicename, String accept){
        APIName = apiname;
        APIPath = apipath;
        RequestMethod = requestMethod;
        PayloadRequired = payloadRequired;
        QueryParamsRequired = queryParamsRequired;
        PayloadType = payloadtype;
        ServiceName = servicename;
        Accept = accept;
    }
}
