package com.gads.gbolahan.gadsleaderboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gads.gbolahan.gadsleaderboard.retro.SkillIQ;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class SkillFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView mRecyclerItems;
    private LinearLayoutManager mLayoutManager;
    private SkillRecyclerAdapter mRecyclerAdapter;
    APIInterface mAPIInterface;

    public static SkillFragment newInstance (int index) {
        SkillFragment fragment = new SkillFragment();
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerItems = root.findViewById(R.id.list_learners);

        getLearnerData();

        return root;
    }

    private void getLearnerData () {
        mAPIInterface = APIClient.getClient().create(APIInterface.class);

        Call<List<SkillIQ>> call = mAPIInterface.getSkillIQ();
        call.enqueue(new Callback<List<SkillIQ>>() {
            @Override
            public void onResponse (Call<List<SkillIQ>> call, Response<List<SkillIQ>> response) {
                List<SkillIQ> learnerList = response.body();
//                Toast.makeText(getContext(), response.body().get(0).getName(), Toast.LENGTH_LONG).show();
                initializeDisplayContent(learnerList);
            }

            @Override
            public void onFailure (Call<List<SkillIQ>> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void initializeDisplayContent (List<SkillIQ> learnerList) {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerAdapter = new SkillRecyclerAdapter(getContext(), learnerList);

        displayLeaderBoard();
    }

    private void displayLeaderBoard () {
        mRecyclerItems.setLayoutManager(mLayoutManager);
        mRecyclerItems.setAdapter(mRecyclerAdapter);
    }
}