package com.cinematools.aboutfilm.ui.aboutfilm.presenter;

import com.cinematools.aboutfilm.R;
import com.cinematools.aboutfilm.model.data.Film;
import com.cinematools.aboutfilm.ui.aboutfilm.view.AboutFilmFragment;
import com.cinematools.aboutfilm.ui.base.BasePresenter;


public class AboutFilmPresenterImpl extends BasePresenter<AboutFilmFragment> implements AboutFilmPresenter {

    private String localizedTitle;
    private String title;
    private String year;
    private String rating;
    private String description;

    AboutFilmPresenterImpl() {}

    @Override
    public void viewIsReady() {}


    @Override
    public void showInfoAboutFilm(Film film) {

        if (film.getLocalizedName() != null) {
            localizedTitle = film.getLocalizedName();
        } else {
            localizedTitle = film.getName();
        }
        getView().setLocalizedTitleFilm(localizedTitle);

        if (film.getName() != null) {
            title = film.getName();
        } else {
            title = film.getLocalizedName();
        }
        getView().setTitleFilm(title);

        if (film.getYear() != null) {
            year = getView().getString(R.string.year) + film.getYear();
        } else {
            year = getView().getString(R.string.year) + getView().getString(R.string.no_data);
        }
        getView().setYear(year);

        if (film.getRating() != null) {
            rating = getView().getString(R.string.rating) + film.getRating();
        } else {
            rating = getView().getString(R.string.rating) + getView().getString(R.string.no_data);
        }
        getView().setRating(rating);

        if (film.getDescription() != null) {
            description = film.getDescription();
        } else {
            description = getView().getString(R.string.no_description);
        }
        getView().setDescription(description);

        getView().setImage(film.getImage_url());
    }
}
