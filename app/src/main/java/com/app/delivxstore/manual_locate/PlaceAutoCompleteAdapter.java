package com.app.delivxstore.manual_locate;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.app.delivxstore.BuildConfig;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.local.PreferencesHelper;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.PlaceAutoCompleteModel;
import com.app.delivxstore.utility.Utility;
import com.app.delivxstore.utility.VariableConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.app.delivxstore.utility.VariableConstants.OUT_JSON;
import static com.app.delivxstore.utility.VariableConstants.TYPE_AUTOCOMPLETE;


/**
 * <h1>PlaceAutoComplete_Adapter</h1>
 * This class is used to provide the PlaceAutoComplete screen, where we can see our Address.
 *
 * @author 3embed
 * @since 3 Jan 2017.
 */
public class PlaceAutoCompleteAdapter extends RecyclerView.Adapter<PlaceAutoCompleteAdapter.PlaceViewHolder>
        implements Filterable {
    private static final String TAG = "PlaceAutocompleteAdapter";
    private Activity mContext;

    private ArrayList<PlaceAutoCompleteModel> mResultList;
    private ArrayList<PlaceAutoCompleteModel> mResultListTemp;
    private PreferencesHelper sessionManager;
    private FontUtils appTypeface;
    private RecyclerView mRecyclerView;

    private int keyIndex = 0;

    /**
     * <h2>PlaceAutoCompleteAdapter</h2>
     * This method is constructor
     *
     * @param context context of the activity from which this is called
     */
    public PlaceAutoCompleteAdapter(Activity context) {
        mContext = context;
        sessionManager = new PreferencesHelper(context);
        appTypeface = new FontUtils(context);
    }


    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = layoutInflater.inflate(R.layout.item_del_address, viewGroup, false);
        return new PlaceViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder mPredictionHolder, final int i) {
        try {
            mPredictionHolder.tv_select_address.setText("" + mResultList.get(i).getAddress());
            mPredictionHolder.tv_select_address.setTypeface(appTypeface.regularFont());
            mPredictionHolder.tv_select_address_fav_title.setVisibility(View.GONE);
            //to add the click listener for the layout and notify
            mPredictionHolder.rl_drop_address_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (mContext instanceof SearchLocationAct) {
                            ((SearchLocationAct) mContext).setAddress(mResultList.get(i), mResultList.get(i).getAddress());

                        }
                        /*if (mContext instanceof SetLocationAct) {
                            ((SetLocationAct) mContext).setAddress(mResultList.get(i),mResultList.get(i).getAddress());
                        }*/
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }

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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                // Skip the autocomplete query if no constraints are given.
                if (constraint != null) {
                    // Query the autocomplete API for the (constraint) search string.
                    Utility.printLog(TAG + " : " + constraint);
                    mResultList = autocomplete(constraint.toString());
                    if (mResultList != null) {
                        // The API successfully returned results.
                        results.values = mResultList;
                        results.count = mResultList.size();
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    // The API returned at least one result, update the data.
                    try {
                        if (mRecyclerView != null)
                            mRecyclerView.getRecycledViewPool().clear();
                        notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };
    }

    /**
     * <h2>autocomplete</h2>
     * This method will filter the address with the input given to it
     *
     * @param input filter given to auto complete
     * @return returns the list of addresses filtered
     */
    private ArrayList<PlaceAutoCompleteModel> autocomplete(String input) {
        ArrayList resultList = null;
        ArrayList<PlaceAutoCompleteModel> auto_complete_pojo_list = new ArrayList<PlaceAutoCompleteModel>();
        PlaceAutoCompleteModel auto_complete_pojo;
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            if (sessionManager.getGoogleMApKey() != null) {
                String key = sessionManager.getGoogleMApKey();
                URL url = getUrlForGoogleAutoComplete(input, key);
                conn = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                // Load the results into a StringBuilder
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
            } else {
                String key = BuildConfig.SERVER_KEY;

                URL url = getUrlForGoogleAutoComplete(input, key);
                conn = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                // Load the results into a StringBuilder
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
            }
            Utility.printLog(TAG + " ,address: " + jsonResults);
        } catch (MalformedURLException e) {
            Utility.printLog(TAG + "Error processing Places API URL" + e);
            return resultList;
        } catch (IOException e) {
            Utility.printLog(TAG + "Error connecting to Places API" + e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            //if status is ok then set all the address in adapter else rotate the keys
            if (jsonObj.getString("status").equals("OK")) {
                JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");
                Utility.printLog(TAG + " ,address: " + jsonObj + " -----, " + predsJsonArray);
                // Extract the Place descriptions from the results
                resultList = new ArrayList(predsJsonArray.length());
                for (int i = 0; i < predsJsonArray.length(); i++) {
                    auto_complete_pojo = new PlaceAutoCompleteModel();
                    Utility.printLog(TAG + " response:" + predsJsonArray.getJSONObject(i).getString("description"));
                    resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
                    auto_complete_pojo.setAddress(predsJsonArray.getJSONObject(i).getString("description"));
                    auto_complete_pojo.setRef_key(predsJsonArray.getJSONObject(i).getString("reference"));
                    auto_complete_pojo.setId(predsJsonArray.getJSONObject(i).getString("place_id"));
                    auto_complete_pojo_list.add(i, auto_complete_pojo);
                }
            } else if (jsonObj.getString("status").equals("OVER_QUERY_LIMIT")) {
                keyIndex++;
                if (keyIndex < 5)
                    autocomplete(input);
            } else if (jsonObj.getString("status").equals("REQUEST_DENIED")) {
                keyIndex++;
                if (keyIndex < 5)
                    autocomplete(input);
            }
        } catch (JSONException e) {
            Utility.printLog(TAG + " ,address exception : " + e);
            e.printStackTrace();
        }
        return auto_complete_pojo_list;
    }


    /**
     * <h2>getUrlForGoogleAutoComplete</h2>
     * This method is used to create the link for google API
     *
     * @param input input for the address filter
     * @return returns the URL for the google API
     */
    private URL getUrlForGoogleAutoComplete(String input, String serverKey) {

        URL url = null;
        try {

            StringBuilder sb = new StringBuilder(VariableConstants.PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=").append(serverKey);

            if (sessionManager.getCurrentLat() != 0.0 && sessionManager.getCurrentLong() != 0.0) {
                sb.append("&location=")
                        .append(sessionManager.getCurrentLat())
                        .append(",").append(sessionManager.getCurrentLong())
                        .append("&radius=500&amplanguage=en");
            }


            sb.append("&input=").append(URLEncoder.encode(input, "utf8"));
            Utility.printLog(TAG + " urL : " + sb.toString());
            url = new URL(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
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
