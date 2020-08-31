package com.app.delivxstore.manual_locate;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by dell on 20-Nov-17.
 */

public class PlacesResult {

    @Expose
    List<Prediction> predictions;

    @Expose
    String status;
}
