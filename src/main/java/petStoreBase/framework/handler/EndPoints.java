package petStoreBase.framework.handler;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "endpoints")
public class EndPoints
{
    private List<EndPoint> endpoint;

    public List<EndPoint> getEndpoint() {
        return endpoint;
    }

    @XmlElement()
    public void setEndpoint(List<EndPoint> endpoint) {
        this.endpoint = endpoint;
    }
}
