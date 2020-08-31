package com.app.delivxstore.main.orderFrom;

import android.app.Activity;

import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.createOrder.CreateOrderContract;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.Utility;

import javax.inject.Inject;

public class OrderFromPresenter implements OrderFromContract.PresenterOperations {


    @Inject
    NetworkService networkService;
    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;
    @Inject
    Utility utility;

    @Inject
    Activity context;
    private String dueDate, dueTime;

    @Inject
    OrderFromContract.ViewOperations view;

    @Inject
    OrderFromPresenter() {

    }


    @Override
    public void searchCustomer() {
        view.searchCustomer();
    }

    @Override
    public void manageAddress() {
          view.manageAddress();
    }

    @Override
    public void DeliveryAddress() {
        view.DeliveryAddress();
    }

    @Override
    public void setTimeIn12Hour(int hour, int minute) {
        dueTime = hour + ":" + minute + ":" + "00";
        String aMpM = "AM";
        String hours = String.valueOf(hour);
        String minutes = String.valueOf(minute);
        int currentHour;
        if (hour > 11) {
            aMpM = "PM";
        }

        if (hour > 11) {
            currentHour = hour - 12;
        } else {
            currentHour = hour;
        }

        if (String.valueOf(currentHour).length() == 1) {
            hours = 0 + "" + currentHour;
        }

        if (String.valueOf(minute).length() == 1) {
            minutes = 0 + "" + minute;
        }
        String time = hours + ":" + minutes + " " + aMpM;
        view.setTimeIn12Hour(time);
    }

    @Override
    public void setDate(int currentYear, int month, int day) {
        String date = currentYear + "-" + (month + 1) + "-" + day;
        dueDate = date;
        String selectedDate = Utility.getDate(date);
        view.setDate(selectedDate);
    }

    @Override
    public void schedule() {
        view.schedule();
    }

    @Override
    public void now() {
    view.now();
    }

    @Override
    public void selectSlot() {
         view.selectTime();

    }

    @Override
    public void selectDate() {
        view.selectDate();
    }

    @Override
    public void createOrder() {

        view.createOrder();



    }

    @Override
    public void showError(String msg) {
        view.showError(msg);
    }

}
