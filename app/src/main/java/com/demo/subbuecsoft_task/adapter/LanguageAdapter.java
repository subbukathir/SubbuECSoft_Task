package com.demo.subbuecsoft_task.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.subbuecsoft_task.R;
import com.demo.subbuecsoft_task.Response.Languages;
import com.demo.subbuecsoft_task.listener.OnLanguageClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subbu on 7/7/17.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {
    private static final String TAG = LanguageAdapter.class.getSimpleName();

    private Context mContext;
    private List<Languages> listLanguages = new ArrayList<>();
    private OnLanguageClickListener mCallback;
    public LanguageAdapter(Context context, List<Languages> listLanguages, OnLanguageClickListener listener){
        this.mContext = context;
        this.listLanguages = listLanguages;
        this.mCallback = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_language,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tv_language.setText(listLanguages.get(position).getName());
        holder.tv_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLanguages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_language;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_language = (TextView) itemView.findViewById(R.id.tv_language);
        }
    }
}
