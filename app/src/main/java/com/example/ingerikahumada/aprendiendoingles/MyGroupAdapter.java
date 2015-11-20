package com.example.ingerikahumada.aprendiendoingles;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by Ing. Erik Ahumada on 20/11/2015.
 */
public class MyGroupAdapter extends RecyclerView.Adapter<MyGroupAdapter.ViewHolder> {
    ArrayList<ParseObject> mDataset;

    public MyGroupAdapter(ArrayList<ParseObject> dataset){
        this.mDataset=dataset;
    }

    @Override
    public MyGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card_view_group, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTextView(mDataset.get(position).getString("name"));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;

        public ViewHolder(View v){
            super(v);
            mTextView=(TextView)v.findViewById(R.id.my_text);
        }

        public void bindTextView(String data){
            this.mTextView.setText(this.mTextView.getText().toString()+" "+data);
        }

    }
}
