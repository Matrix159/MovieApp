package com.matrixprogramming.controller;

import com.matrixprogramming.model.DiscoverModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Eldridge on 2/14/2017.
 */
public interface IMovieAPI {

    // https://api.themoviedb.org/3/discover/movie?api_key=###&with_release_type=2|3&region=US
    @GET("discover/movie")
    Call<DiscoverModel> discover(
            @Query("language") String language,
            @Query("sort_by") String sortBy,
            @Query("with_release_type") String types,
            @Query("region") String region,
            @Query("include_adult") boolean includeAdult,
            @Query("page") int page,
            //@Query("primary_release_year") String releaseYear,
            @Query("primary_release_date.gte") String releaseGreaterEqualThan,
            @Query("primary_release_date.lte") String releaseLessEqualThan);
            //@Query("vote_average.gte") String voteAvgGreaterEqualThan,
            //@Query("vote_average.lte") String voteAvgLesserEqualThan);

}
