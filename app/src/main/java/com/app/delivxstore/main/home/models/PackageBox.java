package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageBox  implements Parcelable {

  @SerializedName("weight")
  @Expose
  private Integer weight;
  @SerializedName("weightCapacityUnit")
  @Expose
  private String weightCapacityUnit;
  @SerializedName("volumeCapacity")
  @Expose
  private Integer volumeCapacity;
  @SerializedName("voulumeCapacityUnit")
  @Expose
  private String voulumeCapacityUnit;
  @SerializedName("weightCapacityUnitName")
  @Expose
  private String weightCapacityUnitName;
  @SerializedName("voulumeCapacityUnitName")
  @Expose
  private String voulumeCapacityUnitName;
  @SerializedName("lengthCapacity")
  @Expose
  private Integer lengthCapacity;
  @SerializedName("lengthCapacityUnit")
  @Expose
  private String lengthCapacityUnit;
  @SerializedName("lengthCapacityUnitName")
  @Expose
  private String lengthCapacityUnitName;
  @SerializedName("widthCapacity")
  @Expose
  private Integer widthCapacity;
  @SerializedName("widthCapacityUnit")
  @Expose
  private String widthCapacityUnit;
  @SerializedName("widthCapacityUnitName")
  @Expose
  private String widthCapacityUnitName;
  @SerializedName("heightCapacity")
  @Expose
  private Integer heightCapacity;
  @SerializedName("heightCapacityUnit")
  @Expose
  private String heightCapacityUnit;
  @SerializedName("heightCapacityUnitName")
  @Expose
  private String heightCapacityUnitName;
  @SerializedName("image")
  @Expose
  private String image;
  @SerializedName("boxId")
  @Expose
  private String boxId;

  protected PackageBox(Parcel in) {
    if (in.readByte() == 0) {
      weight = null;
    } else {
      weight = in.readInt();
    }
    weightCapacityUnit = in.readString();
    if (in.readByte() == 0) {
      volumeCapacity = null;
    } else {
      volumeCapacity = in.readInt();
    }
    voulumeCapacityUnit = in.readString();
    weightCapacityUnitName = in.readString();
    voulumeCapacityUnitName = in.readString();
    if (in.readByte() == 0) {
      lengthCapacity = null;
    } else {
      lengthCapacity = in.readInt();
    }
    lengthCapacityUnit = in.readString();
    lengthCapacityUnitName = in.readString();
    if (in.readByte() == 0) {
      widthCapacity = null;
    } else {
      widthCapacity = in.readInt();
    }
    widthCapacityUnit = in.readString();
    widthCapacityUnitName = in.readString();
    if (in.readByte() == 0) {
      heightCapacity = null;
    } else {
      heightCapacity = in.readInt();
    }
    heightCapacityUnit = in.readString();
    heightCapacityUnitName = in.readString();
    image = in.readString();
    boxId = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    if (weight == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(weight);
    }
    dest.writeString(weightCapacityUnit);
    if (volumeCapacity == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(volumeCapacity);
    }
    dest.writeString(voulumeCapacityUnit);
    dest.writeString(weightCapacityUnitName);
    dest.writeString(voulumeCapacityUnitName);
    if (lengthCapacity == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(lengthCapacity);
    }
    dest.writeString(lengthCapacityUnit);
    dest.writeString(lengthCapacityUnitName);
    if (widthCapacity == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(widthCapacity);
    }
    dest.writeString(widthCapacityUnit);
    dest.writeString(widthCapacityUnitName);
    if (heightCapacity == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(heightCapacity);
    }
    dest.writeString(heightCapacityUnit);
    dest.writeString(heightCapacityUnitName);
    dest.writeString(image);
    dest.writeString(boxId);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<PackageBox> CREATOR = new Creator<PackageBox>() {
    @Override
    public PackageBox createFromParcel(Parcel in) {
      return new PackageBox(in);
    }

    @Override
    public PackageBox[] newArray(int size) {
      return new PackageBox[size];
    }
  };

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public String getWeightCapacityUnit() {
    return weightCapacityUnit;
  }

  public void setWeightCapacityUnit(String weightCapacityUnit) {
    this.weightCapacityUnit = weightCapacityUnit;
  }

  public Integer getVolumeCapacity() {
    return volumeCapacity;
  }

  public void setVolumeCapacity(Integer volumeCapacity) {
    this.volumeCapacity = volumeCapacity;
  }

  public String getVoulumeCapacityUnit() {
    return voulumeCapacityUnit;
  }

  public void setVoulumeCapacityUnit(String voulumeCapacityUnit) {
    this.voulumeCapacityUnit = voulumeCapacityUnit;
  }

  public String getWeightCapacityUnitName() {
    return weightCapacityUnitName;
  }

  public void setWeightCapacityUnitName(String weightCapacityUnitName) {
    this.weightCapacityUnitName = weightCapacityUnitName;
  }

  public String getVoulumeCapacityUnitName() {
    return voulumeCapacityUnitName;
  }

  public void setVoulumeCapacityUnitName(String voulumeCapacityUnitName) {
    this.voulumeCapacityUnitName = voulumeCapacityUnitName;
  }

  public Integer getLengthCapacity() {
    return lengthCapacity;
  }

  public void setLengthCapacity(Integer lengthCapacity) {
    this.lengthCapacity = lengthCapacity;
  }

  public String getLengthCapacityUnit() {
    return lengthCapacityUnit;
  }

  public void setLengthCapacityUnit(String lengthCapacityUnit) {
    this.lengthCapacityUnit = lengthCapacityUnit;
  }

  public String getLengthCapacityUnitName() {
    return lengthCapacityUnitName;
  }

  public void setLengthCapacityUnitName(String lengthCapacityUnitName) {
    this.lengthCapacityUnitName = lengthCapacityUnitName;
  }

  public Integer getWidthCapacity() {
    return widthCapacity;
  }

  public void setWidthCapacity(Integer widthCapacity) {
    this.widthCapacity = widthCapacity;
  }

  public String getWidthCapacityUnit() {
    return widthCapacityUnit;
  }

  public void setWidthCapacityUnit(String widthCapacityUnit) {
    this.widthCapacityUnit = widthCapacityUnit;
  }

  public String getWidthCapacityUnitName() {
    return widthCapacityUnitName;
  }

  public void setWidthCapacityUnitName(String widthCapacityUnitName) {
    this.widthCapacityUnitName = widthCapacityUnitName;
  }

  public Integer getHeightCapacity() {
    return heightCapacity;
  }

  public void setHeightCapacity(Integer heightCapacity) {
    this.heightCapacity = heightCapacity;
  }

  public String getHeightCapacityUnit() {
    return heightCapacityUnit;
  }

  public void setHeightCapacityUnit(String heightCapacityUnit) {
    this.heightCapacityUnit = heightCapacityUnit;
  }

  public String getHeightCapacityUnitName() {
    return heightCapacityUnitName;
  }

  public void setHeightCapacityUnitName(String heightCapacityUnitName) {
    this.heightCapacityUnitName = heightCapacityUnitName;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }



  public String getBoxId() {
    return boxId;
  }

  public void setBoxId(String boxId) {
    this.boxId = boxId;
  }

}
