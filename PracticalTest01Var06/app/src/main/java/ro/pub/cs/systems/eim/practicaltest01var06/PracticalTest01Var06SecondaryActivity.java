package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {

    EditText text0;
    Button button0;
    Integer score;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        button0 = findViewById(R.id.button0);
        text0 = findViewById(R.id.text0);
        score = 0;

        Clickme clickme = new Clickme();
        button0.setOnClickListener(clickme);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String one = extras.getString("1");
            String two = extras.getString("2");
            String three = extras.getString("3");
            Integer count = extras.getInt("count");
            boolean gain = false;
            if (one.equals(two) && two.equals(three)) {
                gain = true;
            }
            if (one.equals("*") && two.equals(three)) {
                gain = true;
            }
            if (two.equals("*") && three.equals(one)) {
                gain = true;
            }
            if (three.equals("*") && one.equals(two)) {
                gain = true;
            }
            if (three.equals("*") && one.equals("*")) {
                gain = true;
            }
            if (three.equals("*") && two.equals("*")) {
                gain = true;
            }
            if (two.equals("*") && one.equals("*")) {
                gain = true;
            }
            if (gain)
                switch (count) {
                    case 1:
                        text0.setText("Gained 50");
                        score = 50;
                        break;
                    case 0:
                        text0.setText("Gained 100");
                        score = 100;
                        break;
                    case 2:
                        text0.setText("Gained 10");
                        score = 10;
                        break;

                }
        }
    }

    public class Clickme implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == button0.getId()) {
                Intent intentToParent = new Intent();
                intentToParent.putExtra("SCORE", score);
                setResult(RESULT_OK, intentToParent);
                finish();
            }
        }
    }
}