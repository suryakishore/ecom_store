package com.app.delivxstore.main.createOrder.produList;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.app.delivxstore.R;

public class ActivityProductList extends AppCompatActivity
{

  @BindView(R.id.rvCategory) RecyclerView rvCategory;
  @BindView(R.id.ivBack)
  AppCompatImageView ivBack;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category);
    ButterKnife.bind(this);
    setViews();
  }

  @OnClick({R.id.ivBack})
  public void onClickListener(View view)
  {
    finish();
  }

  private void setViews() {
    rvCategory.setLayoutManager(new LinearLayoutManager(this));
    ProductAdapter productAdapter = new ProductAdapter();
    rvCategory.setAdapter(productAdapter);
  }
}
