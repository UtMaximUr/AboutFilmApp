package com.cinematools.aboutfilm.ui.aboutfilm.view;

import com.cinematools.aboutfilm.ui.base.MvpView;


public interface AboutFilmView extends MvpView {

    void setImage(String url);

    void setLocalizedTitleFilm(String title);

    void setTitleFilm(String title);

    void setYear(String year);

    void setRating(String rating);

    void setDescription(String description);

}
