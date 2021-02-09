package com.petStore.services.storeService.helper;

import com.petStore.services.storeService.pojo.requestPOJO.OrderPOJO;

public class OrderServiceHelper {

    public OrderPOJO placeOrder() {
        OrderPOJO orderPOJO = new OrderPOJO()
                .setDefaults()
                .withPetId(35)
                .withQuantity(1);
        return orderPOJO;


    }
}
