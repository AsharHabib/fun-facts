package com.example.funfacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_FACT = "KEY_FACT";
    private static final String KEY_COLOR = "KEY_COLOR";
    //declare our view variables
    private TextView factTextView;
    private Button showFactButton;
    private FactBook factBook = new FactBook();
    private ColorWheel colorWheel = new ColorWheel();
    private RelativeLayout relativeLayout;
    public static final String TAG = MainActivity.class.getSimpleName();
    private String mFact = factBook.getFacts()[0];
    private int mColor = Color.parseColor(colorWheel.colors[8]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assign the views from the layout file to the view variables
        factTextView = findViewById(R.id.factTextView);
        showFactButton = findViewById(R.id.showFactButton);
        relativeLayout = findViewById(R.id.relativeLayout);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update the screen with new fact
                mFact = factBook.getFact();
                mColor = colorWheel.getColor();
                relativeLayout.setBackgroundColor(mColor);
                factTextView.setText(mFact);
                showFactButton.setTextColor(mColor);
            }
        };
        showFactButton.setOnClickListener(listener);
        //Toast.makeText(this, "Yay our activity was created!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "were logging from the oncreate method");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_FACT, mFact);
        outState.putInt(KEY_COLOR, mColor);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mFact = savedInstanceState.getString(KEY_FACT);
        mColor = savedInstanceState.getInt(KEY_COLOR);
        factTextView.setText(mFact);
        relativeLayout.setBackgroundColor(mColor);
        showFactButton.setTextColor(mColor);
    }
}