package com.deeppomal.basistask;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetData {

    //GET method to retrieve data from the API
    @GET("anishbajpai014/d482191cb4fff429333c5ec64b38c197/raw/b11f56c3177a9ddc6649288c80a004e7df41e3b9/HiringTask.json")
    Call<String> getAllData();
}
