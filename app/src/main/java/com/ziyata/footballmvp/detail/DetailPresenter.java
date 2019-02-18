package com.ziyata.footballmvp.detail;

import android.os.Bundle;

import com.ziyata.footballmvp.model.FootballData;
import com.ziyata.footballmvp.model.FootballResponse;
import com.ziyata.footballmvp.network.ApiClient;
import com.ziyata.footballmvp.network.ApiInterface;
import com.ziyata.footballmvp.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailContract.Presenter {

    private final DetailContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private int id;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }


    @Override
    public void getDataFootball(Bundle bundle) {
        if (bundle != null) {
                id = Integer.valueOf(bundle.getString(Constant.id));

            view.showProgress();

            Call<FootballResponse> call = apiInterface.getDetailResponse(id);
            call.enqueue(new Callback<FootballResponse>() {
                @Override
                public void onResponse(Call<FootballResponse> call, Response<FootballResponse> response) {
                    view.hideProgress();

                    if (response.body() != null) {
                        FootballResponse footballResponse = response.body();

                        if (footballResponse.getTeams() != null) {
                            view.showDataFootball(footballResponse.getTeams());
                        }
                    }
                }

                @Override
                public void onFailure(Call<FootballResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());

                }
            });
        }
    }
}