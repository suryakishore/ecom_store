package com.app.delivxstore.utility;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import com.app.delivxstore.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class Utility {
  /*display the toast */
  public static synchronized void toastMessage(Context context, String message) {
    if (context != null && message != null) {
      Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
  }

  /*returns if screen has tablet screen configuation*/
  public static boolean isTablet(Context context) {
    boolean result = false;
    if (context.getResources().getConfiguration().smallestScreenWidthDp >= 600) {
      result = true;
    }
    return result;
  }

  public static String getIpAddress(Context context) {
    WifiManager wifiMan =
        (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    WifiInfo wifiInf = wifiMan.getConnectionInfo();
    int ipAddress = wifiInf.getIpAddress();
    return String.format(Locale.getDefault(),
        "%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff),
        (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
  }

  public static void printLog(String... msg) {
    String str = "";
    for (String i : msg) {
      str = str + "\n" + i;
    }
    if (true) {
      Log.d("Store:", str);
    }
  }

  public static String getDeviceId(Context context) {
    // use the ANDROID_ID constant, generated at the first device boot
    String deviceId = Settings.Secure.getString(context.getContentResolver(),
        Settings.Secure.ANDROID_ID);
    // in case known problems are occured
    if ("9774d56d682e549c".equals(deviceId) || deviceId == null) {
      // get a unique deviceID like IMEI for GSM or ESN for CDMA phones
      // don't forget:
      //
      deviceId = Build.SERIAL;
      // if nothing else works, generate a random number
      if (deviceId == null) {
        Random tmpRand = new Random();
        deviceId = String.valueOf(tmpRand.nextLong());
      }
    }
    return deviceId;
  }

  public static String getDate(String getdate) {
    String returnStringDate = "";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat fort = new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.ENGLISH);
    Date date;
    try {
      date = format.parse(getdate);
      returnStringDate = fort.format(date);
    } catch (Exception e) {
      Utility.printLog(" error  in datte" + e);
      returnStringDate = getdate;
    }
    return returnStringDate;
  }

  public static String getTimeStamp(String timeStamp) {
    String returnStringDate = "";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    Date date;
    try {
      date = format.parse(timeStamp);
      returnStringDate = String.valueOf(date.getTime());
      return returnStringDate;
    } catch (Exception e) {
      Utility.printLog(" error  in datte" + e);
      returnStringDate = timeStamp;
    }
    return returnStringDate;
  }

  /*convert time stamp to required formatted time*/
  public static String timeStampToTime(int timeStamp) {
    java.util.Date time = new java.util.Date((long) timeStamp * 1000);
    Utility.printLog(
        "time stamp: " + android.text.format.DateFormat.format("MMM dd", time).toString());
    return android.text.format.DateFormat.format("MMM dd", time).toString();
  }

  /*change time format*/
  public static String changeTimeFormat(int timeStamp) {
    Timestamp timestamp = new Timestamp(timeStamp);
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh a,MMM dd");
    Utility.printLog("time stamp: " + dateFormat.format(timestamp));
    return dateFormat.format(timestamp);
  }

  /*change time format*/
  public static String changeTimeFormat1(int timeStamp) {
    Timestamp timestamp = new Timestamp(timeStamp * 1000);
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,hh a");
    Utility.printLog("time stamp: " + dateFormat.format(timestamp));
    return dateFormat.format(timestamp);
  }

  public static String getFormattedPrice(String tempPrice) {
    String price = "";
    Double dPrice = 0.00;
    if (tempPrice != null && !"".equals(tempPrice) && !"0".equals(tempPrice) && !"00".equals(
        tempPrice)
        && !"0.00".equals(tempPrice) && !"0.0".equals(tempPrice) && !".00".equals(tempPrice)) {
      dPrice = Double.parseDouble(tempPrice);
    }
    NumberFormat nf_out = NumberFormat.getInstance(Locale.US);
    nf_out.setMaximumFractionDigits(2);
    nf_out.setMinimumFractionDigits(2);
    nf_out.setGroupingUsed(false);
    price = nf_out.format(dPrice);
    return price;
  }

  public static double roundValue(double number) {
    double value = number;
    String returnNumber = number + "";
    if (!returnNumber.equals("") && !returnNumber.equals("null")) {
      if (returnNumber.contains(",")) {
        returnNumber = returnNumber.replace(',', '.');
      }
      try {
        String s = String.format("%.4f", (float) number);
        value = Double.parseDouble(s);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return value;
  }

  public static void mShowMessage(String title, String msg, Activity activity) {
    AlertDialog.Builder builder = new AlertDialog.Builder(activity, 5);
    builder.setTitle(title);
    builder.setMessage(msg);
    builder.setPositiveButton(activity.getResources().getString(R.string.OK),
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
          }
        });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  /**
   * <p>this method is for toast message ,
   * which has blue background</p>
   */
  public static void BlueToast(Context context, String msg) {
    Typeface ClanaproNarrMedium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto" +
        "-Medium.ttf");
    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View toastRoot = inflater.inflate(R.layout.toast_msg_back, null);
    TextView tv_toast = (TextView) toastRoot.findViewById(R.id.tv_toast);
    tv_toast.setTypeface(ClanaproNarrMedium);
        /*tv_toast.setWidth(300);
        tv_toast.setHeight(100);*/
    Toast toast = new Toast(context);
    toast.setView(toastRoot);
    toast.show();
    tv_toast.setText(msg);
    toast.setDuration(Toast.LENGTH_LONG);
  }

  /**********************************************************************************************/
  public static String roundOfDoubleValue(String number) {
    String returnNumber = number;
    if (!returnNumber.equals("") && !returnNumber.equals("null")) {
      if (number.contains(",")) {
        number = number.replace(',', '.');
      }
      try {
        double decimalNumber = Double.parseDouble(number);
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        returnNumber = String.format("%.2f", Double.valueOf(twoDForm.format(decimalNumber)));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return returnNumber;
  }

  /**
   *
   */
  public static String sentingDateFormat(int year, int monthOfYear, int dayOfMonth) {
    monthOfYear = monthOfYear + 1;
    String month = "" + monthOfYear;
    String day = "" + dayOfMonth;
    if (monthOfYear <= 9) {
      month = 0 + month;
    }
    if (dayOfMonth <= 9) {
      day = 0 + day;
    }
    String dateFormat = year + "-" + month + "-" + day;
    return dateFormat;
  }

  public static String displayDateFormat(int year, int monthOfYear, int dayOfMonth) {
    monthOfYear = monthOfYear + 1;
    String month = "" + monthOfYear;
    String day = "" + dayOfMonth;
        /*if(monthOfYear<=9)
        {
            month=0+month;
        }*/
    if (dayOfMonth <= 9) {
      day = 0 + day;
    }
        /*switch (monthOfYear) {
            case 1:
                month = "Jan";
                break;

            case 2:
                month = "Feb";
                break;

            case 3:
                month = "Mar";
                break;

            case 4:
                month = "Apr";
                break;

            case 5:
                month = "May";
                break;
            case 6:
                month = "Jun";
                break;

            case 7:
                month = "Jul";
                break;

            case 8:
                month = "Aug";
                break;

            case 9:
                month = "Sep";
                break;

            case 10:
                month = "Oct";
                break;

            case 11:
                month = "Nov";
                break;

            case 12:
                month = "Dec";
                break;

            default:
                break;
        }*/
    //        String dateFormat = day + "-" + month + "-" + year;
    String dateFormat = monthOfYear + "/" + day + "/" + year;
    return dateFormat;
  }

  public static String formatDate(String time) {
    String inputPattern = "yyyy-MM-dd HH:mm:ss";
    String outputPattern = "h.mm a, d MMM ";
    SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
    SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
    Date date = null;
    String str = null;
    try {
      date = inputFormat.parse(time);
      str = outputFormat.format(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return str;
  }

  public static String formatDdMmYyyyDate(String time) {
    String inputPattern = "yyyy-MM-dd HH:mm:ss";
    String outputPattern = "dd/MM/yyyy ";
    SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
    SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
    Date date = null;
    String str = null;
    try {
      date = inputFormat.parse(time);
      str = outputFormat.format(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return str;
  }

  public static String formatDate(String time, String outputPattern) {
    String inputPattern = "yyyy-MM-dd HH:mm:ss";
    //        String outputPattern = "h.mm a, d MMM ";
    SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
    SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
    Date date = null;
    String str = null;
    try {
      date = inputFormat.parse(time);
      str = outputFormat.format(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return str;
  }

  public static boolean isNetworkConnected(Activity activity) {
    ConnectivityManager cm =
        (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (cm != null) {
      return cm.getActiveNetworkInfo() != null;
    }
    return false;
  }

  public static String formatPrice(String amount) {
    try {
      DecimalFormat formatter = new DecimalFormat("###,###,##0");
      return formatter.format(Double.parseDouble(amount)).replace(",", ".");
    } catch (Exception e) {
      e.printStackTrace();
      return "0";
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.O)//yyyy-MM-dd'T'HH:mm:SS'Z'
  public static long getTimeInMilliSec(String startTime) {//yyyy-MM-dd'T'HH:mm:ss
    Long timeStamp = Long.parseLong(startTime);
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date netDate = (new Date(timeStamp * 1000L));
      String inputPattern = "yyyy-MM-dd HH:mm:ss";
      SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
      Date date = null;
      date = inputFormat.parse(sdf.format(netDate));
      return (date.getTime());
    } catch (Exception ex) {
      return 0;
    }
  }

  public static void setLocale(String lang, Context context) {
    try {
      Utility.printLog("Locale lang " + lang);
      Locale myLocale = new Locale(lang);
      Resources res = context.getResources();
      DisplayMetrics dm = res.getDisplayMetrics();
      Configuration conf = res.getConfiguration();
      conf.locale = myLocale;
      res.updateConfiguration(conf, dm);
    } catch (Exception e) {
      Utility.printLog("select_language inside Exception" + e.toString());
    }
    Utility.printLog("Locale lang " + context.getResources().getConfiguration().locale.toString());
  }

  public static void RtlConversion(Activity activity, String lang) {
    if (lang != null && lang.equals("ar")) {
      setLocale(lang, activity);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
      }
    } else {
      if (lang == null) {
        lang = "en";
      }
      setLocale(lang, activity);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
      }
    }
  }

  /**
   * custom method to return the date
   *
   * @return date
   */
  public static String currentDate() {
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    month = month + 1;
    String sMonth = "" + month;
    String clmday = "" + day;
    if (month <= 9) {
      sMonth = 0 + sMonth;
    }
    if (day <= 9) {
      clmday = 0 + clmday;
    }
    String todayDate = year + "-" + sMonth + "-" + clmday;
    return todayDate;
  }

  /**
   * <h2>showAlert</h2>
   * <p>
   * This method is used for showing the alert.
   * </P>
   *
   * @param msg:     message, that we need to show.
   * @param context: calling activity reference
   */
  public static void showAlert(String msg, Context context) {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
    // set title
    alertDialogBuilder.setTitle(context.getString(R.string.note));
    // set dialog message
    alertDialogBuilder
        .setMessage(msg)
        .setCancelable(false)
        .setNegativeButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            //closing the application
            dialog.dismiss();
          }
        });
    // create alert dialog
    AlertDialog alertDialog = alertDialogBuilder.create();
    // show it
    alertDialog.show();
  }

  public static boolean isNetworkAvailable(Context context) {
    ConnectivityManager connectivityManager =
        ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
    return connectivityManager.getActiveNetworkInfo() != null
        && connectivityManager.getActiveNetworkInfo().isConnected();
  }

  /*hides keyboard*/
  public static void hideSoftKeyboard(Activity activity) {
    View view = activity.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
      assert imm != null;
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    activity.getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
  }

  /*change time format*/
  @SuppressLint("SimpleDateFormat")
  public static String convertIntoLongTime(String timeStamp) {
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    SimpleDateFormat output = new SimpleDateFormat("dd MMM yy, HH : mm : ss");
    Date d = null;
    try {
      d = input.parse(timeStamp);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    printLog("formatted iso date : " + output.format(d));
    return output.format(d);
  }

  /**
   * required time stamp in output date format input is gmt time stamp
   */
  public static String getOrderPlacedTime(String txnTimestamp) {
    try {
      Long time = Long.parseLong(txnTimestamp);
      SimpleDateFormat output = new SimpleDateFormat("MMM dd,HH:mm");
      Date date = new Date(time * 1000L);
      String formatted = output.format(date);
      return formatted;
    } catch (Exception ex) {
      Log.w("TAG", "getOrderPlacedTime: " + ex.toString());
    }
    return "";
  }

  @SuppressLint("DefaultLocale")
  public static File filePath(Context context, Intent data) throws IOException {
    String takenNewImage;
    File file;
    String state = Environment.getExternalStorageState();
    takenNewImage = String.format("%s%d.png", context.getResources().getString(R.string.app_name)
        .replace(" ", ""), System.nanoTime());
    file = Environment.MEDIA_MOUNTED.equals(state)
        ? new File(Environment.getExternalStorageDirectory(), takenNewImage) :
        new File(context.getFilesDir(), takenNewImage);
    InputStream inputStream = context.getContentResolver()
        .openInputStream(Objects.requireNonNull(data.getData()));
    FileOutputStream fileOutputStream =
        new FileOutputStream(new File(file.getAbsolutePath()));
    Utility.copyStream(Objects.requireNonNull(inputStream), fileOutputStream);
    fileOutputStream.close();
    inputStream.close();
    return file;
  }

  /**
   * <h2>userPromptWithTwoButtons</h2>
   * This method is used to show the alert with 2 buttons
   *
   * @param context context of the activity
   */
  public static Dialog userPromptWithTwoButtons(Activity context, String message,
      FontUtils fontUtils) {
    Dialog dialog = new Dialog(context, R.style.AlertDialogTheme);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_two_button_alert);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    //        dialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogTheme;
    dialog.setCancelable(false);
    TextView tv_alert_title = dialog.findViewById(R.id.tvAlertTitle);
    TextView tv_alert_body = dialog.findViewById(R.id.tvAlertBody);
    TextView tv_alert_yes = dialog.findViewById(R.id.tvAlertYes);
    TextView tv_alert_no = dialog.findViewById(R.id.tvAlertNo);
    tv_alert_title.setTypeface(fontUtils.getMontserratSemiBold());
    tv_alert_yes.setTypeface(fontUtils.getMontserratRegular());
    tv_alert_no.setTypeface(fontUtils.getMontserratRegular());
    tv_alert_body.setTypeface(fontUtils.getMontserratRegular());
    tv_alert_body.setText(message);
    tv_alert_no.setOnClickListener(v ->
        dialog.dismiss());
    return dialog;
  }

  /*open link in web browser*/
  public static void openWebBrowser(String link, Context context) {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
    context.startActivity(intent);
  }

  /*open link in web browser*/
  public static void callToNumber(String number, Context context) {
    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
        != PackageManager.PERMISSION_GRANTED) {
      // TODO: Consider calling
      //    ActivityCompat#requestPermissions
      // here to request the missing permissions, and then overriding
      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
      //                                          int[] grantResults)
      // to handle the case where the user grants the permission. See the documentation
      // for ActivityCompat#requestPermissions for more details.
      return;
    }
    context.startActivity(intent);
  }

  public static void copyStream(InputStream input, OutputStream output) throws IOException {
    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = input.read(buffer)) != -1) {
      output.write(buffer, 0, bytesRead);
    }
  }

  public void getTimeZone() {
    //        String timeZoneString =  TimezoneMapper.latLngToTimezoneString(Double.parseDouble
    //        (preferenceHelperDataSource.getCurrLatitude()),
    //                Double.parseDouble(preferenceHelperDataSource.getCurrLongitude()));
    //        TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);
  }

  public void mShowMessage(String title, String msg, Activity activity,
      final AlertDialogCallBack callBack) {
    AlertDialog.Builder builder = new AlertDialog.Builder(activity, 5);
    builder.setTitle(title);
    builder.setMessage(msg);
    builder.setPositiveButton(activity.getResources().getString(R.string.OK),
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            callBack.onOkPressed();
            dialogInterface.dismiss();
          }
        });
    builder.show();
  }

  public void makePhoneCall(final String phoneNo, final Context mcontext) {
    AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
    builder.setTitle(phoneNo);
    builder.setPositiveButton(R.string.cancel/*"No"*/, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });
    builder.setNegativeButton("Call", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNo));
        if (ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {
          return;
        }
        mcontext.startActivity(dialIntent);
      }
    });
    AlertDialog alert = builder.create();
    alert.setCancelable(false);
    alert.show();
  }

  public interface AlertDialogCallBack {
    void onOkPressed();

    void onCancelPressed();
  }
}
