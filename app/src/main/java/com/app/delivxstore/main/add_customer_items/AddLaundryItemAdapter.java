package com.app.delivxstore.main.add_customer_items;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.app.delivxstore.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class AddLaundryItemAdapter extends RecyclerView.Adapter<AddLaundryItemAdapter.ViewHolder> {
    private ArrayList<AddLaundryProducts> getCartArrayList;
    private AddLaundryItemView addLaundryItemView;

    public AddLaundryItemAdapter(ArrayList<AddLaundryProducts> getCartArrayList, AddLaundryItemView addLaundryItemView) {
        this.getCartArrayList = getCartArrayList;
        this.addLaundryItemView = addLaundryItemView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.laundry_itemname_qauntity, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AddLaundryProducts getCartProducts = getCartArrayList.get(position);

        if (getCartProducts != null) {
            Log.d("exe", "name" + getCartProducts.getItemName() + "id" + getCartProducts.getParentProductId());
            holder.tv_item_name.setText(getCartProducts.getItemName());
            holder.et_quantity.clearFocus();
            holder.et_quantity.setCursorVisible(false);
            holder.et_quantity.setText(getCartProducts.getQuantity());



            if (holder.et_quantity.hasFocus()) {
                holder.iv_cross.setVisibility(View.GONE);
                holder.iv_add.setVisibility(View.VISIBLE);
            } else {
                holder.iv_cross.setVisibility(View.VISIBLE);
                holder.iv_add.setVisibility(View.GONE);
            }

        }

        holder.iv_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLaundryItemView.removeLaundryItem(position);
            }

        });


        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (getCartProducts != null)
                    addLaundryItemView.updateLaundryItem(position, getCartProducts.getChildProductId(),getCartProducts.getUnitId(), holder.et_quantity.getText().toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return getCartArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements TextWatcher, View.OnTouchListener {

        @BindView(R.id.tv_item_name)
        TextView tv_item_name;
        @BindView(R.id.et_quantity)
        EditText et_quantity;
        @BindView(R.id.iv_cross)
        ImageView iv_cross;
        @BindView(R.id.iv_add)
        ImageView iv_add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            et_quantity.clearFocus();
            et_quantity.addTextChangedListener(this);
            et_quantity.setOnTouchListener(this);
            //et_quantity.clearFocus();
            et_quantity.setCursorVisible(false);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           //  iv_cross.setVisibility(View.GONE);
            //iv_add.setVisibility(View.VISIBLE);

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (et_quantity.hasFocus()) {
                Log.d("exe", "onTextChanged");

                iv_cross.setVisibility(View.GONE);
                iv_add.setVisibility(View.VISIBLE);

            }
        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            et_quantity.setCursorVisible(true);
            return false;
        }


    }


}
