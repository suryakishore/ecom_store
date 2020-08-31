package com.app.delivxstore.main.wallet.stripe.model.response;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ImageUploadModel implements Parcelable {

    @SerializedName("data")
    @Expose
    private ImageResponseData data;

    @SerializedName("message")
    @Expose
    private String message;

  protected ImageUploadModel(Parcel in) {
    data = in.readParcelable(ImageResponseData.class.getClassLoader());
    message = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(data, flags);
    dest.writeString(message);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ImageUploadModel> CREATOR = new Creator<ImageUploadModel>() {
    @Override
    public ImageUploadModel createFromParcel(Parcel in) {
      return new ImageUploadModel(in);
    }

    @Override
    public ImageUploadModel[] newArray(int size) {
      return new ImageUploadModel[size];
    }
  };

  public void setData(ImageResponseData data) {
        this.data = data;
    }

    public ImageResponseData getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return
                "ImageUploadModel{" +
                        "data = '" + data + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}