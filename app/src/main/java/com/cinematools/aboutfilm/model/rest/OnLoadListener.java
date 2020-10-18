package com.cinematools.aboutfilm.model.rest;

public interface OnLoadListener<T> {

    void onLoadSuccess(T result) throws InterruptedException;
    void onLoadFailure(String errorMessage);

}
