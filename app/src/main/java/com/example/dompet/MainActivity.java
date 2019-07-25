package com.example.dompet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ActivityHitung activityHitung = new ActivityHitung();
        FragmentTransaction fragmentTransaction =  this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, activityHitung)
                .addToBackStack(null)
                .commit();
    }
}
