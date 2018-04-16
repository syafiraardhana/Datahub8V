package com.example.syafirawardhana.appdatahub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.syafirawardhana.appdatahub.apihelper.BaseService;
import com.example.syafirawardhana.appdatahub.apihelper.UtilsApi;
import com.example.syafirawardhana.appdatahub.apihelper.model.ApiResponse;
import com.example.syafirawardhana.appdatahub.apihelper.model.DetailModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private ListView lv;
    BaseService mApiService;
    Context mContext;
    GridLayoutManager gridLayoutManager;

    ArrayList<DetailModel> ArrayDetail = new ArrayList<>();

    private String TAG = MainActivity.class.getSimpleName();
    private static String url = "http://api-dev.datahub.id/api/v2/reports/survey";
    private DetailAdapter mAdapter;
    private RecyclerView rvDetailList;
    String surveyId,sessionId;
    String questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);
        this.setTitle("REPORTS / SURVEY");

        Intent intent = getIntent();
        surveyId = intent.getStringExtra("surveyId");
        sessionId =intent.getStringExtra("sessionId");
        questions = intent.getStringExtra("questions");
        intent.putExtra("questions",questions);

        rvDetailList = findViewById(R.id.rvDetailList);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        progressDialog  =   new ProgressDialog(this);

        ArrayDetail = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(this, 1);
        rvDetailList.setLayoutManager(gridLayoutManager);
        mAdapter = new DetailAdapter(this, ArrayDetail, sessionId);
        rvDetailList.setAdapter(mAdapter);

        getDetail();

    }

    private void getDetail() {
        progressDialog.show();
        progressDialog.setMessage("sedang mengambil data detail");
        Log.d("TAG","detail" + surveyId);

        String id = getIntent().getStringExtra("surveyId");

        mApiService.detailRequest("Basic " + sessionId, id).enqueue(
                new Callback<ApiResponse<List<DetailModel>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<DetailModel>>> call, Response<ApiResponse<List<DetailModel>>> response) {
                        Log.d(DetailActivity.class.getSimpleName(), "on response");
                        List<DetailModel> detailModels = response.body().getData();
                        progressDialog.dismiss();
                        mAdapter.setData(detailModels);
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<DetailModel>>> call, Throwable t) {
                        Log.e(DetailActivity.class.getSimpleName(), "on error: " + t);
                        Toast.makeText(DetailActivity.this, "error fetching detail data", Toast.LENGTH_SHORT).show();
                    }
                }

        );


    }
}
