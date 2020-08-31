package com.app.delivxstore.main.laundryitemPhotos;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.delivxstore.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class LaundryItemPhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LaundryItemPhotosContract.View view;
    private Activity activity;
    private ArrayList<LaundryItemPhotos> laundryItemPhotosList;
    private final int VIEW_CAMERA = 1;
    private final int VIEW_PHOTO = 2;
    private RequestOptions requestOptions;

    public LaundryItemPhotosAdapter(Activity activity, LaundryItemPhotosContract.View view, ArrayList<LaundryItemPhotos> laundryItemPhotosList) {
        this.activity = activity;
        this.view = view;
        this.laundryItemPhotosList = laundryItemPhotosList;
        requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.login_logo);
        requestOptions.error(R.drawable.login_logo);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType == VIEW_PHOTO) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selected_photo_item, viewGroup, false);
            return new LaundryPhotoViewHolder(itemView);

        } else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.camera_item, viewGroup, false);
            return new CameraViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        if (viewHolder instanceof LaundryPhotoViewHolder) {


            LaundryPhotoViewHolder laundryPhotoViewHolder = (LaundryPhotoViewHolder) viewHolder;

            LaundryItemPhotos laundryItemPhotos = laundryItemPhotosList.get(i);

            if (laundryItemPhotos.getFile() != null) {

                Glide.with(activity)
                        .setDefaultRequestOptions(requestOptions)
                        .applyDefaultRequestOptions(new RequestOptions().override(150, 150).centerCrop())
                        .load(laundryItemPhotos.getFile())
                        .into(laundryPhotoViewHolder.ivItem);

            } else {

                laundryPhotoViewHolder.progressBar.setVisibility(View.VISIBLE);

                Glide.with(activity)
                        // .setDefaultRequestOptions(requestOptions)
                        .applyDefaultRequestOptions(new RequestOptions().override(150, 150).centerCrop())
                        .load(laundryItemPhotos.getItemPhoto())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                                laundryPhotoViewHolder.progressBar.setVisibility(View.GONE);

                                return false;

                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                                laundryPhotoViewHolder.progressBar.setVisibility(View.GONE);

                                return false;

                            }

                        }).into(laundryPhotoViewHolder.ivItem);

                if (!laundryItemPhotos.isDel()) {
                    laundryPhotoViewHolder.ivCross.setVisibility(View.VISIBLE);

                    laundryPhotoViewHolder.ivCross.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            view.showAlertDialog(i);


                        }

                    });
                } else
                    laundryPhotoViewHolder.ivCross.setVisibility(View.GONE);

            }


        } else {

            CameraViewHolder cameraViewHolder = (CameraViewHolder) viewHolder;

            cameraViewHolder.cvInventoryItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    view.openImageSelectDialog();

                }
            });
        }

    }


    @Override
    public int getItemCount() {

        return laundryItemPhotosList.size();

    }

    @Override
    public int getItemViewType(int position) {
        if (laundryItemPhotosList.get(position).isCamera()) {
            return VIEW_CAMERA;
        }
        return VIEW_PHOTO;
    }

    public class LaundryPhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivCross)
        ImageView ivCross;
        @BindView(R.id.ivItem)
        ImageView ivItem;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public LaundryPhotoViewHolder(@NonNull View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }


    public class CameraViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivItem)
        ImageView ivItem;
        @BindView(R.id.cvInventoryItem)
        CardView cvInventoryItem;

        CameraViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);


        }
    }


}
