package com.cinematools.aboutfilm.ui.main.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinematools.aboutfilm.R;
import com.cinematools.aboutfilm.model.data.FilmsList;
import com.cinematools.aboutfilm.ui.aboutfilm.view.AboutFilmFragment;
import com.cinematools.aboutfilm.ui.main.adapter.RecycleViewAdapter;
import com.cinematools.aboutfilm.ui.main.presenter.MainPresenter;
import com.cinematools.aboutfilm.ui.main.presenter.MainPresenterFactory;

import java.util.HashSet;
import java.util.Objects;

public class MainFragment extends Fragment implements MainView {

    private MainPresenter presenter;
    private RecycleViewAdapter adapter;
    private RecyclerView genresAndFilmsList;

    public static final String NAME_FRAGMENT  = "filmFragment";

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_main, container, false);

        presenter = MainPresenterFactory.createPresenter(baseView.getContext());
        presenter.onAttachView(this);

        adapter = new RecycleViewAdapter();

        genresAndFilmsList = baseView.findViewById(R.id.genres_and_films_list);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position < adapter.getGenresCount()){
                    return 2;
                }else {
                    return 1;
                }
            }
        });
        genresAndFilmsList.setLayoutManager(manager);
        genresAndFilmsList.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickGenres(View itemView, int position) {
                adapter.setFilmsList(presenter.sortFilmsByGenre(adapter.getGenresList().get(position)));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemClickFilm(View itemView, int position) {
                AboutFilmFragment aboutFilmFragment = new AboutFilmFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(NAME_FRAGMENT, adapter.getFilmsList().get(position));
                aboutFilmFragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).
                        getSupportFragmentManager().
                        beginTransaction().
                        add(R.id.containerFragment, aboutFilmFragment).addToBackStack(NAME_FRAGMENT).
                        commit();
            }
        });

        return baseView;
    }

    @Override
    public void showGenresList(HashSet<String> genresList) {
        adapter.setGenresList(genresList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showFilmsList(FilmsList filmsList) {
        adapter.setFilmsList(presenter.sortFilmsByName(filmsList.getFilms()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewIsReady();
    }
}
