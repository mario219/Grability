package com.mario219.grability.restApi;

import com.mario219.grability.response.ResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by marioalejndro on 25/01/17.
 */

public interface RestCall {
    @GET("reddits.json")
    Call<ResponseObject> requestApps();
}
