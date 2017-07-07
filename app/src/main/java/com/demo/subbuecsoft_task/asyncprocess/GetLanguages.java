package com.demo.subbuecsoft_task.asyncprocess;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import com.demo.subbuecsoft_task.Response.Response;
import com.demo.subbuecsoft_task.listener.LanguageRecievedListener;
import com.demo.subbuecsoft_task.rest_api.ApiClient;
import com.demo.subbuecsoft_task.rest_api.ApiConstant;
import com.demo.subbuecsoft_task.rest_api.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by subbu on 7/7/17.
 */

public class GetLanguages {
    private static final String TAG = GetLanguages.class.getSimpleName();
    private Context mContext;
    private LanguageRecievedListener mCallback;
    private String strRequest="";
    private Fragment mFragment;

    private List<Response> mResponse = new ArrayList<>();

    private ApiInterface mInterface;
    private Call<List<Response>> callLanguages;

    public GetLanguages(Context context, Fragment _fragment,String strReq,LanguageRecievedListener listener){

        this.mContext = context;
        this.mCallback = listener;
        this.strRequest = strReq;
        this.mFragment = _fragment;
        mInterface = ApiClient.getClient().create(ApiInterface.class);
        callLanguages = mInterface.getLanguageResult(ApiConstant.TRANSLATION+strReq);
    }

    public void getLanguageResult(){
        Log.e(TAG,"getLanguageResult");
        try {
            callLanguages.enqueue(new Callback<List<Response>>() {
                @Override
                public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                    if(response.isSuccessful()){
                        mResponse = response.body();
                        Log.e(TAG,"success "+ mResponse.get(0).getCategory());
                        mCallback.onDataRecivedSuccess(mResponse);
                    }
                    else{
                        Log.e(TAG,"isNotsuccess");
                        mCallback.onDataRecivedError(response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Response>> call, Throwable t) {

                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
