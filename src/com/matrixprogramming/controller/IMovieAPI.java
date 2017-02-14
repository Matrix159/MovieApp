package com.matrixprogramming.controller;

import com.matrixprogramming.model.DiscoverModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Eldridge on 2/14/2017.
 */
public interface IMovieAPI {

    @GET("discover/movie")
    Call<DiscoverModel> discover();

}
