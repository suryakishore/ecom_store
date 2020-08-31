package com.app.delivxstore.main.inventory;

import android.app.Activity;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.delivxstore.R;
import com.app.delivxstore.main.inventory.model.InventaryProducts;
import com.app.delivxstore.utility.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by murashid on 29-Sep-17.
 * <h1>InventoryAdapter</h1>
 * Recycler View Adapter for Inventory List
 */

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {

    private static final String TAG = "BankListAdapter";
    private Activity activity;
    private ArrayList<InventaryProducts> products;
    private Typeface fontRegular;
    private IntentoryItemClickListener itemClickListener;
    private RequestOptions requestOptions;


    public InventoryAdapter(Activity activity, ArrayList<InventaryProducts> products, Typeface fontRegular, IntentoryItemClickListener itemClickListener) {
        this.activity = activity;
        this.products = products;
        this.itemClickListener = itemClickListener;
        this.fontRegular = fontRegular;
        requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.login_logo);
        requestOptions.error(R.drawable.login_logo);

    }

    public void filter(String filter) {


    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivItem)
        ImageView ivItem;
        @BindView(R.id.rbActiveDeActive)
        RadioButton rbActiveDeActive;
        @BindView(R.id.tvItemName)
        TextView tvItemName;

        ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            tvItemName.setTypeface(fontRegular);

        }

        @OnClick({R.id.cvInventoryItem})
        public void clickEvent() {

            if (Utility.isNetworkConnected(activity)) {

                if (getAdapterPosition() >= 0) {
                    if (products.get(getAdapterPosition()).getStatus().equals("1") || products.get(getAdapterPosition()).getStatus().equals("6")) {
                        rbActiveDeActive.setChecked(false);
                        //   products.get(getAdapterPosition()).setStatus("3");
                        itemClickListener.onItemClicked(products.get(getAdapterPosition()).getChildProductId(), 5, getAdapterPosition());
                    } else {
                        rbActiveDeActive.setChecked(true);
                        //   products.get(getAdapterPosition()).setStatus("1");
                        itemClickListener.onItemClicked(products.get(getAdapterPosition()).getChildProductId(), 6, getAdapterPosition());
                    }
                }
            }
            else {
                Toast.makeText(activity, activity.getResources().getString(R.string.networkError), Toast.LENGTH_LONG).show();
            }

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_inventory_items, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (products.get(position).getMobileImage().size() > 0) {
            Glide.with(activity)
                    .setDefaultRequestOptions(requestOptions)
                    .load(products.get(position).getMobileImage().get(0).getImage())
                    .into(holder.ivItem);
        }

        holder.tvItemName.setText(products.get(position).getProductName());
        if (products.get(position).getStatus().equals("1") || products.get(position).getStatus().equals("6")) {
            holder.rbActiveDeActive.setChecked(true);
        } else {
            holder.rbActiveDeActive.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    /**
     * <h1>IntentoryItemClickListener</h1>
     * Interface for Intentory Click select
     */
    public interface IntentoryItemClickListener {
        void onItemClicked(String id, int status, int position);
    }
}
