package com.dat.controls;

import com.dat.models.District;
import com.dat.models.Ward;

import java.util.HashMap;

public class WardController {
    private final HashMap<Integer, Ward> list;

    public WardController() {
        Ward p = new Ward();
        this.list = p.loadAPI();
    }

    public HashMap<Integer, Ward> getList() {
        return list;
    }

    public HashMap<Integer, Ward> searchList(String name) {
        HashMap<Integer, Ward> result = new HashMap<>();
        for (var k : list.keySet()) {
            var w = list.get(k);
            if (w.getName().toLowerCase().contains(name.toLowerCase())) {
                result.put(k, w);
            }
        }
        return result;
    }

    public HashMap<Integer, Ward> getList(int districtID) {
        HashMap<Integer, Ward> result = new HashMap<>();
        for (var j : list.keySet())
            if (list.get(j).getDistrictID() == districtID)
                result.put(j, list.get(j));
        return result;
    }

    public HashMap<Integer, Ward> getList(HashMap<Integer, District> districts) {
        HashMap<Integer, Ward> result = new HashMap<>();
        for (var i : districts.keySet())
            for (var j : list.keySet())
                if (list.get(j).getDistrictID() == i)
                    result.put(j, list.get(j));
        return result;
    }

    public HashMap<Integer, Ward> searchRelative(String info) {
        HashMap<Integer, Ward> result = new HashMap<>();
        for (var k : list.keySet()) {
            var w = list.get(k);
            if (w.getName().toLowerCase().contains(info.toLowerCase())) {
                result.put(k, w);
            }
        }
        return result;
    }
}
