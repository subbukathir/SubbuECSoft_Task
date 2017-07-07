package com.demo.subbuecsoft_task.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.demo.subbuecsoft_task.R;
import com.demo.subbuecsoft_task.fragments.FragmentLanguages;
import com.demo.subbuecsoft_task.utils.AppUtils;
import com.demo.subbuecsoft_task.utils.ConnectivityStatus;

public class MainActivity extends AppCompatActivity {
private static final String TAG = MainActivity.class.getSimpleName();

    private Handler mHandler;
    private String jsonResult;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
                mPreferences = this.getSharedPreferences(AppUtils.SHARED_PREFS,MODE_PRIVATE);
                mEditor = mPreferences.edit();
                mHandler = new Handler();
                jsonResult = getIntent().getExtras().getString(AppUtils.ARGS_LANGUAGE_JSON);
            this.registerReceiver(receiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
            Log.e(TAG,"onCreate json data " + jsonResult);
                loadInitialFragment();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.registerReceiver(receiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(receiver);
    }

    private void loadInitialFragment() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Fragment _fragment = new FragmentLanguages();
                Bundle bundle = new Bundle();
                bundle.putString(AppUtils.ARGS_LANGUAGE_JSON,jsonResult);
                _fragment.setArguments(bundle);
                FragmentTransaction _transaction = getSupportFragmentManager().beginTransaction();
                _transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
                _transaction.replace(R.id.frame_container,_fragment,null);
                _transaction.commitAllowingStateLoss();
            }
        };

        if(runnable!=null){
            mHandler.post(runnable);
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(!ConnectivityStatus.isConnected(MainActivity.this))
            {
                Log.e(TAG,"not connected");
                mEditor.putString(AppUtils.IS_NETWORK_AVAILABLE,AppUtils.NETWORK_NOT_AVAILABLE);
                mEditor.commit();
            }
            else
            {
                Log.e(TAG,"connected");
                mEditor.putString(AppUtils.IS_NETWORK_AVAILABLE,AppUtils.NETWORK_AVAILABLE);
                mEditor.commit();
            }

        }
    };
}
