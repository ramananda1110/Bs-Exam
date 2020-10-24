package com.orko.androidtestproject.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.orko.androidtestproject.R;
import com.orko.androidtestproject.adapter.FullSizeViewAdapter;
import com.orko.androidtestproject.databinding.ImageViewFragmentBinding;
import com.orko.androidtestproject.model.ImageData;
import com.orko.androidtestproject.utils.NetworkState;
import com.orko.androidtestproject.viewModel.ImageViewModel;

import java.util.List;


public class FullViewFragment extends Fragment {

    ImageViewFragmentBinding binding;
    ImageViewModel viewModel;
    private Context mContext;

    private FullSizeViewAdapter fullSizeViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.image_view_fragment, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        mContext = getActivity();


        viewModel.getUpdateStatus().observe(getActivity(), status -> {
            binding.setProgress(status);
        });

        if (NetworkState.isNetworkAvailable(mContext)) {

            viewModel.getImageList().observe(getActivity(), response -> {

                Log.e("TagData", "size of image " + response.size());

                getImageData(response);


            });
        } else {

            Toast.makeText(mContext, getString(R.string.check_internet_connection), Toast.LENGTH_LONG).show();
        }

        return view;
    }


    private void getImageData(List<ImageData> mDataList) {


        fullSizeViewAdapter = new FullSizeViewAdapter(mDataList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        binding.recyclerView.setLayoutManager(layoutManager);

        binding.recyclerView.setAdapter(fullSizeViewAdapter);

    }

}
