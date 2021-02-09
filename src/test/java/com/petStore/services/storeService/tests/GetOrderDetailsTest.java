package com.petStore.services.storeService.tests;

import com.jayway.jsonpath.JsonPath;
import com.petStore.services.petService.constant.PetServiceConstant;
import com.petStore.services.petService.serviceLauncher.PetServiceLauncher;
import com.petStore.services.storeService.dataProvider.StoreServiceDP;
import com.petStore.services.storeService.pojo.requestPOJO.OrderPOJO;
import com.petStore.services.storeService.pojo.responsePOJO.OrderResponsePOJO;
import com.petStore.services.storeService.serviceLauncher.StoreServiceLauncher;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreBase.framework.handler.Processor;
import petStoreBase.framework.helper.JsonHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetOrderDetailsTest {

    StoreServiceLauncher storeServiceLauncher = new StoreServiceLauncher();
    PetServiceLauncher petServiceLauncher = new PetServiceLauncher();
    JsonHelper jsonHelper = new JsonHelper();
    String orderResponsePOJOClass = "com.petStore.services.storeService.pojo.responsePOJO.OrderResponsePOJO";

    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "placeOrder", dataProviderClass = StoreServiceDP.class, description = "To Place An Order")
    public void getOrderDetails(HashMap<String, Object> data) throws IOException {
        Processor getPetByStatusDetails = petServiceLauncher.getPetsByStatus(PetServiceConstant.AVAILABLE_STATUS);
        List<Integer> responsePetId = JsonPath.read(getPetByStatusDetails.ResponseValidator.GetBodyAsText(), "$..id");
        OrderPOJO payload = (OrderPOJO) data.get("placeOrder");
        Processor createResponse = storeServiceLauncher.placeAnOrder(payload, responsePetId.get(50), 1);
        String status = createResponse.ResponseValidator.GetNodeValue("$.status");
        Boolean complete = createResponse.ResponseValidator.GetNodeValueAsBool("$.complete");
        int petId = createResponse.ResponseValidator.GetNodeValueAsInt("$.petId");
        int orderId = createResponse.ResponseValidator.GetNodeValueAsInt("$.id");
        OrderResponsePOJO orderResponsePOJO = (OrderResponsePOJO) jsonHelper
                .getObjectFromJson(createResponse
                        .ResponseValidator
                        .GetBodyAsText(), orderResponsePOJOClass);
        Assert.assertNotNull(orderResponsePOJO);
        Assert.assertEquals(status, "approved", "Approval Didn't happen");
        Assert.assertTrue(complete, "Order Place Not Successful");
        Assert.assertTrue(responsePetId.contains(petId), "Pet ID is wrong");
        Processor createResponse1 = storeServiceLauncher.getOrderDetails(orderId);
        OrderResponsePOJO getOrderResponsePOJO = (OrderResponsePOJO) jsonHelper
                .getObjectFromJson(createResponse1
                        .ResponseValidator
                        .GetBodyAsText(), orderResponsePOJOClass);
        Assert.assertNotNull(getOrderResponsePOJO);
        int petId1 = createResponse1.ResponseValidator.GetNodeValueAsInt("$.petId");
        int orderId1 = createResponse1.ResponseValidator.GetNodeValueAsInt("$.id");
        Assert.assertEquals(petId,petId1);
        Assert.assertEquals(orderId,orderId1);

    }
}