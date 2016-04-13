package com.parse.starter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public final class MyCardAdapter extends CardAdapter {
    Context context;
    ImageView imageView;ProgressBar p;
    //private List<MyCardModel> cardModelList;

    public MyCardAdapter(Context context) {
        super(context);
        //this.cardModelList = mcl;
        this.context = context;

    }


    public View getCardView(int position, MyCardModel model, View convertView, ViewGroup parent) {
        if (convertView == null) {//context=convertView.getContext();
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.mycard, parent, false);

            assert convertView != null;
        }

       // MyCardModel cardInfo = cardModelList.get(position);
        imageView = (ImageView) convertView.findViewById(R.id.myimage);
//        p=(ProgressBar)convertView.findViewById(R.id.pbLoading);p.setVisibility(View.VISIBLE);

        Picasso.with(context).load(model.imageUrl).resize(350,500).into(imageView);
        ((TextView) convertView.findViewById(R.id.mytitle)).setText(model.hotelname);
        ((TextView) convertView.findViewById(R.id.myaddress)).setText("Address:" + "\n" + model.address);
        ((TextView) convertView.findViewById(R.id.myrating)).setText("Rating:" + model.rating);
//        if(model.isLike())
//        {TextView t=(TextView)convertView.findViewById(R.id.textView);
//            t.setVisibility(View.VISIBLE);
//               }
//        else{TextView t1=(TextView)convertView.findViewById(R.id.textView2);
//        //t1.setVisibility(View.VISIBLE);
//        }

        return convertView;
    }
}
