package com.ziyata.footballmvp.network;

import com.ziyata.footballmvp.model.FootballResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/api/v1/json/1/search_all_teams.php")
    Call<FootballResponse> getFootballResponse(
            @Query("l") String l
    );

    @GET("/api/v1/json/1/lookupteam.php")
    Call<FootballResponse> getDetailResponse(
            @Query("id") int id
    );
}
