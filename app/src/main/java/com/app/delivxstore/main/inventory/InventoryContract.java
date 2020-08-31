package com.app.delivxstore.main.inventory;


import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.inventory.model.CategoryProduct;
import com.app.delivxstore.main.inventory.model.InventaryProducts;
import com.app.delivxstore.main.inventory.model.InventoryData;

import java.util.ArrayList;
import java.util.HashMap;

public interface InventoryContract
{
    interface View extends BaseView {
        void setInventaryData(ArrayList<InventoryData> inventaryDatas, ArrayList<CategoryProduct> categoryProducts,
                              ArrayList<String> categoryNames);

        void setInvenatary(ArrayList<InventaryProducts> invenatary);
        void showMessage(String msg);
        void changeInventary(int position,int status);

    }

    interface Presenter{
        void attachView(Object view);
        void detachView();
        void getInventory();
        void updateInventory(HashMap<String, Integer> selectedProductsWithStatus);
        void setInventory(String productId,int status,int position);
    }

}


