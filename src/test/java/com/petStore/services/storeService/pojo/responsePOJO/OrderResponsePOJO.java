package com.petStore.services.storeService.pojo.responsePOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "petId",
        "quantity",
        "shipDate",
        "status",
        "complete"
})
public class OrderResponsePOJO {

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

}
