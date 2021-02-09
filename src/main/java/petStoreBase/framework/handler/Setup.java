package petStoreBase.framework.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.apache.commons.io.IOUtils;
import petStoreBase.staticData.StaticData;

import java.io.*;

public class Setup implements ResourceLoaderAware {
    String envData = null;
    Resource envResource;
    private ResourceLoader resourceLoader;
    private String environment;
    private String isDockinsEnabled;
    private String dockEnvName;
    private static Logger log = LoggerFactory.getLogger(Setup.class);
    private String EnvironmentFilePath = null;

    public Setup(){
        init();
    }

    public Setup(PropertiesHandler properties){
        init(properties);
    }

    public Setup(String environmentname){
        init(environmentname);
    }


    public void init()
    {
        try{
            InputStream is = new FileInputStream("./Data/Environments/environment_stage.xml");
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while(line != null){
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String fileAsString = sb.toString();
            envData =  fileAsString;


        }catch(Exception ex){
            log.debug("Error reading environment reading from URL, will try to search local");
            throw new RuntimeException("Error reading environment config", ex);
        }

    }

    public void init(PropertiesHandler properties)
    {
        try{
            StaticData state = new StaticData(properties);
            System.out.println("state.getEnvironmentFilePath() = " + state.getEnvironmentFilePath());
            EnvironmentFilePath = state.getEnvironmentFilePath();
            InputStream is = new FileInputStream(state.getEnvironmentFilePath());
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while(line != null){
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String fileAsString = sb.toString();
            envData =  fileAsString;


        }catch(Exception ex){
            log.debug("Error reading environment reading from URL, will try to search local");
            throw new RuntimeException("Error reading environment config", ex);
        }

    }

    public void init(String environmentname)
    {
        try{
            StaticData state = new StaticData(environmentname);
            System.out.println("state.getEnvironmentFilePath() new init = " + state.getEnvironmentFilePath());
            EnvironmentFilePath = state.getEnvironmentFilePath();
            InputStream is = new FileInputStream(state.getEnvironmentFilePath());
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while(line != null){
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String fileAsString = sb.toString();
            envData =  fileAsString;


        }catch(Exception ex){
            log.debug("Error reading environment reading from URL, will try to search local");
            throw new RuntimeException("Error reading environment config", ex);
        }

    }


    public Environment getEnvironmentData()
    {
        Translators transform = new Translators();


        Environment empFromFile = transform.jaxbXMLToObject(IOUtils.toInputStream(envData));

        return empFromFile;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getEnvironment() {
        return environment;
    }


    private String getEnvironmentXmlEndpoint()
    {
        try{
            String currentDir = new File( "." ).getCanonicalPath();
            return "file://"+EnvironmentFilePath;
        }catch(Exception ex){
            throw new RuntimeException("Error reading environment xml", ex);
        }

    }

    public void setEnvironment(String environment) {this.environment = environment;}

    public void setIsDockinsEnabled(String isDockinsEnabled) {
        this.isDockinsEnabled = isDockinsEnabled;
    }

    public void setDockEnvName(String dockEnvName) {
        this.dockEnvName = dockEnvName;
    }

    public APIData GetAPIDetails(String ServiceName, String apiname){
        APIData data = new APIData(ServiceName,apiname, this.getEnvironmentData());
        return data;
    }

}

