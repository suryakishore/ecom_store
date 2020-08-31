package com.app.delivxstore.main.home.tabView.orders.orderDetails;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Reasons;
import com.app.delivxstore.utility.Utility;

import java.util.ArrayList;

/**
 * Created by embed on 22/11/16.
 */
public class CancelAdapter extends RecyclerView.Adapter<CancelAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList<Reasons> mData;
    private ArrayList<ImageView> buttons;
    private int reason=-1;

    public CancelAdapter(Context context, ArrayList<Reasons> mData)
    {
     this.context=context;
     this.mData=mData;
     buttons=new ArrayList<>();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewsd= LayoutInflater.from(context).inflate(R.layout.single_row_cancellation,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(viewsd);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        buttons.add(holder.ivReason);
        holder.tvReason.setText(mData.get(position).getReasons());
        holder.rl_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetAll();
                holder.ivReason.setVisibility(View.VISIBLE);
                reason=position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvReason;
        private ImageView ivReason;
        private LinearLayout rl_reason;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvReason= (TextView) itemView.findViewById(R.id.tvReason);
            ivReason= (ImageView) itemView.findViewById(R.id.ivReason);
            rl_reason= (LinearLayout) itemView.findViewById(R.id.rl_reason);

        }
    }



    private void resetAll() {
        for (int i=0;i<buttons.size();i++)
        {
            buttons.get(i).setVisibility(View.GONE);
        }
    }

    public void submitOnclick(CancellationDialog dialog)
    {
        if(reason!=-1)
        {
            dialog.setSelectedReason(mData.get(reason));
        }
        else
        {
            new Utility().mShowMessage(context.getResources().getString(R.string.message),context.getResources().getString(R.string.selectReason), (Activity) context);
        }

    }

}
