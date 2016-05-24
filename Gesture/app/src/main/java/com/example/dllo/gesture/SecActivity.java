package com.example.dllo.gesture;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by dllo on 16/5/24.
 */
public class SecActivity extends AppCompatActivity {


    private GestureDetector gestureDetector;
    private MyListener myListener;
    private static final int MIN_MOVE = 200;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        myListener = new MyListener();
        gestureDetector = new GestureDetector(this, myListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return gestureDetector.onTouchEvent(event);
    }

    class Temp extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
    class MyListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            if (e1.getX()-e2.getX() > MIN_MOVE){
//                Log.d("MyListener==========", "Scroll");
//                startActivity(new Intent(SecActivity.this, ThirdActivity.class));
//            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            Log.d("MyListener=============>", "Filling");
            startActivity(new Intent(SecActivity.this, ThirdActivity.class));
            return false;
        }
    }
}
