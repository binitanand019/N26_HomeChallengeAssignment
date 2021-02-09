package petStoreBase.framework.launch;

import petStoreBase.framework.handler.PropertiesHandler;
import petStoreBase.framework.handler.Setup;

import java.io.IOException;

public class InitializeEnvironmentObject {

    public Setup setup;

    public InitializeEnvironmentObject(PropertiesHandler properties) throws IOException
    {
        setup = new Setup(properties);
    }

    public InitializeEnvironmentObject(String environmentname) throws IOException
    {
        setup = new Setup(environmentname);
    }


}
