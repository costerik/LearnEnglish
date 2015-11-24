package com.example.ingerikahumada.aprendiendoingles;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
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
        holder.bindTextView(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextView,mTextViewProfessor,mTextViewLetter;

        public ViewHolder(View v){
            super(v);
            v.setOnClickListener(this);
            mTextView=(TextView)v.findViewById(R.id.group_cardview);
            mTextViewProfessor=(TextView)v.findViewById(R.id.professor_cardview);
            mTextViewLetter=(TextView)v.findViewById(R.id.letter_cardview);
        }

        public void bindTextView(ParseObject data){
            this.mTextView.setText(data.getString("name"));
            Log.d("RES", data.getParseObject("createdBy").getObjectId());
            try {
                this.mTextViewProfessor.setText(data.getParseObject("createdBy").fetchIfNeeded().get("name").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.mTextViewLetter.setText(data.getString("letters"));
        }

        @Override
        public void onClick(View v) {
            Log.d("VIEWHOLDER",mDataset.get(getPosition()).getString("name"));
            Toast.makeText(v.getContext(),mDataset.get(getPosition()).getString("name"),Toast.LENGTH_SHORT).show();
        }
    }
}
