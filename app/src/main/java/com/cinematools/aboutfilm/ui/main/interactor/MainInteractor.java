package com.cinematools.aboutfilm.ui.main.interactor;

import com.cinematools.aboutfilm.model.data.FilmsList;
import com.cinematools.aboutfilm.model.rest.OnLoadListener;

public interface MainInteractor {

    void getFilmsList(OnLoadListener<FilmsList> botListener);
}
