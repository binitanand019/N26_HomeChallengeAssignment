package com.petStore.services.storeService.serviceLauncher;

import com.petStore.services.storeService.constant.StoreServiceConstant;
import com.petStore.services.storeService.pojo.requestPOJO.OrderPOJO;
import petStoreBase.framework.Initialize.Initialize;
import petStoreBase.framework.handler.Processor;
import petStoreBase.framework.helper.JsonHelper;
import petStoreBase.framework.launch.Initializer;
import petStoreBase.framework.launch.LaunchService;

import java.io.IOException;
import java.util.HashMap;

public class StoreServiceLauncher {

    static Initialize initialize = Initializer.getInitializer();
    JsonHelper jsonHelper = new JsonHelper();

    public Processor placeAnOrder(OrderPOJO orderPOJO,int petId, int quantity) throws IOException {
        HashMap<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put(StoreServiceConstant.CONTENT_TYPE, StoreServiceConstant.APPLICATION_JSON);
        LaunchService service = new LaunchService("storemodule", "placeOrder", initialize);
        OrderPOJO orderPOJO1 = new OrderPOJO().setDefaults().withPetId(petId).withQuantity(quantity);
        Processor processor;
        processor = new Processor(service, requestHeaders,new String[]{jsonHelper.getObjectToJSON(orderPOJO1)});
        return processor;
    }
    public Processor getOrderDetails(int orderId) throws IOException {
        HashMap<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put(StoreServiceConstant.CONTENT_TYPE, StoreServiceConstant.APPLICATION_JSON);
        String[] queryParam = new String[] {String.valueOf(orderId)};
        LaunchService service = new LaunchService("storemodule", "getOrderDetails", initialize);
        Processor processor;
        processor = new Processor(service, requestHeaders,null,queryParam);

        return processor;
    }

}
