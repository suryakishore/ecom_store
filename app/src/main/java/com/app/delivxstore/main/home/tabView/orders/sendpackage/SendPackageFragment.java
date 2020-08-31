package com.app.delivxstore.main.home.tabView.orders.sendpackage;

import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.main.home.HomeFragment;
import com.app.delivxstore.main.home.model.OrderDetails;
import com.app.delivxstore.main.home.models.Data;
import com.app.delivxstore.main.home.tabView.orders.OrderAdapter;
import com.app.delivxstore.utility.TimeFormatter;
import com.app.delivxstore.utility.Utility;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import dagger.android.support.DaggerFragment;

public class SendPackageFragment extends DaggerFragment implements SendPackageConrtact.SendPackageView {

    private ArrayList<Data> orderDetails = new ArrayList<>();
    private OrderAdapter orderAdapter;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    //    @BindView(R.id.tvFrgTitle) TextView tvFrgTitle;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.rvOrder)
    RecyclerView rvOrder;
    @BindView(R.id.llMain)
    LinearLayout llMain;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_storeName)
    TextView tv_storeName;
    @BindString(R.string.packages_search)
    String new_search;

    @Inject
    SendPackageConrtact.SendPackagePresenter presenter;

    @Inject
    public SendPackageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, null);
        ButterKnife.bind(this, view);

        initView();
        presenter.subscribeFilterData();
        return view;
    }

    @Override
    public void setStoreName(String storeName) {
        if (storeName!=null&&!storeName.equals("All")) {

            tv_storeName.setVisibility(View.VISIBLE);
            tv_storeName.setText(storeName);
        }
        else
            tv_storeName.setVisibility(View.GONE);

    }

    private void initView() {
        search.setHint(new_search);
        presenter.attachView(this);

        llMain.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
//        tvFrgTitle.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));

        presenter.getView(getArguments());

        orderAdapter = new OrderAdapter( orderDetails);
        orderAdapter.mIsFromPreOrder = true;
        GridLayoutManager layoutManager;

        if (Utility.isTablet(getActivity())) {
            layoutManager = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        } else
            layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

        rvOrder.setLayoutManager(layoutManager);
        rvOrder.setItemAnimator(new DefaultItemAnimator());
        rvOrder.setAdapter(orderAdapter);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                assert getParentFragment() != null;
                ((HomeFragment) getParentFragment()).presenter.getOrders();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showProgress() {
        if (!swipeRefreshLayout.isRefreshing())
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void setTitle(boolean tablet, String type) {
        if (tablet) {
//            tvFrgTitle.setVisibility(View.VISIBLE);
//            tvFrgTitle.setText(type);
        } else {
//            tvFrgTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void setListData(ArrayList<OrderDetails> orders) {

    /*    swipeRefreshLayout.setRefreshing(false);

        if (orders != null && orders.size() > 0) {
            orderDetails.clear();
            orderDetails.addAll(orders);
        } else
            orderDetails.clear();

        orderAdapter.notifyDataSetChanged();*/




        ArrayList<OrderDetails> orderDetailsTemp = new ArrayList<>();

        swipeRefreshLayout.setRefreshing(false);
        orderDetailsTemp.clear();


        int index = 0;

        if (orders != null && orders.size() > 0) {


            for (int i = 0; i < orders.size(); i++) {


                OrderDetails orderDetails = orders.get(i);

                String monthIndex = TimeFormatter.getMonth(orderDetails.getDueDatetime());


                if (monthIndex != null) {

                    OrderDetails orderDetailsDate = new OrderDetails();

                    orderDetailsDate.setItemType(1);

                    orderDetailsDate.setDueDatetime(orderDetails.getDueDatetime());

                    orderDetailsTemp.add(orderDetailsDate);

                    orderDetails.setItemType(0);

                    orderDetailsTemp.add(orderDetails);




                    if (i < orders.size()) {


                        for (int j = (i + 1); j < orders.size(); j++) {



                            OrderDetails futureOrderDetails = orders.get(j);

                            String futureMonthIndex = TimeFormatter.getMonth(futureOrderDetails.getDueDatetime());




                            if (monthIndex.equals(futureMonthIndex)) {

                                futureOrderDetails.setItemType(0);

                                orderDetailsTemp.add(futureOrderDetails);


                                index = j;

                                //  i=index-1;

                            } else {



                                //   index = j;

                                i = (j - 1);

                                index = i;




                                break;

                            }

                        }



                        if (index == orders.size() - 1) {
                            break;
                        }


                    } else {

                        orderDetailsTemp.add(orderDetails);

                        break;

                    }


                }


            }


            orderDetails.clear();
            //    orderDetails.addAll(orders);

//            orderDetails.addAll(orderDetailsTemp);


        } else
            orderDetails.clear();

        orderAdapter.notifyDataSetChanged();












    }


    @OnTextChanged(R.id.search)
    public void onTextChange() {
        orderAdapter.getFilter().filter(search.getText());
    }

    @Override
    public void onPause() {
        super.onPause();
        orderAdapter.clearAll();
    }
}




