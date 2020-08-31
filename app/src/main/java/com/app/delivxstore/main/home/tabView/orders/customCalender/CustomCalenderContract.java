package com.app.delivxstore.main.home.tabView.orders.customCalender;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public interface CustomCalenderContract {


    interface CustomCalenderView
    {
        /**
         * <h>Finish Activity</h>
         * <p>This method is using to close the activity after Successful state transition  </p>
         */
        void finishActivity();

        void setDates();



    }

    interface CustomCalenderPresenter
    {

        void getDates();

        void finish();
    }


}
