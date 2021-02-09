package petStoreBase.framework.handler;

public class Paths {

    String urlencodedpayloads;
    String jsonpayloads;
    String xmlpayloads;

    public Paths()
    {

    }

    public Paths(String _jsonpayloads,String _xmlpayloads, String _urlencodedpayloads)
    {
        setJsonpayloads(_jsonpayloads);
        setUrlencodedpayloads(_urlencodedpayloads);
        setXmlpayloads(_xmlpayloads);
    }

    public String getJsonpayloads() {
        return jsonpayloads;
    }

    public String getUrlencodedpayloads() {
        return urlencodedpayloads;
    }

    public String getXmlpayloads() {
        return xmlpayloads;
    }

    public void setJsonpayloads(String jsonpayloads) {
        this.jsonpayloads = jsonpayloads;
    }

    public void setUrlencodedpayloads(String urlencodedpayloads) {
        this.urlencodedpayloads = urlencodedpayloads;
    }

    public void setXmlpayloads(String xmlpayloads) {
        this.xmlpayloads = xmlpayloads;
    }
}
