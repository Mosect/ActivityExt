package com.mosect.example.activityext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = getIntent().getIntExtra("index", 0);
        findViewById(R.id.btn_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("index", index + 1);
                startActivity(intent);
            }
        });
    }

    @Override
    public String toString() {
        return "页面" + (index + 1);
    }
}
