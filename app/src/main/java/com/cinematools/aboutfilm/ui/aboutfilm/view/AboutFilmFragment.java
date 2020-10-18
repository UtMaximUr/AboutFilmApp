package com.cinematools.aboutfilm.ui.aboutfilm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.cinematools.aboutfilm.R;
import com.cinematools.aboutfilm.model.data.Film;
import com.cinematools.aboutfilm.ui.aboutfilm.presenter.AboutFilmPresenter;
import com.cinematools.aboutfilm.ui.aboutfilm.presenter.AboutFilmPresenterFactory;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static com.cinematools.aboutfilm.ui.main.view.MainFragment.NAME_FRAGMENT;

public class AboutFilmFragment extends Fragment implements AboutFilmView {

    private Toolbar toolbar;
    private ImageView previewFilm;
    private TextView noPreviewFilm;
    private TextView titleFilm;
    private TextView yearFilm;
    private TextView ratingFilm;
    private TextView descriptionFilm;
    private TextView titleToolbar;

    private AboutFilmPresenter presenter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_about_films, container, false);

        toolbar = baseView.findViewById(R.id.toolbar);
        previewFilm = baseView.findViewById(R.id.preview_film);
        noPreviewFilm = baseView.findViewById(R.id.no_preview_film);
        titleFilm = baseView.findViewById(R.id.title_film);
        yearFilm = baseView.findViewById(R.id.year);
        ratingFilm = baseView.findViewById(R.id.rating);
        descriptionFilm = baseView.findViewById(R.id.description);

        presenter = AboutFilmPresenterFactory.createPresenter();
        presenter.onAttachView(this);

        toolbar.setNavigationOnClickListener(view ->
                Objects.requireNonNull(getActivity()).
                        getSupportFragmentManager().
                        popBackStack());

        titleToolbar = baseView.findViewById(R.id.scrolling_title);
        titleToolbar.setSelected(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            presenter.showInfoAboutFilm((Film) bundle.getSerializable(NAME_FRAGMENT));
        }

        return baseView;
    }

    @Override
    public void setImage(String url) {
        Picasso.get().load(url).into(previewFilm, new Callback() {
            @Override
            public void onSuccess() {
                noPreviewFilm.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                noPreviewFilm.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void setLocalizedTitleFilm(String title) {
        titleToolbar.setText(title);
    }

    @Override
    public void setTitleFilm(String title) {
        titleFilm.setText(title);
    }

    @Override
    public void setYear(String year) {
        yearFilm.setText(year);
    }

    @Override
    public void setRating(String rating) {
        ratingFilm.setText(rating);
    }

    @Override
    public void setDescription(String description) {
        descriptionFilm.setText(description);
    }
}
