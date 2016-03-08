package com.example.crypsis.snack;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar t=(Toolbar)findViewById(R.id.toolbar);
        //setActionBar(t);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Swipe to Close", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", null).show();
            }
        });
        final CoordinatorLayout cl=(CoordinatorLayout)findViewById(R.id.c);
        Snackbar snackbar = Snackbar
                .make(cl, "Message is deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(cl, "Message is restored!", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });

        snackbar.show();
        Button b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView) findViewById(R.id.textView);
                t.setText("Click");

            }
        });

        Button b2=(Button)findViewById(R.id.button2);
        b2.setOnLongClickListener(new Button.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                TextView t = (TextView) findViewById(R.id.textView);
                t.setText("On Long Click");
                return true;
            }
        });

        EditText etValue = (EditText) findViewById(R.id.ed);
        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Fires right before text is changing
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed
                TextView t = (TextView) findViewById(R.id.textView);
                t.setText(s.toString());
                Toast.makeText(getApplicationContext(), "Text has been changed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
