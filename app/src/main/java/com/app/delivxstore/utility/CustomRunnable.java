package com.app.delivxstore.utility;

import android.os.Handler;
import android.widget.TextView;

public class CustomRunnable implements Runnable {

  public long millisUntilFinished;
  public TextView holder;
  private Handler handler;

  public CustomRunnable(Handler handler, TextView holder, long millisUntilFinished) {
    this.handler = handler;
    this.holder = holder;
    this.millisUntilFinished = millisUntilFinished;

  }

  @Override
  public void run() {
    long seconds = millisUntilFinished / 1000;
    long minutes = seconds / 60;
    long hours = minutes/ 60;
    String time = hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
    holder.setText(time);

    millisUntilFinished -= 1000;
      /* and here comes the "trick" */
    handler.postDelayed(this, 1000);
  }

}