package com.matrixprogramming.controller;

import com.matrixprogramming.model.DiscoverModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/***
 * Created by Eldridge on 2/14/2017.
 */
public interface IMovieAPI {
    /***
     * Using the API to get the date we will be using for the movie.
     * @param language Language of the movie
     * @param sortBy Way to sort the movie
     * @param types Type of movie
     * @param region Region the movie is in
     * @param originLanguage Original language of the movie
     * @param includeAdult If the movie is adult only,  or not
     * @param page Page of the movie
     * @param releaseGreaterEqualThan Release date of movie
     * @param releaseLessEqualThan Release date of movie
     * @return Movie object with everything intact
     */
    @GET("discover/movie")
    Call<DiscoverModel> discover(
            @Query("language") String language,
            @Query("sort_by") String sortBy,
            @Query("with_release_type") String types,
            @Query("region") String region,
            @Query("with_original_language") String originLanguage,
            @Query("include_adult") boolean includeAdult,
            @Query("page") int page,
            @Query("primary_release_date.gte") String releaseGreaterEqualThan,
            @Query("primary_release_date.lte") String releaseLessEqualThan);

}
