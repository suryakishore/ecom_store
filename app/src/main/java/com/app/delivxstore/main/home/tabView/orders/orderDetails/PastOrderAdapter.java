package com.app.delivxstore.main.home.tabView.orders.orderDetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.delivxstore.R;
import com.app.delivxstore.main.home.model.OrderDetails;
import com.app.delivxstore.utility.Utility;
import java.util.ArrayList;

public class PastOrderAdapter extends RecyclerView.Adapter<PastOrderAdapter.MyViewHolder> {

  private Context context;
  private ArrayList<OrderDetails> orders = new ArrayList<>();
  private int lastSelected = 0;

  public PastOrderAdapter(Context context, ArrayList<OrderDetails> orders) {
    this.context = context;
    if (orders != null) {
      this.orders.addAll(orders); /*= orders;*/
    }
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View viewsd = LayoutInflater.from(context).inflate(R.layout.single_row_past_orders, parent,
        false);
    return new MyViewHolder(viewsd);
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

    final int mPosition = holder.getAdapterPosition();
    orders.get(position).setSelected(lastSelected == mPosition);
    holder.cvBackground.setSelected(lastSelected == mPosition);
    if (orders.get(position).isSelected() && context instanceof DialogOrderDetailsActivity) {
      holder.cvBackground.setCardBackgroundColor(ContextCompat.getColor(context,
          R.color.OuterSpace));
      holder.tvPastOrderType.setTextColor(ContextCompat.getColor(context, R.color.white));
      holder.tvPastOrderStatus.setTextColor(ContextCompat.getColor(context, R.color.white));
      holder.tvPastOrderId.setTextColor(ContextCompat.getColor(context, R.color.white));
      holder.tvPastOrderDate.setTextColor(ContextCompat.getColor(context, R.color.white));
      holder.tvPastOrderTime.setTextColor(ContextCompat.getColor(context, R.color.white));
      holder.tvPastOrderAmount.setTextColor(ContextCompat.getColor(context, R.color.white));

      ((DialogOrderDetailsActivity)context).addItems(orders.get(position).getItems(),
          orders.get(position).getCurrencySymbol(), false);
      ((DialogOrderDetailsActivity)context).setFares(orders.get(position).getSubTotalAmount(),
          orders.get(position).getDiscount(),
          orders.get(position).getDeliveryCharge(),
          "0",
          orders.get(position).getTotalAmount(), orders.get(position).getExclusiveTaxes());

    } else if (context instanceof DialogOrderDetailsActivity) {
      holder.cvBackground.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
      holder.tvPastOrderType.setTextColor(ContextCompat.getColor(context, R.color.shark));
      holder.tvPastOrderStatus.setTextColor(ContextCompat.getColor(context, R.color.shark));
      holder.tvPastOrderId.setTextColor(ContextCompat.getColor(context, R.color.shark));
      holder.tvPastOrderDate.setTextColor(ContextCompat.getColor(context, R.color.shark));
      holder.tvPastOrderTime.setTextColor(ContextCompat.getColor(context, R.color.shark));
      holder.tvPastOrderAmount.setTextColor(ContextCompat.getColor(context, R.color.shark));
    }


    if (orders.get(position).getServiceType().equals("1")) {
      holder.tvPastOrderType.setText(context.getResources().getString(R.string.delivery));
    } else {
      holder.tvPastOrderType.setText(context.getResources().getString(R.string.pickUp));
    }

    holder.tvPastOrderStatus.setText(orders.get(position).getStatusMsg());
    holder.tvPastOrderId.setText(orders.get(position).getOrderId());
    holder.tvPastOrderAmount.setText(orders.get(position).getCurrencySymbol() + Utility.formatPrice(orders.get(position).getTotalAmount()));

    String bookingDate = Utility.formatDate(orders.get(position).getBookingDate(), "d MMM yyyy");
    String bookingTime = Utility.formatDate(orders.get(position).getBookingDate(), "h.mm a");

    holder.tvPastOrderDate.setText(bookingDate);
    holder.tvPastOrderTime.setText(bookingTime);


  }


  @Override
  public int getItemCount() {

    return orders.size();

  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.cvBackground)
    CardView cvBackground;
    @BindView(R.id.viewDivider)
    View viewDivider;
    @BindView(R.id.tvPastOrderType)
    TextView tvPastOrderType;
    @BindView(R.id.tvPastOrderId)
    TextView tvPastOrderId;
    @BindView(R.id.tvPastOrderDate)
    TextView tvPastOrderDate;
    @BindView(R.id.tvPastOrderStatus)
    TextView tvPastOrderStatus;
    @BindView(R.id.tvPastOrderTime)
    TextView tvPastOrderTime;
    @BindView(R.id.tvPastOrderAmount)
    TextView tvPastOrderAmount;
    @BindView(R.id.llMain)
    LinearLayout llMain;


    MyViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);


      cvBackground.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
      });
    }
  }
}
