package com.example.movieapp.service;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "http://www.omdbapi.com/?i=tt3896198&apikey=";
    public static MovieDataService getService(){
        if (retrofit==null){
            retrofit =  new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieDataService.class);
    }

}
