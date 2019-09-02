package com.fossourier.nicolas.mynews.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.fossourier.nicolas.mynews.Models.Result;
import com.fossourier.nicolas.mynews.R;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fossourier.nicolas.mynews.R.drawable.nytimesdefault;

// For RecyclerView
public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_section) TextView textSection;
    @BindView(R.id.fragment_main_item_date) TextView textDate;
    @BindView(R.id.fragment_main_item_title) TextView textTitle;
    @BindView(R.id.fragment_main_item_image) ImageView imageArticle;

    private TextView title;
    private TextView articleAbstract;



    public ArticleViewHolder(View itemView) {

        super(itemView);

        ButterKnife.bind(this, itemView);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                WebView webView = (WebView) view.findViewById(R.id.WebView);
//                webView.getSettings().setJavaScriptEnabled(true);
//                webView.loadDataWithBaseURL(null,_htlmContent.getText().toString(),"text/html","utf_8",null);
//                webView.setBackgroundColor(0x01000000);
//            }
//        });

    }
    public void updateWithArticles(Result listArticles, RequestManager glide) {

        this.textTitle.setText(listArticles.getTitle());

        this.textDate.setText(listArticles.getPublishedDate());

        this.textSection.setText(listArticles.getSection());

        if(listArticles.getMultimedia().size() <1) imageArticle.setImageResource(nytimesdefault);

        else {

            glide.load(listArticles.getMultimedia()).apply(RequestOptions.circleCropTransform()).into(imageArticle);

        }
    }

}
