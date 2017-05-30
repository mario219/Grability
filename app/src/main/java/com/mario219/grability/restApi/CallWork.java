package com.mario219.grability.restApi;

import android.content.Context;
import android.util.Log;

import com.mario219.grability.Main;
import com.mario219.grability.response.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marioalejndro on 25/01/17.
 */

public class CallWork {

    private static final String TAG = CallWork.class.getSimpleName();
    private static final String url = "https://www.reddit.com/";
    private OnRestCompleted onRestCompleted;

    public CallWork (OnRestCompleted onRestCompleted){
        this.onRestCompleted = onRestCompleted;
    }

    public void work(Main activity, Context context) {

        final Main activity1 = activity;
        final Context context1 = context;
        //interceptor
        //HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                //.client(httpClient.build())
                .build();

        RestCall service = retrofit.create(RestCall.class);

        Call<ResponseObject> call = service.requestApps();
        call.enqueue(new Callback<ResponseObject>() {

            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try{
                    ResponseObject resObj = response.body();
                    Log.i(TAG, "done");
                    onRestCompleted.onCallCompleted(resObj, activity1, context1);
                }catch (Exception e){
                    Log.i(TAG, "Something went wrong with the response");
                    Log.e("Grability", "exception", e);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.i(TAG, "Couldn't make the request");
            }
        });

    }

}
