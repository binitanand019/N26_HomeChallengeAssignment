package com.petStore.services.petService.pojo.responsePOJO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "category",
        "name",
        "photoUrls",
        "tags",
        "status"
})
public class AddPetToStoreResponsePOJO {

    @JsonProperty("id")
    public int id;
    @JsonProperty("category")
    public Category category;
    @JsonProperty("name")
    public String name;
    @JsonProperty("photoUrls")
    public List<String> photoUrls = null;
    @JsonProperty("tags")
    public List<Tags> tags = null;
    @JsonProperty("status")
    public String status;

}
