package com.techflitter.assignments;

import com.techflitter.assignments.models.Container;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface IWebService {
    @GET("gifs/search")
    Call<Container> getSearchContent(@QueryMap HashMap<String, String> parse);
}
