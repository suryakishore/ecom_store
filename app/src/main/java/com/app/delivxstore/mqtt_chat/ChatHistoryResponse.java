package com.app.delivxstore.mqtt_chat;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DELL on 30-03-2018.
 */

public class ChatHistoryResponse implements Parcelable {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<ChatData> data;

    protected ChatHistoryResponse(Parcel in) {
        message = in.readString();
        data = in.createTypedArrayList(ChatData.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeTypedList(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChatHistoryResponse> CREATOR = new Creator<ChatHistoryResponse>() {
        @Override
        public ChatHistoryResponse createFromParcel(Parcel in) {
            return new ChatHistoryResponse(in);
        }

        @Override
        public ChatHistoryResponse[] newArray(int size) {
            return new ChatHistoryResponse[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ChatData> getData() {
        return data;
    }

    public void setData(ArrayList<ChatData> data) {
        this.data = data;
    }
}
