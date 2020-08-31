package com.app.delivxstore.main.mobileView.pastOrders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.app.delivxstore.R;
import com.app.delivxstore.main.home.model.OrderDetails;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.adapter.PastOrderInOrderDetAdapter;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.PastOrdersData;
import com.app.delivxstore.utility.Utility;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.ArrayList;
import javax.inject.Inject;

public class PastOrdersAct extends DaggerAppCompatActivity implements PastOrdersContract.PastOrdersView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rvPastOrderHis)
    RecyclerView rvPastOrderHis;
    @BindView(R.id.ivBack)
    ImageView ivBack;

    private ArrayList<OrderDetails> orders = new ArrayList<>();

   private PastOrderInOrderDetAdapter historyAdapter;

    @Inject
    PastOrdersContract.PastOrdersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_orders);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }


    public void initViews() {
        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
       // llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPastOrderHis.setLayoutManager(llm);
        rvPastOrderHis.setHasFixedSize(true);
        historyAdapter = new PastOrderInOrderDetAdapter(this, orders);
        rvPastOrderHis.setAdapter(historyAdapter);

    }

    private void getIntentData() {

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String customerId = bundle.getString("customerId");

            presenter.getUserHistory(customerId);

        }


    }

    @OnClick({R.id.ivBack})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.ivBack:
                presenter.closeAct();
                break;
            default:
                break;
        }


    }


    @Override
    public void finishActivity() {
        finish();
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setPastOrders(PastOrdersData pastOrders) {
//        orders.clear();
//        orders.addAll(pastOrders.getPastOrders());
        Utility.printLog("userHistory : " + "orderssize "+orders.size());
        historyAdapter.setOrders(pastOrders.getPastOrders());
        historyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }


}
