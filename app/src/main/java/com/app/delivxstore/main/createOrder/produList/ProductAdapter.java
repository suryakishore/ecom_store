package com.app.delivxstore.main.createOrder.produList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.delivxstore.R;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
{
  @NonNull
  @Override
  public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view =  LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.product_item, viewGroup, false);
    return new ProductViewHolder(view);

  }

  @Override
  public void onBindViewHolder(@NonNull ProductViewHolder categoryViewHolder, int i) {

  }

  @Override
  public int getItemCount() {
    return 2;
  }

  static class ProductViewHolder extends RecyclerView.ViewHolder
  {
    ProductViewHolder(View view)
    {
      super(view);
    }
  }

}
