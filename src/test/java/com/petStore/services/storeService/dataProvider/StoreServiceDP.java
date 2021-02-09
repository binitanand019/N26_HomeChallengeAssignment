package com.petStore.services.storeService.dataProvider;

import com.petStore.services.storeService.helper.OrderServiceHelper;
import com.petStore.services.storeService.pojo.requestPOJO.OrderPOJO;
import org.testng.annotations.DataProvider;

import java.util.HashMap;

public class StoreServiceDP {

    @DataProvider(name = "placeOrder")
    public static Object[][] placeOrder() {
        HashMap<String, Object> data0 = new HashMap<String, Object>();
        OrderPOJO orderPOJO = new OrderServiceHelper().placeOrder();
        data0.put("placeOrder",orderPOJO);
        return new Object[][]{{data0}};
    }
}
