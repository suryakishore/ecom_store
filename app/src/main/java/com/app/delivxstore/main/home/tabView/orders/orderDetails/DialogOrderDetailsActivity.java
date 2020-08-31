package com.app.delivxstore.main.home.tabView.orders.orderDetails;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.adapter.PastOrderInOrderDetAdapter;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.AddOnGroup;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.AddOns;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.ExclusiveTaxes;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.OrderedItemDetails;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.PastOrdersData;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Reasons;
import com.app.delivxstore.main.laundryitemPhotos.LaundryItemPhotosActivity;
import com.app.delivxstore.utility.Slider;
import com.app.delivxstore.utility.Utility;
import com.app.delivxstore.utility.VerticalTextView;
import com.app.ecomstore.drivers.SelectDriversActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

/**
 * <h>dialog order details activity</h>
 * <p>This class is used for the showing the order details for the tablet view.</p>
 */


public class DialogOrderDetailsActivity extends DaggerAppCompatActivity
    implements DialogOrderDetailsContract.ViewOperation {


  public CountDownTimer countDownTimer;
  @BindView(R.id.tvPrintOrder)
  TextView tvPrintOrder;
  @BindView(R.id.tvOrderId)
  TextView tvOrderId;
  @BindView(R.id.ivCloseButton)
  ImageView ivCloseButton;
  @BindView(R.id.tvCurrentOrder)
  VerticalTextView tvCurrentOrder;
  @BindView(R.id.tvPastOrder)
  VerticalTextView tvPastOrder;
  @BindView(R.id.viewCurrentOrderSelector)
  View viewCurrentOrderSelector;
  @BindView(R.id.viewPastOrderSelector)
  View viewPastOrderSelector;
  @BindView(R.id.viewDriverDetails)
  View viewDriverDetails;
  @BindView(R.id.viewRevenueLine)
  View viewRevenueLine;
  @BindView(R.id.ll_serviceCharge)
  LinearLayout ll_serviceCharge;
  /* @BindView(R.id.ivProfile)
   CircleImageView ivProfile;*/
  /*  @BindView(R.id.ivDeviceType)
    ImageView ivDeviceType;*/
  @BindView(R.id.textViewDriverRowDriverName)
  TextView tvName;
  /*@BindView(R.id.tvMobileNo)
  TextView tvMobileNo;*/
   /* @BindView(R.id.ivPayType)
    ImageView ivPayType;*/
  /*  @BindView(R.id.tvOrders)
    TextView tvOrders;*/
  /*  @BindView(R.id.tvRevenue)
    TextView tvRevenue;*/
  @BindView(R.id.tvPaymentType)
  TextView tvPaymentType;
  @BindView(R.id.tvOrderType)
  TextView tvOrderType;
  @BindView(R.id.tvDeliverTo)
  TextView tvDeliverTo;
  @BindView(R.id.llCurrentOrderDetails)
  LinearLayout llCurrentOrderDetails;
  /* @BindView(R.id.llOrdersAndRevenue)
   LinearLayout llOrdersAndRevenue;*/
  @BindView(R.id.llDispatchButtons)
  LinearLayout llDispatchButtons;
  @BindView(R.id.llAcceptRejectButtons)
  LinearLayout llAcceptRejectButtons;
  @BindView(R.id.llDispatchPickUpOrderButtons)
  LinearLayout llDispatchPickUpOrderButtons;
  @BindView(R.id.llItemContainer)
  LinearLayout llItemContainer;
  @BindView(R.id.llBottomButtons)
  RelativeLayout llBottomButtons;
  @BindView(R.id.rvPastOrders)
  RecyclerView rvPastOrders;
  @BindView(R.id.tvAccept)
  TextView tvAccept;
  @BindView(R.id.tvReject)
  TextView tvReject;
  @BindView(R.id.tvManual)
  TextView tvManual;
  @BindView(R.id.tvAutomatic)
  TextView tvAutomatic;
  @BindView(R.id.tvCancel)
  TextView tvCancel;
  @BindView(R.id.tvComments)
  TextView tvComments;
  @BindView(R.id.tvSubTotal)
  TextView tvSubTotal;
  @BindView(R.id.tvDeliveryCharge)
  TextView tvDeliveryCharge;
  @BindView(R.id.tvDiscount)
  TextView tvDiscount;
  @BindView(R.id.tvServiceCharge)
  TextView tvServiceCharge;
  @BindView(R.id.tvEdit)
  TextView tvEdit;
  @BindView(R.id.tvTotal)
  TextView tvTotal;
  @BindView(R.id.tvCancel3)
  TextView tvCancel3;
  @BindView(R.id.tvCancel2)
  TextView tvCancel2;
  @BindView(R.id.tvOrderStatusName)
  TextView tvStatus;
  @BindView(R.id.tvPicked)
  TextView tvPicked;
  @BindView(R.id.tvReady)
  TextView tvReady;
  @BindView(R.id.tvDelay)
  TextView tvDelay;
  @BindView(R.id.progressBar)
  ProgressBar progressBar;
  @BindView(R.id.rlDriverDetails)
  RelativeLayout rlDriverDetails;
  @BindView(R.id.ivCall)
  ImageView ivCall;
  @BindView(R.id.ivDriverCall)
  ImageView ivDriverCall;
  /* @BindView(R.id.ivMessage)
   ImageView ivMessage;*/
  @BindView(R.id.ivDriverProfile)
  ImageView ivDriverProfile;
  @BindView(R.id.tvDriverName)
  TextView tvDriverName;
  /* @BindView(R.id.tvDriverMobNum)
   TextView tvDriverMobNum;*/
  @BindView(R.id.llCostView)
  LinearLayout llCostView;
  @BindView(R.id.llEmptyView)
  LinearLayout llEmptyView;
  @BindView(R.id.taxDetailsRl)
  LinearLayout taxDetailsRl;
  @BindView(R.id.taxDetailsLl)
  LinearLayout taxDetailsLl;
  @BindView(R.id.taxesTv)
  TextView taxesTv;
  @BindView(R.id.tvTaxTotal)
  TextView tvTaxTotal;
  @BindView(R.id.tv_backBtn)
  TextView tv_backBtn;
  @BindView(R.id.constraintLayoutOrderDetailsMainContainer)
  RelativeLayout rlMain;
  @BindView(R.id.rl_profilePic)
  RelativeLayout rl_profilePic;
  @BindView(R.id.rl_orderDet)
  RelativeLayout rl_orderDet;
  @BindView(R.id.ll_pastOrder)
  LinearLayout ll_pastOrder;
  @BindView(R.id.cv_currentOrder)
  CardView cv_currentOrder;
  @BindView(R.id.tvServiceChargeTxt)
  TextView tvServiceChargeTxt;
  @BindView(R.id.rl_myseek)
  RelativeLayout rl_myseek;
  @BindView(R.id.tv_status_text)
  TextView tv_status_text;
  @BindView(R.id.tvDueAt)
  TextView tvDueAt;
  @BindView(R.id.view)
  View view;
  @BindView(R.id.ll_comments)
  LinearLayout ll_comments;
  @BindView(R.id.ll_comments_txt)
  LinearLayout ll_comments_txt;
  @BindView(R.id.myseek)
  Slider myseek;
  @BindView(R.id.ll_manual_assign)
  LinearLayout ll_manual_assign;
  @BindView(R.id.tvManualAssign)
  TextView tvManualAssign;
  @Inject
  DialogOrderDetailsContract.PresenterOperations presenter;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  private String currencySymbol;
  private CancellationDialog cancelDialog;
  private PastOrderInOrderDetAdapter pastOrderAdapter;
  private OrderedItemDetails orderedItemDetails;
  private String storeType, orderStatus = "";
  private Dialog dialog;
  private float addOnCount = 0;

  private ArrayList<Items> itemsArrayList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Utility.RtlConversion(this, preferenceHelperDataSource.getLanguage());

    setContentView(R.layout.dialog_order_details);

    ButterKnife.bind(this);

    getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    presenter.getBundleData(getIntent().getExtras());

    LinearLayoutManager llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    rvPastOrders.setLayoutManager(llm);


    myseek.setSliderProgressCallback(new Slider.SliderProgressCallback() {
      @Override
      public void onSliderProgressChanged(int progress) {

        if (progress > 65) {

          myseek.setProgress(100);

          if (preferenceHelperDataSource.getStoreType() == 5 || storeType.equals("5")) {
            if (tv_status_text.getText().toString() != null && tv_status_text.getText().toString().equals(getResources().getString(R.string.inDispatch))) {
              //     Log.d("exe", "inDispatch");
              presenter.dispatch();
            } else if (tv_status_text.getText().toString() != null && tv_status_text.getText().toString().equals(getResources().getString(R.string.inProgress))) {
              //   Log.d("exe", "inProgress");

              presenter.updateOrder(25);
            }
          } else {

            if (tv_status_text.getText().toString() != null && tv_status_text.getText().toString().equals(getResources().getString(R.string.dispatch))) {
              //  Log.d("exe", "inDispatch");
              presenter.dispatch();
            } else if (tv_status_text.getText().toString() != null && tv_status_text.getText().toString().equals(getResources().getString(R.string.pickUp))) {
              //   Log.d("exe", "pickup");
              presenter.updateOrder(5);
            } else if (tv_status_text.getText().toString() != null && tv_status_text.getText().toString().equals(getResources().getString(R.string.picked))) {

              presenter.updateOrder(7);

            }


          }

          //   presenter.updateBookingStatus();

        }
      }
    });


  }

  /**
   * <h>set the values to views</h>
   *
   * @param orderDetails this object is used to set the values for the particular order id.
   */

  @RequiresApi(api = Build.VERSION_CODES.O)
  @SuppressLint("SetTextI18n")
  @Override
  public void setViews(OrderedItemDetails orderDetails) {
    this.orderedItemDetails = orderDetails;
    this.orderStatus = orderDetails.getStatus();

    llCostView.setVisibility(View.VISIBLE);
    llEmptyView.setVisibility(View.INVISIBLE);
    currencySymbol = orderDetails.getCurrencySymbol();
    tvOrderId.setText(getResources().getString(R.string.orderID).toUpperCase() + " " + orderDetails.getOrderId());
    tvName.setText(orderDetails.getCustomerDetails().getName());
    //   tvMobileNo.setText(orderDetails.getCustomerDetails().getCountryCode() + "-" + 
    //   orderDetails.getCustomerDetails().getMobile());
    //tvOrders.setText(orderDetails.getCustomerDetails().getCustomerOrderCount());
    // tvRevenue.setText(currencySymbol + " " + Utility.formatPrice("" + orderDetails
    // .getCustomerDetails().getCustomerRevenue()));
    tvDeliverTo.setText(orderDetails.getDrop().getAddressLine1() + orderDetails.getDrop().getAddressLine1());
    //    tvSubTotal.setText(currencySymbol + " " + (orderDetails.getSubTotalAmount()));
    tvDeliveryCharge.setText(currencySymbol + " " + Utility.formatPrice(orderDetails.getDeliveryCharge()));
    tvDiscount.setText(currencySymbol + " " + Utility.formatPrice(orderDetails.getDiscount()));
    //  tvServiceCharge.setText(currencySymbol + " " + Utility.formatPrice("0"));
    //  Log.d("exe","orderDetails.getSubTotalAmountWithExcTax()"+orderDetails.getTotalAmount());

      /*  if (orderDetails.getTotalAmount() != null) {

            Double totalAmt = Double.parseDouble(orderDetails.getTotalAmount()) - Double
            .parseDouble(orderDetails.getDeliveryCharge());
            tvTotal.setText(currencySymbol + " " + Utility.roundOfDoubleValue(String.valueOf
            (totalAmt)));

        }*/
        /*if (orderDetails.getCustomerDetails().getDeviceType().equals("1")) {
            ivDeviceType.setImageResource(R.drawable.order_android_logo);  //ios
        } else {
            ivDeviceType.setImageResource(R.drawable.order_android_logo);  //android
        }*/

      /*  if (orderDetails.getPaymentType().equals("1")) {
            ivPayType.setImageResource(R.drawable.atm_card_icon);
            tvPaymentType.setText(getResources().getString(R.string.card));
        } else {
            ivPayType.setImageResource(R.drawable.note_icon);
            tvPaymentType.setText(getResources().getString(R.string.cash));
        }*/

    if (orderDetails.getPaymentType().equals("2") && orderDetails.getPayByWallet().equals("1")) {
      tvPaymentType.setText((getResources().getString(R.string.cash) + "+" + getResources().getString(R.string.wallet)));

    } else if (orderDetails.getPaymentType().equals("1") && orderDetails.getPayByWallet().equals(
        "1")) {
      tvPaymentType.setText((getResources().getString(R.string.card) + "+" + getResources().getString(R.string.wallet)));

    } else if (orderDetails.getPaymentType().equals("1") && orderDetails.getPayByWallet().equals(
        "0")) {
      tvPaymentType.setText((getResources().getString(R.string.card)));

    } else if (orderDetails.getPaymentType().equals("2") && orderDetails.getPayByWallet().equals(
        "0")) {
      tvPaymentType.setText((getResources().getString(R.string.cash)));

    } else if (!orderDetails.getPaymentType().equals("1") && !orderDetails.getPayByWallet().equals("2")) {
      tvPaymentType.setText((getResources().getString(R.string.wallet)));
    }


    if (orderDetails.getServiceType().equals("1")) {
      tvOrderType.setText(getResources().getString(R.string.delivery));
    } else {
      tvOrderType.setText(getResources().getString(R.string.pickup));
    }
    if (orderDetails.getBookingType() != null && orderDetails.getBookingType().equals("1")) {
      tvDueAt.setText(getResources().getString(R.string.asap));
    } else if (orderDetails.getBookingType() != null && orderDetails.getBookingType().equals("2")) {
      //tvDueAt.setText(getResources().getString(R.string.later));

      setCountDownTimer(orderDetails,
          Utility.getTimeInMilliSec(orderDetails.getDueDatetimeTimeStamp()));


    }

      /*  if (!orderDetails.getCustomerDetails().getProfilePic().isEmpty()) {
            RequestOptions myOptions = new RequestOptions()
                    .error(ContextCompat.getDrawable(this, R.drawable.profile))
                    .override(180, 180);

            Glide.with(this)
                    .load(orderDetails.getCustomerDetails().getProfilePic().replace(" ", "%20"))
                    .apply(myOptions)
                    .into(ivProfile);
        }*/

    if (preferenceHelperDataSource.getStoreType() == 5 && orderDetails.getWeight() != null) {
      ll_serviceCharge.setVisibility(View.VISIBLE);
      tvServiceChargeTxt.setText(getResources().getString(R.string.cleaningFee) + "( " + orderDetails.getWeight() + "kg )");
    }

    if (orderDetails.getDriverDetails() != null) {

      rlDriverDetails.setVisibility(View.VISIBLE);
      viewDriverDetails.setVisibility(View.VISIBLE);

      tvDriverName.setText(orderDetails.getDriverDetails().getfName() + " " + orderDetails.getDriverDetails().getlName());
      //  tvDriverMobNum.setText(orderDetails.getDriverDetails().getMobile());

      if (orderDetails.getDriverDetails().getImage() != null && !orderDetails.getDriverDetails().getImage().isEmpty()) {
        RequestOptions myOptions = new RequestOptions()
            .error(ContextCompat.getDrawable(this, R.drawable.profile))
            .override(180, 180);

        Utility.printLog("DriverImageUrl" + orderDetails.getDriverDetails().getImage());
        Glide.with(this)
            .load(orderDetails.getDriverDetails().getImage().replace(" ", "%20"))
            .apply(myOptions)
            .into(ivDriverProfile);
      }

      //   Log.d("exe", "getStoreType" + preferenceHelperDataSource.getStoreType() + "wei" + 
      //   orderDetails.getWeight());

    } else {
      rlDriverDetails.setVisibility(View.GONE);
    }


       /* if (orderDetails.isInDispatch()) {
            rlDriverDetails.setVisibility(View.VISIBLE);
            tv_status_text.setText(getResources().getString(R.string.waitingForDriver));
            tvDriverName.setText(getResources().getString(R.string.driverNotAssigned));
            ivDriverProfile.setVisibility(View.VISIBLE);

            //   holder.btn_statusMsg.setTextColor(Color.RED);
            Glide.with(this).
                    load(R.drawable.live_status)
                    .into(ivDriverProfile);
            ivDriverCall.setVisibility(View.GONE);

        }*/

    this.storeType = orderDetails.getStoreType();

    if (orderedItemDetails.getExtraNote() != null && !orderedItemDetails.getExtraNote().isEmpty()) {
      view.setVisibility(View.VISIBLE);
      ll_comments.setVisibility(View.VISIBLE);
      ll_comments_txt.setVisibility(View.VISIBLE);
      tvComments.setText(orderedItemDetails.getExtraNote());

    }

    setBottomButtons(orderDetails);
    addItems(orderDetails.getItems(), orderDetails.getCurrencySymbol(), false);

  }

  @Override
  public void finishActivity() {
    finish();
  }

  @Override
  public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @SuppressLint("SetTextI18n")
  public void addItems(final ArrayList<Items> items, String currency, boolean enabled) {

    if (items != null && items.size() > 0) {
      itemsArrayList.clear();
      itemsArrayList.addAll(items);
      Log.d("exe", "itemsArrayList" + itemsArrayList);
      llItemContainer.removeAllViews();

      float subTotal = 0.0f;
      addOnCount = 0.0f;

      LayoutInflater layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
      for (int index = 0; index < items.size(); index++) {
        View view = layoutInflater.inflate(R.layout.single_row_items, null);
        TextView tvItemName = view.findViewById(R.id.tvItemName);
        final EditText etQuantity = view.findViewById(R.id.etQuantity);
        final EditText etUnitPrice = view.findViewById(R.id.etUnitPrice);
        final ImageView ivAddPhotos = view.findViewById(R.id.ivAddPhotos);
        TextView tvPrice = view.findViewById(R.id.tvOrderListTotalPrice);
        String itemName = items.get(index).getItemName();
        tvItemName.setText(itemName);


        ArrayList<AddOnGroup> addOnGroupArrayList = new ArrayList<>();

        RecyclerView rvAddOnsList = view.findViewById(R.id.rvAddOnsList);

        rvAddOnsList.setLayoutManager(new LinearLayoutManager(this));
        rvAddOnsList.setHasFixedSize(true);
                /*AddOnsAdapter addOnsAdapter = new AddOnsAdapter(addOnGroupArrayList, 
                currencySymbol);
                rvAddOnsList.setAdapter(addOnsAdapter);*/

        addOnGroupArrayList.clear();


        if (items.get(index).getAddOns() != null && items.get(index).getAddOns().size() > 0) {
          for (int i = 0; i < items.get(index).getAddOns().size(); i++) {

            AddOns addOn = items.get(index).getAddOns().get(i);

            for (int j = 0; j < addOn.getAddOnGroup().size(); j++) {

              addOnCount += (!addOn.getAddOnGroup().get(j).getPrice().equals("")) ?
                  Double.parseDouble(addOn.getAddOnGroup().get(j).getPrice()) : 0;

              addOnGroupArrayList.add(addOn.getAddOnGroup().get(j));

            }
          }

        }

        if (addOnGroupArrayList.size() > 0) {
          rvAddOnsList.setVisibility(View.VISIBLE);
          //          addOnsAdapter.notifyDataSetChanged();
        }

        int quantity;
        float unitPrice = 0;
        quantity = Integer.parseInt(items.get(index).getQuantity() + "");
        if (items.get(index).getFinalPrice() != null) {
          unitPrice = Float.parseFloat(items.get(index).getFinalPrice());
        }


        etQuantity.setEnabled(false);
        etUnitPrice.setEnabled(false);

        // if (!enabled) {
        etQuantity.setBackgroundColor(getResources().getColor(R.color.transperent));
        etUnitPrice.setBackgroundColor(getResources().getColor(R.color.transperent));
        //   }
        //   if (enabled) {
        SpannableString spannableString = new SpannableString(itemName);
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        tvItemName.setText(spannableString);
        //  }
        etQuantity.setText("" + quantity);
        etUnitPrice.setText(Utility.roundOfDoubleValue("" + unitPrice));
        tvPrice.setText(currency + " " + Utility.roundOfDoubleValue(String.valueOf(quantity * unitPrice)));

        subTotal += quantity * unitPrice;

        final int finalIndex = index;


            /*
                etQuantity.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (i == KeyEvent.KEYCODE_ENTER) {
                            int quantity = Integer.parseInt(etQuantity.getText().toString());
                            if (quantity != 0)
                                presenter.updateItem(Integer.parseInt(etQuantity.getText()
                                .toString()), Float.parseFloat(etUnitPrice.getText().toString()),
                                 items.get(finalIndex).getUnitId());
                            else {
                                Animation shake = AnimationUtils.loadAnimation
                                (DialogOrderDetailsActivity.this, R.anim.shake);
                                etQuantity.startAnimation(shake);
                            }
                        }
                        return false;
                    }
                });
                etUnitPrice.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (i == KeyEvent.KEYCODE_ENTER) {
                            float unitPrice = Float.parseFloat(etUnitPrice.getText().toString());
                            if (unitPrice != 0.0)
                                presenter.updateItem(Integer.parseInt(etQuantity.getText()
                                .toString()), unitPrice, items.get(finalIndex).getUnitId());
                            else {
                                Animation shake = AnimationUtils.loadAnimation
                                (DialogOrderDetailsActivity.this, R.anim.shake);
                                etUnitPrice.startAnimation(shake);
                            }
                        }
                        return false;
                    }
                });
                */

        tvItemName.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (orderStatus.equals("12") || orderStatus.equals("13") || orderStatus.equals("14")) {
              editWarning();
            } else {
              presenter.editOrder(items.get(finalIndex));
            }
          }
        });

        if (preferenceHelperDataSource.getStoreType() == 5) {
          ivAddPhotos.setVisibility(View.VISIBLE);


          ivAddPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Log.d("exe", "index" + finalIndex);
              String productId = items.get(finalIndex).getProductId();
              ArrayList<String> images = items.get(finalIndex).getImages();
              openLaundraImages(getIntent().getExtras().getString("orderId"), productId, images);

            }
          });
        }

        llItemContainer.addView(view);

      }

      presenter.getFares(subTotal + addOnCount);

    } else {
      llItemContainer.removeAllViews();
    }

  }

  private void editWarning() {
    Toast.makeText(this, getResources().getString(R.string.editOrderWarning), Toast.LENGTH_LONG).show();
  }


  private void disable(String quantity) {

    //   Log.d("exe", "isEditable" + isEditable);


    if (!quantity.isEmpty() && !quantity.equals("0")) {

      for (int i = 0; i < llItemContainer.getChildCount(); i++) {

        LinearLayout ll = (LinearLayout)llItemContainer.getChildAt(i);
        LinearLayout llMain = (LinearLayout)ll.getChildAt(0);
        LinearLayout llMain1 = (LinearLayout)llMain.getChildAt(2);

        EditText qntEt = (EditText)llMain1.getChildAt(0);
        EditText priceEt = (EditText)llMain1.getChildAt(3);

        qntEt.setEnabled(false);

        priceEt.setEnabled(false);

        qntEt.setBackgroundColor(getResources().getColor(R.color.transperent));
        priceEt.setBackgroundColor(getResources().getColor(R.color.transperent));


        itemsArrayList.get(i).setFinalPrice((priceEt.getText().toString()));

        itemsArrayList.get(i).setQuantity(Integer.parseInt(quantity));

      }

      presenter.saveItems(itemsArrayList);

    } else {

      Toast.makeText(this, getResources().getString(R.string.quantityError), Toast.LENGTH_LONG).show();

    }

  }


  public void openLaundraImages(String orderId, String productId, ArrayList<String> images) {

    Intent intent = new Intent(this, LaundryItemPhotosActivity.class);
    intent.putExtra("orderId", orderId);
    intent.putExtra("productId", productId);
    intent.putStringArrayListExtra("images", images);
    startActivity(intent);

  }

  @OnClick({R.id.tvAccept, R.id.tvReject, R.id.ivCloseButton, R.id.tvAutomatic, R.id.tvCancel,
      R.id.tvCancel2, R.id.tvCancel3, R.id.tvDelay, R.id.tvReady, R.id.tvPicked, R.id.
      tvManual, R.id.tvManualAssign, R.id.ivCall, R.id.ivDriverCall, R.id.rl_profilePic,
      R.id.tv_backBtn, R.id.tvEdit, R.id.constraintLayoutOrderDetailsMainContainer,
      R.id.rlDriverDetails, R.id.tvOrderStatusName})
  public void OnClick(View view) {
    switch (view.getId()) {
      case R.id.constraintLayoutOrderDetailsMainContainer:
        Utility.hideSoftKeyboard(this);
        break;

      case R.id.tvAccept:
        presenter.updateOrder(4);       //4-Accept
        break;

      case R.id.tvReject:
        presenter.updateOrder(3);       //3--Reject
        break;

      case R.id.ivCloseButton:
        onBackPressed();
        //  finish();
        break;

      case R.id.tvAutomatic:

        if (preferenceHelperDataSource.getStoreType() == 5) {
          presenter.updateOrder(25);
        } else {
          presenter.dispatch();
        }
        break;
      case R.id.tvManualAssign:
        presenter.manualDispatch();
        break;

      case R.id.tvCancel:
      case R.id.tvCancel2:
      case R.id.tvCancel3:
        presenter.cancelOrDelayOrder(false);
        break;

      case R.id.tvDelay:
        presenter.cancelOrDelayOrder(true);
        break;

      case R.id.tvReady:
        presenter.updateOrder(5);
        break;

      case R.id.tvPicked:
        if (tvPicked.getText().toString().equals(getResources().getString(R.string.picked))) {
          presenter.updateOrder(6);
        } else {
          presenter.updateOrder(7);
        }
        break;

      case R.id.tvManual:
        presenter.manualDispatch();
        break;

      case R.id.ivCall:
        presenter.callCustomer();
        break;
      case R.id.ivDriverCall:
        presenter.callDriver();
        break;

      case R.id.rl_profilePic:
        //   presenter.getPastOrders();
        presenter.getDriverOngoingOrders();
        break;

      case R.id.tv_backBtn:
        setCurrentOrder();
        break;

      case R.id.tvEdit:

        if (tvEdit.getText().equals(getResources().getString(R.string.edit))) {
          presenter.editItems();
        }

             /*       tvEdit.setText(getResources().getString(R.string.save));
                } else {
                    //  presenter.saveItems();
                    disable(false);
                    tvEdit.setText(getResources().getString(R.string.edit));
                }*/

        break;
         /*   case R.id.rlDriverDetails:
                presenter.getDriverOngoingOrders();
                break;*/
      case R.id.tvOrderStatusName:
        if (preferenceHelperDataSource.getStoreType() == 5) {
          if (tvStatus.getText().toString() != null && tvStatus.getText().toString().equals(getResources().getString(R.string.inDispatch))) {
            presenter.dispatch();
          }
        }

        break;


    }
  }

  @Override
  public void showMessage(String statusMsg) {
    Toast.makeText(this, statusMsg, Toast.LENGTH_LONG).show();
  }

  public void setBottomButtons(OrderedItemDetails orderedItemDetails) {

    llAcceptRejectButtons.setVisibility(View.GONE);
    ll_manual_assign.setVisibility(View.GONE);

    llDispatchButtons.setVisibility(View.GONE);
    llDispatchPickUpOrderButtons.setVisibility(View.GONE);
    //tvEdit.setVisibility(View.GONE);
    tvCancel3.setVisibility(View.GONE);
    tvStatus.setVisibility(View.GONE);
    tvPicked.setVisibility(View.GONE);
    // rlDriverDetails.setVisibility(View.GONE);
    //llOrdersAndRevenue.setVisibility(View.GONE);
    viewRevenueLine.setVisibility(View.GONE);
    //   viewDriverDetails.setVisibility(View.GONE);

    //   Log.d("exe", "orderedItemDetails.getStatus()" + orderedItemDetails.getStatus());

    switch (orderedItemDetails.getStatus()) {

      case "1":

        llAcceptRejectButtons.setVisibility(View.VISIBLE);
        //  llOrdersAndRevenue.setVisibility(View.VISIBLE);
        viewRevenueLine.setVisibility(View.VISIBLE);

        break;

      case "4":

        if (preferenceHelperDataSource.getStoreType() == 5) {
          llDispatchButtons.setVisibility(View.VISIBLE);
          tvManual.setVisibility(View.GONE);
          tvAutomatic.setVisibility(View.VISIBLE);
          tvAutomatic.setText(getResources().getString(R.string.inProgress));
        } else
                   /* if (orderedItemDetails.isInDispatch()) {
                        tvStatus.setVisibility(View.VISIBLE);
                        tvStatus.setText(orderedItemDetails.getStatusMsg());
                    } else */ {
          if (orderedItemDetails.getServiceType().equals("1")) {
            // llDispatchButtons.setVisibility(View.VISIBLE);
            if (orderedItemDetails.getDriverType().equals("1")) {
              // tvManual.setVisibility(View.GONE);

              //    llDispatchButtons.setVisibility(View.GONE);

              ll_manual_assign.setVisibility(View.VISIBLE);

              llDispatchButtons.setVisibility(View.GONE);

              if (preferenceHelperDataSource.getStoreType() == 0) {
                rl_myseek.setVisibility(View.VISIBLE);
                tv_status_text.setText(getResources().getString(R.string.dispatch));
              } else {
                rl_myseek.setVisibility(View.GONE);
                rlDriverDetails.setVisibility(View.VISIBLE);
                tv_status_text.setText(getResources().getString(R.string.waitingForDriver));
                tvDriverName.setText(getResources().getString(R.string.driverNotAssigned));
                ivDriverProfile.setVisibility(View.VISIBLE);

                //   holder.btn_statusMsg.setTextColor(Color.RED);

                Glide.with(this).
                    load(R.drawable.live_status)
                    .into(ivDriverProfile);
                ivDriverCall.setVisibility(View.GONE);

              }

              //rl_myseek.setVisibility(View.VISIBLE);
              //  tv_status_text.setText(getResources().getString(R.string.dispatch));


            } else {
              //  tvManual.setVisibility(View.VISIBLE);
              ll_manual_assign.setVisibility(View.VISIBLE);

              llDispatchButtons.setVisibility(View.GONE);

              tvManualAssign.setVisibility(View.VISIBLE);

              if (preferenceHelperDataSource.getStoreType() == 0) {
                rl_myseek.setVisibility(View.VISIBLE);
                tv_status_text.setText(getResources().getString(R.string.dispatch));
              } else {
                rl_myseek.setVisibility(View.GONE);
                rlDriverDetails.setVisibility(View.VISIBLE);
                tv_status_text.setText(getResources().getString(R.string.waitingForDriver));
                tvDriverName.setText(getResources().getString(R.string.driverNotAssigned));
                ivDriverProfile.setVisibility(View.VISIBLE);

                //   holder.btn_statusMsg.setTextColor(Color.RED);
                Glide.with(this).
                    load(R.drawable.live_status)
                    .into(ivDriverProfile);
                ivDriverCall.setVisibility(View.GONE);
              }
            }
          } else {
            // llDispatchPickUpOrderButtons.setVisibility(View.VISIBLE);

            ll_manual_assign.setVisibility(View.VISIBLE);
            rl_myseek.setVisibility(View.VISIBLE);

            tv_status_text.setText(getResources().getString(R.string.pickUp));

          }

          //     tvEdit.setVisibility(View.VISIBLE);

        }

        break;

      case "5":
        ll_manual_assign.setVisibility(View.VISIBLE);

        rl_myseek.setVisibility(View.VISIBLE);

        tv_status_text.setText(getResources().getString(R.string.picked));

        break;

      case "6":
        tvPicked.setVisibility(View.VISIBLE);
        tvPicked.setText(getResources().getString(R.string.completed));
        break;

      case "8":
      case "9":
      case "10":
      case "11":
      case "18":
        llBottomButtons.setVisibility(View.GONE);
        //    tvCancel3.setVisibility(View.VISIBLE);

        break;

      case "12":
      case "13":
      case "14":
      case "15":
        //  rlDriverDetails.setVisibility(View.VISIBLE);
        //   viewDriverDetails.setVisibility(View.VISIBLE);
        tvStatus.setVisibility(View.VISIBLE);
        tvStatus.setText(orderedItemDetails.getStatusMsg());
        break;

      case "25":
        ll_manual_assign.setVisibility(View.VISIBLE);

        rl_myseek.setVisibility(View.VISIBLE);
        tv_status_text.setText(getResources().getString(R.string.inDispatch));
        break;
      case "40":
        //llBtnMain.setVisibility(View.GONE);
        //   rlDriverDetails.setVisibility(View.VISIBLE);

              /*  if (orderedItemDetails.isInDispatch())
                    llBottomButtons.setVisibility(View.GONE);
                else*/
        if (orderedItemDetails.getDriverType().equals("1")) {
          ll_manual_assign.setVisibility(View.VISIBLE);

          llDispatchButtons.setVisibility(View.GONE);

          if (preferenceHelperDataSource.getStoreType() == 0) {
            rl_myseek.setVisibility(View.VISIBLE);
            tv_status_text.setText(getResources().getString(R.string.dispatch));
          } else {
            rl_myseek.setVisibility(View.GONE);
            rlDriverDetails.setVisibility(View.VISIBLE);
            tv_status_text.setText(getResources().getString(R.string.waitingForDriver));
            tvDriverName.setText(getResources().getString(R.string.driverNotAssigned));
            ivDriverProfile.setVisibility(View.VISIBLE);

            //   holder.btn_statusMsg.setTextColor(Color.RED);
            Glide.with(this).
                load(R.drawable.live_status)
                .into(ivDriverProfile);
            ivDriverCall.setVisibility(View.GONE);


          }

        } else {
          if (!orderedItemDetails.isInDispatch()) {
            ll_manual_assign.setVisibility(View.VISIBLE);
            llDispatchButtons.setVisibility(View.GONE);
            tvManualAssign.setVisibility(View.VISIBLE);
          } else {
            rl_myseek.setVisibility(View.GONE);
          }
          rlDriverDetails.setVisibility(View.VISIBLE);
          tv_status_text.setText(getResources().getString(R.string.waitingForDriver));
          tvDriverName.setText(getResources().getString(R.string.driverNotAssigned));
          ivDriverProfile.setVisibility(View.VISIBLE);

          //   holder.btn_statusMsg.setTextColor(Color.RED);
          Glide.with(this).
              load(R.drawable.live_status)
              .into(ivDriverProfile);
          ivDriverCall.setVisibility(View.GONE);


        }
        break;

      case "21":

        rl_myseek.setVisibility(View.GONE);
        rlDriverDetails.setVisibility(View.VISIBLE);
        tv_status_text.setText(getResources().getString(R.string.waitingForDriver));
        tvDriverName.setText(getResources().getString(R.string.driverNotAssigned));
        ivDriverProfile.setVisibility(View.VISIBLE);

        //   holder.btn_statusMsg.setTextColor(Color.RED);
        Glide.with(this).
            load(R.drawable.live_status)
            .into(ivDriverProfile);
        ivDriverCall.setVisibility(View.GONE);


        break;


    }
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  @Override
  public void showCancelOrDelayDailog(boolean delay) {

    cancelDialog = new CancellationDialog(this, presenter);
    presenter.getCancellationReason();
    cancelDialog.createDialog(delay, presenter.getDueTime(), presenter.getTimestamp());

  }

  @Override
  public void setReasons(ArrayList<Reasons> reasons) {
    if (cancelDialog != null) {
      cancelDialog.setReasons(reasons);
    }
  }

  @Override
  public void moveToListAct(String orderId) {
    Bundle bundle = new Bundle();
    bundle.putString("data", orderId);
    bundle.putString("storeId", preferenceHelperDataSource.getStoreId());
    Intent intent = new Intent(this, SelectDriversActivity.class);
    intent.putExtras(bundle);
    startActivityForResult(intent, 12345);
  }

  @Override
  public void showOrders(PastOrdersData pastOrdersData) {
    if (pastOrdersData.getPastOrders() != null && pastOrdersData.getPastOrders().size() > 0) {
      llCostView.setVisibility(View.VISIBLE);
      llEmptyView.setVisibility(View.INVISIBLE);
      tvCurrentOrder.setTextColor(getResources().getColor(R.color.darkGray));
      viewCurrentOrderSelector.setBackgroundColor(getResources().getColor(R.color.lobolly));
      tvPastOrder.setTextColor(getResources().getColor(R.color.colorPrimary));
      viewPastOrderSelector.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

      rvPastOrders.setVisibility(View.VISIBLE);
      ll_pastOrder.setVisibility(View.VISIBLE);
      rl_orderDet.setVisibility(View.GONE);
      cv_currentOrder.setVisibility(View.GONE);

      pastOrderAdapter = new PastOrderInOrderDetAdapter(this, pastOrdersData.getPastOrders());
      rvPastOrders.setAdapter(pastOrderAdapter);
      pastOrderAdapter.notifyDataSetChanged();
    } else {
      tvCurrentOrder.setTextColor(getResources().getColor(R.color.darkGray));
      viewCurrentOrderSelector.setBackgroundColor(getResources().getColor(R.color.lobolly));
      tvPastOrder.setTextColor(getResources().getColor(R.color.colorPrimary));
      viewPastOrderSelector.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
      tvTotal.setText("");
      rvPastOrders.setVisibility(View.VISIBLE);
      ll_pastOrder.setVisibility(View.VISIBLE);
      rl_orderDet.setVisibility(View.GONE);
      cv_currentOrder.setVisibility(View.GONE);
      llCostView.setVisibility(View.INVISIBLE);
      llEmptyView.setVisibility(View.VISIBLE);
      addItems(itemsArrayList, "", false);
    }
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    if (tvEdit.getText().equals(getResources().getString(R.string.edit))) {
      presenter.getOrderDetails();
    }
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void setFares(String subTotal, String discount, String delCharge, String serviceCharge,
                       String total, ArrayList<ExclusiveTaxes> exclusiveTaxes) {

    float mSubTotal = Float.parseFloat(subTotal);
    float mDiscount = Float.parseFloat(discount);
    float mDelCharge = Float.parseFloat(delCharge);
    float mServiceCharge = Float.parseFloat(serviceCharge);
    float mTotal = mSubTotal - mDiscount + mServiceCharge;
    double totalTaxes = 0.00;
    //   tvSubTotal.setText(currencySymbol + " " + Utility.formatPrice(subTotal));
    tvDiscount.setText(currencySymbol + " " + Utility.formatPrice(discount));
    tvDeliveryCharge.setText(currencySymbol + " " + Utility.formatPrice(delCharge));

    //tvServiceCharge.setText(currencySymbol + " " + Utility.formatPrice(serviceCharge));

    if (storeType.equals("5")) {
      tvSubTotal.setText(currencySymbol + " " + Utility.roundOfDoubleValue(subTotal));
      tvDiscount.setText(currencySymbol + " " + Utility.roundOfDoubleValue(discount));

    } else {
      tvSubTotal.setText(currencySymbol + " " + Utility.roundOfDoubleValue(subTotal));
      tvDiscount.setText(currencySymbol + " " + Utility.roundOfDoubleValue(discount));
    }
    if (exclusiveTaxes.size() < 1 || preferenceHelperDataSource.getStoreType() == 5) {
      taxDetailsRl.setVisibility(View.GONE);
      taxesTv.setVisibility(View.GONE);
    } else {
      taxDetailsRl.setVisibility(View.VISIBLE);
      taxesTv.setVisibility(View.VISIBLE);
      View view;
      TextView taxType1Tv;
      TextView taxType1AmountTv;
      LayoutInflater inflater = LayoutInflater.from(this);
      taxDetailsLl.removeAllViews();
      for (int j = 0; j < exclusiveTaxes.size(); j++) {

        view = inflater.inflate(R.layout.order_tax_details_item, taxDetailsLl, false);
        taxType1Tv = view.findViewById(R.id.taxType1Tv);
        taxType1Tv.setTextColor(getResources().getColor(R.color.shark));
        taxType1AmountTv = view.findViewById(R.id.taxType1AmountTv);
        taxType1AmountTv.setTextColor(getResources().getColor(R.color.shark));
        String taxType =
            exclusiveTaxes.get(j).getTaxCode() + "(" + Utility.roundOfDoubleValue(exclusiveTaxes.get(j).getTaxValue() + "") + "%" + ")";
        taxType1Tv.setText(taxType);
        taxType1AmountTv.setText(currencySymbol + " " + Utility.roundOfDoubleValue(exclusiveTaxes.get(j).getPrice() + ""));
        taxDetailsLl.addView(view);
        if (exclusiveTaxes.get(j).getPrice() != null) {
          mTotal += Float.parseFloat(exclusiveTaxes.get(j).getPrice());
          totalTaxes += Float.parseFloat(exclusiveTaxes.get(j).getPrice());
        }
      }
      tvTaxTotal.setText(currencySymbol + " " + Utility.roundOfDoubleValue(totalTaxes + ""));
    }

    tvTotal.setText(currencySymbol + " " + Utility.roundOfDoubleValue(mTotal + ""));

  }

  @Override
  public void openOrderEditDialog(Items items) {

    dialog = new Dialog(this);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.item_order_edit_dailog);
    dialog.setCancelable(false);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    TextView tvItem_Name = (TextView)dialog.findViewById(R.id.tvItemName);
    TextView tvItemFrom = (TextView)dialog.findViewById(R.id.tvItemFrom);
    TextView tvQty = (TextView)dialog.findViewById(R.id.tvFirstAttribute);
    TextView tvItem_Price = (TextView)dialog.findViewById(R.id.tvItemPrice);
    TextView tvdelete = (TextView)dialog.findViewById(R.id.tvDelete);
    TextView tvUpdate = (TextView)dialog.findViewById(R.id.tvUpdate);
    final EditText etQuantity = (EditText)dialog.findViewById(R.id.etQuantity);
    ImageView ivCancel = (ImageView)dialog.findViewById(R.id.ivCancel);

    tvItem_Name.setText(items.getItemName());
    etQuantity.setText(items.getQuantity() + "");
    etQuantity.setSelection(etQuantity.getText().length());

    // tvItemFrom.setText(getResources().getString(R.string.from)+" "+items.ge());
    tvItem_Price.setText(" X " + currencySymbol + " " + Utility.roundOfDoubleValue(items.getFinalPrice()));

    ivCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.dismiss();
      }

    });
    tvUpdate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //presenter.updateOrder(items,etQuantity.getText().toString());

        disable(etQuantity.getText().toString());

        dialog.dismiss();
      }
    });
    tvdelete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //  presenter.deleteItem(shipmentDetails);
        dialog.dismiss();
      }
    });


    dialog.show();


  }

  private void setCurrentOrder() {
    tvPastOrder.setTextColor(getResources().getColor(R.color.darkGray));
    viewPastOrderSelector.setBackgroundColor(getResources().getColor(R.color.lobolly));
    tvCurrentOrder.setTextColor(getResources().getColor(R.color.colorPrimary));
    viewCurrentOrderSelector.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    rvPastOrders.setVisibility(View.GONE);
    ll_pastOrder.setVisibility(View.GONE);
    cv_currentOrder.setVisibility(View.VISIBLE);
    rl_orderDet.setVisibility(View.VISIBLE);

    presenter.getCurrentOrderDetails();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (data != null) {

      if (requestCode == 12345) {

        if (resultCode == Activity.RESULT_OK) {

          boolean isToFinish = data.getBooleanExtra("isToFinish", false);

          if (isToFinish) {
            finish();
          }
        }
      }
    }
  }


  /**
   * <h>count down timer</h>
   * <p>This method is used to set the count down and count up timer for the later booking
   * orders</p>
   *
   * @param orderDetails this represents the orderdetails object for the particular order id.
   * @param milliseconds this represents the time in milliseconds for the count down timer for
   *                     the increasing timing no need to show.
   */


  @RequiresApi(api = Build.VERSION_CODES.O)
  public void setCountDownTimer(OrderedItemDetails orderDetails, long milliseconds) {
    //  Log.d("exe", "setCountDownTimer");


    Date currentDate = Calendar.getInstance().getTime();

    long currentTimeInMillis = currentDate.getTime();
    long currentTimeStamp;

    final long[] sec = {0};
    final long[] min = {0};
    final long[] hr = {0};

    if (!orderDetails.isIncreaseTimer()) {
      currentTimeStamp = milliseconds - currentTimeInMillis;
    } else {

      long seconds =
          (currentTimeInMillis - Utility.getTimeInMilliSec(orderDetails.getDueDatetimeTimeStamp())) / 1000;
      long minutes = seconds / 60;
      long hours = minutes / 60;

      long days = hours / 24;

      min[0] = minutes % 60;
      hr[0] = hours % 24;
      sec[0] = seconds % 60;

      currentTimeStamp = 24 * 60 * 60 * 1000;
    }


    if (countDownTimer != null) {
      countDownTimer.cancel();
    }

    countDownTimer = new CountDownTimer(currentTimeStamp, 1000) {

      public void onTick(long millisUntilFinished) {

        String finalCountDownTime = "";

        if (!orderDetails.isIncreaseTimer()) {
          long secondsInMilli = 1000;
          long minutesInMilli = secondsInMilli * 60;
          long hoursInMilli = minutesInMilli * 60;
          long daysInMilli = hoursInMilli * 24;

          long elapsedDays = millisUntilFinished / daysInMilli;
          millisUntilFinished = millisUntilFinished % daysInMilli;

          long elapsedHours = millisUntilFinished / hoursInMilli;
          millisUntilFinished = millisUntilFinished % hoursInMilli;

          long elapsedMinutes = millisUntilFinished / minutesInMilli;
          millisUntilFinished = millisUntilFinished % minutesInMilli;


          String day;
          if (elapsedDays != 0) {
                        /*if (elapsedDays == 1)
                            day = "day";

                        else
                            day = "days";*/
            finalCountDownTime = String.format("%02d%s:%02d%s:%02d%s", elapsedDays, "d",
                elapsedHours, "h", elapsedMinutes, "m");
          } else {
            finalCountDownTime = String.format("%02d%s:%02d%s", elapsedHours, "h",
                elapsedMinutes
                , "m");
          }

          tvDueAt.setTextColor(DialogOrderDetailsActivity.this.getResources().getColor(R.color.xanadu));


        } else {

          sec[0]++;
          if (sec[0] == 59) {
            min[0]++;
            sec[0] = 0;
          }

          if (min[0] == 59) {
            min[0] = 0;
            hr[0]++;
          }

          if (hr[0] == 23) {
            hr[0] = 00;
          }

          finalCountDownTime = String.format("%02d%s:%02d%s", hr[0], "h", min[0], "m");

          tvDueAt.setTextColor(DialogOrderDetailsActivity.this.getResources().getColor(R.color.red));

        }
        tvDueAt.setText(finalCountDownTime);

      }

      @SuppressLint("SetTextI18n")
      public void onFinish() {

        orderDetails.setIncreaseTimer(true);
        cancelCoutdownTimer();
        setCountDownTimer(orderDetails, 00000);

      }
    }.start();
  }


  /**
   * <h1>cancelCoutdownTimer</h1>
   * <p>this method is used to close the countdown timer.</p>
   */

  public void cancelCoutdownTimer() {

    if (countDownTimer != null) {
      countDownTimer.cancel();
      countDownTimer = null;
    }

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    cancelCoutdownTimer();
  }
}
