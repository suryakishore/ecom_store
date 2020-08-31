package com.app.delivxstore.manual_locate;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by dell on 22-Nov-17.
 */

public class PlaceDetail
{

    @Expose
    List<PlaceDetailResults> results;

    @Expose
    String status;

}
