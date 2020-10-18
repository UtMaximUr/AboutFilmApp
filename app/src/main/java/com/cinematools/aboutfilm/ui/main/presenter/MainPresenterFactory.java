package com.cinematools.aboutfilm.ui.main.presenter;

import android.content.Context;

import com.cinematools.aboutfilm.App;
import com.cinematools.aboutfilm.ui.main.interactor.MainInteractor;
import com.cinematools.aboutfilm.ui.main.interactor.MainInteractorImpl;

public class MainPresenterFactory {

    public static MainPresenter createPresenter(Context context) {
        MainInteractor interactor = new MainInteractorImpl(App.getManager(context));
        return new MainPresenterImpl(interactor);
    }
}
