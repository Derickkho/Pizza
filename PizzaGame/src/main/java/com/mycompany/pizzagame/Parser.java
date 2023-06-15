/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzagame;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Derick
 */
public class Parser {
    public String parseJSON(String json) {
        String hasil = "";
        JSONArray ar = new JSONArray(json);
        for (int i=0;i<ar.length();i++) {
            JSONObject ao = ar.getJSONObject(i);
             hasil = ao.getString("email");
        }
        return hasil;
}
}
