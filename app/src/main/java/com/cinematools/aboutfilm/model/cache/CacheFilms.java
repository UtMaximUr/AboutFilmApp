package com.cinematools.aboutfilm.model.cache;

import com.cinematools.aboutfilm.model.data.Film;

import java.util.ArrayList;
import java.util.List;

public class CacheFilms {

    private static final CacheFilms instance = new CacheFilms();
    private List<Film> films = new ArrayList<>();

    public static CacheFilms getInstance() {
        return instance;
    }

    private CacheFilms() {}


    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
