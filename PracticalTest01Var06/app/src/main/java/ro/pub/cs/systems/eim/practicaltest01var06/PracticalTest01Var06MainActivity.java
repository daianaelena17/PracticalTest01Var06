package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    Button button0;
    CheckBox check0;
    CheckBox check1;
    CheckBox check2;
    EditText text0;
    EditText text1;
    EditText text2;
    Intent intent;
    Integer score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        button0 = findViewById(R.id.button0);
        check0 = findViewById(R.id.check0);
        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        text0 = findViewById(R.id.text0);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        Clickme clickme = new Clickme();
        button0.setOnClickListener(clickme);
        score = 0;

        intent = new Intent(this, PracticalTest01Var06SecondaryActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 2005:
                if (resultCode == Activity.RESULT_OK) {
                    Toast toast = Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT);
                    toast.show();
                }
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int x = extras.getInt("SCORE");
            Toast toast = Toast.makeText(getApplicationContext(), x, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TEXT0", (String.valueOf(text0.getText())));
        outState.putString("TEXT1", (String.valueOf(text1.getText())));
        outState.putString("TEXT2", (String.valueOf(text2.getText())));
//        outState.putInt("CHECK0", Integer.parseInt(check0.isChecked()));
//        outState.putInt("CHECK1", Integer.parseInt(String.valueOf(text2.getText())));
//        outState.putInt("CHECK2", Integer.parseInt(String.valueOf(text2.getText())));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text0.setText(String.valueOf(savedInstanceState.getString("TEXT0", "")));
        text1.setText(String.valueOf(savedInstanceState.getString("TEXT1", "")));
        text2.setText(String.valueOf(savedInstanceState.getString("TEXT2", "")));
    }

    public class Clickme implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            List<String> list = new ArrayList<>();
            // add 5 element in ArrayList
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("*");
            if (view.getId() == button0.getId()) {
                Random rand = new Random();
                String x = list.get(rand.nextInt(list.size()));
                String y = list.get(rand.nextInt(list.size()));
                String z = list.get(rand.nextInt(list.size()));
                int count = 3;
                if (!check0.isChecked()) {
                    text0.setText(String.valueOf(x));
                    count--;
                }
                if (!check1.isChecked()) {
                    text1.setText(String.valueOf(y));
                    count--;
                }
                if (!check2.isChecked()) {
                    text2.setText(String.valueOf(z));
                    count--;
                }
                Toast toast = Toast.makeText(getApplicationContext(), x + y + z, Toast.LENGTH_SHORT);
                toast.show();
//                intent.putExtra("1", text0.getText().toString());
//                intent.putExtra("2", text1.getText().toString());
//                intent.putExtra("3", text2.getText().toString());
                intent.putExtra("1", "*");
                intent.putExtra("2", "*");
                intent.putExtra("3", "*");
                intent.putExtra("count", count);
                startActivityForResult(intent, 2005);
            }
        }
    }
}