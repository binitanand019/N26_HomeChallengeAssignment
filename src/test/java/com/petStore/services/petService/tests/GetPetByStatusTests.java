package com.petStore.services.petService.tests;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.petStore.services.petService.constant.PetServiceConstant;
import com.petStore.services.petService.dataProvider.PetServiceDP;
import com.petStore.services.petService.pojo.requestPOJO.AddPetsToStorePOJO;
import com.petStore.services.petService.pojo.responsePOJO.GetPetByStatusResponsePOJO;
import com.petStore.services.petService.serviceLauncher.PetServiceLauncher;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreBase.framework.handler.Processor;
import petStoreBase.framework.helper.JsonHelper;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;



public class GetPetByStatusTests {

    PetServiceLauncher petServiceLauncher = new PetServiceLauncher();
    JsonHelper jsonHelper = new JsonHelper();

    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "getAddedPetDetailsToStore", dataProviderClass = PetServiceDP.class, description = "Get pets details based on status")
    public void getCouponDetailsOnStatus(HashMap<String, Object> data) throws IOException {
        AddPetsToStorePOJO payload = (AddPetsToStorePOJO) data.get("addPetToStore");
        Processor createResponse = petServiceLauncher.addPetToStore(payload);
        String categoryName = createResponse.ResponseValidator.GetNodeValue("$.category.name");
        String dogBreadName = createResponse.ResponseValidator.GetNodeValue("$.name");
        String status = createResponse.ResponseValidator.GetNodeValueAsStringFromJsonArray("$.status");
        Processor getPetByStatusDetails = petServiceLauncher.getPetsByStatus(PetServiceConstant.PENDING_STATUS);
        GetPetByStatusResponsePOJO[] getPetByStatusResponsePOJO = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .readValue(getPetByStatusDetails.ResponseValidator.GetBodyAsText(), GetPetByStatusResponsePOJO[].class);
        Assert.assertNotNull(getPetByStatusResponsePOJO);
        List<String> responseCategoryName = JsonPath.read(getPetByStatusDetails.ResponseValidator.GetBodyAsText(), "$..category.name");
        List<String> responseDogBreadName = JsonPath.read(getPetByStatusDetails.ResponseValidator.GetBodyAsText(), "$..name");
        List<String> responseStatus = JsonPath.read(getPetByStatusDetails.ResponseValidator.GetBodyAsText(), "$..status");
        Assert.assertTrue(responseCategoryName.contains(categoryName), "Recent added pets is not present");
        Assert.assertTrue(responseDogBreadName.contains(dogBreadName), "Recent added pets is not present");
        if (status.contains(PetServiceConstant.PENDING_STATUS)) {
            Assert.assertTrue(responseStatus.contains(status), "Recent added pets is not present");
        } else if (status.contains(PetServiceConstant.SOLD_STATUS)){
            Assert.assertFalse(responseStatus.contains(status), "Recent added pets status is not Pending");
        }
    }
}
