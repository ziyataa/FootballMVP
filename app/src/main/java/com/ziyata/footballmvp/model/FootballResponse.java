package com.ziyata.footballmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FootballResponse {
    @SerializedName("teams")
    private List<FootballData> teams;

    public List<FootballData> getTeams() {
        return teams;
    }

    public void setTeams(List<FootballData> teams) {
        this.teams = teams;
    }
}
