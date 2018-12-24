package com.quintus.labs.addremovelist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Quintus Labs on 15/11/18.
 * www.quintuslabs.com
 */
public class MainActivity extends AppCompatActivity {

    final int LIST_REQUEST = 1;
    final int LIST_RESULT = 100;
    Button button;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                if (list == null) {
                    list = new ArrayList<>();
                }
                i.putStringArrayListExtra("list", list);
                startActivityForResult(i, LIST_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LIST_REQUEST && resultCode == LIST_RESULT)
            list = data.getStringArrayListExtra("list");
    }
}
