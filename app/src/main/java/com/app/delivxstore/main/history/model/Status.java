package com.app.delivxstore.main.history.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Status implements Serializable {

	@SerializedName("updatedBy")
	@Expose
	private String updatedBy;

	@SerializedName("statusText")
	@Expose
	private String statusText;

	@SerializedName("updatedOnTimeStamp")
	@Expose
	private int updatedOnTimeStamp;

	@SerializedName("updatedOn")
	@Expose
	private String updatedOn;

	@SerializedName("updatedById")
	@Expose
	private String updatedById;

	@SerializedName("status")
	@Expose
	private int status;

	public String getUpdatedBy(){
		return updatedBy;
	}

	public String getStatusText(){
		return statusText;
	}

	public int getUpdatedOnTimeStamp(){
		return updatedOnTimeStamp;
	}

	public String getUpdatedOn(){
		return updatedOn;
	}

	public String getUpdatedById(){
		return updatedById;
	}

	public int getStatus(){
		return status;
	}
}