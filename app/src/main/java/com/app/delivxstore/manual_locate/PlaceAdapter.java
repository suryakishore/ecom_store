package com.app.delivxstore.manual_locate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.app.delivxstore.R;
import com.app.delivxstore.data.source.local.PreferencesHelper;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.PlaceAutoCompleteModel;
import com.app.delivxstore.utility.Utility;
import com.google.android.gms.common.data.DataBufferUtils;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>
        implements Filterable {
    private static final String TAG = "PlaceAutocompleteAdapter";
    private Activity mContext;

   // private ArrayList<PlaceAutoCompleteModel> mResultList;
    private PreferencesHelper sessionManager;
    private FontUtils appTypeface;
    private RecyclerView mRecyclerView;
    private static final CharacterStyle STYLE_BOLD = new StyleSpan(Typeface.NORMAL);

    private int keyIndex = 0;

    /**
     * <h2>PlaceAutoCompleteAdapter</h2>
     * This method is constructor
     *
     * @param context context of the activity from which this is called
     */

    /**
     * Current results returned by this adapter.
     */
    private ArrayList<AutocompletePrediction> mResultList;

    /**
     * Handles autocomplete requests.
     */
    private GeoDataClient mGeoDataClient;

    /**
     * The bounds used for Places Geo Data autocomplete API requests.
     */
    private  final LatLngBounds mBounds = new LatLngBounds(
            new LatLng(-34.041458, 150.790100), new LatLng(-33.682247, 151.383362));

    /**
     * The autocomplete filter used to restrict queries to a specific set of place types.
     */
    private AutocompleteFilter mPlaceFilter;

    public PlaceAdapter(Activity context) {

        mContext = context;
        sessionManager = new PreferencesHelper(context);
        appTypeface = new FontUtils(context);
        mGeoDataClient = Places.getGeoDataClient(mContext);

    }


    @Override
    public PlaceAdapter.PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = layoutInflater.inflate(R.layout.item_del_address, viewGroup, false);
        return new PlaceAdapter.PlaceViewHolder(convertView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PlaceAdapter.PlaceViewHolder mPredictionHolder, @SuppressLint("RecyclerView") final int i) {
        try {
            mPredictionHolder.tv_select_address.setText("" + mResultList.get(i).getFullText(STYLE_BOLD));
            mPredictionHolder.tv_select_address.setTypeface(appTypeface.regularFont());
            mPredictionHolder.tv_select_address_fav_title.setVisibility(View.GONE);
            //to add the click listener for the layout and notify
            mPredictionHolder.rl_drop_address_layout.setOnClickListener(v -> {
                try
                {
                    PlaceAutoCompleteModel model=new PlaceAutoCompleteModel();
                    model.setId(mResultList.get(i).getPlaceId());
                    if (mContext instanceof SearchLocationAct) {

                        ((SearchLocationAct) mContext).setAddress(model,mResultList.get(i).getFullText(STYLE_BOLD).toString());
                    }
                    /*if (mContext instanceof SetLocationAct) {
                        ((SetLocationAct) mContext).setAddress(model,mResultList.get(i).getFullText(STYLE_BOLD).toString());
                    }*/
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

            });
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (mResultList != null)
            return mResultList.size();
        else
            return 0;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;

    }

    /**
     * Returns the filter for the current set of autocomplete results.
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                // We need a separate list to store the results, since
                // this is run asynchronously.
                ArrayList<AutocompletePrediction> filterData = new ArrayList<>();

                // Skip the autocomplete query if no constraints are given.
                if (constraint != null) {
                    // Query the autocomplete API for the (constraint) search string.
                    filterData = getAutocomplete(constraint);
                }

                results.values = filterData;
                if (filterData != null) {
                    results.count = filterData.size();
                } else {
                    results.count = 0;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (results != null && results.count > 0) {
                    // The API returned at least one result, update the data.
                    mResultList = (ArrayList<AutocompletePrediction>) results.values;
                    notifyDataSetChanged();
                } else {
                    // The API did not return any results, invalidate the data set.
                  //  notifyDataSetInvalidated();
                }
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                // Override this method to display a readable result in the AutocompleteTextView
                // when clicked.
                if (resultValue instanceof AutocompletePrediction) {
                    return ((AutocompletePrediction) resultValue).getFullText(null);
                } else {
                    return super.convertResultToString(resultValue);
                }
            }
        };
    }

    private ArrayList<AutocompletePrediction> getAutocomplete(CharSequence constraint) {
        Utility.printLog( "Starting autocomplete query for: " + constraint);

        // Submit the query to the autocomplete API and retrieve a PendingResult that will
        // contain the results when the query completes.
        Task<AutocompletePredictionBufferResponse> results =
                mGeoDataClient.getAutocompletePredictions(constraint.toString(), mBounds,
                        mPlaceFilter);

        // This method should have been called off the main UI thread. Block and wait for at most
        // 60s for a result from the API.
        try {
            Tasks.await(results, 60, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }

        try {
            AutocompletePredictionBufferResponse autocompletePredictions = results.getResult();

            Utility.printLog("Query completed. Received " + autocompletePredictions.getCount()
                    + " predictions.");

            // Freeze the results immutable representation that can be stored safely.
            return DataBufferUtils.freezeAndClose(autocompletePredictions);
        } catch (RuntimeExecutionException e) {
            // If the query did not complete successfully return null

            Utility.printLog( "Error getting autocomplete prediction API call"+e);
            return null;
        }
    }

    /**
     * <h2>PlaceViewHolder</h2>
     * This method is used for defining the android widgets
     */
    class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_drop_fav_icon, iv_drop_clock_icon;
        RelativeLayout rl_drop_address_layout;
        TextView tv_select_address, tv_select_address_fav_title;

        PlaceViewHolder(View itemView) {
            super(itemView);
            iv_drop_clock_icon = itemView.findViewById(R.id.iv_select_address_left_icon);
            iv_drop_clock_icon.setImageResource(R.drawable.pin_icon);
            rl_drop_address_layout = itemView.findViewById(R.id.rl_select_address_layout);
            tv_select_address = itemView.findViewById(R.id.tv_select_address);
            tv_select_address_fav_title = itemView.findViewById(R.id.tv_select_address_fav_title);
            iv_drop_fav_icon = itemView.findViewById(R.id.iv_select_address_right_icon);
            iv_drop_fav_icon.setVisibility(View.GONE);
        }
    }
}

