package com.app.delivxstore.main.history.adapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.app.delivxstore.utility.OrderStatusConstant.CANCELLED;
import static com.app.delivxstore.utility.VariableConstants.ATTRIBUTE_DATA;
import static com.app.delivxstore.utility.VariableConstants.ZERO;
import static com.app.ecomstore.util.EcomConstants.BLACK;
import static com.app.ecomstore.util.EcomConstants.LIGHT_BLACK;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.PHARMACY;
import static com.app.ecomstore.util.EcomConstants.RESTAURENT;
import static com.app.ecomstore.util.EcomConstants.SIZE;
import static com.app.ecomstore.util.EcomConstants.TWO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemFoodSingleRowBinding;
import com.app.delivxstore.databinding.ItemHistoryProductBinding;
import com.app.delivxstore.databinding.ItemPharmacySingleRowBinding;
import com.app.delivxstore.main.history.attributesdetails.AttributesDetailsActivity;
import com.app.delivxstore.main.history.model.Packaging;
import com.app.delivxstore.main.mobileView.orderDetails.models.Attributes;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomConstants;
import com.app.ecomstore.util.EcomUtil;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.Predicate;

/*binds history details item data to adapter*/
public class HistoryDetailsItemAdapter extends RecyclerView.Adapter {
  private final ArrayList<Products> mProductList;
  private Context mContext;
  private ArrayList<Attributes> mAttributesList = new ArrayList<>();
  private int itemType;

  HistoryDetailsItemAdapter(ArrayList<Products> products, int itemType) {
    mProductList = products;
    this.itemType = itemType;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    switch (viewType) {
      case RESTAURENT:
        ItemFoodSingleRowBinding restaurantBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.item_food_single_row, parent, false);
        return new RestaurantItemViewHolder(restaurantBinding);
      case PHARMACY:
        ItemPharmacySingleRowBinding itemPharmacySingleRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.item_pharmacy_single_row, parent, false);
        return new PharmacyItemViewHolder(itemPharmacySingleRowBinding);
      default:
        ItemHistoryProductBinding binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.item_history_product, parent, false);
        return new HistoryItemViewHolder(binding);
    }
  }

  @SuppressLint("DefaultLocale")
  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof HistoryItemViewHolder) {
      HistoryItemViewHolder viewHolder = (HistoryItemViewHolder) holder;
      Products productsItem = mProductList.get(position);
      if (productsItem.getAttributes().size() > 0 && productsItem.getAttributes().get(0).getValue()
          != null
          && !productsItem.getAttributes().get(0).getValue().equals("")) {
        viewHolder.binding.groupFirst.setVisibility(View.VISIBLE);
        viewHolder.binding.tvFirstAttribute.setText(
            productsItem.getAttributes().get(0).getAttrname());
        viewHolder.binding.tvFirstAttributeValue.setText(
            productsItem.getAttributes().get(0).getValue());
      }
      if (productsItem.getAttributes().size() > 1 && productsItem.getAttributes().get(1).getValue()
          != null
          && !productsItem.getAttributes().get(0).getValue().equals("")) {
        viewHolder.binding.groupSecond.setVisibility(View.VISIBLE);
        viewHolder.binding.tvSecondAttribute.setText(
            productsItem.getAttributes().get(1).getAttrname());
        viewHolder.binding.tvSecondAttributeValue.setText(
            productsItem.getAttributes().get(1).getValue());
      }
      setImage(viewHolder.binding.ivItem, productsItem.getImages().getSmall());
      viewHolder.binding.tvItemName.setText(productsItem.getName());
      viewHolder.binding.tvItemPrice.setText(String.format("%s%s", productsItem.getCurrencySymbol(),
          Utility.roundOfDoubleValue(String.valueOf(productsItem.getAccounting().getUnitPrice()))));
      viewHolder.binding.tvQtyValue.setText(String.format("%sx%s",
          productsItem.getQuantity().getValue(), productsItem.getQuantity().getUnit()));
      viewHolder.binding.tvViewMore.setVisibility(
          productsItem.getAttributes().size() > TWO ? View.VISIBLE : View.GONE);
      viewHolder.binding.tvViewMore.setOnClickListener(view -> {
        Intent intent = new Intent(mContext, AttributesDetailsActivity.class);
        intent.putExtra(ATTRIBUTE_DATA, productsItem.getAttributes());
        mContext.startActivity(intent);
      });
    } else if (holder instanceof RestaurantItemViewHolder) {
      Products productsItem = mProductList.get(position);
      RestaurantItemViewHolder viewHolder = (RestaurantItemViewHolder) holder;
      viewHolder.binding.hsItemUnAvailableStatus.setVisibility(View.GONE);
      viewHolder.binding.tvItemUnAvailableProductName.setText(productsItem.getName());
      viewHolder.binding.tvItemQty.setText(
          String.format("%s %s", mContext.getResources().getString(R.string.qty),
              productsItem.getQuantity().getValue()));
      mAttributesList.clear();
      mAttributesList.addAll(productsItem.getAttributes());
      Predicate<Attributes> conditionPending =
          orderCountData -> !orderCountData.getAttrname().equals(SIZE);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        mAttributesList.removeIf(conditionPending);
      }
      if (mAttributesList != null && mAttributesList.size() > ZERO) {
        if (mAttributesList.get(ZERO).getValue() != null) {
          String size = EcomUtil.getColoredSpanned(mContext.getResources().getString(R.string.size),
              LIGHT_BLACK);
          String attrName = EcomUtil.getColoredSpanned(
              mAttributesList.get(ZERO).getValue(),
              BLACK);
          viewHolder.binding.tvItemSizeAndColor.setText(Html.fromHtml(
              String.format("%s %s", size, attrName)));
        } else {
          viewHolder.binding.tvItemSizeAndColor.setVisibility(View.GONE);
        }
      }
      mAttributesList.clear();
      mAttributesList.addAll(productsItem.getAttributes());
      Predicate<Attributes> conditionIsAddOn =
          orderCountData -> !orderCountData.isAddOn();
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        mAttributesList.removeIf(conditionIsAddOn);
      }
      LinkedHashMap<String, ArrayList<Attributes>> mHashMap = new LinkedHashMap<>();
      if (mAttributesList != null && mAttributesList.size() > ZERO) {
        for (int j = ZERO; j < mAttributesList.size(); j++) {
          Attributes attributes = mAttributesList.get(j);
          String addOnId = attributes.getAddOnId();
          if (mHashMap.containsKey(addOnId)) {
            ArrayList<Attributes> value = mHashMap.get(addOnId);
            if (value != null) {
              value.add(attributes);
            }
          } else {
            ArrayList<Attributes> attributesArrayList = new ArrayList<>();
            attributesArrayList.add(attributes);
            mHashMap.put(addOnId, attributesArrayList);
          }
        }
      }
      viewHolder.binding.llFoodAttributeGroup.removeAllViews();
      LayoutInflater inflaterAttribute = (LayoutInflater) mContext.getSystemService(
          LAYOUT_INFLATER_SERVICE);
      if (mHashMap.size() > ZERO) {
        for (int j = ZERO; j < mHashMap.size(); j++) {
          ArrayList<Attributes> attributesArrayList =
              (ArrayList<Attributes>) mHashMap.values().toArray()[j];
          if (attributesArrayList != null) {
            String addOnName = String.format("%s: ", EcomUtil.getColoredSpanned(
                attributesArrayList.get(EcomConstants.ZERO).getAddOnName(), LIGHT_BLACK));
            for (int i = ZERO; i < attributesArrayList.size(); i++) {
              StringBuilder stringBuilder = new StringBuilder();
              View attributeView = inflaterAttribute.inflate(R.layout.item_food_attribute, null);
              TextView tvAttributeItem = attributeView.findViewById(R.id.tvAttributeItem);
              stringBuilder.append(attributesArrayList.get(i).getAttrname()).append(",");
              String attrName = EcomUtil.getColoredSpanned(
                  stringBuilder.toString().substring(EcomConstants.ZERO,
                      stringBuilder.toString().length() - ONE), BLACK);
              tvAttributeItem.setText(Html.fromHtml(
                  String.format("%s %s", addOnName, attrName)));
              viewHolder.binding.llFoodAttributeGroup.addView(attributeView);
            }
          }
        }
      }
    } else if (holder instanceof PharmacyItemViewHolder) {
      Products productsItem = mProductList.get(position);
      PharmacyItemViewHolder viewHolder = (PharmacyItemViewHolder) holder;
      viewHolder.binding.hsItemUnAvailableStatus.setVisibility(View.GONE);
      viewHolder.binding.groupAsile.setVisibility(View.GONE);
      viewHolder.binding.tvItemDosage.setVisibility(View.GONE);
      viewHolder.binding.tvItemUnAvailableProductName.setText(productsItem.getName());
      if (productsItem.getImages() != null) {
        Glide.with(mContext)
            .load(productsItem.getImages().getMedium())
            .into(viewHolder.binding.ivItemUnAvailableProductImage);
      }
      if (productsItem.getPackaging() != null
          && productsItem.getPackaging().getUnitValue() != ZERO) {
        Packaging packaging = productsItem.getPackaging();
        viewHolder.binding.tvItemTabletCount.setText(String.format("%s %s %s",
            packaging.getUnitValue(), packaging.getUnitType(), packaging.getPackingType()));
      } else {
        viewHolder.binding.tvItemTabletCount.setVisibility(View.GONE);
      }
      if (Integer.parseInt(productsItem.getStatus().getStatus()) == CANCELLED) {
        viewHolder.binding.rlCancelled.setVisibility(View.VISIBLE);
        viewHolder.binding.tvCancel.setText(productsItem.getStatus().getStatusText());
        viewHolder.binding.tvCancelReason.setText(productsItem.getReason());
      }
      viewHolder.binding.tvItemQty.setText(
          String.format("%s %s %s", mContext.getResources().getString(R.string.qty),
              productsItem.getQuantity().getValue(), productsItem.getQuantity().getUnit()));
      viewHolder.binding.tvItemStripCount.setText(
          String.format("%d %s*%s", ONE, productsItem.getQuantity().getUnit(),
              Utility.roundOfDoubleValue(
                  String.format("%s", productsItem.getSingleUnitPrice().getSubTotal()))));
      viewHolder.binding.tvItemUnAvailableProductPrice.setText(
          String.format("%s %s", productsItem.getCurrencySymbol(),
              Utility.roundOfDoubleValue(productsItem.getAccounting().getFinalTotal() + "")));
    }
  }

  /*set image to image view using glide*/
  private void setImage(AppCompatImageView ivItem, String imageUrl) {
    Uri uri = Uri.parse(imageUrl);
    if (imageUrl != null) {
      Glide.with(mContext).load(uri).into(ivItem);
    } else {
      Utility.toastMessage(mContext, mContext.getString(R.string.noImage));
    }
  }

  @Override
  public int getItemCount() {
    return mProductList == null ? ZERO : mProductList.size();
  }

  @Override
  public int getItemViewType(int position) {
    return itemType;
  }

  public static class HistoryItemViewHolder extends RecyclerView.ViewHolder {
    ItemHistoryProductBinding binding;

    HistoryItemViewHolder(@NonNull ItemHistoryProductBinding detailsBinding) {
      super(detailsBinding.getRoot());
      binding = detailsBinding;
    }
  }

  public static class RestaurantItemViewHolder extends RecyclerView.ViewHolder {
    ItemFoodSingleRowBinding binding;

    RestaurantItemViewHolder(@NonNull ItemFoodSingleRowBinding detailsBinding) {
      super(detailsBinding.getRoot());
      binding = detailsBinding;
    }
  }

  public static class PharmacyItemViewHolder extends RecyclerView.ViewHolder {
    ItemPharmacySingleRowBinding binding;

    PharmacyItemViewHolder(@NonNull ItemPharmacySingleRowBinding detailsBinding) {
      super(detailsBinding.getRoot());
      binding = detailsBinding;
    }
  }
}
