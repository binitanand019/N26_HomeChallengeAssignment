package petStoreBase.framework.handler;

import petStoreBase.staticData.StaticData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

public class Translators {
    private StaticData data = new StaticData();
    public Environment jaxbXMLToObject(InputStream is) {
        try {
            JAXBContext context = JAXBContext.newInstance(Environment.class);
            Unmarshaller un = context.createUnmarshaller();
            Environment emp = (Environment) un.unmarshal(is);
            return emp;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
