package com.fossourier.nicolas.mynews.Controllers.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class ResultOfSearchFragment extends Fragment implements View.OnClickListener,
        ResultOfSearchAdapter.onSearchArticleAdapterListener {

    @BindView(R.id.result_of_search_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_search_result_swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.sr_textview_empty)
    TextView mTextView;
    private List<Doc> response;
    private SearchArticle mSearchArticle;
    private ResultOfSearchAdapter mResultOfSearchAdapter;
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
            mResultOfSearchFragmentListener = (ResultOfSearchFragmentListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_result_of_search,
                container, false);
        ButterKnife.bind(this, view);
        configureRecyclerView();
        configureSwipeRefreshLayout();
        updateUI();
        getBundle();
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
        this.response = new ArrayList<>();
        this.mResultOfSearchAdapter = new ResultOfSearchAdapter(this.response, Glide.with(this), this);
        this.mRecyclerView.setAdapter(this.mResultOfSearchAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

      //------------------------------//
     // Configure SwipeRefreshLayout //
    //------------------------------//
    private void configureSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(this::updateUI);
    }


      //-----------------------------------------//
     // Update UI with List of result of search //
    //-----------------------------------------//
    private void updateUI() {
        // List of RecyclerView
        if ( response.size() >= 1) {
            response.clear();
        }
        // Add articles of mSearchArticle (result of search) in response for display in RecyclerView
        if (mSearchArticle.getResponse().getDocs().size() >= 1) {
            response.addAll(mSearchArticle.getResponse().getDocs());

            // If response == 0, show a toast with "NO RESULT"
            if (response.size() == 0) {
                mTextView.setVisibility(View.VISIBLE);
                mTextView.setText(R.string.no_result);
            }
            mResultOfSearchAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onClick(View view) {
    }

      //----------------//
     // Start Callback //
    //----------------//
    @Override
    public void onArticleClicked(Doc resultTopStories) {
        mResultOfSearchFragmentListener.callbackSearchArticle(resultTopStories);
    }

      //----------//
     // Callback //
    //----------//
    public interface ResultOfSearchFragmentListener {
        void callbackSearchArticle(Doc SearchArticle);
    }
}
