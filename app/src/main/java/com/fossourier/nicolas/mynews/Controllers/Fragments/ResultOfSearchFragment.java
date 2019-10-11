package com.fossourier.nicolas.mynews.Controllers.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fossourier.nicolas.mynews.Models.Doc;
import com.fossourier.nicolas.mynews.Models.SearchArticle;
import com.fossourier.nicolas.mynews.R;
import com.fossourier.nicolas.mynews.Views.ResultOfSearchAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fossourier.nicolas.mynews.Controllers.Activities.NotiSearchActivity.SEARCHED_ARTICLE;

public class ResultOfSearchFragment extends Fragment implements View.OnClickListener, ResultOfSearchAdapter.ResultOfSearchRVOnClickListener {

    @BindView(R.id.result_of_search_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.sr_textview_empty)
    TextView mTextView;
    private List<Doc> listSearchArticle;
    private SearchArticle mSearchArticle;
    private ResultOfSearchAdapter mResultOfSearchAdapter;
    private ResultOfSearchFragmentListener mResultOfSearchRVOnClickListener;
    private ResultOfSearchFragmentListener mResultOfSearchFragmentListener;

    public ResultOfSearchFragment() {
        // Required empty public constructor
    }

    //-----------------//
    // Attach callback //
    //-----------------//
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        if (context instanceof ResultOfSearchFragmentListener) {
            mResultOfSearchRVOnClickListener = (ResultOfSearchFragmentListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_of_search, container, false);
        ButterKnife.bind(this, view);
        configureRecyclerView();
        getBundle();
        updateUI();
        return view;
    }

    //---------------------------------------------//
    // Get Bundle with options of searched article //
    //---------------------------------------------//
    private void getBundle() {
        if (getArguments() != null) {
            mSearchArticle = getArguments().getParcelable(SEARCHED_ARTICLE);
        }
    }

    //-------------------------//
    // Configure RecyclerView  //
    //-------------------------//
    private void configureRecyclerView() {
        this.listSearchArticle = new ArrayList<>();
        this.mResultOfSearchAdapter = new ResultOfSearchAdapter(this.listSearchArticle, Glide.with(this), this);
        this.mRecyclerView.setAdapter(this.mResultOfSearchAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //-----------------------------------------//
    // Update UI with List of result of search //
    //-----------------------------------------//
    private void updateUI() {
        listSearchArticle.clear();
        if (mSearchArticle.getResponse().getDocs().isEmpty()) {
            Toast.makeText(getActivity(), "Vous n'avez pas d'article à afficher", Toast.LENGTH_LONG).show();
        } else {
            listSearchArticle.addAll(mSearchArticle.getResponse().getDocs());
        }
        mResultOfSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onSearchArticleClick(int position) {
        mResultOfSearchRVOnClickListener.callbackSearchArticle(listSearchArticle.get(position));
    }


    public interface ResultOfSearchFragmentListener {
        void callbackSearchArticle(Doc SearchArticle);
    }
}
