package com.app.delivxstore.utility;

import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.app.ecomstore.util.EcomConstants;
import com.app.ecomstore.util.EcomUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/*
 * Purpose – This class holds LocationData related operations
 * @author 3Embed
 * Created on Nov 25, 2019
 * Modified on
 */
public class LocationManagerUtil {
  /**
   * This method is using to get current location of the device
   */
  public static double[] getFuseLocation(Activity context,
      FusedLocationListener fusedLocationListener) {
    final double[] mLatLong = new double[TWO];
    FusedLocationProviderClient fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context);
    fusedLocationClient.getLastLocation().addOnFailureListener(
        e -> EcomUtil.printLog("latLong" + e.getMessage()))
        .addOnSuccessListener(context, location -> {
          if (location != null) {
            mLatLong[ZERO] = location.getLatitude();
            mLatLong[ONE] = location.getLongitude();
            if (fusedLocationListener != null) {
              fusedLocationListener.onSuccess(location.getLatitude(), location.getLongitude());
            }
            EcomUtil.printLog("latLong" + mLatLong[ZERO] + "" + mLatLong[ONE]);
          }
        });
    return mLatLong;
  }

  /**
   * This method used to get the current location.
   * @param context context
   * @return return string.
   */
  public static String getCurrentLocation(Activity context) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      LocationManager lm = (LocationManager) context.getApplicationContext().getSystemService(
          Context.LOCATION_SERVICE);
      Geocoder geocoder = new Geocoder(context.getApplicationContext());
      for (String provider : lm.getAllProviders()) {
        @SuppressWarnings("ResourceType")
        Location location = lm.getLastKnownLocation(provider);
        if (location != null) {
          try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                location.getLongitude(), 1);
            if (addresses != null && addresses.size() > 0) {
              stringBuilder.append(addresses.get(0).getAdminArea()).append(",").append(
                  addresses.get(0).getLocality()).append(",").append(
                  addresses.get(0).getPostalCode());
              break;
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stringBuilder.toString();
  }

  /**
   * this will check gps is enabled or not.
   * @param context context
   * @return return boolean
   */
  public static boolean isGpsEnabled(Context context) {
    boolean isEnabled;
    final LocationManager manager = (LocationManager) context.getSystemService(
        Context.LOCATION_SERVICE);
    isEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    return isEnabled;
  }

  public interface FusedLocationListener {
    /**
     * This method is using as an callback of current location
     *
     * @param latitude  current latitude
     * @param longitude current longitude
     */
    void onSuccess(double latitude, double longitude);
  }
}