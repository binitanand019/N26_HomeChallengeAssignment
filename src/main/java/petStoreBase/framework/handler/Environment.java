package petStoreBase.framework.handler;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Environment")
public class Environment
{
    private Framework framework;
    private String name;
    private int urls;
    private Services services;
    private WebSocket webSocket;

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setUrls(int urls) {
        this.urls = urls;
    }

    public int getUrls() {
        return urls;
    }

    @XmlElement
    public void setFramework(Framework framework) {
        this.framework = framework;
    }

    public Framework getFramework() {
        return framework;
    }

    @XmlElement()
    public void setServices(Services services) {
        this.services = services;
    }

    public Services getServices() {
        return services;
    }


    public WebSocket getWebSocket() {
        return webSocket;
    }

    @XmlElement(name="websocket")
    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }
}


