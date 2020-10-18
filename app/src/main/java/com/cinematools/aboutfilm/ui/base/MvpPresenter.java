package com.cinematools.aboutfilm.ui.base;

public interface MvpPresenter<V extends MvpView> {

    void onAttachView(V mvpView);

    void viewIsReady();

    void onDetachView();
}
