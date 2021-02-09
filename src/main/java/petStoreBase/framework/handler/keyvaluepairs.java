package petStoreBase.framework.handler;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="keyvaluepairs")
public class keyvaluepairs {
    List<keyvaluepair> keyvaluepair;

    public List<keyvaluepair> getKeyvaluepair() {
        return keyvaluepair;
    }

    @XmlElement
    public void setKeyvaluepair(List<keyvaluepair> keyvaluepair) {
        this.keyvaluepair = keyvaluepair;
    }
}
