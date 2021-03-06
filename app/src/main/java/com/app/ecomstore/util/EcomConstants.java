package com.app.ecomstore.util;

import java.io.File;

public class EcomConstants {
  public static final Boolean FALSE = false;
  public static final Boolean TRUE = true;
  public static final int PASSWORD_VALID_LENGTH = 4;
  public static final int ZERO = 0;
  public static final int HUNDRED = 100;
  public static final int SUCCESS = 200;
  public static final long THOUSAND = 1000;
  public static final int SIXTY = 60;
  public static final int SIXTY_FIVE = 65;
  public static final int MINUS_ONE = -1;
  public static final int ONE = 1;
  public static final int TWO = 2;
  public static final int THREE = 3;
  public static final int FIVE = 5;
  public static final int SIX = 6;
  public static final int FOUR = 4;
  public static final int SEVEN = 7;
  public static final int NINE = 9;
  public static final int TWENTY = 20;
  public static final int INVALID_CODE = 410;
  public static final int INVALID_TOKEN = 401;
  public static final int DATA_NOT_FOUND = 404;
  public static final int SERVER_ERROR = 500;
  public static final int DRIVER_ASSIGN = 1;
  public static final int DRIVER_NOT_ASSIGN = 2;
  public static final int INTERNEL_SERVER_ERROR = 502;
  public static final int TWENTY_THREE = 23;
  public static final int FOUR_NINE_EIGHT = 498;
  public static final int DELAY_STATUS = 18;
  public static final int SUCCESS_RESPONSE = 201;
  public static final String ITEM_DATA = "itemData";
  public static final String ADD_PRODUCT_DATA = "addProductData";
  public static final String M_CURRENCY_SYMBOL = "mCurrencySymbol";
  public static final String ITEM_AVAILABLE = "isItemUnavailable";
  public static final String STATUS = "status";
  public static final String DRIVER_ID = "driverId";
  public static final String ORDER_ID = "orderId";
  public static final String ITEM_PRICE = "itemPrice";
  public static final String PACKAGE_ID = "packageId";
  public static final String STATUS_MSG = "statusMsg";
  public static final String MASTER_ORDER = "masterOrder";
  public static final String STORE_ORDER = "storeOrder";
  public static final String STORE_ORDER_ID = "storeOrderId";
  public static final String MASTER_ORDER_ID = "masterOrderId";
  public static final String MESSAGE = "message";
  public static final String COUNTRY_CODE = "countryCode";
  public static final String PHONE_NUMBER = "number";
  public static final String OTP_ID = "otpId";
  public static final String DATA = "data";
  public static final String STORE_ID = "storeId";
  public static final String IP_ADDRESS = "0.0.0.0";
  public static final String DEF_LAT = "0";
  public static final String DEF_LAN = "0";
  public static final String OTP_EXPIRY_TIME = "otpExpiryTime";
  public static final int EMAIL_VERIFY_TYPE = 1;
  public static final int NUMBER_VERIFY_TYPE = 2;
  public static final int RESEND_OTP_TYPE = 3;
  public static final String DEFAULT_COUNTRY_CODE = "+91";
  public static final int ANDROID_PLATFORM = 2;
  public static final int NEW = 1;
  public static final int ACCEPTED = 2;
  public static final int CANCELLED = 3;
  public static final int PACKED = 4;
  public static final int READY_FOR_PICK_UP = 5;
  public static final int DISPATCH = 6;
  public static final int COMPLETED = 7;
  public static final int CHECKED = 8;
  public static final int PENDING = 9;
  public static final int IN_REVIEW = 10;

  public static final int LIMIT = 20;
  public static final int SEVENTY_FIVE = 75;
  public static final int ORDER_TYPE = 0;
  public static final int VERIFY_OTP_REQ = 100;
  public static final int DRIVER_REQUEST = 12345;
  public static final int ADD_PRODUCT_REQUEST = 12356;
  public static final String AERIAL_PARNER = "0";
  public static final String STORE_PARNER = "2";
  public static final String CUSTOMER_ID = "mCustomerId";
  public static final String CUSTOMER_NAME = "customerName";
  public static final String CUSTOMER_NUMBER = "customerNumber";
  public static final int SELECT_PARTNER = 100;
  public static final String FINISH = "finish";
  public static final String ADD_PRODUCT = "addProduct";
  public static final String CURRENCY_SYMBOL = "$";
  public static final String BROWSER_VERSION = "1.0";
  public static final String CITY = "Bangalore";
  public static final String COUNTRY = "India";
  public static final String CROME = "crome";
  public static final String CURRENCY_CODE = "INR";
  public static final String CUSTOMER_VERIFICATION_CODE = "resendOtp";
  public static final String FORGOT_PASSWORD = "forgotPassword";
  public static final String USER_NAME = "kishore";
  public static final String CITY_MANAGER = "cityManager";
  public static final String STORE_MANAGER = "storeManager";
  public static final String SELECTED_COUNT = "selectedCount";
  public static final String PARTNER_NAME = "partnerName";
  public static final String TYPE = "TYPE";
  public static final String TYPE_NEW = "TYPE_NEW";
  public static final String TYPE_ACCEPT = "TYPE_ACCEPT";
  public static final String TYPE_DISPATCH = "TYPE_DISPATCH";
  public static final String FROM = "from";
  public static final String TO = "to";
  public static final String ASSIGN_MANUALLY = "assignManually";
  public static final String ATTRIBUTE_DATA = "attributeData";
  public static final String SEARCH_ITEM = "searchItem";
  public static final String PAGE = "page";
  public static final String STORE_ID_TXT = "storeid";
  public static final String STORE_CATEGORY_ID = "storeCategoryId";
  public static final int MAKE_PHONE_CALL = 100;
  public static final int SELECT_REASON = 12346;
  public static final int CAMERA_PERMISSION_REQ_CODE = 101;
  public static final int STORAGE_PERMISSION_REQ_CODE = 100;
  public static final String PRODUCT_ORDER_TYPE = "productOrder";
  public static final int HISTORY_FILTER = 100;
  public static final int RESTAURENT = 1;
  public static final int GROCERY = 2;
  public static final int LAUNDRY = 5;
  public static final int E_COMMERCE = 8;
  public static final int PHARMACY = 6;
  public static final int BOTTOM_SHEET_LOAD_DELAY = 500;
  public static final String START = "start";
  public static final String END = "end";
  public static final int HISTORY_STATUS = 37;
  public static final int BUFFERING_TIME = 250;
  public static final String LIGHT_BLACK = "#7E808C";
  public static final String BLACK = "#3D4152";
  public static final String SIZE = "Size";
  public static final String REASON_TYPE = "type";
  public static final String ID = "id";
  public static final String ADMIN = "admin";
  public static final String REASON = "reason";
  public static final int PHARMACY_REJECT = 12348;
  public static final int PRODUCT_SUBSTITUDE = 12321;
  public static final int ADD_MANUALLY = 12396;
  public static final int GET_CONFIRMATION = 12350;
  public static final int UPDATE_ASILE = 1534;
  public static final int BARCODE_REQUEST = 1535;
  public static final int FORCE_PICK_REQUEST = 1003;
  public static final int UPLOAD_FORCE_PICK_REQUEST = 1005;
  public static final String PRESCRIPTION_IMG = "prescriptionImg";
  public static final String RECEIPT_IMG = "receiptImg";
  public static final String REASON_ID = "reasonId";
  public static final int REASON_PRODUCT_CANCELLED = 8;
  public static final int REASON_ORDER_CANCELLED = 7;
  public static final int REASON_PRODUCT_UNAVAILABLE = 11;
  public static final String UPDATE_TYPE = "updateType";
  public static final String PRODUCT_ID = "produtId";
  public static final String ADD_OR_WEIGHT = "addOrWeight";
  public static final String PRODUCT_ORDER = "produtOrder";
  public static final String PARENT_PRODUCT_ID = "parentProductId";
  public static final String PRODUCT_ORDER_ID = "productOrderId";
  public static final String CHILD_PRODUCT_ID = "childProductId";
  public static final String PRODUCT_ID_TXT = "productId";
  public static final String OPEN_CAMERA = "openCamera";
  public static final String VARIANTS = "variants";
  public static final String IMAGE = "image";
  public static final String AISLE = "aisle";
  public static final String SECTOR = "sector";
  public static final String SHELF = "shelf";
  public static final String CITY_ID = "cityId";
  public static final String SLOT_ID = "slotId";
  public static final String SUPPORTED_DELIVERY_TYPE = "supportedDeliveryType";
  public static final String QUANTITY = "quantiy";
  public static final String BARCODE_DETECTION = "Barcode Detection";
  public static final String PARENT_FOLDER = "master";
  public static final String IMAGE_DATA = "/ImagesData/";
  public static final String PROVIDER_NAME = ".provider";
  public static final String PNG_FORMAT = ".png";
  public static final String PHOTO_PICKER_TYPE = "image/*";
  public static final String FILE_PATH = "filePath";
  public static final String FILE = "file";
  public static final int AMAZON_SERVER = 1;
  public static final String IMAGE_URL = "imageUrl";
  public static final int TWO_THOUSAND = 2000;
  public static final String BAG_COUNT = "bagCount";
  public static final String SHIPPING_LABEL = "shippingLabel";
  public static final int PRINT_LABEL_REQUEST = 1010;
  public static final int WEB_VIEW_REQUEST = 1038;
  public static final int PREVIEW_IMAGE_WIDTH = 70;
  public static final int PREVIEW_IMAGE_HEIGHT = 70;
  public static final String CAMERA_ITEM = "camera";
  public static final String WEB_VIEW = "webView";
  public static final int STORE_CHAT = 2;
  public static final String IS_FOR_VARIANTS = "isForVariants";
  public static final String IS_FOR_SUBSTITUTE = "isForSubstitute";
  public static final String NEEDS_WEIGHT = "needsWeight";
  public static final String PRODUCT = "product";
  public static final String SEND_MANUALLY = "sendManually";
  public static File newFile;
}
