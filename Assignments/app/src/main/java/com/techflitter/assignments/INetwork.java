package com.techflitter.assignments;

import retrofit2.Response;

public interface INetwork<T> {
    void onResponse(Response<T> response);
    void onError(Response<T> response);
    void onNullResponse();
    void onFailure(Throwable t);
}
