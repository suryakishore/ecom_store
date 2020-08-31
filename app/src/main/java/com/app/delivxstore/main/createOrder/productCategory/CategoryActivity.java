package com.app.delivxstore.main.createOrder.productCategory;

import android.os.Bundle;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.app.delivxstore.R;
import dagger.android.DaggerActivity;
import javax.inject.Inject;

public class CategoryActivity extends DaggerActivity
    implements CategoryActivityContract.CategoryView {

  @BindView(R.id.rvCategory) RecyclerView rvCategory;
  @BindView(R.id.ivBack) AppCompatImageView ivBack;

  @Inject CategoryActivityContract.CategoryPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
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
    CategoryAdapter categoryAdapter = new CategoryAdapter();
    rvCategory.setAdapter(categoryAdapter);
  }
}
