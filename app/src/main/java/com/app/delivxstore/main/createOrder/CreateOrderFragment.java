package com.app.delivxstore.main.createOrder;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.app.delivxstore.R;
import com.app.delivxstore.main.createOrder.productCategory.CategoryActivity;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;

public class CreateOrderFragment extends DaggerFragment {


  @BindView(R.id.btnSendAnything) AppCompatButton btnSendAnything;
  @Inject
  public CreateOrderFragment() {
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_create_order, container, false);
    ButterKnife.bind(this, view);

    return view ;

  }


  @OnClick({R.id.btnSendAnything})
  public void onClickListner(View view)
  {
    startActivity(new Intent(getActivity(), CategoryActivity.class));
  }
}
