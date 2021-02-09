package petStoreBase.framework.launch;

import petStoreBase.framework.Initialize.Initialize;
import petStoreBase.framework.handler.APIData;
import petStoreBase.framework.handler.Environment;

public class LaunchService {

    public APIData APIDetails = null;
    public Environment environmentData = null;

    public LaunchService(String Servicename, String apiundertest, Initialize init) {

        APIDetails = init.EnvironmentDetails.setup.GetAPIDetails(Servicename, apiundertest);
        environmentData = init.EnvironmentDetails.setup.getEnvironmentData();
    }

}
