package petStoreBase.framework.handler;

public class WebServiceDetails {
    public String Webservice;
    public String Core;
    public String Baseurl;
    public String AuthRequired;
    public String UserID;
    public String SecurityToken;
    public String Password;

    public WebServiceDetails(String ServiceName, Environment Env){
        GetWebServiceDetails(ServiceName, Env);
    }
    private void GetWebServiceDetails(String servicename, Environment Env){
        Webservice = Env.getServices().GetServiceDetails(servicename).getServicename();
        Core = Env.getServices().GetServiceDetails(servicename).getCore();
        Baseurl = Env.getServices().GetServiceDetails(servicename).getBaseurl();
        AuthRequired = (Env.getServices().GetServiceDetails(servicename).getAuthenticationrequired().trim().toLowerCase());
        UserID  = Env.getServices().GetServiceDetails(servicename).getUserid();
        SecurityToken  = Env.getServices().GetServiceDetails(servicename).getSecuritytoken();
        Password  = Env.getServices().GetServiceDetails(servicename).getPassword();
    }
}
