package com.cinematools.aboutfilm.model.rest;


import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public final class RestCallback<T> implements Callback<T> {

    private final OnLoadListener<T> listener;

    public RestCallback(OnLoadListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, Response<T> response) {
        T body = response.body();

        if (response.isSuccessful()) {
            try {
                listener.onLoadSuccess(body);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            listener.onLoadFailure(response.message());
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, Throwable t) {
        listener.onLoadFailure(t.getMessage());
    }
}
