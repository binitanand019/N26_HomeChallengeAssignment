package com.petStore.services.petService.serviceLauncher;

import com.petStore.services.petService.constant.PetServiceConstant;
import com.petStore.services.petService.pojo.requestPOJO.AddPetsToStorePOJO;
import petStoreBase.framework.Initialize.Initialize;
import petStoreBase.framework.handler.Processor;
import petStoreBase.framework.helper.JsonHelper;
import petStoreBase.framework.launch.Initializer;
import petStoreBase.framework.launch.LaunchService;

import java.io.IOException;
import java.util.HashMap;

public class PetServiceLauncher {

    static Initialize initialize = Initializer.getInitializer();
    JsonHelper jsonHelper = new JsonHelper();

    public Processor addPetToStore(AddPetsToStorePOJO addPetsToStorePOJO) throws IOException {
        HashMap<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put(PetServiceConstant.CONTENT_TYPE, PetServiceConstant.APPLICATION_JSON);
        LaunchService service = new LaunchService("petmodule", "addPetToStore", initialize);
        Processor processor;
        processor = new Processor(service, requestHeaders,new String[]{jsonHelper.getObjectToJSON(addPetsToStorePOJO)});
        return processor;
    }


    public Processor getPetsByStatus(String status) throws IOException {
        HashMap<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put(PetServiceConstant.CONTENT_TYPE, PetServiceConstant.APPLICATION_JSON);
        String[] queryParam = new String[] {status};
        LaunchService service = new LaunchService("petmodule", "getPetByStatus", initialize);
        Processor processor;
        processor = new Processor(service, requestHeaders,null,queryParam);
        return processor;
    }
}
