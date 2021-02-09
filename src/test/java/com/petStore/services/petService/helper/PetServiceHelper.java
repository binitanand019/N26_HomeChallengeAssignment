package com.petStore.services.petService.helper;

import com.petStore.services.petService.pojo.requestPOJO.AddPetsToStorePOJO;

public class PetServiceHelper {

    public AddPetsToStorePOJO addPetsToStore(String status) {

        AddPetsToStorePOJO addPetsToStorePOJO = new AddPetsToStorePOJO()
                .setDefaultData()
                .withStatus(status);
        return addPetsToStorePOJO;

    }
}
