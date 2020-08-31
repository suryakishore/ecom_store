package com.app.delivxstore.main.laundryitemPhotos;

import java.io.File;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class LaundryItemPhotos {

    private String itemPhoto;
    private boolean isCamera;
    private boolean isUploaded;

    public boolean isDel() {
        return isDel;
    }

    public void setDel(boolean del) {
        isDel = del;
    }

    private boolean isDel;
    private File file;

    public File getFile() {
        return file;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

    public void setFile(File file) {
        this.file = file;
    }

  /*  public boolean isFileOrUrl() {
        return isFileOrUrl;
    }

    public void setFileOrUrl(boolean fileOrUrl) {
        isFileOrUrl = fileOrUrl;
    }*/

    public String getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(String itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public boolean isCamera() {
        return isCamera;
    }

    public void setCamera(boolean camera) {
        isCamera = camera;
    }


}
