package com.app.delivxstore.main.add_customer_items;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.data.source.local.PreferencesHelper;
import com.app.delivxstore.main.cart.CartActivity;
import com.app.delivxstore.utility.Utility;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class AddLaundryItemActivity extends DaggerAppCompatActivity implements AddLaundryItemView {

  @BindView(R.id.checkOutBtn)
  Button checkOutBtn;

  @BindView(R.id.checkOutRl)
  RelativeLayout checkOutRl;

  @BindView(R.id.rvAddLaundryItem)
  RecyclerView rvAddLaundryItem;
  @BindView(R.id.tvCurrency)
  TextView tvCurrency;
  @BindView(R.id.ll_add_item)
  LinearLayout ll_add_item;
  @BindView(R.id.et_item_name)
  EditText et_item_name;
  @BindView(R.id.et_enter_quantity)
  EditText et_enter_quantity;
  @BindView(R.id.instructionEt)
  EditText instructionEt;
  @BindView(R.id.estimatedValEt)
  EditText estimatedValEt;

  @BindView(R.id.iv_add)
  ImageView iv_add;
  @BindView(R.id.ivBack)
  ImageView ivBack;

  @BindView(R.id.tv_add_items)
  TextView tv_add_items;

  @BindView(R.id.proBar)
  ProgressBar proBar;

  @BindView(R.id.dummyView)
  View dummyView;

  @Inject
  AddLaundryPresenter presenter;

  @Inject
  PreferencesHelper manager;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;

  private Dialog dialog;
  private AddLaundryItemAdapter addLaundryItemAdapter;
  private String customerId = "", cartId, dueDatetime = "", addressId, address1, address2;
  private int bookingType, serviceType;
  private double latitude, longitude, pickUpLat, pickUpLong;

  private ArrayList<AddLaundryProducts> addLaundryProducts = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_laundry_item);
    ButterKnife.bind(this);
    getIntentData();
    mInitialization();
  }

  private void getIntentData() {

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      customerId = bundle.getString("customerId");
      bookingType = bundle.getInt("bookingType", 0);
      serviceType = bundle.getInt("serviceType", 0);
      latitude = bundle.getDouble("latitude", 0);
      longitude = bundle.getDouble("longitude", 0);
      pickUpLat = bundle.getDouble("pickUpLat", 0);
      pickUpLong = bundle.getDouble("pickUpLong", 0);
      dueDatetime = bundle.getString("dueDatetime");

      addressId = bundle.getString("addressId");
      address1 = bundle.getString("address1");
      address2 = bundle.getString("address2");
    }
  }

  private void mInitialization() {
    // Utility.hideSoftKeyboard(instructionEt);
    // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    dialog = new Dialog(this);
    et_item_name.setText("");
    tvCurrency.setText(preferenceHelperDataSource.getCurrency());
    rvAddLaundryItem.setNestedScrollingEnabled(false);

    addLaundryItemAdapter = new AddLaundryItemAdapter(addLaundryProducts, this);
    rvAddLaundryItem.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager =
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    rvAddLaundryItem.setLayoutManager(layoutManager);
    rvAddLaundryItem.setItemAnimator(new DefaultItemAnimator());
    rvAddLaundryItem.setAdapter(addLaundryItemAdapter);
    rvAddLaundryItem.setNestedScrollingEnabled(false);

    presenter.getCart(customerId);
  }

  @SuppressLint("ShowToast")
  @OnClick({ R.id.checkOutBtn, R.id.iv_add, R.id.tv_add_items, R.id.ivBack })
  public void onClick(View view) {
    String quantity=et_enter_quantity.getText().toString();
    switch (view.getId()) {
      case R.id.checkOutBtn:
//        String val = estimatedValEt.getText().toString();
        if (addLaundryProducts != null && addLaundryProducts.size() >= 1/* && !TextUtils.isEmpty(val)
            && TextUtils.isDigitsOnly(val) && Double.parseDouble(val) > 0*/)
        {
          presenter.checkOut(customerId, 1, bookingType, latitude, longitude, pickUpLat,
              pickUpLong);
        }
       /* else if (TextUtils.isEmpty(estimatedValEt.getText().toString())) {
          Toast.makeText(this, getString(R.string.est_mand_err), Toast.LENGTH_SHORT);
        } */
        else {
          onError(getResources().getString(R.string.addItemsmanditory));
        }
        break;
      case R.id.tv_add_items:

        if (et_item_name.getText().toString().isEmpty()) {
          onError(getResources().getString(R.string.addItemName));
        } else if (TextUtils.isEmpty(quantity) || !TextUtils.isDigitsOnly(quantity) || Integer.parseInt(quantity)<=0) {
          onError(getResources().getString(R.string.addItemQuan));
        } else {
          presenter.submitLaundryItem(customerId, et_item_name.getText().toString(),
              Double.parseDouble(et_enter_quantity.getText().toString()), 2);
        }
        break;

      case R.id.iv_add:
        if (et_item_name.getText().toString().isEmpty()) {
          onError(getResources().getString(R.string.addItemName));
        } else if (TextUtils.isEmpty(quantity) || !TextUtils.isDigitsOnly(quantity) || Integer.parseInt(quantity)<=0) {
          onError(getResources().getString(R.string.addItemQuan));
        } else {
          presenter.submitLaundryItem(customerId, et_item_name.getText().toString(),
              Double.parseDouble(et_enter_quantity.getText().toString()), 2);
        }
        break;
      case R.id.ivBack:
        presenter.stop();
        break;
      default:
        break;
    }
  }

  @Override
  public void stopAct() {
    onBackPressed();
  }

  @Override
  public void setLaundryItems(ArrayList<AddLaundryCart> addLaundryData, String cartId) {

    this.cartId = cartId;
    if (addLaundryData.size() > 0) {
      addLaundryProducts.clear();
      for (int i = 0; i < addLaundryData.size(); i++) {

        AddLaundryCart addLaundryCart = addLaundryData.get(i);

        addLaundryProducts.addAll(addLaundryCart.getProducts());
      }

      addLaundryItemAdapter.notifyDataSetChanged();
    }
  }

  @Override
  public void getFare(int discount, int tax, int storeDeliveryFee, String currencySymbol) {

    Intent intent = new Intent(this, CartActivity.class);
    intent.putExtra("storeDeliveryFee", storeDeliveryFee);
    intent.putExtra("customerId", customerId);
    intent.putExtra("tax", tax);
    intent.putExtra("discount", discount);
    intent.putExtra("currencySymbol", currencySymbol);
    intent.putExtra("cartId", cartId);
    intent.putExtra("serviceType", serviceType);
    intent.putExtra("bookingType", bookingType);
    intent.putExtra("latitude", latitude);
    intent.putExtra("longitude", longitude);
    intent.putExtra("pickUpLat", pickUpLat);
    intent.putExtra("pickUpLong", pickUpLong);
    intent.putExtra("extraNote", instructionEt.getText().toString());
    intent.putExtra("estimatedPackageValue", estimatedValEt.getText().toString());
    intent.putExtra("dueDatetime", dueDatetime);

    intent.putExtra("addressId", addressId);
    intent.putExtra("address1", address1);
    intent.putExtra("address2", address2);

    startActivity(intent);
  }

  @Override
  public void onError(String msg) {

    Utility.showAlert(msg, this);
  }

  @Override
  public void setItemNameAndQuantity(String itemName, String productId) {

      /*  if (itemName != null && productId != null) {

            et_item_name.clearFocus();
            et_item_name.setText(itemName);
            // et_item_name.clearFocus();
            this.productId = productId;

        }

        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }*/

  }

  @Override
  public void onSuccessItemSubmit(String cartId, String msg) {

    Utility.hideSoftKeyboard(this);

    this.cartId = cartId;

    AddLaundryProducts addLaundryPro = new AddLaundryProducts();
    addLaundryPro.setItemName(et_item_name.getText().toString());
    addLaundryPro.setQuantity(et_enter_quantity.getText().toString());
    addLaundryProducts.add(addLaundryPro);
    addLaundryItemAdapter.notifyDataSetChanged();
    if (ll_add_item.getVisibility() == View.GONE) ll_add_item.setVisibility(View.VISIBLE);

    et_item_name.setText("");
    et_enter_quantity.setText("");
    et_enter_quantity.clearFocus();
    et_item_name.setFocusable(true);
    et_item_name.setCursorVisible(true);
    presenter.getCart(customerId);
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
  }

  @Override
  public void removeLaundryItem(int position) {
       Log.d("exe","position"+position);
    AddLaundryProducts getCartProducts = addLaundryProducts.get(position);
    presenter.removeLaundryItem(customerId, cartId, getCartProducts.getChildProductId(),
        getCartProducts.getUnitId(), getCartProducts.getAddedToCartOn(), 2, position);
  }

  @Override
  public void updateLaundryItem(int position, String childProductId, String unitID,
      String quantity) {
    if (quantity == null || quantity.isEmpty() || quantity.equals("0")) {
      onError(getResources().getString(R.string.quantityError));
    } else {
      presenter.upDateCart(position, customerId, cartId, childProductId, unitID,
          Integer.parseInt(quantity), 7);
    }
  }

  @Override
  public void onSuccessRemoveLaundryItem(int position, String msg) {
    addLaundryProducts.remove(position);
    addLaundryItemAdapter.notifyDataSetChanged();
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
  }

  @Override
  public void onSuccessUpdateLaundryItem(int position, String quantity) {

      /*  GetCartProducts getCartProducts = getCartArrayList.get(position);
        getCartProducts.setQuantity(quantity);
        addLaundryItemAdapter.notifyDataSetChanged();*/

  }

  @Override
  public void updateOrder(int pos, String msg, int quantity) {

    AddLaundryProducts products = addLaundryProducts.get(pos);
    products.setQuantity(String.valueOf(quantity));
    addLaundryItemAdapter.notifyItemChanged(pos);

    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
  }

  @Override
  public void selectedCustomItem(String itemName) {

     /*   StoreCategoryAttributeDetail storeCategoryAttributeDetail = storeCategoryAttributeDetailArrayList.get(SelectedCustomOrderPos);
        storeCategoryAttributeDetail.setSelectedItemName(itemName);
        customOrderAdapter.notifyDataSetChanged();

        if (addLaundryItemBottomSheet.isAdded()) {
            addLaundryItemBottomSheet.dismiss();
        }*/

  }

  @Override
  public void startLoginAct() {
    //  Intent intent = new Intent(this, FlexyLoginActivity.class);
    //   startActivity(intent);

  }

  @Override
  public void showAlert(String title, String message) {
    Log.d("exe", "titlemessage");
  }

  @Override
  public void startLogin() {
      /*  Intent intent = new Intent(this, FlexyLoginActivity.class);
        startActivity(intent);*/
  }

  @Override
  public void showProgress() {
    proBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    proBar.setVisibility(View.GONE);
  }
}
