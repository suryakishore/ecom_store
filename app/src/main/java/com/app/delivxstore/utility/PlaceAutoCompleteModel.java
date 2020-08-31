package com.app.delivxstore.utility;

/**
 * <h1>PlaceAutoCompleteModel</h1>
 * used to store the auto search address
 * @author 3embed
 * @author on 27/3/17.
 */

public class PlaceAutoCompleteModel
{
    private String address;
    private String ref_key;
    private String id;
    private String lat, lng;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRef_key() {
        return ref_key;
    }

    public void setRef_key(String ref_key) {
        this.ref_key = ref_key;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
