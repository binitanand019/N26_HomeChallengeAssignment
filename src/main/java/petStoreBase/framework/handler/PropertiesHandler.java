package petStoreBase.framework.handler;

import petStoreBase.staticData.StaticData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesHandler {

    public HashMap<String, String> propertiesMap = new HashMap<String, String>();
    StaticData staticData = new StaticData();
    String propertiesfilepath = staticData.getPropertyFilePath();

    public PropertiesHandler()
    {
        PropertyReader();


    }

    public PropertiesHandler(String environmentname)
    {
        staticData = new StaticData(environmentname);
        propertiesfilepath = staticData.getPropertyFilePath();
        System.out.println("propertiesfilepath = " + propertiesfilepath);
        PropertyReader();


    }

    private void PropertyReader()
    {
        try {

            File file = new File(propertiesfilepath);
            FileInputStream inputstream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(inputstream);
            inputstream.close();

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()){
                String propertykey = (String)enuKeys.nextElement();
                String propertyvalue = properties.getProperty(propertykey);
                propertiesMap.put(propertykey,propertyvalue);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void SwitchEnvironment(String environmentname){

        String oldvalue = propertiesMap.get("environment");
        System.out.println("oldvalue = " + oldvalue);
        SwitchPropertyValue("environment", environmentname);
        staticData = new StaticData(environmentname);
        propertiesfilepath = staticData.getPropertyFilePath();
        System.out.println("propertiesMap-Old = " +oldvalue+ "propertiesMap-New = " +propertiesMap.get("environment"));
        PropertyReader();

    }

    private void SwitchPropertyValue(String keyname, String value){
        propertiesMap.replace(keyname, value);
    }
}


