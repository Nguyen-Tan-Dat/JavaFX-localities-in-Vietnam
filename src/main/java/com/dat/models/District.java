package com.dat.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class District {
    private String name;
    private String province;

    public District() {
    }

    public District(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public HashMap<Integer, District> loadAPI() {
        API api = new API("https://provinces.open-api.vn/api/?depth=2");
        var array = api.getJsonArray();
        int idDistrict = 1;
        HashMap<Integer, District> result = new HashMap<>();
        assert array != null;
        for (Object province : array) {
            String provinceName = ((JSONObject) province).get("name").toString();
            JSONArray jsonDistricts = (JSONArray) ((JSONObject) province).get("districts");
            for (Object district : jsonDistricts) {
                String districtName = ((JSONObject) district).get("name").toString();
                result.put(idDistrict++, new District(districtName, provinceName));
            }
        }
        return result;
    }
}
