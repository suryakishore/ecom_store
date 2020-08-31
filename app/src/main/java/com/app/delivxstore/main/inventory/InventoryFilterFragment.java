package com.app.delivxstore.main.inventory;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.main.inventory.model.CategoryFilter;
import com.app.delivxstore.utility.FontUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InventoryFilterFragment.InventoryInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InventoryFilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFilterFragment extends DaggerFragment implements InventoryFilterAdapter.IntentoryFilterClickListener {

    public static final String ARG_PARAM1 = "param1";
    private ArrayList<String> category;
    private ArrayList<CategoryFilter> categoryFilters;

    private InventoryInteractionListener mListener;

    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;

    @BindView(R.id.tvFilter)
    TextView tvFilter;

    @BindView(R.id.tvClear)
    TextView tvClear;

    @Inject
    FontUtils fontUtils;

    private InventoryFilterAdapter adapter;

    @Inject
    public InventoryFilterFragment() {
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param category Parameter 1.
     * @return A new instance of fragment InventoryFilterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InventoryFilterFragment newInstance(ArrayList<String> category) {
        InventoryFilterFragment fragment = new InventoryFilterFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Test", "onCreate: "+getArguments() );
        Log.d("Test", "onCreate: "+getArguments().getStringArrayList(ARG_PARAM1));

        if (getArguments() != null && getArguments().getStringArrayList(ARG_PARAM1) != null) {
            category = getArguments().getStringArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_inventory_filter, container, false);
        ButterKnife.bind(this,rootView);
        init();
        return rootView;
    }

    private void init()
    {
        Typeface fontMedium = fontUtils.mediumFont();
        tvFilter.setTypeface(fontMedium);
        tvClear.setTypeface(fontMedium);

        categoryFilters = new ArrayList<>();
        for(String name : category)
        {
            CategoryFilter categoryFilter = new CategoryFilter();
            categoryFilter.setCategory(name);
            categoryFilters.add(categoryFilter);
        }
        adapter = new InventoryFilterAdapter(getActivity(), categoryFilters, fontUtils.regularFont(), this);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCategory.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof InventoryInteractionListener) {
            mListener = (InventoryInteractionListener) getParentFragment();
        } else {
            throw new RuntimeException("The parent fragment must implement OnChildFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFilterClicked(int categoryPosition, boolean isSelected){
        if (mListener != null) {
            mListener.onFilterClicked(categoryPosition, isSelected);
        }
    }

    @OnClick({R.id.tvClear})
    public void clickEvent()
    {
        if (mListener != null) {
            for (CategoryFilter filter : categoryFilters)
            {
                filter.setSelected(false);
            }
            adapter.notifyDataSetChanged();
            mListener.onFilterClear();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface InventoryInteractionListener {
        void onFilterClicked(int categoryPosition, boolean isSelected);
        void onFilterClear();
    }
}
