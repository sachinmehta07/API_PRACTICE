package com.example.api_practice;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCall {

    private Activity mActivity;
    private Call<ResponseModel> mApiCallInterface;
    private OnResponseHandler mOnResponseHandler;

    private ArrayList<ResponseModel> modelArrayList;

    public ApiCall(Activity activity, Call<ResponseModel> apiCallInterface, OnResponseHandler onResponseHandler) {
        this.mActivity = activity;
        this.mApiCallInterface = apiCallInterface;
        this.mOnResponseHandler = onResponseHandler;

        // Make the API call
        apiCallInterface.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    ResponseModel responseModel = response.body();
                    if (responseModel != null) {
                        if (responseModel.getStatus() != null && responseModel.getStatus().equals("success")) {
                            List<ResponseModel> dataList = responseModel.getData();
                            if (dataList != null && dataList.size() > 0) {
                                mOnResponseHandler.onSuccess(dataList, responseModel);
                            } else {
                                mOnResponseHandler.onSuccess(new ArrayList<Data>(), responseModel);
                            }
                        } else {
                            mOnResponseHandler.onSuccess(new ArrayList<Data>(), responseModel);
                        }
                    } else {
                        mOnResponseHandler.onSuccess(new ArrayList<Data>(), responseModel);
                    }
                } else {
                    mOnResponseHandler.onFailed();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                mOnResponseHandler.onFailed();
            }
        });
    }

    // Interface to handle API response and failure
    public interface OnResponseHandler {
        void onSuccess(List<Data> dataList, ResponseModel responseModel);

        void onFailed();

        void onFailure();
    }
}
