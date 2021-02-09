package petStoreBase.framework.handler;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "apis")
public class Apis
{
    private String servicename;
    private List<Api> apis;


    public Apis()
    {

    }

    public List<Api> getApi() {
        return apis;
    }

    @XmlElement()
    public void setApi(List<Api> api) {
        this.apis = api;
    }


    public Api GetAPIDetails(String serviceName, String APIName){
        Api api = new Api();

        for (Api ap: apis) {

            if (ap.getApiname().trim().toLowerCase().equals(APIName.toLowerCase().trim())
                    && ap.getServicename().trim().toLowerCase().equals(serviceName.toLowerCase().trim())){
                api = ap;
            }

        }
        return api;
    }


}
