package com.example.genji.am012_fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Matteo on 1/29/16.
 */
public class Question extends Fragment {

    public static String d;
    public static String a1;
    public static String a2;
    public static String a3;
    public static String a4;

    //  private OnFragmentInteractionListener mListener;

    protected TextView textView;
    protected RadioGroup myRadioGroup;
    protected String mLabel = "";
    protected int[] r; //la metto globale sennò da problemi di inner class
    protected int indiceCorrente=6;

    protected RadioButton r1, r2, r3, r4;


    public static Question newInstance(String domanda, String risposta1, String risposta2, String risposta3, String risposta4, int currentQ) {

        Bundle args = new Bundle();

        args.putString("domanda", domanda);
        args.putString("r1", risposta1);
        args.putString("r2", risposta2);
        args.putString("r3", risposta3);
        args.putString("r4", risposta4);
        args.putInt("dc", currentQ);


        Question fragment = new Question();
        fragment.setArguments(args);
        return fragment;
    }

    public static Question newInstance(String domanda) {

        Bundle args = new Bundle();

        args.putString("domanda", domanda);

        Question fragment = new Question();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mLabel = args.getString("domanda");
            a1 = args.getString("r1");
            a2 = args.getString("r2");
            a3 = args.getString("r3");
            a4 = args.getString("r4");
            indiceCorrente =args.getInt("dc");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_question, container, false);
        // myRadioGroup = ((RadioGroup) getView().findViewById(R.id.yourRadioGroup));
        //Ritorna l'activity a cui è assegnato il fragment
        Questions qs = (Questions) getActivity();
        r= qs.getRisposte(); //globale per evitare problemi di inner class
        System.out.println("\t\t\t\t Indice corrente:" + indiceCorrente);


        //Potevo metterlo anche fuori come in principio
        View.OnClickListener rbL = new View.OnClickListener() {
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
                switch (view.getId()) {
                    case R.id.radio11: {
                        System.out.println("Cliccato 1");

                        r[indiceCorrente] = 1;
                    }
                    break;
                    case R.id.radio12: {
                        System.out.println("Cliccato 2");
                        r[indiceCorrente] = 2;
                    }
                    break;
                    case R.id.radio13: {
                        System.out.println("Cliccato 3");
                        r[indiceCorrente] = 3;
                    }
                    break;
                    case R.id.radio14: {
                        System.out.println("Cliccato 4");
                        r[indiceCorrente] = 4;
                    }
                    break;
                }
            }
        };

        /**
         * L' android:OnClick dell'XML guarda all'activity non al fragment per cui
         * non lo metto esplicito nel file ma lo setto da codice Java
         */
        view.findViewById(R.id.radio11).setOnClickListener(rbL);
        view.findViewById(R.id.radio12).setOnClickListener(rbL);
        view.findViewById(R.id.radio13).setOnClickListener(rbL);
        view.findViewById(R.id.radio14).setOnClickListener(rbL);
        // Inflate the layout for this fragment

        return view;
    }

    //inqm funziona
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView = ((TextView) getView().findViewById(R.id.textView));
        textView.setText(mLabel);

        r1 = ((RadioButton) getView().findViewById(R.id.radio11));
        r1.setText(a1);
        r2 = ((RadioButton) getView().findViewById(R.id.radio12));
        r2.setText(a2);
        r3 = ((RadioButton) getView().findViewById(R.id.radio13));
        r3.setText(a3);
        r4 = ((RadioButton) getView().findViewById(R.id.radio14));
        r4.setText(a4);

        myRadioGroup = ((RadioGroup) getView().findViewById(R.id.yourRadioGroup));
    }


}
