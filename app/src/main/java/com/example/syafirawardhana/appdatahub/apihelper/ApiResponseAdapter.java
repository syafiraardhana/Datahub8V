package com.example.syafirawardhana.appdatahub.apihelper;

import com.example.syafirawardhana.appdatahub.apihelper.model.ApiResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Syafira W Ardhana on 14/04/2018.
 */

class ApiResponseAdapter implements JsonDeserializer<ApiResponse> {
    @Override
    public ApiResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
