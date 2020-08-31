/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.delivxstore.R;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models.ProductAttributes;
import java.util.ArrayList;

/**
 * show the product attributes
 */
public class ProductAttributesAddRemoveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<ProductAttributes> mProductAttributesArrayList;
    private String mCurrencySymbol;

    public ProductAttributesAddRemoveAdapter(
            Context context,
            ArrayList<ProductAttributes> mProductAttributesArrayList
    ) {
        this.mContext = context;
        this.mProductAttributesArrayList = mProductAttributesArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_attributes_add_remove,
                parent, false);
        return new ProductAttributesAddRemoveAdapter.ViewHolderItemAttributes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolderItemAttributes viewHolderItemAttributes = (ViewHolderItemAttributes) viewHolder;
        if (mProductAttributesArrayList.get(viewHolderItemAttributes.getAdapterPosition()).getValue().equals("")) {
            viewHolderItemAttributes.textView_itemAttributes_value.setText(
                    mProductAttributesArrayList.
                            get(viewHolderItemAttributes.getAdapterPosition()).
                            getName() + ":");
        } else {
            viewHolderItemAttributes.textView_itemAttributes_value.setText(
                    mProductAttributesArrayList.
                            get(viewHolderItemAttributes.getAdapterPosition()).
                            getName() + " (" +
                            mProductAttributesArrayList.
                                    get(viewHolderItemAttributes.getAdapterPosition()).
                                    getValue() + "):");
        }

        viewHolderItemAttributes.imageView_itemAttributes_add.setOnClickListener(v -> {
            int currentVal = Integer.parseInt(
                    viewHolderItemAttributes.textView_itemAttributes_attributeQnt.getText().toString()
            );
            viewHolderItemAttributes.textView_itemAttributes_attributeQnt.setText((++currentVal)+"");
        });

        viewHolderItemAttributes.imageView_itemAttributes_remove.setOnClickListener(v -> {
            int currentVal = Integer.parseInt(
                    viewHolderItemAttributes.textView_itemAttributes_attributeQnt.getText().toString()
            );
            if (currentVal>0) {
                viewHolderItemAttributes.textView_itemAttributes_attributeQnt.setText((--currentVal)+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductAttributesArrayList.size();
    }

    /**
     * <h>setCurrency</h>
     * <p>get the currency symbol</p>
     * @param mCurrencySymbol   get the currency symbol
     */
    public void setCurrency(String mCurrencySymbol) {
        this.mCurrencySymbol = mCurrencySymbol;
    }

    public class ViewHolderItemAttributes extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_itemAttributes_value)
        TextView textView_itemAttributes_value;
        @BindView(R.id.imageView_itemAttributes_add)
        ImageView imageView_itemAttributes_add;
        @BindView(R.id.imageView_itemAttributes_remove)
        ImageView imageView_itemAttributes_remove;
        @BindView(R.id.textView_itemAttributes_attributeQnt)
        TextView textView_itemAttributes_attributeQnt;

        ViewHolderItemAttributes(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

