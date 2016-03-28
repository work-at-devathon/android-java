package com.parse.starter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<HotelInfo> hotelInfoList;
    Context context;


    public HotelAdapter(List<HotelInfo> hl) {
        this.hotelInfoList = hl;
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);
         context=parent.getContext();

        return new HotelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {

        HotelInfo hotelInfo = hotelInfoList.get(position);
        holder.name.setText(hotelInfo.hotelname);
        holder.address.setText(hotelInfo.address);
        holder.rating.setText(hotelInfo.rating+"");
        Picasso.with(context).setIndicatorsEnabled(true);
         Picasso.with(context).load(hotelInfo.imageUri).resize(275,200).into(holder.image, new Callback() {
             @Override
             public void onSuccess() {

             }

             @Override
             public void onError() {

             }
         });

    }

    @Override
    public int getItemCount() {
        return hotelInfoList.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView address;
        protected TextView rating;
        protected ImageView image;

        public HotelViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            rating = (TextView) itemView.findViewById(R.id.rating);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
