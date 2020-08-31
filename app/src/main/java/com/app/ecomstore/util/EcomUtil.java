package com.app.ecomstore.util;

import static com.app.ecomstore.util.EcomConstants.FIVE;
import static com.app.ecomstore.util.EcomConstants.FOUR;
import static com.app.ecomstore.util.EcomConstants.NINE;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.SIX;
import static com.app.ecomstore.util.EcomConstants.SIXTY;
import static com.app.ecomstore.util.EcomConstants.THOUSAND;
import static com.app.ecomstore.util.EcomConstants.THREE;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.drawable.DrawableCompat;
import com.app.delivxstore.R;

public class EcomUtil {
  /*matches the email format*/
  public static boolean isEmail(@Nullable CharSequence str) {
    return Patterns.EMAIL_ADDRESS.matcher(str).matches();
  }

  /*matches the password size*/
  public static boolean isPassword(String passwordStr) {
    return passwordStr.length() >= 4;
  }

  /**/
  /*matches the email format*/
  public static boolean isPhone(@Nullable CharSequence str) {
    return Patterns.PHONE.matcher(str).matches();
  }

  public static void printLog(String message) {
    Log.d("EcomStore", message);
  }

  public static String getColoredSpanned(String text, String color) {
    String input = "<font color=" + color + ">" + text + "</font>";
    return input;
  }

  /**
   * returns the timer in minutes and seconds format for each millisecond.
   */
  public static StringBuilder getTimerValue(long millisUntilFinished) {
    StringBuilder stringBuilder = new StringBuilder();
    long secondsUntilFinished = millisUntilFinished / THOUSAND;
    long seconds = secondsUntilFinished % SIXTY;
    long mins = secondsUntilFinished / SIXTY;
    String finalSec = (seconds > NINE) ? "" + seconds : "" + ZERO + seconds;
    String finalMin = (mins > NINE) ? "" + mins : "" + ZERO + mins;
    stringBuilder.delete(ZERO, stringBuilder.length());
    stringBuilder.append(finalMin).append(":").append(finalSec);
    return stringBuilder;
  }

  public static void hideSoftKeyboard(View view) {
    try {
      InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
          Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    } catch (Exception e) {
    }
  }

  /**
   * This method used to set the custom drawable based on position
   *
   * @param position   integer
   * @param isSelected boolean
   * @param textView   respective textview.
   */
  public static void setCustomDrawable(int position, boolean isSelected,
      AppCompatTextView textView, Context context) {
    Drawable drawableSelected;
    switch (position) {
      case ZERO:
        EcomUtil.printLog("exe" + "isSelected " + isSelected);
        textView.setTextColor(
            isSelected ? context.getResources().getColor(R.color.white) :
                context.getResources().getColor(R.color.colorRazzm));
        if (isSelected) {
          drawableSelected = context.getResources().getDrawable(
              R.drawable.rectangle_corner_bank_green_solid);
          drawableSelected = DrawableCompat.wrap(drawableSelected);
          DrawableCompat.setTint(drawableSelected, isSelected ?
              context.getResources().getColor(R.color.colorRazzm)
              : context.getResources().getColor(R.color.colorRazzm));
          textView.setBackgroundDrawable(drawableSelected);
        } else {
          EcomUtil.printLog("exe" + "isSelected " + "else case");
          GradientDrawable gd = new GradientDrawable();
          gd.setColor(Color.WHITE);
          gd.setCornerRadius(5);
          gd.setStroke(2, context.getResources().getColor(R.color.colorRazzm));
          textView.setBackgroundDrawable(gd);
        }
        break;
      case ONE:
        textView.setTextColor(
            isSelected ? context.getResources().getColor(R.color.white) :
                context.getResources().getColor(R.color.invoiceColor));
        if (isSelected) {
          drawableSelected = context.getResources().getDrawable(
              R.drawable.rectangle_corner_bank_green_solid);
          drawableSelected = DrawableCompat.wrap(drawableSelected);
          DrawableCompat.setTint(drawableSelected,
              context.getResources().getColor(R.color.invoiceColor));
          textView.setBackgroundDrawable(drawableSelected);
        } else {
          EcomUtil.printLog("exe" + "isSelected " + "else case");
          GradientDrawable gd = new GradientDrawable();
          gd.setColor(Color.WHITE);
          gd.setCornerRadius(5);
          gd.setStroke(2, context.getResources().getColor(R.color.invoiceColor));
          textView.setBackgroundDrawable(gd);
        }
        break;
      case TWO:
        textView.setTextColor(
            isSelected ? context.getResources().getColor(R.color.white) :
                context.getResources().getColor(R.color.colorNiagara));
        if (isSelected) {
          drawableSelected = context.getResources().getDrawable(
              R.drawable.rectangle_corner_bank_green_solid);
          drawableSelected = DrawableCompat.wrap(drawableSelected);
          DrawableCompat.setTint(drawableSelected,
              context.getResources().getColor(R.color.colorNiagara));
          textView.setBackgroundDrawable(drawableSelected);
        } else {
          EcomUtil.printLog("exe" + "isSelected " + "else case");
          GradientDrawable gd = new GradientDrawable();
          gd.setColor(Color.WHITE);
          gd.setCornerRadius(5);
          gd.setStroke(2, context.getResources().getColor(R.color.colorNiagara));
          textView.setBackgroundDrawable(gd);
        }
        break;
      case THREE:
        textView.setTextColor(
            isSelected ? context.getResources().getColor(R.color.white) :
                context.getResources().getColor(R.color.colorKeyLimePie));
        if (isSelected) {
          drawableSelected = context.getResources().getDrawable(
              R.drawable.rectangle_corner_bank_green_solid);
          drawableSelected = DrawableCompat.wrap(drawableSelected);
          DrawableCompat.setTint(drawableSelected,
              context.getResources().getColor(R.color.colorKeyLimePie));
          textView.setBackgroundDrawable(drawableSelected);
        } else {
          EcomUtil.printLog("exe" + "isSelected " + "else case");
          GradientDrawable gd = new GradientDrawable();
          gd.setColor(Color.WHITE);
          gd.setCornerRadius(5);
          gd.setStroke(2, context.getResources().getColor(R.color.colorKeyLimePie));
          textView.setBackgroundDrawable(gd);
        }
        break;
      case FOUR:
        textView.setTextColor(
            isSelected ? context.getResources().getColor(R.color.white) :
                context.getResources().getColor(R.color.colorMediumPurple));
        if (isSelected) {
          drawableSelected = context.getResources().getDrawable(
              R.drawable.rectangle_corner_bank_green_solid);
          drawableSelected = DrawableCompat.wrap(drawableSelected);
          DrawableCompat.setTint(drawableSelected,
              context.getResources().getColor(R.color.colorMediumPurple));
          textView.setBackgroundDrawable(drawableSelected);
        } else {
          EcomUtil.printLog("exe" + "isSelected " + "else case");
          GradientDrawable gd = new GradientDrawable();
          gd.setColor(Color.WHITE);
          gd.setCornerRadius(5);
          gd.setStroke(2, context.getResources().getColor(R.color.colorMediumPurple));
          textView.setBackgroundDrawable(gd);
        }
        break;
      case FIVE:
        textView.setTextColor(
            isSelected ? context.getResources().getColor(R.color.white) :
                context.getResources().getColor(R.color.historyOrderStatus));
        if (isSelected) {
          drawableSelected = context.getResources().getDrawable(
              R.drawable.rectangle_corner_bank_green_solid);
          drawableSelected = DrawableCompat.wrap(drawableSelected);
          DrawableCompat.setTint(drawableSelected,
              context.getResources().getColor(R.color.historyOrderStatus));
          textView.setBackgroundDrawable(drawableSelected);
        } else {
          EcomUtil.printLog("exe" + "isSelected " + "else case");
          GradientDrawable gd = new GradientDrawable();
          gd.setColor(Color.WHITE);
          gd.setCornerRadius(5);
          gd.setStroke(2, context.getResources().getColor(R.color.historyOrderStatus));
          textView.setBackgroundDrawable(gd);
        }
        break;
      case SIX:
        textView.setTextColor(
            isSelected ? context.getResources().getColor(R.color.white) :
                context.getResources().getColor(R.color.colorPrimary));
        if (isSelected) {
          drawableSelected = context.getResources().getDrawable(
              R.drawable.rectangle_corner_bank_green_solid);
          drawableSelected = DrawableCompat.wrap(drawableSelected);
          DrawableCompat.setTint(drawableSelected,
              context.getResources().getColor(R.color.colorPrimary));
          textView.setBackgroundDrawable(drawableSelected);
        } else {
          EcomUtil.printLog("exe" + "isSelected " + "else case");
          GradientDrawable gd = new GradientDrawable();
          gd.setColor(Color.WHITE);
          gd.setCornerRadius(5);
          gd.setStroke(2, context.getResources().getColor(R.color.colorPrimary));
          textView.setBackgroundDrawable(gd);
        }
        break;
    }
  }

  public static String getCurrencySymbol(String symbol) {
    return Base64.encodeToString(symbol.getBytes(), Base64.NO_WRAP);
  }

  public static void strikeThroughText(TextView price) {
    price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
  }

  /**
   * This method is used to get the preperation time based on minutes which we selected from
   * spinner.
   *
   * @param selectItem index
   * @return we will get timing in minutes
   */
  public static String getPreperationTime(int selectItem) {
    String selectedTime = "";
    switch (selectItem) {
      case 0:
        selectedTime = "10";
        break;
      case 1:
        selectedTime = "20";
        break;
      case 2:
        selectedTime = "30";
        break;
      case 3:
        selectedTime = "40";
        break;
      case 4:
        selectedTime = "50";
        break;
      case 5:
        selectedTime = "60";
        break;
      case 6:
        selectedTime = "70";
        break;
      case 7:
        selectedTime = "80";
        break;
      case 8:
        selectedTime = "90";
        break;
      case 9:
        selectedTime = "100";
        break;
      case 10:
        selectedTime = "110";
        break;
      case 11:
        selectedTime = "120";
        break;
      case 12:
        selectedTime = "130";
        break;
      case 13:
        selectedTime = "140";
        break;
      case 14:
        selectedTime = "150";
        break;
      case 15:
        selectedTime = "160";
        break;
      case 16:
        selectedTime = "170";
        break;
      case 17:
        selectedTime = "180";
        break;
    }
    return selectedTime;
  }
}
