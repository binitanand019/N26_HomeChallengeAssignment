package petStoreBase.framework.handler;

import javax.xml.bind.annotation.XmlAttribute;

public class WebSocket {

    private String name;
    private String uri;


    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    @XmlAttribute
    public void setUri(String uri) {
        this.uri = uri;
    }
}
