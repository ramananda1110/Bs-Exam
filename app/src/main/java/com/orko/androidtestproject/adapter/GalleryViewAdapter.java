package com.orko.androidtestproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.orko.androidtestproject.R;
import com.orko.androidtestproject.databinding.GalleryAdapterListBinding;
import com.orko.androidtestproject.model.ImageData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryViewAdapter extends RecyclerView.Adapter<GalleryViewAdapter.GalleryViewHolder> {

    List<ImageData> dataArrayList;
    private LayoutInflater layoutInflater;
    OnGalleryImageListener onGalleryImageListener;


    public GalleryViewAdapter(List<ImageData> dataList, OnGalleryImageListener onGalleryImageListener) {

        this.dataArrayList = dataList;
        this.onGalleryImageListener = onGalleryImageListener;
    }


    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        GalleryAdapterListBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.gallery_adapter_list, viewGroup, false);

        return new GalleryViewHolder(binding, onGalleryImageListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, final int position) {

        ImageData data = dataArrayList.get(position);


        holder.bind(data);

        holder.itemView.setOnClickListener(v -> {
            onGalleryImageListener.onGalleryImagePress(data);
        });
    }


    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    static class GalleryViewHolder extends RecyclerView.ViewHolder {

        private final GalleryAdapterListBinding binding;
        OnGalleryImageListener onFileListener;

        GalleryViewHolder(final GalleryAdapterListBinding binding, OnGalleryImageListener onGalleryImageListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onFileListener = onGalleryImageListener;

        }

        void bind(ImageData data) {
            binding.ivShowImage.setVisibility(View.VISIBLE);
            Picasso.get().load(data.getDownloadUrl()).placeholder(R.drawable.ic_placeholder).fit()
                    .centerCrop().into(binding.ivShowImage);

            binding.tvAuthorName.setText(data.getAuthor());

        }

    }


    public interface OnGalleryImageListener {
        void onGalleryImagePress(ImageData imageData);
    }
}
