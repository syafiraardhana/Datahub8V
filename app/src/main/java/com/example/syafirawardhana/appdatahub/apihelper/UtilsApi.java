package com.example.syafirawardhana.appdatahub.apihelper;

/**
 * Created by Syafira W Ardhana on 29/03/2018.
 */

public class UtilsApi {
    public static final String BASE_URL_API = "https://api-dev.datahub.id/api/v2/";

    public static BaseService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API)
                .create(BaseService.class);
    }
}