package com.bs.exam.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bs.exam.model.ImageData;
import com.bs.exam.repository.ImageRepository;

import java.util.List;


public class ImageViewModel extends AndroidViewModel {

    private ImageRepository repository;

    public ImageViewModel(@NonNull Application application) {
        super(application);
        repository = new ImageRepository();

    }

    public LiveData<Boolean> getUpdateStatus() {
        return repository.getIsUpdated();
    }


    public MutableLiveData<List<ImageData>> getImageList() {
        return repository.getImageList();
    }


}
