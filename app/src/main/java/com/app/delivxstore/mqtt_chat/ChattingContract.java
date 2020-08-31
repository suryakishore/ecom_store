package com.app.delivxstore.mqtt_chat;

import android.content.Intent;
import com.app.delivxstore.BaseView;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by DELL on 28-03-2018.
 */

public interface ChattingContract {

        interface ViewOperations extends BaseView {

                void setActionBar(String custName);

                void setViews(String bid);

                void setRecyclerView();

                void updateData(ArrayList<ChatData> chatDataArry);
                void sendImageData(String imageUrl);

        }

        interface PresenterOperations {

                void getData(Intent intent);

                void setActionBar();

                void initViews();

                void getChattingData();

                void message(String message,int contentType );

                void uploadImage(File file);
        }
}


