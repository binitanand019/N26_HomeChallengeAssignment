package com.petStore.services.storeService.pojo.requestPOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import petStoreBase.framework.utilities.Utility;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "petId",
        "quantity",
        "shipDate",
        "status",
        "complete"
})
public class OrderPOJO {

    @JsonProperty("id")
    public int id;
    @JsonProperty("petId")
    public int petId;
    @JsonProperty("quantity")
    public int quantity;
    @JsonProperty("shipDate")
    public String shipDate;
    @JsonProperty("status")
    public String status;
    @JsonProperty("complete")
    public boolean complete;

    public OrderPOJO withId(int id) {
        this.id = id;
        return this;
    }

    public OrderPOJO withPetId(int petId) {
        this.petId = petId;
        return this;
    }

    public OrderPOJO withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderPOJO withShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public OrderPOJO withStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderPOJO withComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public OrderPOJO setDefaults() {

        return this.withId(Utility.getRandom(99,99999))
                .withPetId(357)
                .withQuantity(1)
                .withShipDate(Utility.getFutureDate(4,"yyyy-MM-dd'T'HH:mm:ss'.000Z'"))
                .withStatus("approved")
                .withComplete(true);
    }

}
