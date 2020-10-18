package com.cinematools.aboutfilm.model.rest.module;

import com.cinematools.aboutfilm.model.data.FilmsList;
import com.cinematools.aboutfilm.model.rest.OnLoadListener;

public interface RestModule {

    void getFilmsList(OnLoadListener<FilmsList> filmsListener);

}
