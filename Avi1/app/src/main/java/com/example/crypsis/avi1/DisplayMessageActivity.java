package com.example.crypsis.avi1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
        String message1 = intent.getStringExtra(MyActivity.EXTRA_MESSAGE1);
        String r=intent.getStringExtra(MyActivity.EXTRA_MESSAGE2);

        TextView textView = new TextView(this);
        TextView textView1 = new TextView(this);
        TextView m = new TextView(this);

        textView.setTextSize(40);
        textView1.setTextSize(40);
        m.setTextSize(40);
        textView.setText(message);
        textView1.setText(message1);
        m.setText(r);

        LinearLayout layout = (LinearLayout) findViewById(R.id.activity);
        layout.addView(textView1);
        layout.addView(textView);layout.addView(m);

    }

}
