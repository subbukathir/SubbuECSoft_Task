package com.demo.subbuecsoft_task.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.demo.subbuecsoft_task.R;
import com.demo.subbuecsoft_task.utils.AppUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by subbu on 7/7/17.
 */

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = SplashScreen.class.getSimpleName();

    private static final int SPLASH_TIME_OUT = 2000;

    private InputStream is;
    private Writer writer = new StringWriter();
    private char[] buffer = new char[1024];
    private String jsonString = "";
    private Context mContext;
    Reader reader;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;
        new PrefetchJsonData().execute();
    }

    private class PrefetchJsonData extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            jsonString = writer.toString();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    intent.putExtra(AppUtils.ARGS_LANGUAGE_JSON,jsonString);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);

        }

        @Override
        protected Void doInBackground(Void... agrs) {

            is = mContext.getResources().openRawResource(R.raw.language);
            try {
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                is.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
    }
}
