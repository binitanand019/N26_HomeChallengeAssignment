package com.petStore.services.petService.pojo.responsePOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name"
})
public class Tags {

    @JsonProperty("id")
    public long id;
    @JsonProperty("name")
    public String name;

}
