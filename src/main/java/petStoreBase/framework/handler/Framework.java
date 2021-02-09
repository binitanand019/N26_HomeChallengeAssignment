package petStoreBase.framework.handler;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Framework")
public class Framework {

    private Paths paths;
    private String mailformatfolder;

    public Paths getPaths() {
        return paths;
    }

    @XmlElement(name = "paths")
    public void setPaths(Paths paths) {
        this.paths = paths;
    }

    @XmlElement(name = "mailformatfolder")
    public void setMailformatfolder(String mailformatfolder) {
        this.mailformatfolder = mailformatfolder;
    }

    public String getMailformatfolder() {
        return mailformatfolder;
    }




}
