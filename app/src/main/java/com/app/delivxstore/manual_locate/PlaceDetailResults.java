package com.app.delivxstore.manual_locate;

import com.google.gson.annotations.Expose;

/**
 * Created by dell on 22-Nov-17.
 */

public class PlaceDetailResults {

    @Expose
    public PlaceGeometry geometry;

    @Expose
    String formatted_address;
}
