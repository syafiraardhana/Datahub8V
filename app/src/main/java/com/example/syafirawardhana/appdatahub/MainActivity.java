package com.example.syafirawardhana.appdatahub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.syafirawardhana.appdatahub.apihelper.BaseService;
import com.example.syafirawardhana.appdatahub.apihelper.UtilsApi;
import com.example.syafirawardhana.appdatahub.apihelper.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText EditTextEmail, EditTextPassword;
    Button ButtonSignIn;
    String email, password;
    String sessionId;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogin;
    CheckBox savelogincheckbox;
    BaseService mApiService;
    Context mContext;

    ArrayList<User> users = new ArrayList<>();
    private String TAG = MainActivity.class.getSimpleName();
    private String url ="https://api-dev.datahub.id/api/v2/surveyors/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        EditTextEmail = findViewById(R.id.EditTextEmail);
        EditTextPassword = findViewById(R.id.EditTextPassword);
        ButtonSignIn = findViewById(R.id.ButtonSignIn);
        ButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        savelogincheckbox = findViewById(R.id.checkBox);
        editor = sharedPreferences.edit();

        savelogin = sharedPreferences.getBoolean("savelogin",true);
        if(savelogin==true){
            EditTextEmail.setText(sharedPreferences.getString("email",null));
            EditTextPassword.setText(sharedPreferences.getString("password",null));
        }
    }

    private void userLogin() {
        email = EditTextEmail.getText().toString().trim();
        password = EditTextPassword.getText().toString().trim();
        Toast.makeText(this,"Login success",Toast.LENGTH_SHORT).show();

        if(savelogincheckbox.isChecked()){
            editor.putBoolean("savelogin",true);
            editor.putString("email",email);
            editor.putString("password",password);
            editor.commit();
        }else{
            Toast.makeText(this,"Login error",Toast.LENGTH_SHORT).show();
        }
        postLogin();
    }

    private void postLogin() {
        mApiService.loginRequest(email, password)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {

                            User user = new User();
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONObject data = jsonObject.getJSONObject("data");

                            user.setId(data.getString("id"));
                            user.setUsername(data.getString("username"));
                            user.setEmail(data.getString("email"));
                            user.setSessionid(data.getString("session_id"));
                            sessionId = data.getString("session_id");

                            JSONArray roles = data.getJSONArray("roles");
                            for (int i = 0; i < roles.length(); i++) {
                                JSONObject role = roles.getJSONObject(i);
                                user.setRoleName(role.getString("name"));

                                JSONArray organizations = data.getJSONArray("organizations");
                                for (int a = 0; a < organizations.length(); a++) {
                                    JSONObject organization = organizations.getJSONObject(i);
                                    user.setGetOrganizationName(organization.getString("name"));
                                    user.setDisplayName(organization.getString("displayName"));
                                }
                            }
                            System.out.println("Display Name : " + user.getUsername());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        moveLayout();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("tag", "status user ERROR > " + t.toString());
                    }
                });
    }
    private void moveLayout() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("sessionid", sessionId);
        Log.d("TAG", "ceksessionid 1 " + sessionId);
        startActivity(intent);
    }
}
