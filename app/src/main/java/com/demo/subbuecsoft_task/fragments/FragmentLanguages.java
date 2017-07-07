package com.demo.subbuecsoft_task.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.subbuecsoft_task.R;
import com.demo.subbuecsoft_task.Response.Languages;
import com.demo.subbuecsoft_task.adapter.LanguageAdapter;
import com.demo.subbuecsoft_task.listener.OnLanguageClickListener;
import com.demo.subbuecsoft_task.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subbu on 7/7/17.
 */

public class FragmentLanguages extends Fragment implements OnLanguageClickListener {
    private static final String TAG = FragmentLanguages.class.getSimpleName();

    private AppCompatActivity mActivity;
    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;
    private List<Languages> listLanguages = new ArrayList<>();
    private LanguageAdapter mAdapter;
    private Bundle mBundle;
    private String strLanguages="";

    private View rootView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mActivity = (AppCompatActivity) getActivity();
            mBundle = getArguments();
            if (mBundle!=null){
                strLanguages = mBundle.getString(AppUtils.ARGS_LANGUAGE_JSON);
                listLanguages = AppUtils.convertJsonToList(strLanguages);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_languages,container,false);
        initUi();
        return rootView;
    }

    private void initUi() {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_languages);
        if(listLanguages.size()>0){
            mAdapter = new LanguageAdapter(mActivity,listLanguages,this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity,RecyclerView.VERTICAL,false);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }else Log.e(TAG," list is empty");

    }

    @Override
    public void onStart() {
        super.onStart();
        setupActionbar();
    }

    private void setupActionbar() {
        try {
            mToolbar = (Toolbar) mActivity.findViewById(R.id.toolbar);
            mToolbar.setTitle(R.string.lbl_languages);
            mActivity.setSupportActionBar(mToolbar);
            mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void onItemClick(int position) {
        Log.e(TAG,"onItemClick "+ position);
        Bundle bundle = new Bundle();
        bundle.putString(AppUtils.ARGS_REQUEST_STRING,listLanguages.get(position).getId());
        Fragment _fragment = new FragmentViewLanguage();
        _fragment.setArguments(bundle);
        FragmentTransaction _transaction = mActivity.getSupportFragmentManager().beginTransaction();
        _transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        _transaction.replace(R.id.frame_container,_fragment,AppUtils.TAG_FRAGMNENT_VIEW_LANGUAGE);
        _transaction.addToBackStack(AppUtils.TAG_FRAGMNENT_VIEW_LANGUAGE);
        _transaction.commit();
    }
}
