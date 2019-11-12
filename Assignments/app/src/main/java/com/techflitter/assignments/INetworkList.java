package com.techflitter.assignments;

import java.util.List;

import retrofit2.Response;

public interface INetworkList<T> {
    void onResponseList(Response<List<T>> response);

    void onErrorList(Response<List<T>> response);

    void onNullListResponse();

    void onFailureList(Throwable t);
}