package com.app.delivxstore.main.store_filter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.main.MainActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private ArrayList<StoreDetails> stringsList = new ArrayList<>();
    private Context context;
    private int mSelectedItem;

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_single_item, parent, false);
        return new StoreViewHolder(view);
    }

    public void setArrayList(ArrayList<StoreDetails> stringsList) {
        this.stringsList.clear();
        this.stringsList.addAll(stringsList);
        if (this.stringsList.size() > 0) {
            StoreDetails allStoreDetails = new StoreDetails();
            allStoreDetails.setStoreName("All");
            this.stringsList.add(0, allStoreDetails);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, final int position) {
        holder.tv_storeName.setText(stringsList.get(position).getStoreName());
        holder.ll_mainParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedItem = position;
               notifyItemChanged(mSelectedItem);
                ((MainActivity) context).publishSelectedStore(stringsList.get(position));

            }

        });
        if (mSelectedItem == position) {
            holder.tv_storeName.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

        }


    }

    @Override
    public int getItemCount() {
        return stringsList.size();
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_storeName)
        TextView tv_storeName;
        @BindView(R.id.ll_mainParent)
        LinearLayout ll_mainParent;

        public StoreViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
