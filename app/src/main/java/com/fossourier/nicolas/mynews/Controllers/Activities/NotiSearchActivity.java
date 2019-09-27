package com.fossourier.nicolas.mynews.Controllers.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import com.fossourier.nicolas.mynews.R;
import com.fossourier.nicolas.mynews.Utils.SharedPreferences;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;


public class NotiSearchActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar_noti_search)
    Toolbar mToolbarNotiSearch;
    @BindView(R.id.edit_text_search)
    EditText mEditTextSearch;
    @BindView(R.id.edit_begin_date)
    TextView mEditBeginDate;
    @BindView(R.id.text_view_begin_date)
    TextView mTextViewBeginDate;
    @BindView(R.id.edit_end_date)
    TextView mEditEndDate;
    @BindView(R.id.text_view_end_date)
    TextView mTextViewEndDate;
    @BindView(R.id.checkbox_arts)
    CheckBox mCheckboxArts;
    @BindView(R.id.checkbox_automobiles)
    CheckBox mCheckboxAutomobiles;
    @BindView(R.id.checkbox_books)
    CheckBox mCheckboxBooks;
    @BindView(R.id.checkbox_business)
    CheckBox mCheckboxBusiness;
    @BindView(R.id.checkbox_fashion)
    CheckBox mCheckboxFashion;
    @BindView(R.id.checkbox_food)
    CheckBox mCheckboxFood;
    @BindView(R.id.checkbox_health)
    CheckBox mCheckboxHealth;
    @BindView(R.id.checkbox_home)
    CheckBox mCheckboxHome;
    @BindView(R.id.checkbox_insider)
    CheckBox mCheckboxInsider;
    @BindView(R.id.checkbox_magazine)
    CheckBox mCheckboxMagazine;
    @BindView(R.id.button_done_for_search)
    TextView mButtonSearch;
    @BindView(R.id.text_enable_notifications)
    TextView mTextEnableNotifications;
    @BindView(R.id.button_for_enable_notifications)
    Switch mButtonForEnableNotifications;

    private SharedPreferences mSharedPreferences;
    public List<String> mListSection;

    // Boolean for notifications and search choice
    public Boolean mOptionChoisen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti_search);
        ButterKnife.bind(this);
        configureToolbar();
        mListSection = new ArrayList<>();
        initListener();
        configureView();
        mSharedPreferences = SharedPreferences.getInstance(this);
        mOptionChoisen = Objects.requireNonNull(getIntent().getExtras())
                .getBoolean(getString(R.string.boolean_notisearch));

    }

    @Override
    public void onClick(View view) {

    }

    private void initListener() {
        mEditTextSearch.setOnClickListener(this);
        mEditBeginDate.setOnClickListener(this);
        mEditEndDate.setOnClickListener(this);
        mCheckboxArts.setOnClickListener(this);
        mCheckboxAutomobiles.setOnClickListener(this);
        mCheckboxBooks.setOnClickListener(this);
        mCheckboxBusiness.setOnClickListener(this);
        mCheckboxFashion.setOnClickListener(this);
        mCheckboxFood.setOnClickListener(this);
        mCheckboxHealth.setOnClickListener(this);
        mCheckboxHome.setOnClickListener(this);
        mCheckboxInsider.setOnClickListener(this);
        mCheckboxMagazine.setOnClickListener(this);
        mButtonSearch.setOnClickListener(this);
    }

    private void configureToolbar() {
        setSupportActionBar(mToolbarNotiSearch);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // Display view according with button clicked
    private void configureView() {
        // SEARCH
        if (mOptionChoisen) {
            mTextEnableNotifications.setVisibility(View.GONE);
            mButtonForEnableNotifications.setVisibility(View.GONE);
            mButtonSearch.setVisibility(View.VISIBLE);
            mEditBeginDate.setVisibility(View.VISIBLE);
            mTextViewBeginDate.setVisibility(View.VISIBLE);
            mEditEndDate.setVisibility(View.VISIBLE);
            mTextViewEndDate.setVisibility(View.VISIBLE);
        }
        // NOTIFICATIONS
        else {
            mTextEnableNotifications.setVisibility(View.VISIBLE);
            mButtonForEnableNotifications.setVisibility(View.VISIBLE);
            mButtonSearch.setVisibility(View.GONE);
            mEditBeginDate.setVisibility(View.GONE);
            mTextViewBeginDate.setVisibility(View.GONE);
            mEditEndDate.setVisibility(View.GONE);
            mTextViewEndDate.setVisibility(View.GONE);
        }
    }


    private void configureCheckbox() {
        mListSection = mSharedPreferences.getCheckBoxSection();
        if (mListSection != null) {
            if (mListSection.contains(getString(R.string.section_arts))) {
                mCheckboxArts.setChecked(true);
            }
            if (mListSection.contains(getString(R.string.section_automobiles))) {
                mCheckboxAutomobiles.setChecked(true);
            }
            if (mListSection.contains(getString(R.string.section_books))) {
                mCheckboxBooks.setChecked(true);
            }
            if (mListSection.contains(getString(R.string.section_business))) {
                mCheckboxBusiness.setChecked(true);
            }
            if (mListSection.contains(getString(R.string.section_fashion))) {
                mCheckboxFashion.setChecked(true);
            }
            if (mListSection.contains(getString(R.string.section_food))) {
                mCheckboxFood.setChecked(true);
            }
            if (mListSection.contains(getString(R.string.section_health))) {
                mCheckboxHealth.setChecked(true);
            }
            if (mListSection.contains(getString(R.string.section_home))) {
                mCheckboxHome.setChecked(true);
            }
            if (mListSection.contains(getString(R.string.section_insider))) {
                mCheckboxInsider.setChecked(true);
            }
            if (mListSection.contains(getString(R.string.section_magazine))) {
                mCheckboxMagazine.setChecked(true);
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
