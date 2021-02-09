package petStoreBase.framework.Initialize;

import petStoreBase.framework.handler.PropertiesHandler;
import petStoreBase.framework.launch.InitializeEnvironmentObject;

import java.io.IOException;

public class Initialize {
    public PropertiesHandler Properties;
    public InitializeEnvironmentObject EnvironmentDetails;
    public Initialize(){ loadenvironment(); }

    public Initialize(String environment){ EnvironmentReload(environment); }

    public void EnvironmentReload(String environmentname){
        loadenvironment();
        System.out.println("environmentname from Initialize = " + environmentname);
        Properties.SwitchEnvironment(environmentname);
        reloadenvironment(environmentname);
    }

    private void loadenvironment(){
        try{
            Properties = new PropertiesHandler();
            System.out.println("PropertiesHandler while loading new file = " + Properties.propertiesMap.get("Environment"));
            System.setProperty("env", Properties.propertiesMap.get("environment"));
            EnvironmentDetails = new InitializeEnvironmentObject(Properties);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }


    private void reloadenvironment(String environmentname){
        try{
            Properties = new PropertiesHandler(environmentname);
            System.out.println("PropertiesHandler while loading new file = " + Properties.propertiesMap.get("environment"));
            EnvironmentDetails = new InitializeEnvironmentObject(environmentname);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }

    }


}

