package com.gdjt.gyanoday;

public class Constant {

    public static final boolean DUMMY_DATA_ON = true;

    public static final long SPLASH_TIMER = 2 * 1000;

    public static final int HOME_SCREEN_INDEX = 1;
    public static final int PUJA_SCREEN_INDEX = 2;
    public static final int ABOUT_SCREEN_INDEX = 3;
    public static final int MORE_SCREEN_INDEX = 4;

    public static final int DEFAULT_SCREEN_INDEX =  HOME_SCREEN_INDEX;

    public static final int ROW_TYPE_HEADER_INDEX = 1;
    public static final int ROW_TYPE_CHILD_INDEX = 2;

    public static final int IMAGE_TYPE_WEB = 1;
    public static final int IMAGE_TYPE_DRAWABLE = 2;

    public static final int PERMISSION_KEY = 1000;

    public static final int MORE_OPTION_NOTIFICATION_INDEX = 0;
    public static final int MORE_OPTION_POOJA_INDEX = 1;
    public static final int MORE_OPTION_TRUST_INDEX = 2;
    public static final int MORE_OPTION_CONTACT_INDEX = 3;
    public static final int MORE_OPTION_TIRTH_INDEX = 4;
    public static final int MORE_OPTION_DONATION_INDEX = 5;
    public static final int MORE_OPTION_NIYAM_INDEX = 6;
    public static final int MORE_OPTION_FEEDBACK_INDEX = 7;
    public static final int MORE_OPTION_GALLERY_INDEX = 8;



    public static final double TEMPLE_LATITUDE = 12.9502092;
    public static final double TEMPLE_LONGITUDE = 77.7171059;

    public static final int PUJA_DENIK_INDEX = 1;
    public static final int PUJA_OTHER_INDEX = 2;
    public static final int PUJA_STROTA_INDEX = 3;
    public static final int PUJA_AARTI_INDEX = 4;
    public static final int PUJA_BHAJAN_INDEX = 5;
    public static final int PUJA_BHAKTI_INDEX = 6;
    public static final int PUJA_CHALISA_INDEX = 7;
    public static final int PUJA_STUTI_INDEX = 8;

    public static final int TIRTH_INDEX_TEMPLE = 1;
    public static final int TIRTH_INDEX_KARNATKA = 2;
    public static final int TIRTH_INDEX_TAMILNADU = 3;

    public static final int IMAGE_ACTIVITY_TAMILNADU_INDEX = 1;
    public static final int IMAGE_ACTIVITY_KARNATKA_INDEX = 2;



    public static final int NIYAM_CHILD_TYPE = 2;
    public static final int NIYAM_ADULT_TYPE = 1;


    public static final String SHARED_PREF_LAUNCH_FIRST_TIME = "unison_first_time";

    public static final String INTENT_NOTIFICATION_DATA = "notification_bean";
    public static final String INTENT_GENERAL_LIST_TITLE = "list_title";
    public static final String INTENT_GENERAL_LIST_INDEX = "list_index";

    public static final String INTENT_GENERAL_DETAILS_TITLE = "details_title";
    public static final String INTENT_GENERAL_DETAILS_DESC = "details_desc";

    public static final String INTENT_TITLE = "title";
    public static final String INTENT_SUBTITLE = "sub_title";
    public static final String INTENT_TEMPLE_LIST = "temple_list";
    public static final String INTENT_IMAGE_ACTIVITY_INDEX = "image_index";


    //All json file keys:
    public static final String FILE_JINVANI_NAME = "jinvani.json";
    public static final String FILE_MORE_NAME = "more.json";
    public static final String FILE_TRUST_NAME = "trust.json";
    public static final String FILE_TIRTH_LIST_NAME = "tirth.json";
    public static final String FILE_BAN_TIRTH_NAME = "ban_temples.json";

    public static final String JSON_DATA_ID_KEY = "id";
    public static final String JSON_DATA_HINDI_TEXT_KEY = "h_name";
    public static final String JSON_DATA_ENGLISH_TEXT_KEY = "e_name";
    public static final String JSON_DATA_IMAGE_PATH_KEY = "img_path";
    public static final String JSON_DATA_IMAGE_TYPE_KEY = "img_type";
    public static final String JSON_DATA_VAL_KEY = "text_val";
    public static final String JSON_DATA_ACTION_KEY = "action_index";

    public static final String JSON_TRUST_ID_KEY = "id";
    public static final String JSON_TRUST_NAME_KEY = "name";
    public static final String JSON_TRUST_WORK_KEY = "work";
    public static final String JSON_TRUST_IMG_PATH_KEY = "img_path";
    public static final String JSON_TRUST_IMG_TYPE_KEY = "img_type";
    public static final String JSON_TRUST_VIEW_TYPE_KEY = "row_type";

    public static final String JSON_TEMPLE_ID_KEY = "id";
    public static final String JSON_TEMPLE_HNAME_KEY = "h_name";
    public static final String JSON_TEMPLE_ENAME_KEY = "e_name";
    public static final String JSON_TEMPLE_ADDRESS_KEY = "add";
    public static final String JSON_TEMPLE_LANDMARK_KEY = "landmark";
    public static final String JSON_TEMPLE_CONTACT_KEY = "contact";
    public static final String JSON_TEMPLE_FOOD_KEY = "food";
    public static final String JSON_TEMPLE_STAY_KEY = "stay";
    public static final String JSON_TEMPLE_LAT_KEY = "lat";
    public static final String JSON_TEMPLE_LON_KEY = "lon";


    public static int USER_PHONE_NO_MIN_LENGTH = 10;
}
