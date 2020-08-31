package com.app.delivxstore.main.laundryitemPhotos;

import com.app.delivxstore.BaseView;

import org.json.JSONArray;

import java.io.File;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public interface LaundryItemPhotosContract {

    interface View extends BaseView{

       void openImageSelectDialog();

        void finishAct();
        void setLaundryImg(int pos,String laundryImg);
        void onError(String msg);

        void showAlertDialog(int position);
    }

    interface Presenter {

        void uploadToS3(String orderId,int pos,File image);

        void uploadData(double orderId, String productId, JSONArray jsonArray);

        void deleteItem(String fileName);

        void closeAct();
    }

}
