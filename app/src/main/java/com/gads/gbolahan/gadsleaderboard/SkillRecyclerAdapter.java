package com.gads.gbolahan.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gads.gbolahan.gadsleaderboard.retro.Learner;
import com.gads.gbolahan.gadsleaderboard.retro.SkillIQ;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SkillRecyclerAdapter extends RecyclerView.Adapter<SkillRecyclerAdapter.ViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private String mName;
    private String mInfo;
    private List<SkillIQ> mLearnerList;

    public SkillRecyclerAdapter (Context context, List<SkillIQ> learnerList) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mLearnerList = learnerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_skill_iq_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position) {
        mName = mLearnerList.get(position).getName();
        mInfo = mLearnerList.get(position).getScore() + " skill IQ Score, " + mLearnerList.get(position).getCountry();

        holder.mTextName.setText(mName);
        holder.mTextInfo.setText(mInfo);
    }

    @Override
    public int getItemCount () {
        return mLearnerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextName;
        public final TextView mTextInfo;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.text_learner_name);
            mTextInfo = itemView.findViewById(R.id.text_learner_info);

            final String name = mTextName.getText().toString();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Snackbar.make(v, name, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }
}
