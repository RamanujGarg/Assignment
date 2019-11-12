package com.techflitter.assignments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    //Singleton instance of rest client
    private static RestClient restClientInstance;
    private Retrofit retrofit;
    private IWebService iWebService;


    private RestClient() {

        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new NoModuleExclusionStrategy(false))
                .addDeserializationExclusionStrategy(new NoModuleExclusionStrategy(true))
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //("From Basic Token","withBasic");

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body());

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.Companion.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        iWebService = retrofit.create(IWebService.class);
    }

    public static RestClient getInstance() {
        if (restClientInstance == null) {
            restClientInstance = new RestClient();
        }
        synchronized (restClientInstance) {
            return restClientInstance;
        }
    }

    public static void clearInstance() {
        RestClient.restClientInstance = null;
    }

    IWebService getApiService() {
        return iWebService;
    }

    Retrofit getRetrofit() {
        return retrofit;
    }
}
