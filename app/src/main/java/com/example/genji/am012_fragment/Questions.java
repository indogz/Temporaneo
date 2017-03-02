package com.example.genji.am012_fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.Arrays;

/* see
 *
 * http://developer.android.com/training/animation/cardflip.html
 * http://developer.android.com/guide/topics/resources/animation-resource.html
 *
 */

public class Questions extends AppCompatActivity {

    float x1, x2;
    final static float MIN_DISTANCE = 150.0f;
    private int whichSwipe; //whichSwipe>0 left <0 right
    protected Quesito[] listaDomande = null;
    protected int[] risposte;
    protected int indiceCorrente = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // portrait mode
        if (findViewById(R.id.fragment) != null) {
            Question f1 = new Question();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, f1);
            ft.commit();
        }

        setQuestions();



        /*
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.fragment, f2);
        ft.commit();
        */


    }

    private void setQuestions() {
        listaDomande = new Quesito[2];
        Quesito q = new Quesito("Quanti colori ci sono nella bandiera italiana?", "1", "2", "3", "4", "2");
        Quesito q2 = new Quesito("Di che colore era il cavallo di Napoleone?", "Bianco", "Nero", "Zebrato", "Maculato", "Bianco");
        listaDomande[0] = q;
        listaDomande[1] = q2;
        risposte = new int[listaDomande.length];
    }

    public int[] getRisposte() {
        return risposte;
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

        Question q = new Question();
        Quesito quesitoCorrente = null;
        System.out.println("\t\t\t\t Indice corrente:" + indiceCorrente);
        quesitoCorrente = listaDomande[indiceCorrente];


        System.out.println("Cipolla"+ Arrays.toString(risposte));
        FragmentManager fm = getFragmentManager();
        Fragment nextFragment = null;
        Fragment currentFragment = fm.findFragmentById(R.id.fragment);
        if (currentFragment instanceof Question) {
            nextFragment = Question.newInstance(quesitoCorrente.getDomanda(), quesitoCorrente.getA1(), quesitoCorrente.getA2(), quesitoCorrente.getA3(), quesitoCorrente.getA4(), indiceCorrente);
        } else {
            nextFragment = Question.newInstance("Qualquadra non cosa");
        }

        FragmentTransaction ft = fm.beginTransaction();
        // (enter, exit)
        if (whichSwipe < 0) {
            ft.setCustomAnimations(R.animator.slide_in_right, R.animator.fade);
            if (indiceCorrente < listaDomande.length - 1) {
                indiceCorrente++;
            }
        } else {
            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.fade);
            if (indiceCorrente > 0) {
                indiceCorrente--;
            }
        }

        printAnswers();
        // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.replace(R.id.fragment, nextFragment);
        ft.commit();

    }

    public  void printAnswers(){
        for(int y=0; y<listaDomande.length;y++){
            System.out.println(risposte[y]);
        }
    }




}
