package petStoreBase.framework.handler;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EndPoint {

    private String  core, servicename,baseurl,authenticationrequired,userid,password,securitytoken,stafservicename;

    public String getStafservicename() {
        return stafservicename;
    }

    @XmlAttribute
    public void setStafservicename(String stafservicename) {
        this.stafservicename = stafservicename;
    }

    private int port;

    public EndPoint()
    {

    }
    @XmlAttribute
    public void setCore(String core) {
        this.core = core;
    }

    public String getCore() {
        return core;
    }

    @XmlAttribute
    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    @XmlAttribute
    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getServicename() {
        return servicename;
    }
    @XmlAttribute
    public void setAuthenticationrequired(String authenticationrequired) {
        this.authenticationrequired = authenticationrequired;
    }
    @XmlAttribute
    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }
    @XmlAttribute
    public void setPassword(String password) {
        this.password = password;
    }
    @XmlAttribute
    public void setSecuritytoken(String securitytoken) {
        this.securitytoken = securitytoken;
    }
    @XmlAttribute
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAuthenticationrequired() {
        return authenticationrequired;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public String getPassword() {
        return password;
    }

    public String getSecuritytoken() {
        return securitytoken;
    }

    public String getUserid() {
        return userid;
    }

    public List<Api> GetAPIs(String APIName){

        return GetAllAPIs("pgresponse");
    }

    private List<Api> GetAllAPIs(String ServiceName){
        List<Api> apis = new ArrayList<Api>();
        Iterator<Api> itr = apis.iterator();
        while (itr.hasNext()){
            Api apidetails = itr.next();
            if(apidetails.getServicename().equals(ServiceName)){

                Api apidata = apidetails;
            }
        }
        return  apis;
    }
}


