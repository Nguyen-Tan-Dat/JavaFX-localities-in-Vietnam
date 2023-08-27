package com.dat.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Ward {
    private String name;
    private int districtID;

    public Ward() {
    }

    public Ward(String name, int districtID) {
        this.name = name;
        this.districtID = districtID;
    }

    public String getName() {
        return name;
    }


    public int getDistrictID() {
        return districtID;
    }

    public HashMap<Integer, Ward> loadAPI(){
        API api = new API("https://provinces.open-api.vn/api/?depth=3");
        var array = api.getJsonArray();
        HashMap<Integer,Ward> result=new HashMap<>();
        int idDistrict = 1;
        int idWard = 1;
        assert array != null;
        for (Object province : array) {
            JSONArray jsonDistricts = (JSONArray) ((JSONObject) province).get("districts");
            for (Object district : jsonDistricts) {
                JSONArray jsonWards = (JSONArray) ((JSONObject) district).get("wards");
                for (Object ward : jsonWards) {
                    String wardName = ((JSONObject) ward).get("name").toString();
                    result.put(idWard++, new Ward(wardName, idDistrict));
                }
                idDistrict++;
            }
        }
        return result;
    }
}
