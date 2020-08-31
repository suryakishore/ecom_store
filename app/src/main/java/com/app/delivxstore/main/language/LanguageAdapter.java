package com.app.delivxstore.main.language;

import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.databinding.ItemLanguageBinding;
import com.app.delivxstore.main.language.model.LanguageList;
import java.util.ArrayList;

/*binds language data to adapter*/
public class LanguageAdapter extends RecyclerView.Adapter implements View.OnClickListener {
  private final LanguageFragment mLanguageFragment;
  private final ArrayList<LanguageList> mLanguageList;
  private int mPosition = -1;

  LanguageAdapter(LanguageFragment languageFragment,
      ArrayList<LanguageList> languageList) {
    mLanguageFragment = languageFragment;
    mLanguageList = languageList;
  }

  public void setPosition(int position) {
    mPosition = position;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemLanguageBinding itemLanguageBinding =
        ItemLanguageBinding.inflate(LayoutInflater.from(parent.getContext()));
    return new LanguageViewHolder(itemLanguageBinding);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof LanguageViewHolder) {
      LanguageViewHolder viewHolder = (LanguageViewHolder) holder;
      viewHolder.binding.rbSingleOperator.setChecked(
          mLanguageList.get(holder.getAdapterPosition()).isSelected());
      viewHolder.binding.tvLanguage.setText(mLanguageList.get(position).getLanguageName());
      viewHolder.binding.tvLanguage.setOnClickListener(view -> {
        setView(holder.getAdapterPosition());
        mPosition = holder.getAdapterPosition();
        notifyDataSetChanged();
      });
      viewHolder.binding.clItem.setOnClickListener(view -> {
        setView(holder.getAdapterPosition());
        mPosition = holder.getAdapterPosition();
        notifyDataSetChanged();
      });
      viewHolder.binding.rbSingleOperator.setOnClickListener(view -> {
        setView(holder.getAdapterPosition());
        mPosition = holder.getAdapterPosition();
        notifyDataSetChanged();
      });
    }
  }

  /*set selected view*/
  private void setView(int position) {
    mLanguageList.get(position).setSelected(!mLanguageList.get(position).isSelected());
    if (mLanguageList.get(position).isSelected()) {
      removeOtherSelectedViews(mLanguageList, position);
    }
  }

  /*remove other selected views*/
  private void removeOtherSelectedViews(ArrayList<LanguageList> languageList, int position) {
    for (int i = 0; i < languageList.size(); i++) {
      if (i != position) {
        languageList.get(i).setSelected(false);
      }
    }
  }

  @Override
  public int getItemCount() {
    return mLanguageList == null ? ZERO : mLanguageList.size();
  }

  @Override
  public void onClick(View view) {
    if (mPosition!=-1)
    mLanguageFragment.setLanguage(mLanguageList.get(mPosition));
  }

  static class LanguageViewHolder extends RecyclerView.ViewHolder {
    ItemLanguageBinding binding;

    LanguageViewHolder(ItemLanguageBinding itemLanguageBinding) {
      super(itemLanguageBinding.getRoot());
      binding = itemLanguageBinding;
    }
  }
}
