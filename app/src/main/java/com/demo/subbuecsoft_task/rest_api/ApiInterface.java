package com.demo.subbuecsoft_task.rest_api;

import com.demo.subbuecsoft_task.Response.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

import static android.R.attr.value;

/**
 * Created by subbu on 7/7/17.
 */

public interface ApiInterface {
    @GET()
    Call<List<Response>> getLanguageResult(@Url String str);
}
