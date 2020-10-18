package com.cinematools.aboutfilm.model.rest;

import com.cinematools.aboutfilm.constants.Constants;
import com.cinematools.aboutfilm.model.data.FilmsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmApi {
    @GET(Constants.GET)
    Call<FilmsList> getFilmsList();
}
