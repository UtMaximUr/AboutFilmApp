package com.cinematools.aboutfilm.model.manager.factory;

import com.cinematools.aboutfilm.model.data.FilmsList;
import com.cinematools.aboutfilm.model.rest.OnLoadListener;

public interface FilmsManager {

    void getFilmsList(OnLoadListener<FilmsList> filmsListener);
}
