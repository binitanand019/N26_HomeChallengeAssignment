package petStoreBase.framework.handler;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "services")
public class Services
{
    private EndPoints endpoints;
    private Apis apis;
    public EndPoints getEndpoints() {
        return endpoints;
    }

    @XmlElement
    public void setEndpoints(EndPoints endpnt) {
        this.endpoints = endpnt;
    }

    public Apis getApis() {
        return apis;
    }

    @XmlElement
    public void setApis(Apis apis) {
        this.apis = apis;
    }

    public EndPoint GetServiceDetails(String ServiceName){
        EndPoint ep = new EndPoint();
        List<EndPoint> collection = endpoints.getEndpoint();

        for (EndPoint e: collection) {

            if (e.getServicename().trim().toLowerCase().equals(ServiceName)){
                if(e.getBaseurl().toString().contains("${env}")) {
                    String env = (System.getProperty("sit-env") != null) ?  System.getProperty("sit-env") : "dev1";
                    e.setBaseurl(e.getBaseurl().replace("${env}", env));
                }
                ep = e;
            }

        }
        return ep;
    }
}
