package com.app.delivxstore.main.store_filter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.app.delivxstore.R;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.DriverData;
import com.app.delivxstore.utility.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder>{


    private ArrayList<DriverData> driverList=new ArrayList<>();
    private Context context;
    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_driver_layout, parent, false);
        return new DriverViewHolder(view);
    }

    public void setDriverList(ArrayList<DriverData> driverList,Context context)
    {
        this.context=context;
        this.driverList.clear();
        this.driverList.addAll(driverList);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {

        Utility.printLog("DriverDataAdded"+driverList.get(position).getFirstName());
        holder.tv_driverName.setText(driverList.get(position).getFirstName());
        holder.tv_driverMobNum.setText(driverList.get(position).getPhone());

        if(driverList.get(position).getImage()!=null) {
            RequestOptions myOptions = new RequestOptions()
                    .error(ContextCompat.getDrawable(context, R.drawable.profile))
                    .override(50, 50);

            Glide.with(context)
                    .load(driverList.get(position).getImage().replace(" ", "%20"))
                    .apply(myOptions)
                    .into(holder.ivDriverProfile);
        }
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public static class DriverViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_driverName) TextView tv_driverName;
        @BindView(R.id.tv_driverMobNum) TextView tv_driverMobNum;
        @BindView(R.id.ivDriverProfile) ImageView ivDriverProfile;

        public DriverViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
