package com.example.crypsis.retro;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomerViewHolder> {

    private List<Customer> customerInfoList;
    Context context;

    public MyRecyclerViewAdapter(List<Customer> hl) {
        this.customerInfoList = hl;
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycard, parent, false);
        context = parent.getContext();
        return new CustomerViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, int position) {
        Customer customerInfo = customerInfoList.get(position);
        holder.name.setText(customerInfo.name);
        holder.sellername.setText(customerInfo.seller_name);
        holder.price.setText(customerInfo.display_price);
        Picasso.with(context).load(customerInfo.image_url).into(holder.image);
        Picasso.with(context).load(customerInfo.seller_image).into(holder.circularImageView);
    }


    @Override
    public int getItemCount() {
        return customerInfoList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView sellername;
        protected TextView price;
        protected ImageView image;
        protected CircularImageView circularImageView;

        public CustomerViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.sellername);
            sellername = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.displayprice);
            image = (ImageView) itemView.findViewById(R.id.imageurl);
            circularImageView = (CircularImageView) itemView.findViewById(R.id.sellerimage);
        }
    }
}