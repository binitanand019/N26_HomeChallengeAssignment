package petStoreBase.staticData;

import petStoreBase.framework.handler.Api;
import petStoreBase.framework.handler.EndPoint;
import petStoreBase.framework.handler.Environment;
import petStoreBase.framework.handler.PropertiesHandler;

import java.util.HashMap;
import java.util.List;

public class StaticData {


    public String datafolder = "Data";
    private String propertyfilename = "settings.properties";
    private String objectrepository = "ObjectRepository/Elements.xml";
    private String environmentfolder = "Environments";
    private String payloadfolder = "Payloads";
    private String MailTemplateFolder = "StaticData";
    private String TestRunReportName = "testrunreport.txt";
    private String GetTestEnvironment = "stage";
    public String PropertyFilePath = getDatafolder() + "/" + propertyfilename;
    public String EnvironmentFilePath = getDatafolder() + "/" + environmentfolder + "/environment_"
            + GetTestEnvironment + ".xml";
    public String ObjectRepositoryPath = getDatafolder() + "/" + objectrepository;
    public String Payloadpath = getDatafolder() + "/" + payloadfolder;
    public String TestRunReportPath = getDatafolder() + "/" + MailTemplateFolder + "/" + TestRunReportName;


    public StaticData() {
        String environment = System.getenv("environment");
        if (environment != null) {
            GetTestEnvironment = environment;
        }
    }

    public StaticData(String environmentname) {
        setGetTestEnvironment(environmentname);

        EnvironmentFilePath = getDatafolder() + "/" + environmentfolder + "/environment_" + GetTestEnvironment
                + ".xml";
    }

    public StaticData(PropertiesHandler props) {
        String environment = System.getenv("environment");
        if (environment != null)
            GetTestEnvironment = environment;
        else
            GetTestEnvironment = props.propertiesMap.get("environment");

        EnvironmentFilePath = getDatafolder() + "/" + environmentfolder + "/environment_" + GetTestEnvironment
                + ".xml";
    }

    public String getDatafolder() {
        return datafolder;
    }

    public String getPropertyFilePath() {
        return PropertyFilePath;
    }

    public String getEnvironmentFilePath() {
        return EnvironmentFilePath;
    }

    public String getPayloadfolder() {
        return Payloadpath;
    }

    public void setGetTestEnvironment(String getTestEnvironment) {
        GetTestEnvironment = getTestEnvironment;
    }

}

