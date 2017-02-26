package com.matrixprogramming.controller;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/***
 * Created by Eldridge & Sprowls on 2/14/2017.
 */
public class MovieAPI
{
    /**
     * The httpclient that adds default headers and query strings.
     */
    private final OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder().addInterceptor(chain ->
            {
                HttpUrl originalHttpUrl = chain.request().url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key",
                                "ea4a9abb14b2368e6a68f4e9c50e975f")
                        .build();
                System.out.println(url);
                Request request = chain.request().newBuilder()
                        .url(url)
                        .build();
                return chain.proceed(request);
            });

    /**
     * A retrofit builder code to get it up and running.
     */
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    /**
     * The controller to make calls to the movieDB API
     **/
    private final IMovieAPI controller = retrofit.create(IMovieAPI.class);

    /***
     * Getting the controller.
     * @return IMovieAPI instance
     */
    public IMovieAPI getController()
    {
        return this.controller;
    }

    /***
     * Constructor for the MovieAPI instance
     */
    public MovieAPI()
    {
    }
}
