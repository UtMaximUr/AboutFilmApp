package com.cinematools.aboutfilm.model.rest.module;

import com.cinematools.aboutfilm.model.cache.CacheFilms;
import com.cinematools.aboutfilm.model.data.Film;
import com.cinematools.aboutfilm.model.data.FilmsList;
import com.cinematools.aboutfilm.model.rest.FilmApi;
import com.cinematools.aboutfilm.model.rest.OnLoadListener;
import com.cinematools.aboutfilm.model.rest.RestCallback;

import java.util.ArrayList;


public class FilmsRestModule implements RestModule {

    private final FilmApi filmApi;

    public FilmsRestModule(FilmApi filmApi) {
        this.filmApi = filmApi;
    }

    @Override
    public void getFilmsList(final OnLoadListener<FilmsList> filmsListener) {

        filmApi.getFilmsList().enqueue(new RestCallback<>(new OnLoadListener<FilmsList>() {
            @Override
            public void onLoadSuccess(FilmsList result) throws InterruptedException {
                ArrayList<Film> films = new ArrayList<>(result.getFilms());
                FilmsList botList = new FilmsList(films);
                CacheFilms.getInstance().setFilms(films);
                filmsListener.onLoadSuccess(botList);
            }

            @Override
            public void onLoadFailure(String errorMessage) {
                filmsListener.onLoadFailure(errorMessage);
            }
        }));
    }
}
