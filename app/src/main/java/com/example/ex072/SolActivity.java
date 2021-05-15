package com.example.ex072;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class SolActivity extends AppCompatActivity {
    Intent gi;
    double a, b, c, root1, root2, discriminant;
    String rootsAnswer;
    TextView shownRoots;
    WebView wV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sol);

        shownRoots = (TextView) findViewById(R.id.shownRoots);
        wV = (WebView)findViewById(R.id.wV);

        wV.getSettings().setJavaScriptEnabled(true);
        wV.setWebViewClient(new MyWebViewClient());

        gi = getIntent();
        a = gi.getDoubleExtra("a", 1);
        b = gi.getDoubleExtra("b", 1);
        c = gi.getDoubleExtra("c", 1);

        solveEquation();
        createAnswer();

        shownRoots.setText(rootsAnswer);
        wV.loadUrl("https://www.google.com/search?q=" + a + "x%5E2%2B" + b + "*x%2B" + c);
    }

    public void solveEquation (){
        discriminant = Math.pow(b, 2) - 4 * a * c;
        root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
    }

    public void createAnswer(){
        if (discriminant > 0){
            rootsAnswer = "X1 = " + root1 + "\nX2 = " + root2;
        }
        else if (discriminant == 0){
            rootsAnswer = "X = " + root1;
        }
        else {
            rootsAnswer = "There is no solution!";
        }
    }

    public void returnMain(View view) {
        gi.putExtra("answer", rootsAnswer);
        setResult(RESULT_OK, gi);
        finish();
    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}