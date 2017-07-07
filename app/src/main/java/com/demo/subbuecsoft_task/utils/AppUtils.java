package com.demo.subbuecsoft_task.utils;

import android.util.Log;

import com.demo.subbuecsoft_task.Response.Languages;
import com.demo.subbuecsoft_task.Response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by subbu on 7/7/17.
 */

public class AppUtils {
    private static final String TAG = AppUtils.class.getSimpleName();
    //Shared preference variables
    public static final String SHARED_PREFS = "shared_preference";

    public static final String SHARED_AR_AE = "shared_ar_ae";
    public static final String SHARED_ZH_CN = "shared_zh_cn";
    public static final String SHARED_EN_US = "shared_en_us";
    public static final String SHARED_ES_US = "shared_es_us";
    public static final String SHARED_IS_US = "shared_is_us";

    //Ags variables
    public static final String ARGS_LANGUAGE_JSON = "arg_language_json";
    public static final String ARGS_REQUEST_STRING = "arg_request_string";


    public static final String TAG_FRAGMNENT_VIEW_LANGUAGE = "tag_view_language";

    public static final String IS_NETWORK_AVAILABLE = "is_network_available";
    public static final String NETWORK_AVAILABLE = "network_available";
    public static final String NETWORK_NOT_AVAILABLE = "network_not_available";

    public static final String APP_NAME = "APP_NAME";
    public static final String LABEL_BOOKMARKS_ADDED = "LABEL_BOOKMARKS_ADDED";


    public static String convertToJson(List<Response> listLanguages)
    {
        Log.e(TAG,"convertToJson");
        Gson gson = new GsonBuilder().create();
        JsonArray languageArray = gson.toJsonTree(listLanguages).getAsJsonArray();

        Log.e(TAG,"response json array :"+ languageArray.toString());
        return languageArray.toString();
    }

    //conversion of json string to language list
    public static List<Response> convertJsonToResponseList(String jsonArray)
    {
        Log.e(TAG,"convertJsonToResponseList");

        Type listType = new TypeToken<List<Response>>() {}.getType();
        List<Response> listResponseLanguages = new Gson().fromJson(jsonArray.toString(), listType);

        //Log.e(TAG,"response collection array :"+ listResponseLanguages.get(0).getCategory());
        Log.e(TAG,"converting");
        return listResponseLanguages;
    }

    //conversion of json string to language list
    public static List<Languages> convertJsonToList(String jsonArray)
    {
        Log.e(TAG,"convertJsonToList");

        Type listType = new TypeToken<List<Languages>>() {}.getType();
        List<Languages> listLanguages = new Gson().fromJson(jsonArray.toString(), listType);

        return listLanguages;
    }

}
