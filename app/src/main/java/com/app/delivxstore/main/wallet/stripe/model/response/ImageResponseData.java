package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ImageResponseData implements Parcelable {
  @SerializedName("originalName")
  @Expose
  private String originalName;

  @SerializedName("fileName")
  @Expose
  private String fileName;

  @SerializedName("mimetype")
  @Expose
  private String mimetype;

  @SerializedName("imageUrl")
  @Expose
  private String imageUrl;

  protected ImageResponseData(Parcel in) {
    originalName = in.readString();
    fileName = in.readString();
    mimetype = in.readString();
    imageUrl = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(originalName);
    dest.writeString(fileName);
    dest.writeString(mimetype);
    dest.writeString(imageUrl);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ImageResponseData> CREATOR = new Creator<ImageResponseData>() {
    @Override
    public ImageResponseData createFromParcel(Parcel in) {
      return new ImageResponseData(in);
    }

    @Override
    public ImageResponseData[] newArray(int size) {
      return new ImageResponseData[size];
    }
  };

  public String getOriginalName() {
    return originalName;
  }

  public String getFileName() {
    return fileName;
  }

  public String getMimetype() {
    return mimetype;
  }

  public String getUrl() {
    return imageUrl;
  }

  @Override
  public String toString() {
    return
        "ImageResponseData{" +
            "originalName = '" + originalName + '\'' +
            ",fileName = '" + fileName + '\'' +
            ",mimetype = '" + mimetype + '\'' +
            ",imageUrl = '" + imageUrl + '\'' +
            "}";
  }
}
