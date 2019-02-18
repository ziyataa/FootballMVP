package com.ziyata.footballmvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ziyata.footballmvp.R;
import com.ziyata.footballmvp.model.FootballData;
import com.ziyata.footballmvp.utils.Constant;
import com.ziyata.footballmvp.view.DetailActivity;
import com.ziyata.footballmvp.view.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Bundle bundle;
    private final Context context;
    private final List<FootballData> footballDataList;

    public MainAdapter(Context context, List<FootballData> footballDataList) {
        this.context = context;
        this.footballDataList = footballDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_football, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final FootballData footballData = footballDataList.get(i);

        viewHolder.txtNamaTeam.setText(footballData.getStrTeam());
        Glide.with(context).load(footballData.getStrTeamBadge()).into(viewHolder.imgAvatar);
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(context).load(footballData.getStrTeamBadge()).apply(options).into(viewHolder.imgAvatar);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString(Constant.id, footballData.getIdTeam());
                context.startActivity(new Intent(context, DetailActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return footballDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgAvatar)
        ImageView imgAvatar;
        @BindView(R.id.txtNamaTeam)
        TextView txtNamaTeam;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}