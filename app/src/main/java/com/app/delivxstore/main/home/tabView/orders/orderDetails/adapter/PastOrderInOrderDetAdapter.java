package com.app.delivxstore.main.home.tabView.orders.orderDetails.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.main.home.model.OrderDetails;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.DialogOrderDetailsActivity;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items;
import com.app.delivxstore.utility.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PastOrderInOrderDetAdapter extends RecyclerView.Adapter<PastOrderInOrderDetAdapter.PastOrderViewHolder> {

    private Context context;
    private ArrayList<OrderDetails> orders=new ArrayList<>();
    private int lastSelected=0;

    public PastOrderInOrderDetAdapter(Context context, ArrayList<OrderDetails> orders) {
        this.context = context;
        if(orders!=null)
            this.orders.addAll(orders); /*= orders;*/
    }

    public void setOrders(ArrayList<OrderDetails> orders)
    {
        if(orders!=null) {
            this.orders.clear();
            this.orders.addAll(orders); /*= orders;*/
        }
    }
    @NonNull
    @Override
    public PastOrderInOrderDetAdapter.PastOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewsd= LayoutInflater.from(context).inflate(R.layout.single_row_order_history,parent,false);
        return new PastOrderInOrderDetAdapter.PastOrderViewHolder(viewsd);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final PastOrderInOrderDetAdapter.PastOrderViewHolder holder, final int position) {

        Utility.printLog("userHistory : " + "position "+position);

        final int mPosition=holder.getAdapterPosition();
        holder.tvStatusBtn.setText(orders.get(position).getStatusMsg());
        holder.tvorderID.setText(orders.get(position).getOrderId());
        holder.tvCost.setText(orders.get(position).getCurrencySymbol()+" "+ Utility.formatPrice(orders.get(position).getTotalAmount()));

        String bookingDate=Utility.formatDate(orders.get(position).getBookingDate(),"d MMM yyyy");
        String bookingTime=Utility.formatDate(orders.get(position).getBookingDate(),"h.mm a");

       // holder.tv_date.setText(bookingDate);
        holder.tvDate.setText(bookingTime+","+bookingDate);
        holder.tvName.setText(orders.get(position).getStoreName());
     //   holder.tv_storeAddress.setText(orders.get(position).getStoreAddress());


       // addItems(orders.get(position).getItems(),orders.get(position).getCurrencySymbol(),true,holder.llItemContainer);

       /* if(orders.get(position).isSelected())
            holder.ll_itemView.setVisibility(View.VISIBLE);
        else
            holder.ll_itemView.setVisibility(View.GONE);

        holder.cv_mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orders.get(position).isSelected())
                    orders.get(position).setSelected(false);
                else
                    orders.get(position).setSelected(true);
                notifyDataSetChanged();
            }
        });*/


        holder.llHistoryMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("orderId", orders.get(position).getOrderId());
                Intent intent = null;

                if (Utility.isTablet(context)) {
                    intent = new Intent(context, DialogOrderDetailsActivity.class);
                    intent.putExtras(bundle);
                } else {
                   /* intent = new Intent(context, OrderDetailsForMobile.class);
                    intent.putExtras(bundle);*/
                }

                context.startActivity(intent);

            }
        });


    }



    public void addItems(final ArrayList<Items> items, String currency, boolean enabled, LinearLayout llItemContainer){

        if(items!=null && items.size()>0){

            llItemContainer.removeAllViews();
            float subTotal=0.0f;

            for (int index=0;index<items.size();index++)
            {
                LayoutInflater layoutInflater= (LayoutInflater)context. getSystemService(LAYOUT_INFLATER_SERVICE);
                View view=layoutInflater.inflate(R.layout.sinle_item_in_past_order,null);
                TextView tvItemName=view.findViewById(R.id.tvItemName);
                TextView tvItemQnty=view.findViewById(R.id.tvItemQnty);
                final EditText etQuantity=view.findViewById(R.id.etQuantity);
                final EditText etUnitPrice=view.findViewById(R.id.etUnitPrice);
                TextView tvPrice=view.findViewById(R.id.tvOrderListTotalPrice);

                tvItemName.setText(items.get(index).getItemName());
                tvItemQnty.setText(items.get(index).getQuantity()+"");
                int quantity;
                float unitPrice=0;
                quantity= Integer.parseInt(items.get(index).getQuantity()+"");
                if (items.get(index).getFinalPrice()!=null)
                unitPrice= Float.parseFloat(items.get(index).getFinalPrice());
                etQuantity.setEnabled(enabled);
                etUnitPrice.setEnabled(enabled);

                if(!enabled){
                    etQuantity.setBackgroundColor(context.getResources().getColor(R.color.transperent));
                    etUnitPrice.setBackgroundColor(context.getResources().getColor(R.color.transperent));
                }
                etQuantity.setText(""+quantity);
                etUnitPrice.setText(""+ unitPrice);
                tvPrice.setText(currency+" "+Utility.formatPrice(String.valueOf(quantity*unitPrice)));
                subTotal+=quantity*unitPrice;
                llItemContainer.addView(view);
            }

        }
        else {
            llItemContainer.removeAllViews();
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class PastOrderViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.tvorderID)TextView tvorderID;
      //  @BindView(R.id.tv_date)TextView tv_date;
        @BindView(R.id.tvStatusBtn)TextView tvStatusBtn;
        @BindView(R.id.textViewDriverRowDriverName)TextView tvName;
       // @BindView(R.id.tv_storeAddress)TextView tv_storeAddress;
        @BindView(R.id.tvOrderListOrderDate)TextView tvDate;
        @BindView(R.id.tvCost)TextView tvCost;
        @BindView(R.id.llHistoryMain) LinearLayout llHistoryMain;
    //    @BindView(R.id.llItemContainer)LinearLayout llItemContainer;
       // @BindView(R.id.ll_itemView)LinearLayout ll_itemView;
       // @BindView(R.id.cv_mainView)CardView cv_mainView;

        PastOrderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
