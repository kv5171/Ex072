package com.example.ex072;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText a, b, c;
    String aStr, bStr, cStr;
    Intent si;
    TextView shownRoots;
    Random rnd;
    final int REQUEST_CODE = 5566;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = (EditText) findViewById(R.id.a);
        b = (EditText) findViewById(R.id.b);
        c = (EditText) findViewById(R.id.c);
        shownRoots = (TextView) findViewById(R.id.shownRoots);

        rnd = new Random();

        si = new Intent(this, SolActivity.class);
    }

    public void getValues(View view) {
        aStr = a.getText().toString();
        bStr = b.getText().toString();
        cStr = c.getText().toString();

        if (aStr.equals("") || bStr.equals("") || cStr.equals("") ||
                aStr.equals("-") || cStr.equals("-") || bStr.equals("-") || aStr.equals(".") || bStr.equals(".") || cStr.equals(".")) {
            Toast.makeText(this, "Enter a number in each cell!", Toast.LENGTH_SHORT).show();
        } else if (Float.parseFloat(aStr) == 0.0) {
            Toast.makeText(this, "a cannot be equal to 0!", Toast.LENGTH_SHORT).show();
        } else {
            si.putExtra("a", Double.parseDouble(aStr));
            si.putExtra("b", Double.parseDouble(bStr));
            si.putExtra("c", Double.parseDouble(cStr));
            startActivityForResult(si, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int source, int good, @Nullable Intent data_back) {
        super.onActivityResult(source, good, data_back);
        if (data_back != null) {
            shownRoots.setText("The answers are:\n" + data_back.getStringExtra("answer"));
        }
    }

    public void randomNums(View view) {
        a.setText("" + (Float.MIN_VALUE + (Float.MAX_VALUE - Float.MIN_VALUE) * rnd.nextFloat()));
        b.setText("" + (Float.MIN_VALUE + (Float.MAX_VALUE - Float.MIN_VALUE) * rnd.nextFloat()));
        c.setText("" + (Float.MIN_VALUE + (Float.MAX_VALUE - Float.MIN_VALUE) * rnd.nextFloat()));
    }
}