package com.dat.controls;

import com.dat.models.Province;

import java.util.HashMap;

public class ProvinceController {
    private final HashMap<String, Province> list;
    public ProvinceController(){
        Province p=new Province();
        this.list =p.loadAPI();
    }

    public HashMap<String, Province> getList() {
        return list;
    }

    public HashMap<String, Province> search(String name) {
        HashMap<String, Province> result = new HashMap<>();
        for (String i : list.keySet()) {
            if (i.toLowerCase().contains(name.toLowerCase())) {
                result.put(i, list.get(i));
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

    public HashMap<String, Province> searchRelative(String info) {
        HashMap<String, Province> result = new HashMap<>();
        for (String i : list.keySet()) {
            if (i.toLowerCase().contains(info.toLowerCase())||(contains(i, info))) {
                result.put(i, list.get(i));
            }
        }
        return result;
    }
}
