package petStoreBase.framework.handler;

public class APIData {
    public String Webservice;
    public String Core;
    public String Baseurl;
    public String AuthRequired;
    public String UserID;
    public String SecurityToken;
    public String Password;
    public String APIName;
    public String APIPath;
    public String RequestMethod;
    public String PayloadRequired;
    public String QueryParamsRequired;
    public String PayloadBody;
    public String PayloadType;
    public String ServiceName;
    public String Accept;

    APIDetails apidetails;
    public String CompleteURL;

    WebServiceDetails webservicedata;

    public APIData(String ServiceName, String apiname, Environment Env){


        MapWebservicedetails(ServiceName, apiname, Env);
        MapAPIDetails(ServiceName, apiname,Env);

    }

    private void MapAPIDetails(APIDetails apidata){
        APIDetails apiinfo = new APIDetails(apidata.APIName, apidata.APIPath, apidata.RequestMethod,  apidata.PayloadRequired,apidata.QueryParamsRequired, apidata.PayloadType, apidata.ServiceName, apidata.Accept);
        apidetails = apiinfo;
    }


    private void MapAPIDetails(String serviceName, String apiname, Environment Env){

        Api apidetail = Env.getServices().getApis().GetAPIDetails(serviceName, apiname);
        APIName = apidetail.getApiname();
        APIPath = apidetail.getPath();
        RequestMethod = apidetail.getRequestmethod();
        QueryParamsRequired = apidetail.getQueryparamsrequired();
        PayloadRequired = apidetail.getPayloadrequired();
        PayloadType = apidetail.getPayloadtype();
        ServiceName = apidetail.getServicename();
        Accept = apidetail.getAccept();
        APIDetails apiinfo = new APIDetails(APIName, APIPath, RequestMethod,  PayloadRequired, QueryParamsRequired, PayloadType, ServiceName, Accept);
        apidetails = apiinfo;
    }


    private void MapWebservicedetails(WebServiceDetails wsDetails){
        webservicedata = wsDetails;

    }

    private void MapWebservicedetails(String ServiceName, String apiname, Environment Env){
        webservicedata  = new WebServiceDetails(ServiceName, Env);
        Webservice = webservicedata.Webservice;
        //System.out.println("Webservice = " + Webservice);
        Core = webservicedata.Core;
        Baseurl = webservicedata.Baseurl;
        AuthRequired = webservicedata.AuthRequired;
        UserID  = webservicedata.UserID;
        SecurityToken  = webservicedata.SecurityToken;
        Password  = webservicedata.Password;
    }

    public APIData(WebServiceDetails webServiceDetails, APIDetails apidtails){
        MapWebservicedetails(webServiceDetails);
        MapAPIDetails(apidtails);
    }



    public void GetAPIDetails(String ServiceName, String apiname, Environment Env){
        Webservice = webservicedata.Webservice;
        Core = webservicedata.Core;
        Baseurl = webservicedata.Baseurl;
        AuthRequired = webservicedata.AuthRequired;
        UserID  = webservicedata.UserID;
        SecurityToken  = webservicedata.SecurityToken;
        Password  = webservicedata.Password;

        Api apidetail = Env.getServices().getApis().GetAPIDetails(ServiceName, apiname);

        APIName = apidetail.getApiname();
        APIPath = apidetail.getPath();
        RequestMethod = apidetail.getRequestmethod();
        QueryParamsRequired = apidetail.getQueryparamsrequired();
        PayloadRequired = apidetail.getPayloadrequired();
        PayloadType = apidetail.getPayloadtype();
        ServiceName = apidetail.getServicename();
        CompleteURL = createpath();


    }



    @SuppressWarnings("Since15")
    private String createpath() {
        String filepath = Baseurl+"/"+APIPath;
        return String.valueOf(filepath);
    }
}

