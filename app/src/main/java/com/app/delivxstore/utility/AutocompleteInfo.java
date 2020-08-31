package com.app.delivxstore.utility;

/**
 * Created by dell on 05-Feb-18.
 */

public  class AutocompleteInfo {
    private final String description;
    private final String id;



    public AutocompleteInfo(String description, String id) {
        this.description = description;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return description;
    }
}
