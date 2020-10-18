package com.cinematools.aboutfilm.ui.aboutfilm.presenter;

import com.cinematools.aboutfilm.model.data.Film;
import com.cinematools.aboutfilm.ui.aboutfilm.view.AboutFilmFragment;
import com.cinematools.aboutfilm.ui.base.MvpPresenter;

public interface AboutFilmPresenter extends MvpPresenter<AboutFilmFragment> {

    void showInfoAboutFilm(Film film);
}
