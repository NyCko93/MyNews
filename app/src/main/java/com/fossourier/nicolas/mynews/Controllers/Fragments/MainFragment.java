package com.fossourier.nicolas.mynews.Controllers.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fossourier.nicolas.mynews.Controllers.Activities.WebViewActivity;
import com.fossourier.nicolas.mynews.Models.Article;
import com.fossourier.nicolas.mynews.Models.Result;
import com.fossourier.nicolas.mynews.R;
import com.fossourier.nicolas.mynews.Utils.NewYorkTimesStreams;
import com.fossourier.nicolas.mynews.Views.ArticleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A placeholder fragment containing a simple view.
 */
@SuppressWarnings("ConstantConditions")
public class MainFragment extends Fragment {


    private static final String POSITION = "POSITION";
    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView;
    Disposable disposable;
    RecyclerView.Adapter<com.fossourier.nicolas.mynews.Views.ArticleViewHolder> mAdapter;
    private List<Result> listArticles = new ArrayList<>();
    private int mPosition;

    public MainFragment() {
    }

    public static MainFragment newInstance(int position) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView(); // Call during UI creation
        assert getArguments() != null;
        mPosition = getArguments().getInt(POSITION);
        Log.d(TAG, "onCreateView: " + mPosition);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        executeHttpRequestWithFragmentAccorded();
        super.onViewCreated(view, savedInstanceState);
    }

    // Request for TopStories
    // section == arts, automobiles, books, business, fashion, food, health, home, insider, magazine, movies, national, nyregion, obituaries,
    // opinion, politics, realestate, science, sports, sundayreview, technology, theater, tmagazine, travel, upshot, world
    public void executeHttpRequestTopStories() {
        NewYorkTimesStreams.streamTopStories("science").subscribe(new Observer<Article>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Article article) {
                updateUI(article);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
            }
        });
    }

    // Request for MostPopular
    // timePeriodDays == 1, 7 or 30 (days)
    public void executeHttpRequestMostPopular() {
        NewYorkTimesStreams.streamMostPopular("30").subscribe(new Observer<Article>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Article article) {
                updateUI(article);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
            }
        });
    }

    // Request for Business
    // Section of TopStories
    public void executeHttpRequestBusiness() {
        NewYorkTimesStreams.streamBusiness("business").subscribe(new Observer<Article>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Article article) {
                updateUI(article);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
            }
        });
    }

    // Call request in accord with the position of ViewPager
    private void executeHttpRequestWithFragmentAccorded(){
        switch (mPosition)
        {
            case 0:
                executeHttpRequestTopStories();
                Log.e("TAG", "executeHttpRequestWithFragmentAccorded >> topstories");
                break;
            case 1:
                executeHttpRequestMostPopular();
                Log.e("TAG", "executeHttpRequestWithFragmentAccorded >> mostpopular");
                break;
            case 2:
                executeHttpRequestBusiness();
                Log.e("TAG", "executeHttpRequestWithFragmentAccorded >> moviereviews");
                break;
        }
    }


    private void updateUI(Article article) {
        displayArticle();
        listArticles.clear();
        listArticles.addAll(article.getResult());
        mAdapter.notifyDataSetChanged();
    }

    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {

        this.listArticles = new ArrayList<>();
        this.mAdapter = new ArticleAdapter(this.listArticles, Glide.with(getActivity()),this::recyclerViewOnClick);
        this.recyclerView.setAdapter(this.mAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // Start Article Activity when clicked on a RecyclerView item to display it in a WebView
    public void recyclerViewOnClick(int position) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        String url = listArticles.get(position).getUrl();
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    // Method to manage the display of article
    private void displayArticle() {
        if (listArticles.size() < 1)
            Toast.makeText(getActivity(), "Vous n'avez pas d'article à afficher", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }
}