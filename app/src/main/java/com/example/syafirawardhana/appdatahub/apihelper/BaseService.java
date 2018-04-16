package com.example.syafirawardhana.appdatahub.apihelper;

import com.example.syafirawardhana.appdatahub.apihelper.model.ApiResponse;
import com.example.syafirawardhana.appdatahub.apihelper.model.DetailModel;
import com.example.syafirawardhana.appdatahub.apihelper.model.SurveyModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Syafira W Ardhana on 02/04/2018.
 */

public interface BaseService {
    @FormUrlEncoded
    @POST("surveyors/login")
    Call<ResponseBody> loginRequest(@Field("identifier") String email,
                                    @Field("password") String password);

    @GET("jobs/assign")
    Call<ApiResponse<List<SurveyModel>>> surveyRequest(@Header("Authorization") String auto);

//    @GET("surveys/{id}")
//    Call<ResponseBody> getSurvey(@Path("id") String id);

    @GET("reports/survey/{id}")
    Call<ApiResponse<List<DetailModel>>> detailRequest(@Header("Authorization") String auto, @Path("id") String id);
}
