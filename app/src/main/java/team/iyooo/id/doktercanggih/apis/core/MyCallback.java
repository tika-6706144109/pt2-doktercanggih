package team.iyooo.id.doktercanggih.apis.core;


import android.util.Log;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import team.iyooo.id.doktercanggih.IYOOApp;

/**
 * Created by tikafby on 2/28/16.
 */
public class MyCallback<T> implements Callback<T> {
    ApiCallback<T> apiCallback;
    private final static String TAG = "API";

    public MyCallback(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }


    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {
        apiCallback.onApiSuccess(response.body(), IYOOApp.GSON.toJson(response.body()));
        Log.d(TAG, "API CALL: " + response.raw().request().urlString());
    }

    @Override
    public void onFailure(Throwable t) {
        apiCallback.onApiError(t.getLocalizedMessage());
    }
}
