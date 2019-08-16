package com.fossourier.nicolas.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.fossourier.nicolas.mynews.Models.Article;
import com.fossourier.nicolas.mynews.R;
import com.fossourier.nicolas.mynews.Utils.NewYorkTimesStreams;

import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    // 4 - Declare Subscription
    private Disposable disposable;

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }
}