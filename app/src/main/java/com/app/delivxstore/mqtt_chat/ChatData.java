package com.app.delivxstore.mqtt_chat;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * <h>ChatData</h>
 * Created by Ali on 12/22/2017.
 */
public class ChatData implements Parcelable {
  public static final Creator<ChatData> CREATOR = new Creator<ChatData>() {
    @Override
    public ChatData createFromParcel(Parcel in) {
      return new ChatData(in);
    }

    @Override
    public ChatData[] newArray(int size) {
      return new ChatData[size];
    }
  };
  @SerializedName("bid")
  @Expose
  private long bid;
  @SerializedName("timestamp")
  @Expose
  private long timestamp;
  @SerializedName("content")
  @Expose
  private String content;
  @SerializedName("fromID")
  @Expose
  private String fromID;
  @SerializedName("targetId")
  @Expose
  private String targetId;
  @SerializedName("type")
  @Expose
  private int type;
  private int custProType;
  @SerializedName("contentType")
  @Expose
  private int contentType;
  @SerializedName("_id")
  @Expose
  private String _id;

  public ChatData() {
  }

  protected ChatData(Parcel in) {
    bid = in.readLong();
    timestamp = in.readLong();
    content = in.readString();
    fromID = in.readString();
    targetId = in.readString();
    type = in.readInt();
    contentType = in.readInt();
    _id = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(bid);
    dest.writeLong(timestamp);
    dest.writeString(content);
    dest.writeString(fromID);
    dest.writeString(targetId);
    dest.writeInt(type);
    dest.writeInt(contentType);
    dest.writeString(_id);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public int getCustProType() {
    return custProType;
  }

  public void setCustProType(int custProType) {
    this.custProType = custProType;
  }

  public long getBid() {
    return bid;
  }

  public void setBid(long bid) {
    this.bid = bid;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getFromID() {
    return fromID;
  }

  public void setFromID(String fromID) {
    this.fromID = fromID;
  }

  public String getTargetId() {
    return targetId;
  }

  public void setTargetId(String targetId) {
    this.targetId = targetId;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public int getContentType() {
    return contentType;
  }

  public void setContentType(int contentType) {
    this.contentType = contentType;
  }

  @Override
  public boolean equals(Object obj) {
//        return super.equals(obj);
    ChatData chatData = (ChatData) obj;
    if (chatData != null&&chatData.get_id()!=null && !"".equals(chatData.get_id())
        && chatData.get_id().equals(this.get_id())) {
      return true;
    } else {
      return false;
    }
  }
}
