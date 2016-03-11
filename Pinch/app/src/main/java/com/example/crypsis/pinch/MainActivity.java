package com.example.crypsis.pinch;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView; private float sf = 1f;
    Matrix matrix;

    ScaleGestureDetector sgd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.iv);
        matrix=new Matrix();
        sgd=new ScaleGestureDetector(this,new ScaleListener());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sgd.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
           sf*=detector.getScaleFactor();
            sf=Math.max(0.1f,Math.min(sf,5.0f));
            matrix.setScale(sf,sf);
            imageView.setImageMatrix(matrix);
            return true;
        }




    }
}
