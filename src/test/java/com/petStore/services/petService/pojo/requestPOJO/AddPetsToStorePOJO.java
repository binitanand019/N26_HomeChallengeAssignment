package com.petStore.services.petService.pojo.requestPOJO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.petStore.services.petService.constant.PetServiceConstant;
import petStoreBase.framework.utilities.Utility;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "category",
        "photoUrls",
        "tags",
        "status"
})
public class AddPetsToStorePOJO {

    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("category")
    public CategoryPOJO category;
    @JsonProperty("photoUrls")
    public List<String> photoUrls = null;
    @JsonProperty("tags")
    public List<TagPOJO> tags = null;
    @JsonProperty("status")
    public String status;

    public AddPetsToStorePOJO withId(int id) {
        this.id = id;
        return this;
    }

    public AddPetsToStorePOJO withName(String name) {
        this.name = name;
        return this;
    }

    public AddPetsToStorePOJO withCategory(CategoryPOJO category) {
        this.category = category;
        return this;
    }

    public AddPetsToStorePOJO withPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public AddPetsToStorePOJO withTags(List<TagPOJO> tags) {
        this.tags = tags;
        return this;
    }

    public AddPetsToStorePOJO withStatus(String status) {
        this.status = status;
        return this;
    }

    public AddPetsToStorePOJO setDefaultData() {
        return withId(Utility.getRandom(350,9999))
                .withCategory(new CategoryPOJO().setDefaultData())
                .withName("rottweiler")
                .withTags(new ArrayList<TagPOJO>(){{add(new TagPOJO().setDefaultData());}})
                .withPhotoUrls(new ArrayList<String>(){{add("https://www.google.com/search?q=rottweiler&rlz=1C5CHFA_enIN903IN903&tbm=isch&source=iu&ictx=1&fir=e9-HCR3hSvqqzM%252CBSn3QjoIGr3T3M%252C%252Fm%252F01nw62&vet=1&usg=AI4_-kRmbY6wr3R36-p__sGybXH2gYNi9g&sa=X&ved=2ahUKEwjWm8PpttXuAhVGWysKHQ4ZDyQQ_B16BAhCEAE");}})
                .withStatus(PetServiceConstant.AVAILABLE_STATUS);

    }

}
