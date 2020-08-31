package com.app.delivxstore.utility;

import android.net.Uri;
import android.os.Build;
import com.app.delivxstore.BuildConfig;
import java.io.File;

public class VariableConstants {
  public static final String APP_VERSION = BuildConfig.VERSION_NAME;
  public static final String DEVICE_MODEL = android.os.Build.MODEL;
  public static final String DEVICE_MAKER = android.os.Build.MANUFACTURER;
  public static final String DEVICE_VERSION = Build.VERSION.RELEASE;
  public static final int CAMERA_PIC = 1, GALLERY_PIC = 2, CROP_IMAGE = 3,
      REQUEST_CODE_PERMISSION_MULTIPLE = 123;
  public static final String PARENT_FOLDER = "master";
  public static final String PROFILE_PIC = "driver/ProfilePics/";
  public static final int AWS = 1;
  public static final int UPLOAD_RECEIPT = 2;
  public static final String STORE = "store";
  public static final int USER_TYPE = 2; //1 for customer 2 for driver
  public static final int RC_READ_PHONE_STATE = 101;
  public static final int RC_LOCATION_STATE = 102;
  public static final String SIGNATURE_UPLOAD = "driver/signature/";
  public static final String BANK_PROOF = "store/BankProof";
  public static final String SIGNATURE_PIC_DIR = PARENT_FOLDER + "/sign";
  public static final int DEVICE_TYPE = 2; //1 for ios 2 for android
  public static final String MANAGE_ADDRESS_ACT = "act_manage_address";
  public static final String ADD_ADDRESS = "act_address";
  public static final String COMING_FROM = "comingFrom";
  public final static int LOCATION_UPDATE_INTERVAL = 5000;
  public final static int REQUEST_CHECK_SETTINGS = 23456;
  /*Google places API*/
  public static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
  public static final String TYPE_AUTOCOMPLETE = "/autocomplete";
  public static final String OUT_JSON = "/json";
  public static final String CHOOSEPAYMENT_ACT = "act_choose_payment";
  public static final int RESPONSE_CODE_SUCCESS = 200;
  public static final int RESPONSE_UNAUTHORIZED = 404;
  public static final String MESSAGE = "message";
  public static final int ONE = 1;
  public static final int ZERO = 0;
  public static final String STRIP_DATA = "strip_data";
  public static final int MINUS_ONE = -1;
  public static final int EIGHTEEN = -18;
  public static final int NOUGHT = 24;
  public static final int MARSH_MALLOW = 23;
  public static final int STRIP_PIC_WIDTH = 130;
  public static final int STRIP_PIC_HEIGHT = 142;
  public static final String FROM = "FROM";
  public static final String PATCH = "PATCH";
  public static final String CAUSE = "cause";
  public static final int FOUR = 4;
  public static final String FOR = "for";
  public static final String PAYMENT = "payment";
  public static final String WITH_DRAW = "with_draw";
  public static final String ACCOUNT_DATA = "account_data";
  public static final int SHOW_TIME = 4000;
  public static final String RECHARGE = "recharge";
  public static final String STRIP_PG = "strip";
  public static final String DEFAULT_CURRENCY_SYMBOL = "$";
  public static final String HISTORY_DATA = "history_data";
  public static final String CERTIFICATE_TYPE = "X.509";
  public static final String ALIAS_NAME = "ca";
  public static final String REQUESTED_PROTOCOL = "TLS";
  public static final int ARRAY_SIZE = 1;
  public static final int LOWER_BOUND = 0;
  public static final String CERTIFICATION_ERROR = "Unexpected default trust managers:";
  public static final String LOG_OUT = "log_out";
  public static final int WITH_STORE = 1;
  public static boolean IS_STRIPE_ADDED = false;
  public static String AMAZON_IDFOLDER = "idpic";
  public static String AMAZON_MMJFOLDER = "mmjpic";
  public static String AMAZON_PROFILEFOLDER = "profilepic";
  public static String AMAZON_WISHFOLDER = "wishlist";
  public static double CURRENT_ZONE_LAT;
  public static double CURRENT_ZONE_LONGI;
  public static boolean IS_WALLET_UPDATED = false;
  public static int ALL_TRANSACTION = 0;
  public static int DEBIT_TRANSACTION = 2;
  public static int CREDIT_TRANSACTION = 1;
  public static int ALL_ORDER = 0;
  public static int ALL_STORE = 0;
  public static int FETCH_SIZE = 20;
  public static int SKIP = 0;
  public static int LIMIT = 10;
  public static String PAGE_STATE = null;
  public static boolean IS_ACCOUNT_UPDATED = false;
  public static String imageType = "";
  public static Uri newProfileImageUri;
  public static File newFile;
  public static String STRIP_PIC = "strip_pic";
  public static int ASPECT_VALUE = 4;
  public static String STRIPE_PIC = "stripe_pic";
  public static String ATTRIBUTE_DATA = "attribute_data";
  public static String DATA_SIZE = "10";
  public static String FROM_PAGE = "0";
  public static String APP_NAME = "Shoppd";
  public static boolean IS_PASSWORD_CHANGED = false;
  public static String PDF_URL = "https://docs.google.com/gview?embedded=true&url=";
/*
public static String PDF_URL = "https://drive.google.com/viewerng/viewer?embedded=true&url=";
*/

}

