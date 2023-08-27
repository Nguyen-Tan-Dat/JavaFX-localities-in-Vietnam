package com.dat.models;

import org.json.JSONObject;

import java.util.HashMap;

public class Province{
    String name=null;

    public Province() {
    }

    public Province(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<String,Province> loadAPI(){
        API api = new API("https://provinces.open-api.vn/api/?depth=1");
        var array = api.getJsonArray();
        HashMap<String, Province> result = new HashMap<>();
        assert array != null;
        for (var i : array) {
            String provinceName = ((JSONObject) i).get("name").toString();
            result.put(provinceName, new Province(provinceName));
        }
        return result;
    }
}
