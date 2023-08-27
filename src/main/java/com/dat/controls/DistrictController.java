package com.dat.controls;

import com.dat.models.District;

import java.util.HashMap;

public class DistrictController {
    private final HashMap<Integer, District> list;

    public DistrictController() {
        District p=new District();
        this.list =p.loadAPI();
    }

    public HashMap<Integer, District> getList() {
        return list;
    }


    public HashMap<Integer, District> listDistrict(String province) {
        HashMap<Integer, District> result = new HashMap<>();
        for (var i : list.keySet()) {
            var d = list.get(i);
            if (d.getProvince().equals(province))
                result.put(i, d);
        }
        return result;
    }


    public HashMap<Integer, District> searchList(String name) {
        HashMap<Integer, District> result = new HashMap<>();
        for (var j : list.keySet()) {
            var d = list.get(j);
            if (d.getName().toLowerCase().contains(name.toLowerCase())) {
                result.put(j, d);
            }
        }
        return result;
    }

    private boolean contains(String content, String charAts) {
        for (int i = 0, j = 0; i < charAts.length(); i++) {
            boolean contain = false;
            for (int k = j; k < content.length(); k++) {
                if (content.charAt(k) == charAts.charAt(i)) {
                    j = k;
                    contain = true;
                }
            }
            if (!contain) return false;
        }
        return true;
    }

    public HashMap<Integer, District> searchRelative(String info) {
        HashMap<Integer, District> result = new HashMap<>();
        for (var j : list.keySet()) {
            var d = list.get(j);
            if (d.getName().toLowerCase().contains(info.toLowerCase()) || contains(d.getName(), info)) {
                result.put(j, d);
            }
        }
        return result;
    }
}
