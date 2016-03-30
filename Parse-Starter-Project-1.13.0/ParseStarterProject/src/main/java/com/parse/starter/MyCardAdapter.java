package com.parse.starter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public final class MyCardAdapter extends CardAdapter{
    Context context;ImageView imageView;
    private List<MyCardModel> cardModelList;

    public MyCardAdapter(Context context,List<MyCardModel> mcl)
    {
        super(context);
        this.cardModelList=mcl;

    }
    public View getCardView(int position, MyCardModel model, View convertView, ViewGroup parent) {
        if(convertView == null) {context=convertView.getContext();
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.mycard, parent, false);

            assert convertView != null;
        }

        MyCardModel cardInfo=cardModelList.get(position);
       imageView= (ImageView) convertView.findViewById(R.id.myimage);
        Picasso.with(context).load(cardInfo.imageUrl).into(imageView);
        ((TextView)convertView.findViewById(R.id.mytitle)).setText(cardInfo.hotelname);
        ((TextView)convertView.findViewById(R.id.myaddress)).setText(cardInfo.address);
        ((TextView)convertView.findViewById(R.id.myrating)).setText(cardInfo.rating);
        return convertView;
    }
}
