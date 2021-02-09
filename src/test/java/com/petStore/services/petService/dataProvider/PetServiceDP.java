package com.petStore.services.petService.dataProvider;

import com.petStore.services.petService.constant.PetServiceConstant;
import com.petStore.services.petService.helper.PetServiceHelper;
import com.petStore.services.petService.pojo.requestPOJO.AddPetsToStorePOJO;
import org.testng.annotations.DataProvider;

import java.util.HashMap;

public class PetServiceDP {

    @DataProvider(name = "addPetToStore")
    public static Object[][] addPetToStore() {
        HashMap<String, Object> data0 = new HashMap<String, Object>();
        HashMap<String, Object> data1 = new HashMap<String, Object>();
        HashMap<String, Object> data2 = new HashMap<String, Object>();
        AddPetsToStorePOJO addPetsToStorePOJO0 = new PetServiceHelper().addPetsToStore(PetServiceConstant.AVAILABLE_STATUS);
        AddPetsToStorePOJO addPetsToStorePOJO1 = new PetServiceHelper().addPetsToStore(PetServiceConstant.PENDING_STATUS);
        AddPetsToStorePOJO addPetsToStorePOJO2 = new PetServiceHelper().addPetsToStore(PetServiceConstant.SOLD_STATUS);
        data0.put("addPetToStore",addPetsToStorePOJO0);
        data1.put("addPetToStore",addPetsToStorePOJO1);
        data2.put("addPetToStore",addPetsToStorePOJO2);
        return new Object[][]{{data0},{data1},{data2}};
    }

    @DataProvider(name = "getAddedPetDetailsToStore")
    public static Object[][] getAddedPetDetailsToStore() {
        HashMap<String, Object> data1 = new HashMap<String, Object>();
        HashMap<String, Object> data2 = new HashMap<String, Object>();
        AddPetsToStorePOJO addPetsToStorePOJO1 = new PetServiceHelper().addPetsToStore(PetServiceConstant.PENDING_STATUS);
        AddPetsToStorePOJO addPetsToStorePOJO2 = new PetServiceHelper().addPetsToStore(PetServiceConstant.SOLD_STATUS);
        data1.put("addPetToStore",addPetsToStorePOJO1);
        data2.put("addPetToStore",addPetsToStorePOJO2);
        return new Object[][]{{data1},{data2}};
    }
}
