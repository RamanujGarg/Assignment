package com.techflitter.assignments;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseModelHandler<T> {

    protected INetwork iNetwork;
    protected INetworkList iNetworkList;

    protected Callback<List<T>> actionOnResponseListCallBack() {
        return new Callback<List<T>>() {
            @Override
            public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                if (response == null) {
                    iNetworkList.onNullListResponse();
                }
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        iNetworkList.onNullListResponse();
                    }
                    if (response.body() != null) {
                        iNetworkList.onResponseList(response);
                    } else {
                        iNetworkList.onErrorList(response);
                    }
                } else {
                    iNetworkList.onErrorList(response);
                }
            }

            @Override
            public void onFailure(Call<List<T>> call, Throwable t) {
                Log.d("BaseModelHandler", t.getMessage());
                iNetworkList.onFailureList(t);
            }
        };
    }

    protected Callback<T> actionOnResponseCallBack() {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response == null) {
                    iNetwork.onNullResponse();
                }
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        iNetwork.onNullResponse();
                    }
                    if (response.body() != null) {
                        iNetwork.onResponse(response);
                    } else {
                        iNetwork.onError(response);
                    }
                } else {
                    iNetwork.onError(response);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                iNetwork.onFailure(t);
            }
        };
    }
}
