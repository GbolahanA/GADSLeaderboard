package com.gads.gbolahan.gadsleaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gads.gbolahan.gadsleaderboard.retro.Learner;
import com.gads.gbolahan.gadsleaderboard.ui.main.PageViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView mRecyclerItems;
    private LinearLayoutManager mLayoutManager;
    private LearnerRecyclerAdapter mLearnerRecyclerAdapter;
    APIInterface mAPIInterface;

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance (int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView (
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerItems = root.findViewById(R.id.list_learners);
        
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged (@Nullable String s) {
                textView.setText(s);
            }
        });

        getLearnerData();

        return root;
    }

    private void getLearnerData () {
        mAPIInterface = APIClient.getClient().create(APIInterface.class);

        Call<List<Learner>> call = mAPIInterface.getLearners();
        call.enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse (Call<List<Learner>> call, Response<List<Learner>> response) {
                List<Learner> learnerList = response.body();
//                Toast.makeText(getContext(), response.body().get(0).getName(), Toast.LENGTH_LONG).show();
                initializeDisplayContent(learnerList);
            }

            @Override
            public void onFailure (Call<List<Learner>> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void initializeDisplayContent (List<Learner> learnerList) {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLearnerRecyclerAdapter = new LearnerRecyclerAdapter(getContext(), learnerList);

        displayLeaderBoard();
    }

    private void displayLeaderBoard () {
        mRecyclerItems.setLayoutManager(mLayoutManager);
        mRecyclerItems.setAdapter(mLearnerRecyclerAdapter);
    }
}