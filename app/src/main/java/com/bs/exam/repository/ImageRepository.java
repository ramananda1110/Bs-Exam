package com.bs.exam.repository;

import androidx.lifecycle.MutableLiveData;

import com.bs.exam.model.ImageData;
import com.bs.exam.network.Api;
import com.bs.exam.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {

    private final MutableLiveData<Boolean> isUpdated = new MutableLiveData<>();

    public MutableLiveData<List<ImageData>> getImageList() {

        MutableLiveData<List<ImageData>> responseMutableLiveData = new MutableLiveData<>();

        isUpdated.setValue(true);

        final ApiService apiReader = Api.getApiService();

        Call<List<ImageData>> list = apiReader.getImageList();

        list.enqueue(new Callback<List<ImageData>>() {
            @Override
            public void onResponse(Call<List<ImageData>> call, Response<List<ImageData>> response) {
                assert response.body() != null;
                responseMutableLiveData.postValue(response.body());
                isUpdated.setValue(false);
            }

            @Override
            public void onFailure(Call<List<ImageData>> call, Throwable t) {

            }
        });


        return responseMutableLiveData;
    }

    public MutableLiveData<Boolean> getIsUpdated() {
        return isUpdated;
    }
}
