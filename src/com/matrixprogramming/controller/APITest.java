package com.matrixprogramming.controller;

import com.matrixprogramming.model.DiscoverModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by Eldridge on 2/14/2017.
 */
public class APITest
{
    public static void main(String[] args)
    {
        MovieAPI movieAPI = new MovieAPI();
        Call<DiscoverModel> discoverCall = movieAPI.movieAPI.discover();
        discoverCall.enqueue(new Callback<DiscoverModel>()
        {
            @Override
            public void onResponse(Call<DiscoverModel> call, Response<DiscoverModel> response)
            {
                System.out.println("Does it reach here?");
                if(response.isSuccessful())
                {
                    System.out.println(response.body());
                }

            }

            @Override
            public void onFailure(Call<DiscoverModel> call, Throwable t)
            {
                System.out.println("Call failed");
            }
        });
    }
}
