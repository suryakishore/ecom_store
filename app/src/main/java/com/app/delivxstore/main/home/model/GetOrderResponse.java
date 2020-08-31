package com.app.delivxstore.main.home.model;

import java.io.Serializable;
import java.util.ArrayList;

public class GetOrderResponse implements Serializable {

    private ArrayList<OrderDetails> newOrders;

    private ArrayList<OrderDetails> acceptedOrder;


    public ArrayList<OrderDetails> getNewLaterOrder() {
        return newOrder;
    }

    public String getAcceptedOrderCount() {
        return acceptedOrderCount;
    }

    public void setAcceptedOrderCount(String acceptedOrderCount) {
        this.acceptedOrderCount = acceptedOrderCount;
    }

    public ArrayList<OrderDetails> getAcceptedOrder() {
        return acceptedOrder;
    }

    public void setAcceptedOrder(ArrayList<OrderDetails> acceptedOrder) {
        this.acceptedOrder = acceptedOrder;
    }

    public void setNewLaterOrder(ArrayList<OrderDetails> newOrder) {
        this.newOrder = newOrder;
    }

    private ArrayList<OrderDetails> newOrder;
    private ArrayList<OrderDetails> pickUpReadyOrder;
    private ArrayList<OrderDetails> unassign;
    private ArrayList<OrderDetails> pickedUpOrder;
    private ArrayList<OrderDetails> inDispatchOrder;
    private ArrayList<OrderDetails> ready;
    private ArrayList<OrderDetails> laterOrder;
    private ArrayList<OrderDetails> packageOrder;
    private ArrayList<OrderDetails> preparing;
    private ArrayList<OrderDetails> inProgress;
    private ArrayList<OrderDetails> indelivery;
    private int storeType;
    private String unassignCount;
    private String pickUpReadyOrderCount;
    private String newCount;
    private String pickedUpOrderCount;
    private String readyCount;
    private String laterOrderCount,packageOrderCount;
    private String inDispatchOrderCount;
    private String preparingCount;
    private String inProgressCount,indeliveryCount,newOrderCount,acceptedOrderCount;

    public ArrayList<OrderDetails> getInProgress() {
        return inProgress;
    }

    public void setInProgress(ArrayList<OrderDetails> inProgress) {
        this.inProgress = inProgress;
    }

    public ArrayList<OrderDetails> getIndelivery() {
        return indelivery;
    }

    public void setIndelivery(ArrayList<OrderDetails> indelivery) {
        this.indelivery = indelivery;
    }

    public String getIndeliveryCount() {
        return indeliveryCount;
    }

    public void setIndeliveryCount(String indeliveryCount) {
        this.indeliveryCount = indeliveryCount;
    }

    public String getNewLaterOrderCount() {
        return newOrderCount;
    }

    public void setNewLaterOrderCount(String newOrderCount) {
        this.newOrderCount = newOrderCount;
    }

    public String getInProgressCount() {
        return inProgressCount;
    }

    public void setInProgressCount(String inProgressCount) {
        this.inProgressCount = inProgressCount;
    }

    public String getPackageOrderCount() {
        return packageOrderCount;
    }

    public void setPackageOrderCount(String packageOrderCount) {
        this.packageOrderCount = packageOrderCount;
    }

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
    }

    public ArrayList<OrderDetails> getPackageOrder() {
        return packageOrder;
    }

    public void setPackageOrder(ArrayList<OrderDetails> packageOrder) {
        this.packageOrder = packageOrder;
    }

    public ArrayList<OrderDetails> getAssign() {
        return pickedUpOrder;
    }

    public void setAssign(ArrayList<OrderDetails> pickedUpOrder) {
        this.pickedUpOrder = pickedUpOrder;
    }

    public ArrayList<OrderDetails> getNewOrders() {
        return newOrders;
    }

    public void setNewOrders(ArrayList<OrderDetails> newOrders) {
        this.newOrders = newOrders;
    }

    public ArrayList<OrderDetails> getPickup() {
        return pickUpReadyOrder;
    }

    public void setPickup(ArrayList<OrderDetails> pickUpReadyOrder) {
        this.pickUpReadyOrder = pickUpReadyOrder;
    }

    public String getPreparingCount() {
        return preparingCount;
    }

    public void setPreparingCount(String preparingCount) {
        this.preparingCount = preparingCount;
    }

    public String getReadyCount() {
        return readyCount;
    }

    public void setReadyCount(String readyCount) {
        this.readyCount = readyCount;
    }

    public ArrayList<OrderDetails> getUnassign() {
        return unassign;
    }

    public void setUnassign(ArrayList<OrderDetails> unassign) {
        this.unassign = unassign;
    }

    public String getLaterOrderCount() {
        return laterOrderCount;
    }

    public void setLaterOrderCount(String laterOrderCount) {
        this.laterOrderCount = laterOrderCount;
    }

    public ArrayList<OrderDetails> getReady() {
        return ready;
    }

    public void setReady(ArrayList<OrderDetails> ready) {
        this.ready = ready;
    }

    public ArrayList<OrderDetails> getLaterOrder() {
        return laterOrder;
    }

    public void setLaterOrder(ArrayList<OrderDetails> laterOrder) {
        this.laterOrder = laterOrder;
    }

    public ArrayList<OrderDetails> getIndispatch() {
        return inDispatchOrder;
    }

    public void setIndispatch(ArrayList<OrderDetails> inDispatchOrder) {
        this.inDispatchOrder = inDispatchOrder;
    }

    public String getUnassignCount() {
        return unassignCount;
    }

    public void setUnassignCount(String unassignCount) {
        this.unassignCount = unassignCount;
    }

    public String getPickupCount() {
        return pickUpReadyOrderCount;
    }

    public void setPickupCount(String pickUpReadyOrderCount) {
        this.pickUpReadyOrderCount = pickUpReadyOrderCount;
    }

    public String getIndispatchCount() {
        return inDispatchOrderCount;
    }

    public void setIndispatchCount(String inDispatchOrderCount) {
        this.inDispatchOrderCount = inDispatchOrderCount;
    }

    public ArrayList<OrderDetails> getPreparing() {
        return preparing;
    }

    public void setPreparing(ArrayList<OrderDetails> preparing) {
        this.preparing = preparing;
    }

    public String getNewCount() {
        return newCount;
    }

    public void setNewCount(String newCount) {
        this.newCount = newCount;
    }

    public String getAssignCount() {
        return pickedUpOrderCount;
    }

    public void setAssignCount(String pickedUpOrderCount) {
        this.pickedUpOrderCount = pickedUpOrderCount;
    }

    @Override
    public String toString() {
        return "ClassPojo [" +
                "assign = " + pickedUpOrder +
                ", newOrders = " + newOrders +
                ", pickup = " + pickUpReadyOrderCount +
                ", preparingCount = " + preparingCount +
                ", readyCount = " + readyCount +
                ", unassign = " + unassign +
                ", laterOrderCount = " + laterOrderCount +
                ", ready = " + ready +
                ", laterOrder = " + laterOrder +
                ", indispatch = " + inDispatchOrder +
                ", unassignCount = " + unassignCount +
                ", pickupCount = " + pickUpReadyOrderCount +
                ", indispatchCount = " + inDispatchOrderCount +
                ", preparing = " + preparing +
                ", newCount = " + newCount +
                ", assignCount = " + pickedUpOrderCount +
                "]";
    }
}

