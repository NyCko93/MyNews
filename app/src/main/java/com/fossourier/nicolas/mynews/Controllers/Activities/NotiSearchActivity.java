package com.fossourier.nicolas.mynews.Controllers.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fossourier.nicolas.mynews.Controllers.Fragments.ResultOfSearchFragment;
import com.fossourier.nicolas.mynews.Models.Doc;
import com.fossourier.nicolas.mynews.Models.SearchArticle;
import com.fossourier.nicolas.mynews.R;
import com.fossourier.nicolas.mynews.Utils.AlarmHelper;
import com.fossourier.nicolas.mynews.Utils.DateHelper;
import com.fossourier.nicolas.mynews.Utils.ErrorListener;
import com.fossourier.nicolas.mynews.Utils.FocusListener;
import com.fossourier.nicolas.mynews.Utils.NewYorkTimesStreams;
import com.fossourier.nicolas.mynews.Utils.NotiSearchConditions;
import com.fossourier.nicolas.mynews.Utils.SharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.fossourier.nicolas.mynews.Models.Result.TOPSTORIES_EXTRA;

public class NotiSearchActivity extends AppCompatActivity implements View.OnClickListener,
        ResultOfSearchFragment.ResultOfSearchFragmentListener, ErrorListener, FocusListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.section_arts)
    CheckBox sectionArts;
    @BindView(R.id.section_automobiles)
    CheckBox sectionAutomobiles;
    @BindView(R.id.section_books)
    CheckBox sectionBooks;
    @BindView(R.id.section_business)
    CheckBox sectionBusiness;
    @BindView(R.id.section_fashion)
    CheckBox sectionFashion;
    @BindView(R.id.section_food)
    CheckBox sectionFood;
    @BindView(R.id.section_health)
    CheckBox sectionHealth;
    @BindView(R.id.section_home)
    CheckBox sectionHome;
    @BindView(R.id.section_insider)
    CheckBox sectionInsider;
    @BindView(R.id.section_magazine)
    CheckBox sectionMagazine;
    @BindView(R.id.edit_text_search)
    EditText editTextSearch;
    @BindView(R.id.button_done_for_search)
    TextView buttonSearch;
    @BindView(R.id.edit_begin_date)
    TextView editBeginDate;
    @BindView(R.id.text_view_begin_date)
    TextView textViewBeginDate;
    @BindView(R.id.edit_end_date)
    TextView editEndDate;
    @BindView(R.id.text_view_end_date)
    TextView textViewEndDate;
    @BindView(R.id.edit_text_hour_of_notifications)
    TextView editTextHourOfNotifications;
    @BindView(R.id.text_view_hour_of_notifications)
    TextView textViewHourOfNotifications;
    @BindView(R.id.text_enable_notifications)
    TextView textEnableNotifications;
    @BindView(R.id.button_for_enable_notifications)
    Switch buttonSwitchNotifications;
    private static SharedPreferences mSharedPreferences;
    public static final String SEARCHED_ARTICLE = "searched_article";
    private String mBeginDate = "";
    private String mEndDate = "";
    public String mQueryTermSearch;
    public List<String> mSectionCheckbox;
    public String mQueryTermNotifications;
    public Boolean mNotificationsEnable;
    public Boolean mActivityChoisen;
    public String mHourOfNotifications;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti_search);
        ButterKnife.bind(this);
        // Search or Notifications
        mActivityChoisen = Objects.requireNonNull(getIntent().getExtras())
                .getBoolean(getString(R.string.boolean_notisearch));
        configureToolbar();
        mSectionCheckbox = new ArrayList<>();
        initListener();
        hide_keyboard(this);
        mSharedPreferences = SharedPreferences.getInstance(this);
        configureView();
        configureQueryTermSearch();
        // If Notifications selected
        if (!mActivityChoisen) {
            configureCheckbox();
            configureSwitch();
            configureHourOfNotifications();
        }
    }

      //-----------------------------//
     // Initialisation of listeners //
    //-----------------------------//
    private void initListener() {

        editTextSearch.setOnClickListener(this);
        editBeginDate.setOnClickListener(this);
        editEndDate.setOnClickListener(this);
        sectionArts.setOnClickListener(this);
        sectionAutomobiles.setOnClickListener(this);
        sectionBooks.setOnClickListener(this);
        sectionBusiness.setOnClickListener(this);
        sectionFashion.setOnClickListener(this);
        sectionFood.setOnClickListener(this);
        sectionHealth.setOnClickListener(this);
        sectionHome.setOnClickListener(this);
        sectionInsider.setOnClickListener(this);
        sectionMagazine.setOnClickListener(this);
        buttonSearch.setOnClickListener(this);
    }

      //-----------------------//
     // For hide the keyboard //
    //-----------------------//
    public static void hide_keyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(view.getWindowToken(),
                0);
    }

      //----------------//
     // Toolbar        //
    //----------------//
    private void configureToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

      //-------------------------------------------//
     // Display views according to asked activity //
    //-------------------------------------------//
    private void configureView() {
        // For search
        if (mActivityChoisen) {
            // Layout Notifications >> GONE
            textViewHourOfNotifications.setVisibility(View.GONE);
            editTextHourOfNotifications.setVisibility(View.GONE);
            textEnableNotifications.setVisibility(View.GONE);
            buttonSwitchNotifications.setVisibility(View.GONE);
            // Layout Search >> VISIBLE
            buttonSearch.setVisibility(View.VISIBLE);
            editBeginDate.setVisibility(View.VISIBLE);
            textViewBeginDate.setVisibility(View.VISIBLE);
            editEndDate.setVisibility(View.VISIBLE);
            textViewEndDate.setVisibility(View.VISIBLE);
        }
        // For notification
        else {
            // Layout Notifications >> VISIBLE
            textViewHourOfNotifications.setVisibility(View.VISIBLE);
            editTextHourOfNotifications.setVisibility(View.VISIBLE);
            textEnableNotifications.setVisibility(View.VISIBLE);
            buttonSwitchNotifications.setVisibility(View.VISIBLE);
            // Layout Search >> GONE
            buttonSearch.setVisibility(View.GONE);
            editBeginDate.setVisibility(View.GONE);
            textViewBeginDate.setVisibility(View.GONE);
            editEndDate.setVisibility(View.GONE);
            textViewEndDate.setVisibility(View.GONE);
        }
    }

      //-----------------------------//
     // Configure query term search //
    //-----------------------------//
    private void configureQueryTermSearch() {
        // Set text for notification
        if (!mActivityChoisen) {
            mNotificationsEnable = mSharedPreferences.getNotiSearchBoolean();
            mQueryTermNotifications = mSharedPreferences.getQueryTermNotifications();
            if (!mQueryTermNotifications.equals("") || mQueryTermNotifications.isEmpty()) {
                editTextSearch.setText(mQueryTermNotifications);
            }
        }
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (mActivityChoisen) {
                    mQueryTermSearch = editTextSearch.getText().toString();
                } else {
                    mQueryTermNotifications = editTextSearch.getText().toString();
                }
            }
        });

    }

      //--------------------//
     // Configure checkbox //
    //--------------------//
    private void configureCheckbox() {
        mSectionCheckbox = mSharedPreferences.getSectionOfNotifications();
        if (mSectionCheckbox != null) {
            if (mSectionCheckbox.contains(getString(R.string.section_arts))) {
                sectionArts.setChecked(true);
            }
            if (mSectionCheckbox.contains(getString(R.string.section_automobiles))) {
                sectionAutomobiles.setChecked(true);
            }
            if (mSectionCheckbox.contains(getString(R.string.section_books))) {
                sectionBooks.setChecked(true);
            }
            if (mSectionCheckbox.contains(getString(R.string.section_business))) {
                sectionBusiness.setChecked(true);
            }
            if (mSectionCheckbox.contains(getString(R.string.section_fashion))) {
                sectionFashion.setChecked(true);
            }
            if (mSectionCheckbox.contains(getString(R.string.section_food))) {
                sectionFood.setChecked(true);
            }
            if (mSectionCheckbox.contains(getString(R.string.section_health))) {
                sectionHealth.setChecked(true);
            }
            if (mSectionCheckbox.contains(getString(R.string.section_home))) {
                sectionHome.setChecked(true);
            }
            if (mSectionCheckbox.contains(getString(R.string.section_insider))) {
                sectionInsider.setChecked(true);
            }
            if (mSectionCheckbox.contains(getString(R.string.section_magazine))) {
                sectionMagazine.setChecked(true);
            }
        }
    }

      //---------------------------------------------------------------------------//
     // Save if notifications is enable and checkbox all conditions are respected //
    //---------------------------------------------------------------------------//
    public void configureSwitch() {
        if (mNotificationsEnable) {
            buttonSwitchNotifications.setChecked(true);
        } else {
            buttonSwitchNotifications.setChecked(false);
        }
        buttonSwitchNotifications.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            buttonSwitchNotifications.setChecked(validationOfNotifications());
            storeNotifications();
        });
    }

      //---------------------------------------------------------------------//
     // Get the selected hour or get the hour per default for Notifications //
    //---------------------------------------------------------------------//
    private void configureHourOfNotifications() {
        mHourOfNotifications = mSharedPreferences.getNotifTime();
        if (mHourOfNotifications != null && !mHourOfNotifications.isEmpty()) {
            editTextHourOfNotifications.setText(mHourOfNotifications);
        } else {
            editTextHourOfNotifications.setText("");
        }
    }

      //-----------------------------------------------------------//
     // Check conditions are respected for validate Notifications //
    //-----------------------------------------------------------//
    public boolean validationOfNotifications() {
        return NotiSearchConditions.forValidateNotifications(mQueryTermNotifications, mSectionCheckbox,
                buttonSwitchNotifications.isChecked(),this);
    }

      //-----------------------------------------------------------//
     // Store options for Notifications and configure AlarmHelper //
    //-----------------------------------------------------------//
    private void storeNotifications() {
        mSharedPreferences.storeQueryTermNotifications(mQueryTermNotifications);
        mNotificationsEnable = buttonSwitchNotifications.isChecked();
        mSharedPreferences.storeNotiSearchBoolean(mNotificationsEnable);
        mSharedPreferences.storeSectionOfNotifications(mSectionCheckbox);
        mSharedPreferences.storeNotifTime(mHourOfNotifications);
        (new AlarmHelper()).configureAlarmNotif(NotiSearchActivity.this,
                mSharedPreferences.getNotiSearchBoolean(),
                DateHelper.setTimeNotif(mSharedPreferences.getNotifTime()));
    }

      //-------------------------------------------//
     // Add and remove section choisen for search //
    //-------------------------------------------//
    public void addSections(String sectionChoisen) {
        ArrayList<String> section = mSharedPreferences.getListSection(1);
        if (!section.contains(sectionChoisen)) {
            section.add(sectionChoisen);
            mSharedPreferences.storeListSection(1, section);
        }
    }

    public void removeSections(String sectionChoisen) {
        ArrayList<String> section = mSharedPreferences.getListSection(1);
        if (section.contains(sectionChoisen)) {
            section.remove(sectionChoisen);
            mSharedPreferences.storeListSection(1, section);
        }
    }

    @Override
    public void onClick(View view) {
        // The default day is the day of use
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        // Date picker for select the desired date
        DatePickerDialog setDate;
        if (view != editTextSearch) {
            hide_keyboard(this);
        }
        switch (view.getId()) {
            // Check all conditions for validate the search
            case R.id.button_done_for_search:
                if (NotiSearchConditions.forValidateSearch(editTextSearch.getText().toString(), sectionArts.isChecked(),
                        sectionAutomobiles.isChecked(), sectionBooks.isChecked(), sectionBusiness.isChecked(),
                        sectionFashion.isChecked(), sectionFood.isChecked(), sectionHealth.isChecked(),sectionHome.isChecked(),sectionInsider.isChecked(),sectionMagazine.isChecked(),this,this)
                        && DateHelper.datesAreValid(this, mBeginDate, mEndDate)) {
                    executeSearchRequest();
                }
                break;
            // Time picker for select the desired hour of Notifications
            case R.id.edit_text_hour_of_notifications:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        (view1, hourOfDay, minute) -> {
                            mHourOfNotifications = hourOfDay + ":" + minute;
                            editTextHourOfNotifications.setText(mHourOfNotifications);
                        }, hour, min, false);
                timePickerDialog.show();
                break;
            // Here we add section arts when checkbox is checked
            case R.id.section_arts:
                if (sectionArts.isChecked()) {
                    // Check the activity choisen
                    if (mActivityChoisen) {
                        // Add for request of search
                        addSections(getString(R.string.section_arts));
                    } else {
                        // Add to sections choisen for Notifications
                        if (!mSectionCheckbox.contains(getString(R.string.section_arts))) {
                            mSectionCheckbox.add(getString(R.string.section_arts));
                        } else {
                            mSectionCheckbox.remove(getString(R.string.section_arts));
                        }
                    }
                } else {
                    // Remove if not checked
                    if (mActivityChoisen) {
                        removeSections(getString(R.string.section_arts));
                    } else {
                        mSectionCheckbox.remove(getString(R.string.section_arts));
                    }
                }
                break;
            // Here we add section automobiles when checkbox is checked
            case R.id.section_automobiles:
                if (sectionAutomobiles.isChecked()) {
                    if (mActivityChoisen) {
                        // Add for request of search
                        addSections(getString(R.string.section_automobiles));
                    } else {
                        // Add to sections choisen for Notifications
                        if (!mSectionCheckbox.contains(getString(R.string.section_automobiles))) {
                            mSectionCheckbox.add(getString(R.string.section_automobiles));
                        } else {
                            mSectionCheckbox.remove(getString(R.string.section_automobiles));
                        }
                    }
                } else {
                    // Remove if not checked
                    if (mActivityChoisen) {
                        removeSections(getString(R.string.section_automobiles));
                    } else {
                        mSectionCheckbox.remove(getString(R.string.section_automobiles));
                    }
                }
                break;
            // Here we add section books when checkbox is checked
            case R.id.section_books:
                if (sectionBooks.isChecked()) {
                    if (mActivityChoisen) {
                        // Add for request of search
                        addSections(getString(R.string.section_books));
                    } else {
                        // Add to sections choisen for Notifications
                        if (!mSectionCheckbox.contains(getString(R.string.section_books))) {
                            mSectionCheckbox.add(getString(R.string.section_books));
                        } else {
                            mSectionCheckbox.remove(getString(R.string.section_books));
                        }
                    }
                } else {
                    // Remove if not checked
                    if (mActivityChoisen) {
                        removeSections(getString(R.string.section_books));
                    } else {
                        mSectionCheckbox.remove(getString(R.string.section_books));
                    }
                }
                break;
            // Here we add section business when checkbox is checked
            case R.id.section_business:
                if (sectionBusiness.isChecked()) {
                    if (mActivityChoisen) {
                        // Add for request of search
                        addSections(getString(R.string.section_business));
                    } else {
                        // Add to sections choisen for Notifications
                        if (!mSectionCheckbox.contains(getString(R.string.section_business))) {
                            mSectionCheckbox.add(getString(R.string.section_business));
                        } else {
                            mSectionCheckbox.remove(getString(R.string.section_business));
                        }
                    }
                } else {
                    // Remove if not checked
                    if (mActivityChoisen) {
                        removeSections(getString(R.string.section_business));
                    } else {
                        mSectionCheckbox.remove(getString(R.string.section_business));
                    }
                }
                break;
            // Here we add section fashion when checkbox is checked
            case R.id.section_fashion:
                if (sectionFashion.isChecked()) {
                    if (mActivityChoisen) {
                        // Add for request of search
                        addSections(getString(R.string.section_fashion));
                    } else {
                        // Add to sections choisen for Notifications
                        if (!mSectionCheckbox.contains(getString(R.string.section_fashion))) {
                            mSectionCheckbox.add(getString(R.string.section_fashion));
                        } else {
                            mSectionCheckbox.remove(getString(R.string.section_fashion));
                        }
                    }
                } else {
                    // Remove if not checked
                    if (mActivityChoisen) {
                        removeSections(getString(R.string.section_fashion));
                    } else {
                        mSectionCheckbox.remove(getString(R.string.section_fashion));
                    }
                }
                break;
            // Here we add section food when checkbox is checked
            case R.id.section_food:
                if (sectionFood.isChecked()) {
                    if (mActivityChoisen) {
                        // Add for request of search
                        addSections(getString(R.string.section_food));
                    } else {
                        // Add to sections choisen for Notifications
                        if (!mSectionCheckbox.contains(getString(R.string.section_food))) {
                            mSectionCheckbox.add(getString(R.string.section_food));
                        } else {
                            mSectionCheckbox.remove(getString(R.string.section_food));
                        }
                    }
                } else {
                    // Remove if not checked
                    if (mActivityChoisen) {
                        removeSections(getString(R.string.section_food));
                    } else {
                        mSectionCheckbox.remove(getString(R.string.section_food));
                    }
                }
                break;
            // Here we add section health when checkbox is checked
            case R.id.section_health:
                if (sectionHealth.isChecked()) {
                    if (mActivityChoisen) {
                        // Add for request of search
                        addSections(getString(R.string.section_health));
                    } else {
                        // Add to sections choisen for Notifications
                        if (!mSectionCheckbox.contains(getString(R.string.section_health))) {
                            mSectionCheckbox.add(getString(R.string.section_health));
                        } else {
                            mSectionCheckbox.remove(getString(R.string.section_health));
                        }
                    }
                } else {
                    // Remove if not checked
                    if (mActivityChoisen) {
                        removeSections(getString(R.string.section_health));
                    } else {
                        mSectionCheckbox.remove(getString(R.string.section_health));
                    }
                }
                break;
            // Here we add section home when checkbox is checked
            case R.id.section_home:
                if (sectionHome.isChecked()) {
                    if (mActivityChoisen) {
                        // Add for request of search
                        addSections(getString(R.string.section_home));
                    } else {
                        // Add to sections choisen for Notifications
                        if (!mSectionCheckbox.contains(getString(R.string.section_home))) {
                            mSectionCheckbox.add(getString(R.string.section_home));
                        } else {
                            mSectionCheckbox.remove(getString(R.string.section_home));
                        }
                    }
                } else {
                    // Remove if not checked
                    if (mActivityChoisen) {
                        removeSections(getString(R.string.section_home));
                    } else {
                        mSectionCheckbox.remove(getString(R.string.section_home));
                    }
                }
                break;
            // Here we add section insider when checkbox is checked
            case R.id.section_insider:
                if (sectionInsider.isChecked()) {
                    if (mActivityChoisen) {
                        // Add for request of search
                        addSections(getString(R.string.section_insider));
                    } else {
                        // Add to sections choisen for Notifications
                        if (!mSectionCheckbox.contains(getString(R.string.section_insider))) {
                            mSectionCheckbox.add(getString(R.string.section_insider));
                        } else {
                            mSectionCheckbox.remove(getString(R.string.section_insider));
                        }
                    }
                } else {
                    // Remove if not checked
                    if (mActivityChoisen) {
                        removeSections(getString(R.string.section_insider));
                    } else {
                        mSectionCheckbox.remove(getString(R.string.section_insider));
                    }
                }
                break;
            // Here we add section magazine when checkbox is checked
            case R.id.section_magazine:
                if (sectionMagazine.isChecked()) {
                    if (mActivityChoisen) {
                        // Add for request of search
                        addSections(getString(R.string.section_magazine));
                    } else {
                        // Add to sections choisen for Notifications
                        if (!mSectionCheckbox.contains(getString(R.string.section_magazine))) {
                            mSectionCheckbox.add(getString(R.string.section_magazine));
                        } else {
                            mSectionCheckbox.remove(getString(R.string.section_magazine));
                        }
                    }
                } else {
                    // Remove if not checked
                    if (mActivityChoisen) {
                        removeSections(getString(R.string.section_magazine));
                    } else {
                        mSectionCheckbox.remove(getString(R.string.section_magazine));
                    }
                }
                break;
            case R.id.edit_begin_date:
                setDate = new DatePickerDialog(this, dateSettingListenerBegin,
                        year, month, day);
                setDate.show();
                break;
            case R.id.edit_end_date:
                setDate = new DatePickerDialog(this, dateSettingListenerEnd,
                        year, month, day);
                setDate.show();
                break;
        }
    }

      //---------------------------//
     // Execute request of search //
    //---------------------------//
    private void executeSearchRequest() {
        ArrayList<String> sections = mSharedPreferences.getListSection(1);
        Disposable disposable = NewYorkTimesStreams.streamArticleSearch(mQueryTermSearch,sections,mBeginDate,mEndDate)
                .subscribeWith(new DisposableObserver<SearchArticle>() {
                    @Override
                    public void onNext(SearchArticle searchArticle) {
                        setSearchFragment(searchArticle);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void setSearchFragment(SearchArticle searchArticle) {
        if (searchArticle.getResponse().getDocs().isEmpty()) {
            Toast.makeText(this, R.string.no_result,
                    Toast.LENGTH_SHORT).show();
        } else {
            Bundle bundle = new Bundle();
            bundle.putParcelable(SEARCHED_ARTICLE, searchArticle);
            ResultOfSearchFragment resultOfSearchFragment = (ResultOfSearchFragment)
                    getSupportFragmentManager().findFragmentById(R.id.activity_search_frame_layout);
            if (resultOfSearchFragment == null) {
                resultOfSearchFragment = new ResultOfSearchFragment();
                resultOfSearchFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.activity_search_frame_layout,
                        resultOfSearchFragment)
                        .commit();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!mActivityChoisen) {
            storeNotifications();
        }
    }

      //-------------------------------//
     // Configure DatePicker Listener //
    //-------------------------------//
    DatePickerDialog.OnDateSetListener dateSettingListenerBegin
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            mBeginDate = DateHelper.pickerFormatDate(year, month, day, editBeginDate);
        }
    };
    DatePickerDialog.OnDateSetListener dateSettingListenerEnd
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            mEndDate = DateHelper.pickerFormatDate(year, month, day, editEndDate);
        }
    };

    @Override
    public void onShowErrorString(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowErrorFromResources(int error) {
        Toast.makeText(this, getString(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetRequestFocus(boolean bool) {
        if (bool) { editTextSearch.requestFocus();
        }
    }

    @Override
    public void callbackSearchArticle(Doc searchArticle) {
        startResultOfSearchFragment(searchArticle);
    }

    private void startResultOfSearchFragment(Doc searchArticle) {
        Intent intent = new Intent(NotiSearchActivity.this,
                WebViewActivity.class);
        intent.putExtra(TOPSTORIES_EXTRA, searchArticle.getWebUrl());
        startActivity(intent);
    }
}
