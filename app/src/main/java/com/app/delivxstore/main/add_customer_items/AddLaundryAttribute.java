package com.app.delivxstore.main.add_customer_items;

import java.io.Serializable;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class AddLaundryAttribute implements Serializable {

    private String attributeName;

    private String id;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAttributeName ()
    {
        return attributeName;
    }

    public void setAttributeName (String attributeName)
    {
        this.attributeName = attributeName;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [attributeName = "+attributeName+", id = "+id+"]";
    }
}
