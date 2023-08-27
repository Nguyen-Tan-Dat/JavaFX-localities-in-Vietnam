package com.dat.models;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public record API(String url) {

    public JSONArray getJsonArray() {
        String dataJson = contentFromURL(url);
        if (dataJson == null) return null;
        return new JSONArray(dataJson);
    }

    private String contentFromURL(String url) {
        try {
            InputStream inputStream = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            int cp;
            while ((cp = reader.read()) != -1)
                stringBuilder.append((char) cp);
            return stringBuilder.toString();
        } catch (IOException e) {
            System.out.println("No internet access");
        }
        return null;
    }
}
