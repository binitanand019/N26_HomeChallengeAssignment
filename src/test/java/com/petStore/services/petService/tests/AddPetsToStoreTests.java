package com.petStore.services.petService.tests;


import com.petStore.services.petService.constant.PetServiceConstant;
import com.petStore.services.petService.dataProvider.PetServiceDP;
import com.petStore.services.petService.pojo.requestPOJO.AddPetsToStorePOJO;
import com.petStore.services.petService.pojo.responsePOJO.AddPetToStoreResponsePOJO;
import com.petStore.services.petService.serviceLauncher.PetServiceLauncher;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreBase.framework.handler.Processor;
import petStoreBase.framework.helper.JsonHelper;
import java.io.IOException;
import java.util.HashMap;


public class AddPetsToStoreTests {

    PetServiceLauncher petServiceLauncher = new PetServiceLauncher();
    JsonHelper jsonHelper = new JsonHelper();
    String addPetToStoreResponsePOJOClass = "com.petStore.services.petService.pojo.responsePOJO.AddPetToStoreResponsePOJO";
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "addPetToStore", dataProviderClass = PetServiceDP.class, description = "Add pets to store with different status")
    public void addPetToStoreOnDifferentStatus(HashMap<String, Object> data) throws IOException {
        AddPetsToStorePOJO payload = (AddPetsToStorePOJO) data.get("addPetToStore");
        Processor createResponse = petServiceLauncher.addPetToStore(payload);
        String categoryName = createResponse.ResponseValidator.GetNodeValue("$.category.name");
        String dogBreadName = createResponse.ResponseValidator.GetNodeValue("$.name");
        String status = createResponse.ResponseValidator.GetNodeValueAsStringFromJsonArray("$.status");
        AddPetToStoreResponsePOJO addPetToStoreResponsePOJO = (AddPetToStoreResponsePOJO) jsonHelper
                .getObjectFromJson(createResponse
                        .ResponseValidator
                        .GetBodyAsText(), addPetToStoreResponsePOJOClass);
        Assert.assertNotNull(addPetToStoreResponsePOJO);
        Assert.assertEquals(categoryName,"Domestic Dogs", "Category name mismatch");
        Assert.assertEquals(dogBreadName,"rottweiler", "DogBread name mismatch");
        if(status==PetServiceConstant.AVAILABLE_STATUS) {
            Assert.assertTrue(status.contains(PetServiceConstant.AVAILABLE_STATUS));
        }
        else if(status==PetServiceConstant.PENDING_STATUS) {
            Assert.assertTrue(status.contains(PetServiceConstant.PENDING_STATUS));
        }
        else if(status==PetServiceConstant.SOLD_STATUS) {
            Assert.assertTrue(status.contains(PetServiceConstant.SOLD_STATUS));
        }
    }
}
