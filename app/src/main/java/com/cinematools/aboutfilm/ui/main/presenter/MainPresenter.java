package com.cinematools.aboutfilm.ui.main.presenter;

import com.cinematools.aboutfilm.model.data.Film;
import com.cinematools.aboutfilm.ui.base.MvpPresenter;
import com.cinematools.aboutfilm.ui.main.view.MainFragment;

import java.util.List;

public interface MainPresenter extends MvpPresenter<MainFragment> {

    List<Film> sortFilmsByName(List<Film> films);

    List<Film> sortFilmsByGenre(String genre);
}
