package com.app.delivxstore.main.manage_address;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.app.delivxstore.R;
import com.app.delivxstore.utility.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${3embed} on ${27-10-2018}.
 * Banglore
 */

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.ViewHolder> {

    private ArrayList<DataInGetAddress> addressArrayList;
    private Typeface fontMedium, fontRegular;
    private Activity mContext;

    ManageAddressAdapter(Activity activity,ArrayList<DataInGetAddress> manageAddressArrayList, Typeface fontMedium, Typeface fontRegular) {
        this.addressArrayList = manageAddressArrayList;
        this.fontMedium = fontMedium;
        this.fontRegular = fontRegular;
        this.mContext=activity;
    }

    @Override
    public ManageAddressAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_address_row, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ManageAddressAdapter.ViewHolder holder, int position)
    {

        int pos=holder.getAdapterPosition();

        holder.mainContent_Rl.setOnClickListener(view -> {
         /*   Intent intent=new Intent();
            intent.putExtra("addr1",addressArrayList.get(pos).getAddLine1());
            intent.putExtra("addr2",addressArrayList.get(pos).getAddLine2());
            intent.putExtra("addId",addressArrayList.get(pos).getId());
            intent.putExtra("lat",addressArrayList.get(pos).getLatitude());
            intent.putExtra("longi",addressArrayList.get(pos).getLongitude());
            intent.putExtra("savedAs",addressArrayList.get(pos).getTaggedAs());
             mContext.setResult(RESULT_OK,intent);
             mContext.finish();*/

            if(mContext instanceof ManageAddressAct)
            {
                ((ManageAddressAct)mContext).mSelectAddress(addressArrayList.get(pos).getId(),pos);
            }

        });


        holder.deleteTv.setOnClickListener(view -> {
             if(mContext instanceof ManageAddressAct)
             {
                 ((ManageAddressAct)mContext).mDeleteAddress(addressArrayList.get(pos).getId(),pos,
                         Utility.roundValue(addressArrayList.get(pos).getLatitude()),Utility.roundValue(addressArrayList.get(pos).getLongitude()));
             }

        });

        holder.editTv.setOnClickListener(view -> {
             if(mContext instanceof ManageAddressAct)
             {
                 ((ManageAddressAct)mContext).mEditAddress(addressArrayList.get(pos));
             }

        });
        String taggedAs;
        if(addressArrayList.get(pos).getTaggedAs().equalsIgnoreCase(mContext.getString(R.string.home)))
        {
            holder.addressCatogoryIconIv.setImageResource(R.drawable.save_location_home_icon);
            taggedAs=mContext.getString(R.string.home);
        }
        else if(addressArrayList.get(pos).getTaggedAs().equalsIgnoreCase(mContext.getString(R.string.work)))
        {
            holder.addressCatogoryIconIv.setImageResource(R.drawable.save_location_briefcase_icon);
            taggedAs=mContext.getString(R.string.work);

        }
        else
        {
            holder.addressCatogoryIconIv.setImageResource(R.drawable.save_location_location_icon);
            if(addressArrayList.get(pos).getTaggedAs().equals(""))
            taggedAs=mContext.getString(R.string.other);
            else
                taggedAs=addressArrayList.get(pos).getTaggedAs();


        }

        holder.hometxtTv.setText(taggedAs);

        holder.addressFieldTv.setText(addressArrayList.get(pos).getFlatNumber()+", "+
                addressArrayList.get(pos).getLandmark()+", "+
                addressArrayList.get(pos).getFullAddress());

    }

    @Override
    public int getItemCount() {
        return addressArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.addressCatogoryIconIv) ImageView addressCatogoryIconIv;
        @BindView(R.id.hometxtTv) TextView hometxtTv;
        @BindView(R.id.editTv) TextView editTv;
        @BindView(R.id.deleteTv) TextView deleteTv;
        @BindView(R.id.addressFieldTv) TextView addressFieldTv;
        @BindView(R.id.mainContent_Rl) RelativeLayout mainContent_Rl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            deleteTv.setTypeface(fontMedium);
            editTv.setTypeface(fontMedium);
            hometxtTv.setTypeface(fontMedium);
            addressFieldTv.setTypeface(fontRegular);

        }
    }
}
