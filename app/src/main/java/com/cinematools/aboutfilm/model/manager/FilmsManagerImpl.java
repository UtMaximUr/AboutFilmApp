package com.cinematools.aboutfilm.model.manager;

import com.cinematools.aboutfilm.model.data.FilmsList;
import com.cinematools.aboutfilm.model.rest.OnLoadListener;
import com.cinematools.aboutfilm.model.manager.factory.FilmsManager;
import com.cinematools.aboutfilm.model.rest.module.RestModule;

public class FilmsManagerImpl implements FilmsManager {

    private final RestModule restModule;

    public FilmsManagerImpl(RestModule restModule) {
        this.restModule = restModule;
    }

    @Override
    public void getFilmsList(OnLoadListener<FilmsList> filmsListener) {
        restModule.getFilmsList(filmsListener);
    }
}
