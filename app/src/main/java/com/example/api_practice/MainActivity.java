package com.example.api_practice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private ApiCall apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiCall = new ApiCall(this, new ApiInterface() {
            @Override
            public Call<ResponseModel> userdetails(int userId, String userTitle) {
                ApiClient apiClient = ApiClient.getApplication();
                return apiClient.getApiInterface().userdetails(userId, userTitle);
            }
        }, new ApiCall.OnResponseHandler() {
            @Override
            public void onSuccess(List<Data> arrayDatalist, ResponseModel responseModel) {
                // handle success
            }

            @Override
            public void onFailure() {
                // handle failure
            }
        });

        // make API call
        apiCall.getUserDetails(1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    }
}
