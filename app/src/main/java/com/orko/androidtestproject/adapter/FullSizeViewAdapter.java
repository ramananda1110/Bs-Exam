package com.orko.androidtestproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.orko.androidtestproject.R;
import com.orko.androidtestproject.databinding.FullSizeAdapterListBinding;
import com.orko.androidtestproject.model.ImageData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FullSizeViewAdapter extends RecyclerView.Adapter<FullSizeViewAdapter.FullSizeViewHolder> {

    List<ImageData> dataArrayList;
    private LayoutInflater layoutInflater;


    public FullSizeViewAdapter(List<ImageData> dataList) {

        this.dataArrayList = dataList;

    }


    @NonNull
    @Override
    public FullSizeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        FullSizeAdapterListBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.full_size_adapter_list, viewGroup, false);

        return new FullSizeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FullSizeViewHolder holder, final int position) {

        ImageData data = dataArrayList.get(position);


        holder.bind(data);


    }


    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    static class FullSizeViewHolder extends RecyclerView.ViewHolder {

        private final FullSizeAdapterListBinding binding;

        FullSizeViewHolder(final FullSizeAdapterListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(ImageData data) {
            binding.ivShowImage.setVisibility(View.VISIBLE);
            Picasso.get().load(data.getDownloadUrl()).placeholder(R.drawable.ic_placeholder).into(binding.ivShowImage);


        }

    }


}
