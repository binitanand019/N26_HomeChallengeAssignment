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
public class CategoryPOJO {

    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;

    public CategoryPOJO withId(int id) {
        this.id = id;
        return this;
    }

    public CategoryPOJO withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryPOJO setDefaultData() {
        return withId(Utility.getRandom(400, 9999))
                .withName("Domestic Dogs");
    }

}
