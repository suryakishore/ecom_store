package com.app.delivxstore.main.history.adapter;

import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.databinding.ItemAttributesBinding;
import com.app.delivxstore.main.history.model.AttributesItem;
import java.util.List;

/*binds recyclerview data of attributes*/
public class AttributeAdapter extends RecyclerView.Adapter {

  private List<AttributesItem> mAttributesItems;

  public AttributeAdapter(List<AttributesItem> attributesItems) {
    this.mAttributesItems = attributesItems;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new AttributeViewHolder(ItemAttributesBinding.inflate(LayoutInflater.from(parent.getContext())));
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof AttributeViewHolder) {
      AttributeViewHolder viewHolder = (AttributeViewHolder)holder;
      AttributesItem attributesItem = mAttributesItems.get(position);
      if (attributesItem.getValue() == null || attributesItem.getValue().equals("")) {
        viewHolder.binding.textViewAttributesTitle.setVisibility(View.GONE);
        viewHolder.binding.textViewAttributesValue.setVisibility(View.GONE);
      }
      viewHolder.binding.textViewAttributesTitle.setText(attributesItem.getAttrname());
      viewHolder.binding.textViewAttributesValue.setText(attributesItem.getValue());
    }
  }

  @Override
  public int getItemCount() {
    return mAttributesItems == null ? ZERO : mAttributesItems.size();
  }

  public static class AttributeViewHolder extends RecyclerView.ViewHolder {
    ItemAttributesBinding binding;

    AttributeViewHolder(ItemAttributesBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}