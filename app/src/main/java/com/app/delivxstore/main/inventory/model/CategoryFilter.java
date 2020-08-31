package com.app.delivxstore.main.inventory.model;

public class CategoryFilter {
    private String category;
    private boolean isSelected= false;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
