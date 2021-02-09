package com.nahara.toka.model;

import com.google.gson.annotations.SerializedName;

public class Id {
    public Id(){}
    @SerializedName("baseId")
    private String baseId;



    public String getBaseId(){
        return this.baseId;
    }


    public void setBaseId(String baseId) {
        this.baseId=baseId;
    }
}
