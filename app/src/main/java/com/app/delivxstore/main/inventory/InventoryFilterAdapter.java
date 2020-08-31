package com.app.delivxstore.main.inventory;

import android.app.Activity;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.delivxstore.R;
import com.app.delivxstore.main.inventory.model.CategoryFilter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by murashid on 29-Sep-17.
 * <h1>InventoryFilterAdapter</h1>
 * Recycler View Adapter for Inventory List
 */

public class InventoryFilterAdapter extends RecyclerView.Adapter<InventoryFilterAdapter.ViewHolder>{

    private static final String TAG = "BankListAdapter";
    private Activity activity;
    private ArrayList<CategoryFilter> categoryFilters;
    private Typeface  fontRegular;
    private IntentoryFilterClickListener listener;

    public InventoryFilterAdapter(Activity activity, ArrayList<CategoryFilter> categoryFilters,Typeface  fontRegular, IntentoryFilterClickListener listener)
    {
        this.activity = activity;
        this.categoryFilters = categoryFilters;
        this.listener = listener;
        this.fontRegular = fontRegular;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.cbCategory)
        AppCompatCheckBox cbCategory;

        ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);
            cbCategory.setTypeface(fontRegular);
        }

        @OnClick({R.id.cbCategory})
        public void clickEvent()
        {
            if(getAdapterPosition() >= 0)
            {
                categoryFilters.get(getAdapterPosition()).setSelected(cbCategory.isChecked());
                listener.onFilterClicked(getAdapterPosition(), cbCategory.isChecked());
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_inventory_filter_items, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.cbCategory.setText(categoryFilters.get(position).getCategory());
        holder.cbCategory.setChecked(categoryFilters.get(position).isSelected());
    }

    @Override
    public int getItemCount() {
        return categoryFilters.size();
    }

    /**
     * <h1>IntentoryItemClickListener</h1>
     * Interface for Intentory Click select
     * */
    public interface IntentoryFilterClickListener
    {
        void onFilterClicked(int categoryPosition, boolean isSelected);
    }
}
