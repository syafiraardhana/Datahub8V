package com.example.syafirawardhana.appdatahub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
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
import com.example.syafirawardhana.appdatahub.apihelper.model.JobModel;
import com.example.syafirawardhana.appdatahub.apihelper.model.SurveyModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobListActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    BaseService mApiService;
    Context mContext;
    GridLayoutManager gridLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;

    private String TAG = MainActivity.class.getSimpleName();
    private static String url = "https://api-dev.datahub.id/api/v2/jobs/assign";
    private JobListAdapter mAdapter;
    private RecyclerView rvJobList;
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);
        this.setTitle("JOB-LIST");

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });

        Intent intent = getIntent();
        sessionId = intent.getStringExtra("sessionid");

        rvJobList = findViewById(R.id.rvJobList);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        progressDialog  =   new ProgressDialog(this);

        gridLayoutManager = new GridLayoutManager(this, 1);
        rvJobList.setLayoutManager(gridLayoutManager);
        mAdapter = new JobListAdapter(this, sessionId);
        rvJobList.setAdapter(mAdapter);

        getJobList();

    }
    private void getJobList(){
        progressDialog.show();
        progressDialog.setMessage("Sedang mengambil data JobList");
        Log.d("TAG", "you here 2 " + sessionId);

        mApiService.surveyRequest("Basic " + sessionId ).enqueue(new Callback<ApiResponse<List<SurveyModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<SurveyModel>>> call, Response<ApiResponse<List<SurveyModel>>> response) {
                mAdapter.setData(response.body().getData());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<SurveyModel>>> call, Throwable t) {
                Log.e(JobListActivity.class.getSimpleName(), "on error " + t);
                Toast.makeText(JobListActivity.this, "Error fetching survey data", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
