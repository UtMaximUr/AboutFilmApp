package com.cinematools.aboutfilm.ui.main.interactor;

import com.cinematools.aboutfilm.model.data.FilmsList;
import com.cinematools.aboutfilm.model.rest.OnLoadListener;
import com.cinematools.aboutfilm.model.manager.factory.FilmsManager;

public class MainInteractorImpl implements MainInteractor {

    private final FilmsManager filmsManager;

    public MainInteractorImpl(FilmsManager filmsManager) {
        this.filmsManager = filmsManager;
    }

    @Override
    public void getFilmsList(OnLoadListener<FilmsList> botListener) {
        filmsManager.getFilmsList(botListener);
    }
}
