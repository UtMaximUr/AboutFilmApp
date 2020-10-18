package com.cinematools.aboutfilm.model.manager.factory;

import android.content.Context;

import com.cinematools.aboutfilm.model.rest.FilmApi;
import com.cinematools.aboutfilm.model.manager.FilmsManagerImpl;
import com.cinematools.aboutfilm.model.rest.module.FilmsRestModule;
import com.cinematools.aboutfilm.model.rest.module.RestModule;
import com.cinematools.aboutfilm.model.rest.service.RestService;

public class FilmsFactory {

    public static FilmsManagerImpl createFilmsManager(Context context) {
        RestService restService = new RestService();
        RestModule restModule = new FilmsRestModule(restService.getRetrofit().create(FilmApi.class));

        return new FilmsManagerImpl(restModule);
    }
}
