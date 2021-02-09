package petStoreBase.framework.handler;

import javax.xml.bind.annotation.XmlAttribute;

public class Api
{
    String apiname;
    String servicename;
    String name;
    String path;
    String requestmethod;
    String payloadrequired;
    String queryparamsrequired;
    String payloadtype;
    String accept;
    String stafapiname;
    String optionalstafurlpath;


    public Api(String _apiname, String _servicename,  String _name, String _path, String _requestmethod, String _payloadrequired, String _queryparamsrequired, String _payloadtype)
    {
        setServicename(_servicename);
        setApiname(_apiname);
        setName(_name);
        setPath(_path);
        setPayloadrequired(_payloadrequired);
        setPayloadtype(_payloadtype);
        setQueryparamsrequired(_queryparamsrequired);
        setRequestmethod(_requestmethod);
    }

    public Api()
    {

    }

    @XmlAttribute
    public String getAccept() { return accept; }

    public void setAccept(String accept) { this.accept = accept; }


    @XmlAttribute
    public String getOptionalstafurlpath() {
        return optionalstafurlpath;
    }

    public void setOptionalstafurlpath(String optionalstafurlpath) {
        this.optionalstafurlpath = optionalstafurlpath;
    }

    public String getStafapiname() {
        return stafapiname;
    }

    @XmlAttribute
    public void setStafapiname(String stafapiname) {
        this.stafapiname = stafapiname;
    }

    public String getServicename() {
        return servicename;
    }

    public String getApiname() {
        return apiname;
    }

    @XmlAttribute
    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    @XmlAttribute
    public void setApiname(String apiname) {
        this.apiname = apiname;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public void setPath(String path) {
        this.path = path;
    }

    @XmlAttribute
    public void setPayloadrequired(String payloadrequired) {
        this.payloadrequired = payloadrequired;
    }

    @XmlAttribute
    public void setPayloadtype(String payloadtype) {
        this.payloadtype = payloadtype;
    }

    @XmlAttribute
    public void setQueryparamsrequired(String queryparamsrequired) {
        this.queryparamsrequired = queryparamsrequired;
    }

    @XmlAttribute
    public void setRequestmethod(String requestmethod) {
        this.requestmethod = requestmethod;
    }


    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getPayloadrequired() {
        return payloadrequired;
    }

    public String getPayloadtype() {
        return payloadtype;
    }

    public String getQueryparamsrequired() {
        return queryparamsrequired;
    }

    public String getRequestmethod() {
        return requestmethod;
    }

}
