package com.app.delivxstore.main.history.attributesdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ActivityAttributesDetailsBinding;
import com.app.delivxstore.main.history.adapter.AttributeAdapter;
import com.app.delivxstore.main.history.model.AttributesItem;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.List;
import javax.inject.Inject;

/*shows screen for attributes details */
public class AttributesDetailsActivity extends DaggerAppCompatActivity
    implements AttributeDetailsContract.AttributesView {

  @Inject
  AttributeDetailsContract.Presenter presenter;
  private ActivityAttributesDetailsBinding mBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = ActivityAttributesDetailsBinding.inflate(LayoutInflater.from(this));
    setContentView(mBinding.getRoot());
    initViews();
    presenter.getBundleData(getIntent());
  }

  /*initialize views*/
  private void initViews() {
    mBinding.includeActionBar.ivBack.setImageResource(R.drawable.ic_close);
    mBinding.includeActionBar.ivBack.setColorFilter(ContextCompat.getColor(this, R.color.black),
        android.graphics.PorterDuff.Mode.MULTIPLY);
    mBinding.includeActionBar.tvTitle.setText(R.string.customization);
    mBinding.includeActionBar.ivBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(R.anim.stay_activity, R.anim.bottem_slide_up);
  }

  @Override
  public void setValues(List<AttributesItem> attributesItems) {
    AttributeAdapter adapter = new AttributeAdapter(attributesItems);
    mBinding.rvAttributes.setHasFixedSize(true);
    mBinding.rvAttributes.setLayoutManager(new LinearLayoutManager(this));
    mBinding.rvAttributes.setAdapter(adapter);
  }
}
