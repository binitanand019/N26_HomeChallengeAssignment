package petStoreBase.framework.handler;

import javax.xml.bind.annotation.XmlAttribute;

public class keyvaluepair {
    String key;
    String value;


    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @XmlAttribute
    public void setKey(String key) {
        this.key = key;
    }

    @XmlAttribute
    public void setValue(String value) {
        this.value = value;
    }



}
