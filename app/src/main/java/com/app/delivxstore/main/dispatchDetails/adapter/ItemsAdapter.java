package com.app.delivxstore.main.dispatchDetails.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Items> mItems;
    private String mCurrencySymbol;

    public ItemsAdapter(
            Context context,
            ArrayList<Items> mItems
    ) {
        this.mContext = context;
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_name,
                parent, false);
        return new ItemsAdapter.ViewHolderItemAttributes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolderItemAttributes viewHolderItemAttributes = (ViewHolderItemAttributes) viewHolder;
        viewHolderItemAttributes.textView_itemNames_itemName.setText(mItems.get(viewHolderItemAttributes.getAdapterPosition()).getItemName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
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

        @BindView(R.id.textView_itemNames_itemName)
        TextView textView_itemNames_itemName;

        ViewHolderItemAttributes(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}