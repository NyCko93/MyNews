package com.fossourier.nicolas.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {


    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView;
    Disposable disposable;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private List<Result> listArticles;


    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView(); // - 4 Call during UI creation
        executeHttpRequestWithRetrofit(); // 5 - Execute stream after UI creation
        displayArticle();
        return view;
    }

    private void executeHttpRequestWithRetrofit() {
        NewYorkTimesStreams.streamFetchUserFollowing("science").subscribe(new Observer<Article>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Article article) {

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

    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
//        recyclerView = (RecyclerView) recyclerView.findViewById(R.id.fragment_main_recycler_view);
        // 3.1 - Reset list
        this.listArticles = new ArrayList<>();
        // 3.2 - Create adapter passing the list of users
        this.mAdapter = new ArticleAdapter(this.listArticles);
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.mAdapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(mLayoutManager);
    }

//    // Constructor of RecyclerView
//    private void buildRecyclerView() {
//        RecyclerView recyclerView = null;
//        recyclerView = (RecyclerView) recyclerView.findViewById(R.id.fragment_main_recycler_view);
//        new LinearLayoutManager(getActivity());
//        ArticleAdapter adapter=new ArticleAdapter(listArticles);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);
//
//    }

    // Method to manage the display of history
    private void displayArticle() {
        if (listArticles.size() > 0) {
            listArticles.remove(listArticles.size() - 1);
        }

        if (listArticles.size() < 1)
            Toast.makeText(getActivity(), "Vous n'avez pas d'historique à afficher", Toast.LENGTH_LONG).show();
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