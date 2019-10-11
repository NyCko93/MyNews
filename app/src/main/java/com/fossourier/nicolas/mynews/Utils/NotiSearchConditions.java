package com.fossourier.nicolas.mynews.Utils;

import com.fossourier.nicolas.mynews.R;

import java.util.List;



public class NotiSearchConditions {


    private static boolean mBoolean;

       //--------------------------------------------------------------------------------------------//
      // Verifications if the user has enter query term and selected minimum one section for search //
     // If not display a toast                                                                     //
    //--------------------------------------------------------------------------------------------//
    public static boolean forValidateSearch(String editTextSearch,
                                            Boolean section_arts, Boolean section_automobiles, Boolean section_books,
                                            Boolean section_business, Boolean section_fashion,
                                            Boolean section_food, Boolean section_health, Boolean section_home, Boolean section_insider, Boolean section_magazine, ErrorListener errorListener,
                                            FocusListener focusListener) {
        if (editTextSearch == null || editTextSearch.equals("")) {
            if (errorListener != null) {
                errorListener.onShowErrorFromResources(R.string.check_query_fields);
            }
            focusListener.onGetRequestFocus(true);
            return false;
        }
        if (!section_arts && !section_automobiles && !section_books && !section_business && !section_fashion && !section_food &&
                !section_health) {
            if (errorListener != null) {
                errorListener.onShowErrorFromResources(R.string.check_checkbox);
            }
            focusListener.onGetRequestFocus(false);
            return false;
        }
        focusListener.onGetRequestFocus(false);
        return true;
    }

       //---------------------------------------------------------------------------------------------------//
      // Verifications if the user has enter query term and selected minimum one section for notifications //
     // If not display a toast                                                                            //
    //---------------------------------------------------------------------------------------------------//
    public static boolean forValidateNotifications(String keywords, List<String> sections,
                                                   boolean buttonSwitchNotifications, ErrorListener errorListener) {
        if (buttonSwitchNotifications) {
            if (keywords == null || keywords.isEmpty()) {
                if (errorListener != null) {
                    errorListener.onShowErrorFromResources(R.string.check_query_fields);
                }
                return false;
            }
            if (sections.size() == 0) {
                if (errorListener != null) {
                    errorListener.onShowErrorFromResources(R.string.check_checkbox);
                }
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
