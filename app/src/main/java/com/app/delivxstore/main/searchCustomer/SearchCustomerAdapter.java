package com.app.delivxstore.main.searchCustomer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.delivxstore.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCustomerAdapter extends RecyclerView.Adapter<SearchCustomerAdapter.ViewHolder> {


    private ArrayList<SearchCustomerData> searchCustomerData;
    private searchCustomerInterfaceCallBack searchCustomerInterfaceCallBack;

    public SearchCustomerAdapter(ArrayList<SearchCustomerData> searchCustomerData, searchCustomerInterfaceCallBack searchCustomerInterfaceCallBack) {
        this.searchCustomerData = searchCustomerData;
        this.searchCustomerInterfaceCallBack = searchCustomerInterfaceCallBack;

    }


    @NonNull
    @Override
    public SearchCustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_customer_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCustomerAdapter.ViewHolder viewHolder, int i) {
        SearchCustomerData data = searchCustomerData.get(i);

        if (data.getName() != null) {
            viewHolder.tvCustomerName.setText(data.getName());
        }
        if (data.getPhone() != null) {
            viewHolder.tvNumber.setText(data.getPhone());
        }
        if (data.getEmail() != null) {
            viewHolder.tvMail.setText(data.getEmail());
        }

        viewHolder.llcustomerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCustomerInterfaceCallBack.getCustomerValues(data.getName(), data.getPhone(), data.getEmail(),data.getCustomerId());
            }

        });
    }

    @Override
    public int getItemCount() {
        return searchCustomerData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCustomerName)
        TextView tvCustomerName;
        @BindView(R.id.tvMail)
        TextView tvMail;
        @BindView(R.id.tvNumber)
        TextView tvNumber;
        @BindView(R.id.llcustomerItem)
        LinearLayout llcustomerItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
