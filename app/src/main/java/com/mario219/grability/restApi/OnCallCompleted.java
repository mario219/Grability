package com.mario219.grability.restApi;

import android.content.Context;

import com.mario219.grability.Main;
import com.mario219.grability.response.ResponseObject;

/**
 * Created by marioalejndro on 25/01/17.
 */

public interface OnCallCompleted {

    public void onCallCompleted(ResponseObject response, Main activity, Context context);

}
