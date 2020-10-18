package com.cinematools.aboutfilm.ui.main.presenter;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.cinematools.aboutfilm.model.cache.CacheFilms;
import com.cinematools.aboutfilm.model.data.Film;
import com.cinematools.aboutfilm.model.data.FilmsList;
import com.cinematools.aboutfilm.model.rest.OnLoadListener;
import com.cinematools.aboutfilm.ui.base.BasePresenter;
import com.cinematools.aboutfilm.ui.main.interactor.MainInteractor;
import com.cinematools.aboutfilm.ui.main.view.MainFragment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class MainPresenterImpl extends BasePresenter<MainFragment> implements MainPresenter, Comparator<Film> {

    private final MainInteractor interactor;
    private final HashSet<String> genres = new HashSet<>();

    MainPresenterImpl(MainInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void viewIsReady() {
        loadBotsList();
    }

    private void loadBotsList() {
        interactor.getFilmsList(new OnLoadListener<FilmsList>() {
            @Override
            public void onLoadSuccess(FilmsList result) {
                for (Film film : result.getFilms()) {
                    genres.addAll(film.getGenres());
                }

                getView().showGenresList(genres);
                getView().showFilmsList(result);
            }

            @Override
            public void onLoadFailure(String errorMessage) {
                Log.e("filmsTag", errorMessage);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Film> sortFilmsByName(List<Film> sortFilmsList){
        sortFilmsList.sort(new MainPresenterImpl(interactor));
        return sortFilmsList;
    }

    @Override
    public List<Film> sortFilmsByGenre(String genre){
        HashSet<Film> sortFilmsByGenreList = new HashSet<>();
        List<Film> cacheFilms = CacheFilms.getInstance().getFilms();
        for (Film film: cacheFilms) {
            for (int i = 0; i < film.getGenres().size(); i++) {
                if(film.getGenres().get(i).indexOf(genre) == 0){
                    sortFilmsByGenreList.add(film);
                }
            }
        }
        return new ArrayList<>(sortFilmsByGenreList);
    }

    @Override
    public int compare(Film firstFilm, Film secondFilm) {
        return firstFilm.getLocalizedName().compareTo(secondFilm.getLocalizedName());
    }
}
