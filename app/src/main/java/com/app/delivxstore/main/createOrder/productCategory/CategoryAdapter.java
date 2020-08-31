package com.app.delivxstore.main.createOrder.productCategory;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.delivxstore.R;
import com.app.delivxstore.main.createOrder.produList.ActivityProductList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>
{
  private Context mContext;
  @NonNull
  @Override
  public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    mContext=viewGroup.getContext();
    View view =  LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.category_item, viewGroup, false);
    return new CategoryViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
    categoryViewHolder.llMain.setOnClickListener(
        v -> mContext.startActivity(new Intent(mContext, ActivityProductList.class)));
  }

  @Override
  public int getItemCount() {
    return 2;
  }

  static class CategoryViewHolder extends RecyclerView.ViewHolder
  {
    @BindView(R.id.llMain) LinearLayout llMain;
    CategoryViewHolder(View view) {
      super(view);
      ButterKnife.bind(this,view);
    }
  }

}
