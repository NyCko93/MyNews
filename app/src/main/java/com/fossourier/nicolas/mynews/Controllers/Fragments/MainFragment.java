package com.fossourier.nicolas.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    // Declare Subscription
    private Disposable disposable;

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView; // 1 - Declare RecyclerView

    // 2 - Declare list of users (GithubUser) & Adapter
    private List<Result> listArticles;
    private ArticleAdapter adapter;

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

        return view;

    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // 3 - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){
        // 3.1 - Reset list
        this.listArticles = new ArrayList<>();
        // 3.2 - Create adapter passing the list of users
        this.adapter = new ArticleAdapter(this.listArticles);
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.ArticleAdapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(List<Result> article){
        listArticles.addAll(article);
        adapter.notifyDataSetChanged();
    }
}