package com.petStore.services.petService.pojo.requestPOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import petStoreBase.framework.utilities.Utility;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name"
})
public class TagPOJO {

    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;

    public TagPOJO withId(int id) {
        this.id = id;
        return this;
    }

    public TagPOJO withName(String name) {
        this.name = name;
        return this;
    }

    public TagPOJO setDefaultData() {
        return this.withId(Utility.getRandom(400,99999))
                .withName("Domestic");
    }

}
