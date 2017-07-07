package com.demo.subbuecsoft_task.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.subbuecsoft_task.R;
import com.demo.subbuecsoft_task.Response.Response;
import com.demo.subbuecsoft_task.adapter.LanguageAdapter;
import com.demo.subbuecsoft_task.asyncprocess.GetLanguages;
import com.demo.subbuecsoft_task.listener.LanguageRecievedListener;
import com.demo.subbuecsoft_task.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subbu on 7/7/17.
 */

public class FragmentViewLanguage extends Fragment implements LanguageRecievedListener {
    private static final String TAG = FragmentViewLanguage.class.getSimpleName();

    private AppCompatActivity mActivity;
    private Toolbar mToolbar;

    private TextView tv_welcome,tv_app_description;
    private List<Response> listLanguagesResult = new ArrayList<>();
    private LanguageAdapter mAdapter;
    private Bundle mBundle;
    private String strLanguageRequest="";

    private LinearLayout ll_data_found,ll_error;
    private AppCompatButton btn_retry;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private GetLanguages mGetLanguages;
    private ProgressDialog progressDialog;

    private View rootView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mActivity = (AppCompatActivity) getActivity();
            mPreferences = mActivity.getSharedPreferences(AppUtils.SHARED_PREFS, Context.MODE_PRIVATE);
            mEditor = mPreferences.edit();

            mBundle = getArguments();
            if (mBundle!=null){
                strLanguageRequest = mBundle.getString(AppUtils.ARGS_REQUEST_STRING);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_language,container,false);
        initUi();
        getResultData();
        return rootView;
    }

    private void initUi() {
        tv_welcome = (TextView) rootView.findViewById(R.id.tv_welcome);
        tv_app_description = (TextView) rootView.findViewById(R.id.tv_app_description);

        ll_data_found = (LinearLayout) rootView.findViewById(R.id.ll_data_found);
        ll_error = (LinearLayout) rootView.findViewById(R.id.ll_error);
        btn_retry = (AppCompatButton) rootView.findViewById(R.id.btn_retry);
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getResultData();
            }
        });
        ll_data_found.setVisibility(View.GONE);
        ll_error.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        setupActionbar();
    }

    private void setupActionbar() {
        try {
            mToolbar = (Toolbar) mActivity.findViewById(R.id.toolbar);
            mToolbar.setTitle(R.string.lbl_view_languages);
            mActivity.setSupportActionBar(mToolbar);
            mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.onBackPressed();
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void getResultData(){
        String isNwAvail = mPreferences.getString(AppUtils.IS_NETWORK_AVAILABLE,"");
        if(isNwAvail.equals(AppUtils.NETWORK_AVAILABLE)){
            showProgressDialog();
            mGetLanguages = new GetLanguages(mActivity,FragmentViewLanguage.this,strLanguageRequest,this);
            mGetLanguages.getLanguageResult();
        }
        else{
            showDataFromOfflineStorage();
        }

    }

    private void showDataFromOfflineStorage() {
        Log.e(TAG,"showDataFromOfflineStorage " + "request string " + strLanguageRequest);
        switch (strLanguageRequest){
            case "ar_AE": {
                String jsonString = mPreferences.getString(AppUtils.SHARED_AR_AE,null);
                if(jsonString !=null){
                    listLanguagesResult = AppUtils.convertJsonToResponseList(jsonString);
                    if(listLanguagesResult.size()>0) setProperties();
                }
                else showError();


            }
            break;
            case "zh_CN":{
                String jsonString = mPreferences.getString(AppUtils.SHARED_ZH_CN,null);
                if(jsonString !=null){
                    listLanguagesResult = AppUtils.convertJsonToResponseList(jsonString);
                    if(listLanguagesResult.size()>0) setProperties();
                }
                else showError();}
            break;
            case "en_US":{
                String jsonString = mPreferences.getString(AppUtils.SHARED_EN_US,null);
                if(jsonString !=null){
                    listLanguagesResult = AppUtils.convertJsonToResponseList(jsonString);
                    if(listLanguagesResult.size()>0) setProperties();
                }
                else showError();}
            break;
            case "es_US":{
                String jsonString = mPreferences.getString(AppUtils.SHARED_ES_US,null);
                if(jsonString !=null){
                    listLanguagesResult = AppUtils.convertJsonToResponseList(jsonString);
                    if(listLanguagesResult.size()>0) setProperties();
                }
                else showError();}
            break;
            case "is_IS":{
                String jsonString = mPreferences.getString(AppUtils.SHARED_IS_US,null);
                if(jsonString !=null){
                    listLanguagesResult = AppUtils.convertJsonToResponseList(jsonString);
                    if(listLanguagesResult.size()>0) setProperties();
                }
                else showError();}
            break;
        }
    }

    private void showError() {
        ll_data_found.setVisibility(View.GONE);
        ll_error.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDataRecivedSuccess(List<Response> responseList) {
        Log.e(TAG,"onDataRecivedSuccess");
        hideProgressDialog();
        listLanguagesResult = responseList;
        storeLocally();
        setProperties();
    }

    private void storeLocally() {
        switch (strLanguageRequest){
            case "ar_AE": {
                String strjson = AppUtils.convertToJson(listLanguagesResult);
                mEditor.putString(AppUtils.SHARED_AR_AE, strjson);
                mEditor.commit();
            }
                break;
            case "zh_CN":{
                String strjson = AppUtils.convertToJson(listLanguagesResult);
                mEditor.putString(AppUtils.SHARED_ZH_CN,strjson);
                mEditor.commit();}
                break;
            case "en_US":{
                String strjson = AppUtils.convertToJson(listLanguagesResult);
                mEditor.putString(AppUtils.SHARED_EN_US,strjson);
                mEditor.commit();}
            break;
            case "es_US":{
                String strjson = AppUtils.convertToJson(listLanguagesResult);
                mEditor.putString(AppUtils.SHARED_ES_US,strjson);
                mEditor.commit();}
            break;
            case "is_IS":{
                String strjson = AppUtils.convertToJson(listLanguagesResult);
                mEditor.putString(AppUtils.SHARED_IS_US,strjson);
                mEditor.commit();}
            break;
        }
    }

    @Override
    public void onDataRecivedError(String strErr) {
        Log.e(TAG,"onDataRecivedError " + strErr);
        hideProgressDialog();
    }

    private void setProperties(){
        if(listLanguagesResult.size()>0){
            for(Response response: listLanguagesResult){
                if(response.getKey().equals(AppUtils.APP_NAME))tv_welcome.setText(response.getValue());
                if(response.getKey().equals(AppUtils.LABEL_BOOKMARKS_ADDED))tv_app_description.setText(response.getValue());
            }
            ll_data_found.setVisibility(View.VISIBLE);
            ll_error.setVisibility(View.GONE);
        }


    }
    private void showProgressDialog(){
        progressDialog= new ProgressDialog(mActivity);
        progressDialog.setMessage("loading");
        if(!progressDialog.isShowing()) progressDialog.show();
    }
    private void hideProgressDialog(){
        if(progressDialog.isShowing())progressDialog.hide();
    }
}
