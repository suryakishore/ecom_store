package com.app.delivxstore.utility;

import android.content.Context;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import com.app.delivxstore.R;

/**
 * <h1>FontUtils</h1>
 * This class is used to define the different font using in the application
 *
 * @author 3Embed
 * @since on 28-2-2018.
 */

public class FontUtils {

  Context mContext;

  public FontUtils(Context context) {
    this.mContext = context;
  }

  public Typeface regularFont() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_regular);
  }

  public Typeface mediumFont() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_medium);
  }

  public Typeface boldFont() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_bold);
  }

  public Typeface lightFont() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_light);
  }



  public Typeface getMontserratBlackItalic() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_black_italic);
  }

  public Typeface getMontserratBold() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_bold);
  }

  public Typeface getMontserratBoldItalic() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_bold_italic);
  }

  public Typeface getMontserratExtraBold() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_extra_bold);
  }

  public Typeface getMontserratExtraBoldItalic() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_extra_bold_italic);
  }

  public Typeface getMontserratExtraLight() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_extra_light);
  }

  public Typeface getMontserratExtraLightItalic() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_extra_light_italic);
  }

  public Typeface getMontserratItalic() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_italic);
  }

  public Typeface getMontserratLight() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_light);
  }

  public Typeface getMontserratLightItalic() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_light_italic);
  }

  public Typeface getMontserratMedium() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_medium);
  }

  public Typeface getMontserratMediumItalic() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_medium_italic);
  }

  public Typeface getMontserratRegular() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_regular);
  }

  public Typeface getMontserratSemiBold() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_semi_bold);
  }

  public Typeface getMontserratSemiBoldItalic() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_semi_bold_italic);
  }

  public Typeface getMontserratThin() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_thin);
  }

  public Typeface getMontserratThinItalic() {
    return ResourcesCompat.getFont(mContext, R.font.montserrat_thin_italic);
  }
}
