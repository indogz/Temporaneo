package com.example.genji.am012_fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

/* see
 *
 * http://developer.android.com/training/animation/cardflip.html
 * http://developer.android.com/guide/topics/resources/animation-resource.html
 *
 */

public class MainActivity extends AppCompatActivity {

    float x1, x2;
    final static float MIN_DISTANCE = 150.0f;
    private int whichSwipe; //whichSwipe>0 left <0 right

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // portrait mode
        if (findViewById(R.id.fragment) != null) {
            Fragment1 f1 = new Fragment1();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, f1);
            ft.commit();
        }


        /*
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.fragment, f2);
        ft.commit();
        */


    }

    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        whichSwipe = 1;
                        changeFragment();
                    }

                    // Right to left swipe action
                    else {
                        whichSwipe = -1;
                        changeFragment();
                    }
                } else {
                    Toast.makeText(this, "TAP", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void changeFragment() {
        // act only in portrait mode
        if (findViewById(R.id.fragment) != null) {
            FragmentManager fm = getFragmentManager();
            Fragment nextFragment = null;
            Fragment currentFragment = fm.findFragmentById(R.id.fragment);
            if (currentFragment instanceof Fragment1) {
                nextFragment = new Fragment2();
            } else {
                nextFragment = new Fragment1();
            }

            FragmentTransaction ft = fm.beginTransaction();
            // (enter, exit)
            if (whichSwipe < 0) {
                ft.setCustomAnimations(R.animator.slide_in_right, R.animator.fade);
            } else {
                ft.setCustomAnimations(R.animator.slide_in_left, R.animator.fade);
            }
            // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.replace(R.id.fragment, nextFragment);
            ft.commit();
        } else {
            Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_SHORT).show();
        }

    }
}
