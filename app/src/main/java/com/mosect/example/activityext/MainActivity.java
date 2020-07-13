package com.mosect.example.activityext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mosect.activityext.ActivityExt;
import com.mosect.activityext.ExtInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActivityExt.MainAct";

    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "before super onCreate");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "after super onCreate");
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
        // ExtInfo extInfo = ActivityExt.getInstance().getInfo(MainActivity.this);

        int count = ActivityExt.getInstance().getInfoSize();
        for (int i = 0; i < count; i++) {
            ExtInfo extInfo = ActivityExt.getInstance().getInfo(i);
        }
    }

    @Override
    public String toString() {
        return "页面" + (index + 1);
    }
}
