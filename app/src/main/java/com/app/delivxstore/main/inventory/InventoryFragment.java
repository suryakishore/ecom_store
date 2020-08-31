package com.app.delivxstore.main.inventory;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.delivxstore.R;
import com.app.delivxstore.main.inventory.model.CategoryProduct;
import com.app.delivxstore.main.inventory.model.InventaryProducts;
import com.app.delivxstore.main.inventory.model.InventoryData;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;


public class InventoryFragment extends DaggerFragment implements InventoryAdapter.IntentoryItemClickListener, InventoryContract.View, InventoryFilterFragment.InventoryInteractionListener {

    private static final String TAG = "Mura Test";
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.rvInventoryItems)
    RecyclerView rvInventoryItems;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Inject
    FontUtils fontUtils;

    @Inject
    InventoryFilterFragment inventoryFilterFragment;

    @Inject
    InventoryContract.Presenter presenter;

    private InventoryAdapter inventoryAdapter;
    private ArrayList<InventoryData> inventaryDatas;
    private ArrayList<InventaryProducts> inventaryProducts;
    private ArrayList<InventaryProducts> inventaryProductsTemp;

    private ArrayList<CategoryProduct> categoryProducts;
    private ArrayList<String> categoryNames;
    private HashMap<String, Integer> selectedProductsWithStatus;
    private boolean isFirstTimeCheckClicking = true;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public static InventoryFragment newInstance() {
        return new InventoryFragment();
    }

    @Inject
    public InventoryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
        ButterKnife.bind(this, rootView);
        presenter.attachView(this);
        init();
        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    private void init() {

        inventaryDatas = new ArrayList<>();
        inventaryProducts = new ArrayList<>();
        inventaryProductsTemp=new ArrayList<>();
        categoryProducts = new ArrayList<>();
        categoryNames = new ArrayList<>();
        selectedProductsWithStatus = new HashMap<>();

        GridLayoutManager layoutManager;
        if (Utility.isTablet(getActivity()))
            layoutManager = new GridLayoutManager(getActivity(), 7, GridLayoutManager.VERTICAL, false);
        else
            layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

        inventoryAdapter = new InventoryAdapter(getActivity(), inventaryProducts, fontUtils.regularFont(), this);

        rvInventoryItems.setLayoutManager(layoutManager);

        rvInventoryItems.setAdapter(inventoryAdapter);

        presenter.getInventory();

    }

    @Override
    public void onItemClicked(String id, int status, int position) {
        presenter.setInventory(id, status, position);

    }

    @Override
    public void setInventaryData(ArrayList<InventoryData> inventaryDatas, ArrayList<CategoryProduct> categoryProducts,
                                 ArrayList<String> categoryNames) {

        inventoryAdapter.notifyDataSetChanged();

        Bundle args = new Bundle();
        args.putStringArrayList(InventoryFilterFragment.ARG_PARAM1, categoryNames);
        inventoryFilterFragment.setArguments(args);

        getChildFragmentManager().beginTransaction().replace(R.id.fragmentInvenoryFilter, inventoryFilterFragment).commit();

    }

    @Override
    public void setInvenatary(ArrayList<InventaryProducts> invenatary) {

        inventaryProducts.clear();

        inventaryProducts.addAll(invenatary);

        inventaryProductsTemp.addAll(invenatary);

        inventoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeInventary(int position, int status) {

        InventaryProducts inventaryPro = inventaryProducts.get(position);
        inventaryPro.setStatus(String.valueOf(status));
       inventoryAdapter.notifyItemChanged(position);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu_inventory, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            int searchImgId = androidx.appcompat.R.id.search_button; // I used the explicit layout ID of searchview's ImageView
            ImageView v = (ImageView) searchView.findViewById(searchImgId);
            v.setImageResource(R.drawable.ic_search_inventory);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText.equals("")) {
                        inventaryProducts.clear();
                        inventaryProducts.addAll(inventaryProductsTemp);
                        inventoryAdapter.notifyDataSetChanged();
                    } else {
                        inventaryProducts.clear();
                        newText = newText.toLowerCase();
                        for (InventaryProducts item : inventaryProductsTemp) {
                            if ((item.getProductName().toLowerCase().contains(newText))||(item.getChildProductId().toLowerCase().contains(newText))) {
                                inventaryProducts.add(item);
                            }
                        }
                        inventoryAdapter.notifyDataSetChanged();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                presenter.updateInventory(selectedProductsWithStatus);
                break;

            case R.id.menu_filter:
                closeAndOpenDrawer();
                break;
        }
        return true;
    }

    private void closeAndOpenDrawer() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
    }

    @Override
    public void onFilterClicked(int categoryPosition, boolean isSelected) {
        /*if(isFirstTimeCheckClicking)
        {
            categoryProducts.clear();
            categoryProductsTemp.clear();
            categoryProducts.addAll(inventaryDatas.get(categoryPosition).getCategoryProducts());
            categoryProductsTemp.addAll(inventaryDatas.get(categoryPosition).getCategoryProducts());
            isFirstTimeCheckClicking = false;
        }
        else if(isSelected)
        {
            categoryProducts.addAll(inventaryDatas.get(categoryPosition).getCategoryProducts());
            categoryProductsTemp.clear();
            categoryProductsTemp.addAll(categoryProducts);
            categoryProducts.clear();
            for(InventoryData inventoryData : inventaryDatas)
            {
                if(categoryProductsTemp.containsAll(inventoryData.getCategoryProducts()))
                {
                    categoryProducts.addAll(inventoryData.getCategoryProducts());
                }
            }
        }
        else
        {
            categoryProducts.removeAll(inventaryDatas.get(categoryPosition).getCategoryProducts());
            categoryProductsTemp.removeAll(inventaryDatas.get(categoryPosition).getCategoryProducts());
            if(categoryProducts.size() == 0)
            {
                onFilterClear();
            }
        }
        inventoryAdapter.notifyDataSetChanged();*/
    }

    @Override
    public void onFilterClear() {
        isFirstTimeCheckClicking = true;
        categoryProducts.clear();
       /* for (InventoryData inventoryData : inventaryDatas)
        {
            categoryProducts.addAll(inventoryData.getCategoryProducts());
        }*/
        inventoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }


}
