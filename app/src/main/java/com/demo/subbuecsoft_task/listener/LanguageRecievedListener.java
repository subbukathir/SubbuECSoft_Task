package com.demo.subbuecsoft_task.listener;

import com.demo.subbuecsoft_task.Response.Response;

import java.util.List;

/**
 * Created by subbu on 7/7/17.
 */

public interface LanguageRecievedListener {
    void onDataRecivedSuccess(List<Response> responseList);
    void onDataRecivedError(String strErr);
}
