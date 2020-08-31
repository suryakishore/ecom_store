package com.app.delivxstore.utility;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFormatter {
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

  /**
   * this method used to get the  formatted time stamp
   *
   * @param time time is in milliseconds.
   * @return return formatted time.
   */
  public static String getTime(String time) {
    String inputPattern = "yyyy-MM-dd HH:mm:ss";
    String outputPattern = "hh.mm a";
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

  /**
   * this method used to get the  formatted time stamp
   *
   * @param time time is in milliseconds.
   * @return return formatted time.
   */
  public static String getDateTimeStamp(String time) {
    // Log.d("exe", "time" + time);
    Long timeStamp = Long.parseLong(time);
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date netDate = (new Date(timeStamp * 1000L));
      String inputPattern = "yyyy-MM-dd HH:mm:ss";
      String outputPattern = "hh.mm a";
      SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
      SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
      Date date = null;
      String str = null;
      try {
        date = inputFormat.parse(sdf.format(netDate));
        str = outputFormat.format(date);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return str;
    } catch (Exception ex) {
      return "xx";
    }
  }

  public static String getMonth(String time) {
    String inputPattern = "yyyy-MM-dd HH:mm:ss";
    String outputPattern = "d";
    SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
    SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
    Date date = null;
    String str = null;
    try {
      date = inputFormat.parse(time);
      str = outputFormat.format(date);
    } catch (ParseException e) {
      e.printStackTrace();
      Log.d("exe", "ParseException" + e.getMessage());
    }
    return str;
  }

  /**
   * this method used to get the  formatted time stamp
   *
   * @param time time is in milliseconds.
   * @return return formatted time.
   */
  public static String getDate(String time) {
    Long timeStamp = Long.parseLong(time);
    try {
      Date netDate = (new Date(timeStamp * 1000L));
      return android.text.format.DateFormat.format("dd MMM yyyy HH:mm", netDate).toString();
    } catch (Exception ex) {
      Log.d("exe", "Exception" + ex.toString());
      return "xx";
    }
  }

  /**
   * required time stamp in output date format input is gmt time stamp
   */
  public static String getTrackingTransactionTime(String txnTimestamp) {
    Long time = Long.parseLong(txnTimestamp);
    SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy  HH:mm");
    Date date = null;
    date = new Date(time * 1000L);
    String formatted = output.format(date);
    return formatted;
  }

  /**
   * this method used to get the  formatted time stamp
   *
   * @param txnTimestamp time is in milliseconds.
   * @return return formatted time.
   */
  public static String getPendingTime(String txnTimestamp) {
    Long time = Long.parseLong(txnTimestamp);
    SimpleDateFormat output = new SimpleDateFormat("mm");
    Date date = null;
    date = new Date(time);
    String formatted = output.format(date);
    return formatted;
  }
}
