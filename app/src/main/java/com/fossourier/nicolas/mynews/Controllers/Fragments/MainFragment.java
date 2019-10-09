package com.fossourier.nicolas.mynews.Controllers.Fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.fossourier.nicolas.mynews.Utils.Connectivity;
import com.fossourier.nicolas.mynews.Utils.NewYorkTimesStreams;
import com.fossourier.nicolas.mynews.Utils.SharedPreferences;
import com.fossourier.nicolas.mynews.Views.ArticleAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static androidx.constraintlayout.widget.Constraints.TAG;


@SuppressWarnings("ConstantConditions")
public class MainFragment extends Fragment {

    private static final String POSITION = "POSITION";
    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView;
    Disposable disposable;
    RecyclerView.Adapter<com.fossourier.nicolas.mynews.Views.ArticleViewHolder> mAdapter;
    private List<Result> listArticles = new ArrayList<>();
    private int mPosition;
    private String mSectionChoisen = "Section Choisen";
    private SharedPreferences mSharedPreferences;
    TextView textView;

    public MainFragment() {
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mSharedPreferences = SharedPreferences.getInstance(context);
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
        //-----------------------------------------------------------------------------------------------------------------------------------------//
       // Request for TopStories                                                                                                                  //
      //  section == arts, automobiles, books, business, fashion, food, health, home, insider, magazine, movies, national, nyregion, obituaries, //
     //   opinion, politics, realestate, science, sports, sundayreview, technology, theater, tmagazine, travel, upshot, world                   //
    //-----------------------------------------------------------------------------------------------------------------------------------------//
    public void executeHttpRequestTopStories() {
        NewYorkTimesStreams.streamTopStories("home").subscribe(new Observer<Article>() {

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
                displayArticle();
            }
        });
    }

       //-----------------------------------------//
      // Request for MostPopular                 //
     // timePeriodDays == 1, 7 or 30 (days)     //
    //-----------------------------------------//
    public void executeHttpRequestMostPopular() {
        NewYorkTimesStreams.streamMostPopular("7").subscribe(new Observer<Article>() {

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
                displayArticle();
            }
        });
    }

       //-----------------------//
      // Request for Section   //
     // Section of TopStories //
    //-----------------------//
    public void executeHttpRequestSection(final String sectionChoisen) {
        NewYorkTimesStreams.streamTopStories(sectionChoisen).subscribe(new Observer<Article>() {

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
                displayArticle();
            }
        });
    }

        //------------------------------------------------------------------------------------------------------------//
       // Call request in accord with the position of ViewPager                                                      //
      // First we check the internet connectivity                                                                   //
     // If it is good, we call the request otherwise we display a message to say that the connection does not work //
    //------------------------------------------------------------------------------------------------------------//
    private void executeHttpRequestWithFragmentAccorded(){
        switch (mPosition)
        {
            case 0: if (Connectivity.networkInfo(getActivity())){
                executeHttpRequestTopStories();
                Log.e("TAG", "executeHttpRequestWithFragmentAccorded >> topstories");
            } else {
                internetDoesNotWork();
            }break;
            case 1: if (Connectivity.networkInfo(getActivity())){
                executeHttpRequestMostPopular();
                Log.e("TAG", "executeHttpRequestWithFragmentAccorded >> mostpopular");} else {
                internetDoesNotWork();
            }break;
            case 2: if (Connectivity.networkInfo(getActivity())){
                ArrayList<String> listSection = mSharedPreferences.getListSection(0);
                if (listSection.size() < 1) {
                    executeHttpRequestSection("business");
                } else {
                    mSectionChoisen = listSection.get(0);
                    executeHttpRequestSection(mSectionChoisen);
                    Log.e("TAG", "executeHttpRequestWithFragmentAccorded >> fragment of section choisen");
                }
            } else {
                internetDoesNotWork();
            }break;
        }
    }

      //--------------------------------------------------------------------//
     // Here we display a message to say that the connection does not work //
    //--------------------------------------------------------------------//
    @SuppressLint("SetTextI18n")
    private void internetDoesNotWork() {
        textView.setVisibility(View.VISIBLE);
        textView.setText("Internet does not work");
    }

    private void updateUI(Article article) {
        listArticles.clear();
        listArticles.addAll(article.getResult());
        mAdapter.notifyDataSetChanged();
    }

      //------------------------//
     // Configure RecyclerView //
    //------------------------//
    private void configureRecyclerView() {

        this.listArticles = new ArrayList<>();
        this.mAdapter = new ArticleAdapter(this.listArticles, Glide.with(getActivity()),this::recyclerViewOnClick);
        this.recyclerView.setAdapter(this.mAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

      //---------------------------------------------------------------------------------------//
     // Start Article Activity when clicked on a RecyclerView item to display it in a WebView //
    //---------------------------------------------------------------------------------------//
    public void recyclerViewOnClick(int position) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        String url = listArticles.get(position).getUrl();
        intent.putExtra("URL", url);
        startActivity(intent);
    }

      //------------------------------------------//
     // Method for display a toast when size < 1 //
    //------------------------------------------//
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

      //-----------------------------------------------------------//
     // Method for update the fragment 2 with the section choisen //
    //-----------------------------------------------------------//
    public void updateFragmentSection(String section) {
        mSectionChoisen = section;
        executeHttpRequestSection(mSectionChoisen);

    }
}