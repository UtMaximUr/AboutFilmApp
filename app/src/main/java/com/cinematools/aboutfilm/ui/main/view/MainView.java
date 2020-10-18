package com.cinematools.aboutfilm.ui.main.view;

import com.cinematools.aboutfilm.model.data.FilmsList;
import com.cinematools.aboutfilm.ui.base.MvpView;

import java.util.HashSet;


public interface MainView extends MvpView {

    void showGenresList(HashSet<String> genresList);

    void showFilmsList(FilmsList filmsList);

}
