package com.cleanup.exomvvm_simplecalc.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cleanup.exomvvm_simplecalc.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SimpleCalcFragment.newInstance())
                    .commitNow();
        }
    }
}