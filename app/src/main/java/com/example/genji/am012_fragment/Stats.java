package com.example.genji.am012_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.duration;

public class Stats extends AppCompatActivity {

    TextView corrette,sbagliate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Button btnQuiz= (Button) findViewById(R.id.uguale);
        TextView corrette=(TextView) findViewById(R.id.corrette);
        TextView sbagliate=(TextView) findViewById(R.id.sbagliate);

        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_SHORT;

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Stats.this, Questions.class);

                Toast toast = Toast.makeText(context, "Start quiz", duration);
                toast.show();
                startActivityForResult(i, 1);
            }
        });
    }

    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        corrette=(TextView) findViewById(R.id.corrette);
        sbagliate=(TextView) findViewById(R.id.sbagliate);

        System.out.println("ESISTOOOOOOOOOOOOOOOOOO");

        if(resultCode==RESULT_OK){
            corrette.setText(data.getExtras().getString("numeroCorrette"));
            sbagliate.setText(data.getExtras().getString("numeroSbagliate"));
        }
    }
}
